package GridWorld;


import Iteration.ValueIteration;
import Iteration.PolicyIteration;

import java.util.ArrayList;

import GridWorld.Policy;
import GridWorld.World;

import GridWorld.Constants;
/*
 * A class for the agent
 */
public class Agent {
	
	// How many rows and cols for the utility array
	public int utilArrRow;
	public int utilArrCol;
	
	// PolicyAgent or ValueAgent
	public String name;
	
	// True for ValueIteration, False for PolicyIteration
	public boolean isValueIteration;
	
	// ArrayList of policies for each state per iteration
	public ArrayList<Policy[][]> listOfPolicy = null;
	
	public void InitAndRun(World gridworld, String name, boolean isValueIteration)
	{
		name = name;
		listOfPolicy = new ArrayList<>();
		
		this.utilArrRow = gridworld.num_row;
		this.utilArrCol = gridworld.num_col;
		
		if (isValueIteration)
		{
			// Value Iteration
			listOfPolicy = Iteration.ValueIteration.valueIteration(gridworld);
		}
		else
		{
			// Policy Iteration
			listOfPolicy = Iteration.PolicyIteration.policyIteration(gridworld);
		}
	}
}
