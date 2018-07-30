package main;

import GridWorld.Constants;

import java.io.IOException;

import GridWorld.Agent;
/*
 * Main class to run ValueIteration and Policy Iteration for GridWorld
 */
import GridWorld.World;

public class main {

	public static void main (String args[]) throws IOException
	{

		World NewWorld;
		
		// Building GridWorld
		NewWorld = new World(6, 6, Constants.B_SQUARES, Constants.G_SQUARES, Constants.WALL_SQUARES);
		
		// Create an agents
		Agent ValueIterAgent = new Agent();
		Agent PolicyIterAgent = new Agent();
		
		ValueIterAgent.InitAndRun(NewWorld, "ValueIter", true);
		
		PolicyIterAgent.InitAndRun(NewWorld, "PolicyIter", false);

		// Manually plot based on what iterations I need. Upscaled to 500 to see what happens
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 1, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 5, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 10, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 15, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 20, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 25, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 30, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 35, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 40, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 45, 50);
		helper.PolicyPlot.PlotUtility(ValueIterAgent, NewWorld, 50, 50);
		helper.plotCSV.PlotToCSV(ValueIterAgent, NewWorld);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 1, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 5, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 10, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 15, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 20, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 25, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 30, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 35, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 40, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 45, 50);
		helper.PolicyPlot.PlotUtility(PolicyIterAgent, NewWorld, 50, 50);
		helper.plotCSV.PlotToCSV(PolicyIterAgent, NewWorld);
		
	}
}
