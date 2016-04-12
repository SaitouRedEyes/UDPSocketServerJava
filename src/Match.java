
public class Match 
{
	private int id;
	private Player p1, p2;
	private Ball ball;
	
	public Match(int id, Player p1, Player p2, Ball ball) 
	{
		this.id = id;
		this.p1 = p1;
		this.p2 = p2;
		this.ball = ball;
	}
	
	public int GetID() { return this.id; }
	public Player GetPlayer1() { return this.p1; }
	public Player GetPlayer2() { return this.p2; }
	
	public Ball GetBall() { return this.ball; }
}
