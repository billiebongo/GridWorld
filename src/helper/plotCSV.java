package helper;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

import java.util.Iterator;

import GridWorld.Agent;
import GridWorld.Policy;
import GridWorld.Constants;
import GridWorld.World;

public class plotCSV {

	public static void PlotToCSV (Agent agent, World gridworld) 
			throws IOException
	{

		String filename = agent.name + "_toCSV" + ".csv"; 
		String sb = "";
		
		File thefile = new File(filename);
		thefile.createNewFile();

		
		// Output in desirable fornmat (6 decimal places)
		String dp = "00.";
		for (int i = 0; i < 6; i ++) dp += "0";
		
        DecimalFormat decformat = new DecimalFormat(dp);
        
        // Print out the first row. A reference to all the non wall states of this grid
        
        // For all iterations, plot the CSV
        // Each row is one iteration
        // Starting with, col row, Util1, Util2, Util3 etc.
        
        for (int col = 0; col < agent.utilArrCol; col++) {			
			for (int row = 0; row < agent.utilArrRow; row++) {
				
				// No need to plot for wall
				if (gridworld.grid[row][col].isWall()) continue;
				// Current no of iterations
		        int curIter = 0;
				// Iterator to go through the Policy list
				Iterator<Policy[][]> theIterator = agent.listOfPolicy.iterator();
				// Plot the coordinates this is at, so we have a reference
				sb += String.valueOf(col) + " " + String.valueOf(row) + ",";
				// While there's still iterations to plot
				while (theIterator.hasNext())
				{
					Policy[][] curIterArr = theIterator.next();

					// Get the list of all the policies
					String UtilValue = decformat.format(agent.listOfPolicy.get(curIter)[row][col].utility);
					
					sb += UtilValue;
					
					if(theIterator.hasNext()) {
						sb += ",";
					
					}
					System.out.print(curIter + " ");
					curIter+= 1;
				}
				sb += "\n";
				System.out.println("line");
			}	        
	    }
        
	     // Actually write to the file
	    FileWriter fw = new FileWriter(thefile.getAbsoluteFile());
	    BufferedWriter bw = new BufferedWriter(fw);
	    bw.write(sb);
	    
	    bw.close();
        
	}
}
