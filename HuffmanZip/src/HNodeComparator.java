import java.util.Comparator;

/**
 * Implementation of HNodeComparator by 
 * @author Amelia Do 
 * @param <E>
 */
public class HNodeComparator implements Comparator<HNode> {

	/**
	 * Compares the given numbers.
	 * 
	 * @param  node1  the first number to compare
	 * @param  node2  the second number to compare
	 *
	 * @return -1 value when node1  <  node2
	 *         +1 value when node1  >  node2
	 *          0       when node1  == node2
	 */
	@Override
	public int compare(HNode node1, HNode node2) 
	{
		if (node1.freq < node2.freq) 
		{
			return -1;
		}
		else if (node1.freq > node2.freq)
		{
			return +1;
		}
		else
		{
			return node1.symbols.compareTo(node2.symbols);
		}
	}

}
