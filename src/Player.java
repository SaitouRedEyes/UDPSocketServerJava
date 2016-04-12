import java.net.InetAddress;

public class Player 
{	
	private float x, y, width, height;
	private int score;
	private InetAddress address;
	private int door;
	private boolean isUpdated;
	private int screenW, screenH;
	
	public Player(InetAddress address, int door, int screenW, int screenH)
	{
		this.score = 0;		
		this.address = address;
		this.door = door;
		this.isUpdated = false;
		this.screenW = screenW;
		this.screenH = screenH;
	}
	
	public float GetPosX() { return this.x; }
	public void SetPosX(float value) { this.x = value; }
	
	public float GetPosY() { return this.y; }
	public void SetPosY(float value) { this.y = value; }
	
	public float GetWidth() { return this.width; }
	public void SetWidth(float value) { this.width = value; }
	
	public float GetHeight() { return this.height; }
	public void SetHeight(float value) { this.height = value; }
	
	public int GetScreenW() { return this.screenW; }
	public int GetScreenH() { return this.screenH; }
	
	public int GetScore() { return this.score; }
	public void SetScore(int score) { this.score = score; }
	
	public InetAddress GetAddress() { return this.address; }
	public void SetAddress(InetAddress address) { this.address = address; }
	
	public int GetDoor() { return this.door; }
	public void SetDoor(int door) { this.door = door; }
	
	public Boolean GetIsUpdated() { return isUpdated; }
	public void SetIsUpdated(boolean value) { this.isUpdated = value; }
}
