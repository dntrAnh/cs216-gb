public class CompareSortAlgos {

    public static void main(String[] args) {
        CompareSortAlgos compareSortAlgos = new CompareSortAlgos();
        compareSortAlgos.compareSortAlgos(100000, 1000000, 100000, 'r');
//        compareSortAlgos.compareSortAlgos(100000, 1000000, 100000, 'i');
//        compareSortAlgos.compareSortAlgos(100000, 1000000, 100000, 'd');
    }

    public void compareSortAlgos(int initSize, int finalSize, int sizeStep, char order) {
        System.out.println("SIZE\tQSms\tHSms\tMSms");
        for (int size = initSize; size <= finalSize; size += sizeStep) {
            Double[] arrayForQuickSort = SortUtils.generateArray(size, order);
            Double[] arrayForHeapSort = SortUtils.generateArray(size, order);
            Double[] arrayForMergeSort = SortUtils.generateArray(size, order);

            long startQ = System.currentTimeMillis();
            QuickSort.sort(arrayForQuickSort);;
            long qTime = System.currentTimeMillis() - startQ;

            long startH = System.currentTimeMillis();
            HeapSort.sort(arrayForHeapSort);
            long hTime = System.currentTimeMillis() - startH;

            long startM = System.currentTimeMillis();
            MergeSort.sort(arrayForMergeSort);
            long mTime = System.currentTimeMillis() - startM;

            System.out.println(size + "\t" + qTime + "\t" + hTime + "\t" + mTime);
        }
    }
}
