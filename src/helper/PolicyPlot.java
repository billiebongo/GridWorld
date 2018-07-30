package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import GridWorld.Agent;
import GridWorld.Policy;
import GridWorld.Constants;
import GridWorld.World;

public class PolicyPlot {
	// Helper helps to plot Policy at a certain iteration. This plot will be saved in a file
	// NOTE Iteration is deducted by -1 (I.E, no iteration 0)
	// Formats the utility into a nice grid so we can see
	public static void PlotUtility (Agent agent, World gridworld, int iterationNo, int lastIteration) 
			throws IOException
	{
		// filename format 
		String filename =  agent.name + "_Iter_" + String.valueOf(iterationNo) + ".txt"; 
		
		// Make a new file for this iteration
		File thefile = new File(filename);
		thefile.createNewFile();	
		
        // Actually write to the file
        FileWriter fw = new FileWriter(thefile.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        
        // Some data text for easier review
        bw.write("Iter no " + String.valueOf(iterationNo) + " of agent " + agent.name + "\n");
        
        if (iterationNo != lastIteration)
        bw.write("The policy calculated at this iteration might not be the optimal.\n"
        		+ "Please look at the plot of the very last iteration of this agent. (Last iteration = "
        		+ String.valueOf(lastIteration)+"\n\n");
        else
        bw.write("The policy estimated at this iteration should be optimal if convergence is reached.\n"
        		+ "This is the very last iteration of this agent.\n\n");
        
        // Print util grid
        bw.write(BuildUtilGrid(agent,iterationNo));
        bw.write("\n");
        // Print actions
        bw.write(BuildPolicyGrid(agent, iterationNo));
        bw.write("\n");
        // Util Plot
        bw.write(BasicUtilPlot(agent, iterationNo, gridworld));
        bw.close();
	}
	
	// Building  utility grid
	private static String BuildUtilGrid (Agent theagent, int iterationNo)
	{
		// Prints out the utilArr
		
		StringBuilder sb = new StringBuilder();
		sb.append("Utility grid (To 3 D.P):\n\n");
		for (int col = 0; col < theagent.utilArrCol; col++ )
		{
			
			//drawing grid
			sb.append("#########");
			
		}
		
		sb.append("#\n");
		
		// Print utilities in 3 D.P
		String threedp = "00.000";
        DecimalFormat decformat = new DecimalFormat(threedp);
        
        for (int row = 0; row < theagent.utilArrRow; row++) {
        	// Add the border
        	sb.append("#");
        	// For the evil space between them 
 			for (int col = 0; col < theagent.utilArrCol; col++ )
 			{
 				sb.append("        #");
 			}
 			sb.append("\n#");
						
			for (int col = 0; col < theagent.utilArrCol; col++) {
				// This long ugly code gets utility of the given grid
				sb.append(String.format(" %s #",
						decformat.format(
						// Get the list of all the policies
						(theagent.listOfPolicy.get
						// Get Iteration No
						(iterationNo -1)
						[row][col].utility))));
			}
	        // Add the 2nd border
	        sb.append("\n");
	        sb.append("#");
	        // For the evil space between them
			for (int col = 0; col < theagent.utilArrCol; col++ )
			{
				sb.append("        #");
			}
			sb.append("\n");
			
	        // Add another delimiter for the next row
	        for(int col = 0 ; col < theagent.utilArrCol ; col++) {
	        	sb.append("#########");
	        }
	        sb.append("#\n");
	    }
        
        return sb.toString();
	}
	
	// Generates grid drawing with the directions (string type)
	private static String BuildPolicyGrid (Agent theagent, int iterationNo)
	{
		
		StringBuilder sb = new StringBuilder();
		sb.append("Policy grid:\n\n");
		for (int col = 0; col < theagent.utilArrCol; col++ )
		{
			
			// draw the grid
			sb.append("##########");
			
		}
		
		sb.append("#\n");
		
		// Print utilities in 3 D.P
		String threedp = "00.000";
        DecimalFormat decformat = new DecimalFormat(threedp);
        
        for (int row = 0; row < theagent.utilArrRow; row++) {
        	// Add the border
        	sb.append("#");
        	// For the evil space between them 
 			for (int col = 0; col < theagent.utilArrCol; col++ )
 			{
 				sb.append("         #");
 			}
 			sb.append("\n#");
 			
			for (int col = 0; col < theagent.utilArrCol; col++) {
				// Get direction, then convert it to string
				Policy.DIR direction = theagent.listOfPolicy.get
				// Get Iteration No
				(iterationNo -1)
				[row][col].direction;
				
				String dir_string = "";
				
				if (direction != null)
				{
					switch (direction)
					{
						case UP:
							dir_string = "^";
							break;
						case DOWN:
							dir_string = "V";
							break;
						case LEFT:
							dir_string ="<";
							break;
						case RIGHT:
							dir_string = ">";
							break;	
					}
				} 
				else
				{
					dir_string = "X"; // wall
				}
				
				//drawing the grid
				sb.append(String.format("    %s    #", dir_string));
			}
			
			
	        // Add the 2nd border
	        sb.append("\n");
	        sb.append("#");
	        // For the evil space between them
			for (int col = 0; col < theagent.utilArrCol; col++ )
			{
				sb.append("         #");
			}
			sb.append("\n");
			
	        // Add another delimiter for the next row
	        for(int col = 0 ; col < theagent.utilArrCol ; col++) {
	        	sb.append("##########");
	        }
	        sb.append("#\n");
	        System.out.println("WHAT");
	    }
        
        return sb.toString();
	}
	
	// prints out coordinates and correspoding utilities
	private static String BasicUtilPlot (Agent theagent, int iterationNo, World gw)
	{   
		String UtilString = "Utility Plot:\nCoordinates are in (col,row) format\nwith the top left corner being (0,0)\n\n";
		
        for (int col = 0; col < theagent.utilArrCol; col++) {			
			for (int row = 0; row < theagent.utilArrRow; row++) {
				if (gw.grid[row][col].isWall()) continue; //print directions if state is not a wall
				String dp = "00.";
				for (int i = 0; i < 6; i ++) dp += "0";
				
		        DecimalFormat decformat = new DecimalFormat(dp);

				// Get the list of all the policies
				String UtilValue = decformat.format(theagent.listOfPolicy.get
						// Get Iteration No
						(iterationNo -1)
						[row][col].utility);
				
				UtilString += "(" + String.valueOf(col) + "," + String.valueOf(row) + "): " + UtilValue + "\n";
			}
	    }
        
        return UtilString;
	}
}
