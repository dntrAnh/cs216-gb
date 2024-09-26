import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SortAlgosTest {
	
	private Integer[] numbers;

    public static <E> E[] load(E... items) 
    {
        return items;
    }

    @Test
    public void test_MaxHeap() 
    {
    	numbers = load(1);
		MaxHeap<Integer> heap = new MaxHeap<>(numbers);
	
		numbers = load(1, 2, 3);
		heap = new MaxHeap<>(numbers);
		assertEquals(SortUtils.toString(numbers), "[1 2 3]");
		
        numbers = load(3, 1, 4, 1, 5, 9, 2, 6, 5);
        heap = new MaxHeap<>(numbers);
        assertEquals(heap.size, 9);
    }

    @Test
    public void test_HeapSort()
    {
    	// empty 
    	numbers = load();
		HeapSort.sort(numbers);
		assertEquals(SortUtils.toString(numbers), "[]");
		
    	// single
		numbers = load(1);
		HeapSort.sort(numbers);
		assertEquals(SortUtils.toString(numbers), "[1]");
		
    	// multi
		numbers = load(3, 1, 4, 1, 5, 9, 2, 6, 5);
		HeapSort.sort(numbers);
		assertEquals(SortUtils.toString(numbers), "[6 9 6 5 9 2 6 1 3]");
    }
    
    @Test
    public void test_QuickSort()
    {
    	// empty 
    	numbers = load();
    	
    	// single
    	numbers = load(1);
		assertEquals(QuickSort.partition(numbers, 0, 0), 0);
		assertEquals(SortUtils.toString(numbers), "[1]");
		
		// multi 
		numbers = load(3, 1, 2);
		assertEquals(QuickSort.partition(numbers, 0, 2), 0);
		QuickSort.sort(numbers);
		assertEquals(SortUtils.toString(numbers), "[1 2 3]");
    }
    
    @Test
	public void test_MergeSort() {
//    	// empty 
//    	numbers = load();
//    	MergeSort.sort(numbers);
//		assertEquals(SortUtils.toString(numbers), "[]");
		
		// single 
		numbers = load(1);
		MergeSort.sort(numbers);
		assertEquals(SortUtils.toString(numbers), "[1]");
		
		// multi 
		numbers = load(2, 1, 4, 3, 7, 10, 5);
		
		assertEquals(SortUtils.toString(MergeSort.sort(numbers)), "[1 2 3 4 5 7 10]");
    }
    
}
