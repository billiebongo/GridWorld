package GridWorld;


public class Constants {


	// Dimensions
	public final static int ROWS = 6;
	public final static int COLS = 6;
	
	// Rewards
	public final static double W_REWARD = -0.04;
	public final static double B_REWARD = -1;
	
	public final static double G_REWARD = 1;

	

	//public final static String DELIMITER = "#";
	
	//public final static String COORD_DELIMITER = ",";
	
	public final static String B_SQUARES = "1,1 # 1,5 # 2,2 # 3,3 # 4,4";
	public final static String G_SQUARES = "0,0 # 0,2 # 0,5 # 1,3 # 2,4 # 3,5";
	public final static String WALL_SQUARES = "0,1 # 1,4 # 4,1 # 4,2 # 4,3";
	
	// Starting Position
	public final static String Start_Pos = "3,2";

	// By using a discount factor of 0.95055216 I was actually able to get results close to the lab manual 
	// for (Value iter, 50 iterations)
	// Just for fun
	public final static double DISCOUNT_FACTOR = 0.99;
	
	// Transition model
	// Prob he will move in the intended direction


	
	// K. Number of times the simplified Bellman update is repeated to produce the next estimate
	public final static int K_VAL = 1;
	
	/*
	 * For Policy Plotting etc
	 */
	
	// Strings for the directions

	

	
	
	
	
}
