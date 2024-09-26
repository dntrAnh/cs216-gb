public class MergeSort 
{

	public static <E> E[] merge(E[] leftSide, E[] rightSide) 
	{
		// 1. create the result primitive array with enough space to hold both sides
		int totalLength = leftSide.length + rightSide.length;
		E[] result = SortUtils.arrayAs(leftSide, totalLength);

		// 2. keep two indices starting at the beginning of each side (and an index into the result)
		int indexLeft = 0, indexRight = 0, indexResult = 0;

		// 3. transfer to the result the smaller element from the two sides and advance the corresponding index
		while (indexLeft < leftSide.length && indexRight < rightSide.length) {
			if (SortUtils.compare(leftSide[indexLeft], rightSide[indexRight]) <= 0) {
				result[indexResult] = leftSide[indexLeft];
				indexLeft++;
			} 
			else {
				result[indexResult] = rightSide[indexRight];
				indexRight++;
			}
			indexResult++;
		}

		while (indexLeft < leftSide.length) {
			result[indexResult++] = leftSide[indexLeft++];
		}

		while (indexRight < rightSide.length) {
			result[indexResult++] = rightSide[indexRight++];
		}

		return result;
	}

	public static <E> E[] sort(E[] items, int i, int j) 
	{	
		if (i == j) {  // Base case for recursion: no elements to sort
			return SortUtils.copyCell(items, i);
		}

		int mid = (i + j) / 2;

		// Recursive case: sort the two halves
		E[] sortedLeft = sort(items, i, mid);
		E[] sortedRight = sort(items, mid + 1, j); 


		// Merge the sorted halves
		return merge(sortedLeft, sortedRight);
	}

	public static <E> E[] sort(E[] items) 
	{
		return sort(items, 0, items.length - 1);
	}

}
