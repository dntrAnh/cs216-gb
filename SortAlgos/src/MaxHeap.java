/**
 * Implementation of MaxHeap<E> 
 * @author ameliado
 */


public class MaxHeap<E> {
	E[] items; 	// primitive array of items 
	int size; 	// size of heap 
	
	/**
	 * Creates a max heap from the given items
	 * @param theItems - the given items 
	 */
	public MaxHeap(E[] theItems) 
	{
		items = theItems;
		size = theItems.length;
		
		this.makeHeap();
	}
	
	/**
	 * Pushes the item at the given index down the items array
	 * @param i - the index where the item is
	 */
	private void pushDown( int i )
	{
		while ( !isLeaf(i) ) {
	        int leftIndex = left(i);
	        int rightIndex = right(i);
	        int largest = i;
	        if (///leftIndex < size && 
	        	SortUtils.compare(items[largest], items[leftIndex]) < 0) {
	            largest = leftIndex;
	        }

	        if (rightIndex < size && 
	        	SortUtils.compare(items[largest], items[rightIndex]) < 0) {
	            largest = rightIndex;
	        }

	        if (largest != i) {
	        	SortUtils.swap(items, i, largest);	      
	            i = largest;
	        } 
	        else {
	            break;
	        }
	    }
	}
	
	/**
	 * Rearranges the array items so that it represents a max heap
	 */
	private void makeHeap()
	{
		for (int i = (size / 2) - 1; i >= 0; i--) {
            pushDown(i);
        }
	}
	
	/**
	 * Returns and removes the max value in the heap
	 * @return the max value in the heap 
	 */
	public E pop()
	{
		E maxVal = items[0];
		
		items[0] = items[size - 1];
		size--;
		
		pushDown(0);
		
		return maxVal;
	}
	
	/**
	 * Determines if the item at the given index is a leaf
	 * @param i - given index to check if the item is a leaf
	 */
	private boolean isLeaf( int i )
	{
	    return this.left(i) >= size;
	    
	}
	
	/**
	 * Returns the left child index
	 * @param i - index of the item to find left child of 
	 * @return index of the left child of item at index i
	 */
	private int left( int i )
	{
		return 2 * i + 1;
	}
	
	/**
	 * Returns the right child index
	 * @param i - index of the item to find right child of 
	 * @return index of the right child of item at index i
	 */
	private int right( int i )
	{
		return 2 * i + 2;
	}
	
	public String toString() 
	{
		return SortUtils.toString(items, size);
	}
	
}
