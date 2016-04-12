public class Ball 
{
	private float x, y, radius;
	private int speedX, speedY;
	private int screenW = 480, screenH = 800;
	private boolean couldCollide;
	
	public Ball(float x, float y)
	{
		this.x = x;
		this.y = y;
		this.radius = screenW * 0.04f;
		this.speedX = 4;
		this.speedY = 4;
		couldCollide = true;
	}
	
	public float GetX() { return this.x; }
	public float GetY() { return this.y; }
	
	public void update(Player player1, Player player2)	
	{
		this.x += speedX;
		this.y += speedY;
		
		System.out.println(couldCollide);
		
		CollisionWithScreen();
		CollisionWithPlayer(player1, player2);
	}
	
	private void ChangeBallState(boolean width)
	{		
		if(couldCollide)
		{
			if(width) speedX *= -1;
			else      speedY *= -1;
		
			couldCollide = false;
		}
	}
	
	private void CollisionWithScreen()
	{
		if(x + radius > screenW || x - radius < 0)      ChangeBallState(true);
		else if(y + radius > screenH || y - radius < 0) ChangeBallState(false);
		else                                            couldCollide = true;
	}
	
	private void CollisionWithPlayer(Player player1, Player player2)
	{
		/*if (x - radius < player1.GetPosX() + player1.GetWidth() && x + radius > player1.GetPosX() &&
			 y - radius < player1.GetPosY() + player1.GetHeight() && y + radius > player1.GetPosY() ||
			 x - radius < player2.GetPosX() + player2.GetWidth() && x + radius > player2.GetPosX() &&
			 y - radius < player2.GetPosY() + player2.GetHeight() && y + radius > player2.GetPosY())
		{
			speedY *= -1;
			couldCollide = false;
		}*/
	}	
}