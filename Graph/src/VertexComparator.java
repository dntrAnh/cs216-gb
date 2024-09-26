/**
 * Comparator for Vertices
 */

import java.util.Comparator;

public class VertexComparator implements Comparator<Vertex>
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
	public int compare( Vertex a, Vertex b )
	{
		if ( a.distance < b.distance ) {
			return -1;
		}
		else if ( a.distance > b.distance ) {
			return +1;
		}
		else {
			return 0;
		}
	}
}