import java.util.HashMap;

public class DisjointSets<E> 
{
	private class Node 
	{
		E item;      
		int rank;    
		Node parent; 

		Node(E theItem) 
		{
			this.item = theItem;
			this.rank = 0;
			this.parent = this;
		}
	}

	/*
	 * key (E) : item 
	 * value (Node) : value 
	 * connect 'item' (what user knows) with 'value' (what graph knows)
	 */
	HashMap<E, Node> disjointSet = new HashMap<>();

	/*
	 * - Creates a Disjoint-Sets data structure with the 
	 * given items as individual sets
	 * - Makes a single node/set for each of the given items 
	 * and associates each pair in the internal Hash Map.
	 */
	public DisjointSets(Iterable<E> items)
	{
		disjointSet = new HashMap<>();
		for (E item : items) {
			disjointSet.put(item, new Node(item));
		}
	}

	/**
	 * Find representation of the current node
	 * @param curr - current node to find the representation of 
	 * @return representation of the current node
	 */
	private Node findRep(Node curr)
	{
		if (curr.parent == curr) {
			return curr;
		}
		curr.parent = findRep(curr.parent);
		return curr.parent;
	}

	/**
	 * Return true if the representation of the item a and b is the same
	 * @param a - item a to be compared
	 * @param b - item b to be compared 
	 * @return true if the representation of the two item is the same
	 */
	public boolean sameSet(E a, E b)
	{
		Node repA = findRep(disjointSet.get(a));
		Node repB = findRep(disjointSet.get(b));
		return repA == repB;
	}

	/**
	 * Merges the sets that contain the given items a and b
	 * @param a - item a of a set to be merged 
	 * @param b - item b of a set to be merged
	 */
	public void union(E a, E b)
	{
		if (sameSet(a, b)) {
			return; 
		}

		Node repA = findRep(disjointSet.get(a));
		Node repB = findRep(disjointSet.get(b));

		if (repA.rank < repB.rank) {
			repA.parent = repB;
		}
		else if (repA.rank > repB.rank) {
			repB.parent = repA; 
		}
		else {
			repB.parent = repA; 
			repA.rank += 1;
		}
	}
}
