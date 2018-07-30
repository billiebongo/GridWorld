package GridWorld;

public class State {

	// Initialise as white tile
	private double reward = 0.0;
	
	// and that tile is not a wall
	private boolean is_wall = false;
	
	public State (double reward)
	{
		this.reward = reward;
	}
	
	public void isWall (boolean isWall)
	{
		this.is_wall = isWall;
	}
	
	public void setReward (double reward)
	{
		this.reward = reward;
	}
	
	public double getReward ()
	{
		return this.reward;
	}
	
	public boolean isWall ()
	{
		return this.is_wall;
	}
}
