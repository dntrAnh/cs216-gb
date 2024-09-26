import java.util.Comparator;

/**
 * Visitor for counting items in a given range.
 */

public class CountRangeVisitor<E> implements Visitor<E>
{
	private E a, b;
	private Comparator<E> comp;
	private int count = 0;

	/**
	 * 
	 * @param a The lower bound of the range
	 * @param b The upper bound of the range
	 * @param comp The comparator used for comparing elements
	 */
	public CountRangeVisitor( E a, E b, Comparator<E> comp )
	{
		this.a = a;
		this.b = b;
		this.comp = comp;
	}

	@Override
	public void visit(E item) {
		if (comp.compare(item, a) >= 0 && comp.compare(item, b) <= 0) 
		{
			count++;
		}
	}
	
	public int getValue() {
		return count; 
	}
}