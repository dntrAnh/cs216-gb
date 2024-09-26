 /** Implementation of the Algorithm QuickSort
 * @author ameliado
 */
public class QuickSort {

	/**
	 * 
	 * @param items
	 * @param i
	 * @param j
	 * @return
	 */
	public static <E> int partition(E[] items, int i, int j)
	{
		int middle = (i + j) / 2;

		// 1. pick a pivot element and swap it with the last item 
		// in the range
		E pivot = items[middle];
		swap(items, middle, j);

		// 5. Repeat steps 2-4 until i,j meet
		int left = i;
		int right = j - 1;

		while (left <= right) {
			// 2. move forward index left until it finds an item bigger than the pivot
			while (left <= right && SortUtils.compare(items[left], pivot) < 0) {
				left++;
			}
			// 3. move backward index right until it finds an item smaller than the pivot
			while (left <= right && SortUtils.compare(items[right], pivot) >= 0) {
				right--;
			}
			// 4. exchange the values at left, right
			if (left < right) {
				swap(items, left, right);
				left++;
				right--;
			}
		}
		// 6. exchange the pivot and the item at index left
		swap(items, left, j); // Put the pivot in its proper place

		return left; // Return the position of the pivot
	}

	private static <E> void swap(E[] items, int i, int j) {
		SortUtils.swap(items, i, j);
	}

	/**
	 * 
	 * @param items
	 * @param i
	 * @param j
	 */
	public static <E> void sort(E[] items, int i, int j)
	{
		if (i < j) {
            int pivotIndex = partition(items, i, j);
            sort(items, i, pivotIndex - 1);
            sort(items, pivotIndex + 1, j);
        }
	}

	/**
	 * 
	 * @param items
	 */
	public static <E> void sort(E[] items)
	{
		sort(items, 0, items.length - 1);
	}
}
