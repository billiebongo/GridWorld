package Iteration;

import java.util.ArrayList;

import GridWorld.Policy;
import helper.ArrayHelper;
import helper.UtilityHelper;
import GridWorld.Constants;
import GridWorld.World;

public class ValueIteration {
	public static ArrayList<Policy[][]> valueIteration(World gridworld)
	{
		// To hold all the utilities
		ArrayList<Policy[][]> utilityArray = new ArrayList<>();
		
		// Utility in the current iteration
		Policy[][] curUtilArr = new Policy[gridworld.num_row][gridworld.num_col];
		
		// New Utility calculated for this iteration
		Policy[][] newUtilArr = new Policy[gridworld.num_row][gridworld.num_col];
		
		// Init the new util array. Cur util will be copied anyway, so no need to init that
		for (int i = 0; i < gridworld.num_row; i++)
			for (int j = 0; j < gridworld.num_col; j++)
				newUtilArr[i][j] = new Policy();
		
		// Using a soft limit here to cap out the iterations. 
		// Convergence should happen within 50 iterations, as stated in assignment manual
		for (int iterations = 0; iterations < 50; iterations++)
		{
			// The new utility becomes the current utility (As one iteration has elapsed)
			ArrayHelper.PolicyArr2DCopy(newUtilArr, curUtilArr);
			
			// Append to the big utility array
			// Use copy as the value might be changed later
			Policy curUtilCopy[][] = new Policy[gridworld.num_row][gridworld.num_col];
			
			ArrayHelper.PolicyArr2DCopy(curUtilArr, curUtilCopy);
			
			// Append
			utilityArray.add(curUtilCopy);
			
			// Loop through states
			for(int row = 0 ; row < gridworld.num_row ; row++) {
		        for(int col = 0 ; col < gridworld.num_col ; col++) {
		        	
		        	// No need to calculate for wall grids
		        	if(gridworld.grid[row][col].isWall())
		        		continue;
		        	
		        	// Get best policy for the current grid
		        	newUtilArr[row][col] = UtilityHelper.getBestPolicy(row, col, curUtilArr, gridworld.grid);
		        }
			}
		}
		
		return utilityArray;
	}
}
