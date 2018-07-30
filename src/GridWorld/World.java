package GridWorld;

import GridWorld.State;
import GridWorld.Constants;


public class World {

	// States per iteration
	public State[][] grid = null;
	
	public int num_row = 0;
	public int num_col = 0;
	
	// Init a grid with the num_row, and num_col
	public World (int num_row, int num_col, String B_Squares, String G_Squares, String Wall_Squares)
	{
		this.num_row = num_row;
		this.num_col = num_col;
		grid = new State[num_row][num_col];
		
		for (int i = 0;i < num_row; i++)
		{
			for (int j =0;j < num_col; j++)
			{
				// Init as all white tiles first
				grid[i][j] = new State(Constants.W_REWARD);
			}
		}
		
		// Set the appropriate rewards
		InitGridWorld(B_Squares, G_Squares, Wall_Squares);
	}
	
	//Set the appropriate rewards
	public void InitGridWorld(String B_Squares, String G_Squares, String Wall_Squares)
	{
		// Set the reward for brown squares
		String[] brown_squares = Constants.B_SQUARES.split("#");
		
		for (int i = 0; i < brown_squares.length; i++)
		{
			//coordinates of square
			String[] Coords = brown_squares[i].trim().split(",");
			
			// Set Brown reward =-1
			grid[Integer.parseInt(Coords[0])][Integer.parseInt(Coords[1])].setReward(Constants.B_REWARD);
			
		}

		
		// Set the reward for green squares =1
		String[] green_squares = Constants.G_SQUARES.split("#");
		
		for (int i = 0; i < green_squares.length; i++)
		{
			// Trim to remove spaces, then extract the coordinates
			String[] Coords = green_squares[i].trim().split(",");

			grid[Integer.parseInt(Coords[0])][Integer.parseInt(Coords[1])].setReward(Constants.G_REWARD);
			
		}
		
		// Set walls, reward is set as 0 and utilities of wall state is 0
		String[] wall_squares = Constants.WALL_SQUARES.split("#");
		
		for (int i = 0; i < wall_squares.length; i++)
		{

			String[] Coords = wall_squares[i].trim().split(",");

			
			// Apply the correct reward
			grid[Integer.parseInt(Coords[0])][Integer.parseInt(Coords[1])].setReward(0.0);
			// agent cannot enter wall state
			grid[Integer.parseInt(Coords[0])][Integer.parseInt(Coords[1])].isWall(true);
		}
		
	}
}

// For all grids this must happen

// Initial utility
// Value based: Start with every U(S) = 0
// Policy based: Calculate optimal U(S) with Bellman equation

// In one iteration:
// Calculate best action based on utility (Using Bellman equation)
// Get utility of each action
// Apply discount and reward for final utility

// Convergence should happen in no more than 50 iterations I.E, optimal policy should be decided by then