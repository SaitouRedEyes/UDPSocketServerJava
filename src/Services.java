import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Services 
{
	MatchManager matchManager = new MatchManager();		
	Match match;
	
	public void SendMatchID(DatagramPacket receivePacket, String screenW, String screenH, DatagramSocket socket, byte[] sendData, byte[] sendData2)
	{
		match = matchManager.RegisterPlayer(receivePacket, screenW, screenH);
		
		if(match != null) 
		{
			try
			{	
				sendData = String.valueOf("p1;" + match.GetID()).getBytes();
				sendData2 = String.valueOf("p2;" + match.GetID()).getBytes();
				
				DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, match.GetPlayer1().GetAddress(), match.GetPlayer1().GetDoor());
				DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData.length, match.GetPlayer2().GetAddress(), match.GetPlayer2().GetDoor());
				
				System.out.println("Send data to the clients"); 
				socket.send(sendPacket1);
				socket.send(sendPacket2);
			}
			catch(IOException e) { System.err.println("IOException " + e); }
		}
	}
	
	public void SendPlayerPos(String matchID, String playerControl, String posX, String posY, String width, String height, DatagramSocket socket, byte[] sendData, byte[] sendData2)
	{
		match = matchManager.GetMatch(matchID);
		
		float normalizeScreenX = (float)match.GetPlayer1().GetScreenW() / match.GetPlayer2().GetScreenW();
		float normalizeScreenY = (float)match.GetPlayer1().GetScreenH() / match.GetPlayer2().GetScreenH();		
		
		if(match != null)
		{
			try
			{
				if(playerControl.equals("p1")) //Update P1
				{
					if(normalizeScreenX >= 1) match.GetPlayer1().SetPosX(Float.parseFloat(posX) * normalizeScreenX);
					else match.GetPlayer1().SetPosX(Float.parseFloat(posX) / normalizeScreenX);
					
					if(normalizeScreenY >= 1) match.GetPlayer1().SetPosY(Float.parseFloat(posY) * normalizeScreenY);
					else match.GetPlayer1().SetPosY(Float.parseFloat(posY) / normalizeScreenY);
					
					match.GetPlayer1().SetWidth(Float.parseFloat(width));
					match.GetPlayer1().SetHeight(Float.parseFloat(height));
					match.GetPlayer1().SetIsUpdated(true);
				}
				else //Update P2
				{
					if(normalizeScreenX >= 1) match.GetPlayer2().SetPosX(Float.parseFloat(posX) / normalizeScreenX);
					else match.GetPlayer2().SetPosX(Float.parseFloat(posX) * normalizeScreenX);
					
					if(normalizeScreenY >= 1) match.GetPlayer2().SetPosY(Float.parseFloat(posY) / normalizeScreenY);
					else match.GetPlayer2().SetPosY(Float.parseFloat(posY) * normalizeScreenY);
					
					match.GetPlayer2().SetWidth(Float.parseFloat(width));
					match.GetPlayer2().SetHeight(Float.parseFloat(height));
					match.GetPlayer2().SetIsUpdated(true);
				}
				
				//Update Game.
				if(match.GetPlayer1().GetIsUpdated() && match.GetPlayer2().GetIsUpdated())
				{	
					match.GetPlayer1().SetIsUpdated(false);
					match.GetPlayer2().SetIsUpdated(false);
					match.GetBall().update(match.GetPlayer1(), match.GetPlayer2());
					
					float normalizeBallXP1, normalizeBallYP1, normalizeBallXP2, normalizeBallYP2;
					
					if(normalizeScreenX >= 1)
					{
						normalizeBallXP1 = (float)match.GetBall().GetX() / normalizeScreenX;
						normalizeBallXP2 = (float)match.GetBall().GetX() * normalizeScreenX;
					}
					else
					{
						normalizeBallXP1 = (float)match.GetBall().GetX() * normalizeScreenX;
						normalizeBallXP2 = (float)match.GetBall().GetX() / normalizeScreenX;
					}
					
					if(normalizeScreenY >= 1)
					{
						normalizeBallYP1 = (float)match.GetBall().GetY() / normalizeScreenY;
						normalizeBallYP2 = (float)match.GetBall().GetY() * normalizeScreenY;
					}
					else
					{
						normalizeBallYP1 = (float)match.GetBall().GetY() * normalizeScreenY;
						normalizeBallYP2 = (float)match.GetBall().GetY() / normalizeScreenY;
					}
		
					sendData = String.valueOf(match.GetPlayer2().GetPosX() + ";" + normalizeBallXP1 + ";" + normalizeBallYP1).getBytes();
					sendData2 = String.valueOf(match.GetPlayer1().GetPosX() + ";" + normalizeBallXP2 + ";" + normalizeBallYP2).getBytes();
					
					DatagramPacket sendPacket1 = new DatagramPacket(sendData, sendData.length, match.GetPlayer1().GetAddress(), match.GetPlayer1().GetDoor());
					DatagramPacket sendPacket2 = new DatagramPacket(sendData2, sendData2.length, match.GetPlayer2().GetAddress(), match.GetPlayer2().GetDoor());
					
					socket.send(sendPacket1);
					socket.send(sendPacket2);					
				}				
			}
			catch(IOException e) { System.err.println("IOException " + e); }
		}
	}
}