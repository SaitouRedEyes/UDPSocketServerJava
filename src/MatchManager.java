import java.net.DatagramPacket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchManager 
{
	private List<Match> matchList;
	private Player p1, p2;
	
	public MatchManager()
	{
		matchList = new ArrayList<Match>();
	}
	
	public Match RegisterPlayer(DatagramPacket packet, String screenW, String screenH)
	{	
		if(p1 == null)
		{
			System.out.println("Registering p1!!");			
			p1 = new Player(packet.getAddress(), packet.getPort(), Integer.parseInt(screenW), Integer.parseInt(screenH));
			return null;
		}
		else 
		{
			System.out.println("Registering p2 and starting a new match!!");
			p2 = new Player(packet.getAddress(), packet.getPort(), Integer.parseInt(screenW), Integer.parseInt(screenH));
			return StartMatch();
		}
	}
	
	private Match StartMatch()
	{		
		Random random = new Random();
		Boolean registerMatch = true; 
		int matchID = 0;     
		
		System.out.println("Get a ID for a match");
		
		if(matchList.size() > 0)
		{
	        while(registerMatch)
	        {
	        	matchID = random.nextInt(500)+1;
	        	
	        	for(int i = 0; i < matchList.size(); i++)
	        	{
	        		if(matchID == matchList.get(i).GetID()) break;
	        		else
	        		{
	        			if(i == matchList.size() - 1) registerMatch = false;
	        		}
	        	}
	        }
		}
		else matchID = random.nextInt(500)+1;
        
		Ball ball = new Ball(200, 200);
        System.out.println("creating a new Match and add it to a list");
        Match match = new Match(matchID, p1, p2, ball);
        matchList.add(match);
		
		p1 = p2 = null;
		
		System.out.println("Match list number: " + matchList.size());
		return match; 
	}
	
	public Match GetMatch(String matchID)
	{
		for(Match match : matchList)
		{
			if(Integer.parseInt(matchID) == match.GetID()) return match;
		}		
		return null;
	}
}