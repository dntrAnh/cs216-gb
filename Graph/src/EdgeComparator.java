/**
 * Comparator for Edges 
 */

import java.util.Comparator;

public class EdgeComparator implements Comparator<Edge>
{
	/**
	 * Compares the given numbers.
	 * 
	 * @param  distance  the first number to compare
	 * @param  newDist  the second number to compare
	 *
	 * @return negative value when a < b
	 *         positive value when a > b
	 *             0          when a == b
	 */

	@Override
	public int compare(Edge a, Edge b) {
		if ( a.getWeight() < b.getWeight() ) {
			return -1;
		}
		else if ( a.getWeight() > b.getWeight() ) {
			return +1;
		}
		else {
			return 0;
		}
	}
}