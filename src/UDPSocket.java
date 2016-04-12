import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class UDPSocket 
{	
	public static void main(String args[]) throws Exception
	{
		final int CONNECT = 0, UPDATE_POS = 1;
				
		DatagramSocket serverSocket = new DatagramSocket(7777);
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		byte[] sendData2 = new byte[1024];
		
		Services services = new Services();
		
		while(true)
		{
			try
		    {
				//System.out.println("Waiting connections: ");
				DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
				serverSocket.receive(receivePacket);			
				
				String data = new String(receivePacket.getData());
				
				String[] dataSplited = data.split(";");
				
				switch(Integer.parseInt(dataSplited[0]))
				{
					case CONNECT:    services.SendMatchID(receivePacket, dataSplited[1], dataSplited[2], serverSocket, sendData, sendData2); break;
					case UPDATE_POS: services.SendPlayerPos(dataSplited[1], dataSplited[2], dataSplited[3], dataSplited[4], 
															dataSplited[5], dataSplited[6], serverSocket, sendData, sendData2); break;
					default:         System.out.println("Service not found!!"); break;
				}				
		    }
			catch(IOException e) { System.err.println("IOException " + e); }
		}
	}
}