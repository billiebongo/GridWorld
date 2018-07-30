package Iteration;

import java.util.ArrayList;

import GridWorld.Policy;
import GridWorld.Policy.DIR;
import GridWorld.Constants;
import helper.ArrayHelper;
import helper.UtilityHelper;

import GridWorld.World;

public class PolicyIteration {
	
	public static ArrayList<Policy[][]> policyIteration(World gridworld)
	{
		// To hold all the utilities
		ArrayList<Policy[][]> utilityArray = new ArrayList<>();
		
		// Utility in the current iteration
		Policy[][] curUtilArr = new Policy[gridworld.num_row][gridworld.num_col];;
		
		// Init the cur util arr
		// For policy iteration, assign a dummy direction (in this case, up).
		// This is initial policy
		for (int i = 0; i < gridworld.num_row; i++)
			for (int j = 0; j < gridworld.num_col; j++)
			{
				curUtilArr[i][j] = new Policy();
				if (!gridworld.grid[i][j].isWall())
				curUtilArr[i][j].direction = DIR.UP;
			}
		
		// Using a soft limit here to cap out the iterations. 
		// Convergence should happen within 50 iterations, as stated in assignment manual
		for (int iterations = 0; iterations < 50; iterations++)
		{	
			// Append to the big utility array
			// Use copy as the value might be changed later
			Policy curUtilCopy[][] = new Policy[gridworld.num_row][gridworld.num_col];
			helper.ArrayHelper.PolicyArr2DCopy(curUtilArr, curUtilCopy);
			
			// Append
			utilityArray.add(curUtilCopy);
			
			// Cal new policy
			Policy [][] newPolicyCal = CalNewPolicy(curUtilArr, gridworld);
			
			// Loop through states
			for(int row = 0 ; row < gridworld.num_row ; row++) {
		        for(int col = 0 ; col < gridworld.num_col ; col++) {
		        	
		        	// No need to calculate for wall grids
		        	if(gridworld.grid[row][col].isWall())
		        		continue;
		        	
		        	// Get best policy for the current grid
		        	Policy bestPolicy = UtilityHelper.getBestPolicy(row, col, curUtilArr, gridworld.grid);
		        	
		        	// Policy for this current action
		        	Policy curPolicy = UtilityHelper.getPolicy(newPolicyCal[row][col].direction, row, col, newPolicyCal, gridworld.grid);
		        	
		        	// If best policy is better than current policy, change and improve
		        	if (bestPolicy.utility > curPolicy.utility)
		        	{
		        		newPolicyCal[row][col] = bestPolicy;
		        	}
		        }
			}
			
			ArrayHelper.PolicyArr2DCopy(newPolicyCal, curUtilArr);
		}
		
		return utilityArray;
	}
	
	// Calculate a new policy based on the update utilities 
	public static Policy[][] CalNewPolicy(final Policy[][] curUtilArr,
			final World gridworld) {
		

		Policy[][] curUtilArrCopy = helper.ArrayHelper.PolicyArrDeepCopy(curUtilArr, gridworld);
		
		Policy[][] newUtilArr = new Policy[gridworld.num_row][gridworld.num_col];
		// Init the new util array. Cur util will be copied anyway, so no need to init that
		for (int i = 0; i < gridworld.num_row; i++)
			for (int j = 0; j < gridworld.num_col; j++)
				newUtilArr[i][j] = new Policy();
		
		for (int k = 0; k < Constants.K_VAL; k++)
		{
			// For each state
			for (int row = 0; row < gridworld.num_row; row++) {
				for (int col = 0; col < gridworld.num_col; col++) {
	
					// Not necessary to calculate for walls
					if (gridworld.grid[row][col].isWall())
						continue;
	
					newUtilArr[row][col] = helper.UtilityHelper.getPolicy(curUtilArrCopy[row][col].direction, row, col, curUtilArrCopy, gridworld.grid);
				}
			}
			
			// New becomes current at the end of every loop
			helper.ArrayHelper.PolicyArr2DCopy(newUtilArr, curUtilArrCopy);
		}
		
		return newUtilArr;
	}
}
