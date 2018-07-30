package helper;

import GridWorld.Policy;
import GridWorld.World;

public class ArrayHelper {


	public static void PolicyArr2DCopy(Policy[][] src, Policy[][] dest) {

		for (int i = 0; i < src.length; i++) {
			System.arraycopy(src[i], 0, dest[i], 0, src[i].length);
		}
	}
	
	// Deep copy
	public static Policy[][] PolicyArrDeepCopy(Policy[][] src, World gridworld) {
		
		Policy[][] copiedPolicy = new Policy[src.length][src[0].length];
		
		// Loop through array and copy
		for (int i = 0; i < src.length; i++) {
			for (int j =0; j< src[0].length; j++)
			{
				
				copiedPolicy[i][j] = new Policy ();
				if (gridworld.grid[i][j].isWall()) continue;
				copiedPolicy[i][j].direction = src[i][j].direction;
				copiedPolicy[i][j].utility = src[i][j].utility;
			}
		}
		
		return copiedPolicy;
	}
	
}
