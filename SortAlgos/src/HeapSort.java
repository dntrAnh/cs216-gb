/**
 * Implementation of Heap Sort algorithm 
 * @author ameliado
 */
public class HeapSort {

	/**
	 * Sorts the items using HeapSort algorithm
	 * @param items - given items to be sorted 
	 */
	public static <E> void sort(E[] items) 
	{
		// 1. create a max heap out of the items
		MaxHeap<E> maxHeap = new MaxHeap<>(items);

		// 2. pop the max element from the heap and store it 
		// at the end of the given array; each time we pop 
		// from the heap a new spot opens up
	    for (int i = items.length - 1; i > 0; i--) {
	        items[i] = maxHeap.pop();
	    }
	}

}
