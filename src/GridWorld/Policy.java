package GridWorld;

/* Class for policy
 * A "Policy" is basically 2 things:
 * 1. Direction the intelligent agent decides to move
 * 2. Utility of that action
 * 
 * There can be more than 1 Policy for a grid (Depending on the number of acceptable options
 */

// Implement Comparable so it's possible to sort
public class Policy implements Comparable<Policy> {

	public enum DIR {
		UP,
		DOWN,
		LEFT,
		RIGHT
	}
	
	public DIR direction = null;
	
	public double utility = 0.0;

	@Override
	// return whetherprevious utility -1,0,1 for less than, equal, more than new utility
	public int compareTo(Policy o) {
		return ((Double) o.utility).compareTo(utility);
	}
}
