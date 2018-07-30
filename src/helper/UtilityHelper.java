package helper;

import GridWorld.Constants;

import java.util.ArrayList;
import java.util.Collections;

import GridWorld.Policy;
import GridWorld.State;

/*
 * Functions to calculate utility
 */
public class UtilityHelper {
	
	// Calculate Policy based on action
	// Constructs a newPolicy in the given direction, and calculate utility for it
	public static Policy getPolicy(Policy.DIR dir, final int row, final int col, final Policy[][] policyArr, final State[][] grid)
	{
		Policy newPolicy = new Policy();
		// for each move direction, calculate the utilties
		switch (dir) {
		case UP:
			newPolicy.direction = Policy.DIR.UP;
			newPolicy.utility = getMovingUpUtil(row, col, policyArr, grid);
			break;
		case DOWN:
			newPolicy.direction = Policy.DIR.DOWN;
			newPolicy.utility = getMovingDownUtil(row, col, policyArr, grid);
			break;
		case LEFT:
			newPolicy.direction = Policy.DIR.LEFT;
			newPolicy.utility = getMovingLeftUtil(row, col, policyArr, grid);
			break;
		case RIGHT:
			newPolicy.direction = Policy.DIR.RIGHT;
			newPolicy.utility = getMovingRightUtil(row, col, policyArr, grid);
			break;
		}
		
		return newPolicy;
	}
	
	// Calculate and find Optimal Policy based on action

	public static Policy getBestPolicy(
			final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid)
	{
		// An array for 4 directions, since we're comparing 4
		ArrayList<Policy> newPolicy = new ArrayList<Policy>();
		// Initiate policy for all the 4 directions
		Policy upPolicy = new Policy(), downPolicy = new Policy(),
				leftPolicy = new Policy(), rightPolicy = new Policy();
		
		// Find the respective utilities
		upPolicy.direction = Policy.DIR.UP;
		upPolicy.utility = getMovingUpUtil(row, col, policyArr, grid);

		downPolicy.direction = Policy.DIR.DOWN;
		downPolicy.utility = getMovingDownUtil(row, col, policyArr, grid);

		leftPolicy.direction = Policy.DIR.LEFT;
		leftPolicy.utility = getMovingLeftUtil(row, col, policyArr, grid);

		rightPolicy.direction = Policy.DIR.RIGHT;
		rightPolicy.utility = getMovingRightUtil(row, col, policyArr, grid);
		
		// Choose policy with the highest utility and return policy
		newPolicy.add(upPolicy);
		newPolicy.add(downPolicy);
		newPolicy.add(leftPolicy);
		newPolicy.add(rightPolicy);
		Collections.sort(newPolicy);
		Policy chosenPolicy = newPolicy.get(0);
		return chosenPolicy;
	}

	// Calculations for the utility of the 4 directions by getting the utilities for UP, DOWN, LEFT, RIGHT grids with methods, getUpUtil, getDownUtil, getLeftUtil, getRightUtil
	
	// UP direction utility
	private static double getMovingUpUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid){
		
		// Starting utility
		double utility = 0.0;
		
		// Add to the utility
		
		// Intends to move up
		utility += getUpGridUtil(row, col, policyArr, grid) * 0.8; 
		
		// Intends to go at either right angle
		utility += getLeftGridUtil(row, col, policyArr, grid) * 0.1;
		utility += getRightGridUtil(row, col, policyArr, grid) * 0.1;
		
		// Apply discount factor
		utility = grid[row][col].getReward() + Constants.DISCOUNT_FACTOR * utility;
		
		return utility;
		
	}
	
	// DOWN direction utility
	private static double getMovingDownUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid){
		
		// Starting utility
		double utility = 0.0;
		
		// Add to the utility
		
		// Intends to move up
		utility += getDownGridUtil(row, col, policyArr, grid) * 0.8; 
		
		// Intends to go at either right angle
		utility += getLeftGridUtil(row, col, policyArr, grid) * 0.1;
		utility += getRightGridUtil(row, col, policyArr, grid) * 0.1;
		
		// Apply discount factor
		utility = grid[row][col].getReward() + Constants.DISCOUNT_FACTOR * utility;
		
		return utility;
		
	}
	
	// LEFT direction utility
	private static double getMovingLeftUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid){
		
		// Starting utility
		double utility = 0.0;
		
		// Add to the utility
		
		// Intends to move up
		utility += getLeftGridUtil(row, col, policyArr, grid) * 0.8; 
		
		// Intends to go at either right angle
		utility += getUpGridUtil(row, col, policyArr, grid) * 0.1;
		utility += getDownGridUtil(row, col, policyArr, grid) * 0.1;
		
		// Apply discount factor
		utility = grid[row][col].getReward() + Constants.DISCOUNT_FACTOR * utility;
		
		return utility;
		
	}
	
	// RIGHT direction utility
	private static double getMovingRightUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid){
		
		// Starting utility
		double utility = 0.0;
		
		// Add to the utility
		
		// Intends to move up
		utility += getLeftGridUtil(row, col, policyArr, grid) * 0.8; 
		
		// Intends to go at either right angle
		utility += getUpGridUtil(row, col, policyArr, grid) * 0.1;
		utility += getDownGridUtil(row, col, policyArr, grid) * 0.1;
		
		// Apply discount factor
		utility = grid[row][col].getReward() + Constants.DISCOUNT_FACTOR * utility;
		
		return utility;
		
	}
	
	

	
	// Moving Up
	private static double getUpGridUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid) {
		
		// If the move is not out of bounds or is a wall
		return (row - 1 >= 0 
				&& !grid[row - 1][col].isWall()) 
				?
				// Allow the move
				policyArr[row - 1][col].utility 
				: 
				// Disallow the move
				policyArr[row][col].utility;
	}
	
	// Moving Down
	private static double getDownGridUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid) {
		
		// If the move is not out of bounds or is a wall
		return (row + 1 < grid[0].length 
				&& !grid[row + 1][col].isWall()) 
				?
				// Allow the move
				policyArr[row + 1][col].utility 
				: 
				// Disallow the move
				policyArr[row][col].utility;
	}
	
	// Moving Left
	private static double getLeftGridUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid) {
		
		// If the move is not out of bounds or is a wall
		return (col - 1 >= 0 
				&& !grid[row][col - 1].isWall()) 
				?
				// Allow the move
				policyArr[row][col - 1].utility 
				: 
				// Disallow the move
				policyArr[row][col].utility;
	}
	
	// Moving Right
	private static double getRightGridUtil(final int row, final int col,
			final Policy[][] policyArr,
			final State[][] grid) {
		
		// If the move is not out of bounds or is a wall
		return (col + 1 < grid.length 
				&& !grid[row][col + 1].isWall()) 
				?
				// Allow the move
				policyArr[row][col + 1].utility 
				: 
				// Disallow the move
				policyArr[row][col].utility;
	}
}
