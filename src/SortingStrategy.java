public interface SortingStrategy {
    void bubbleSort(int []num);
    void insertSort(int []num);
    void selectSort(int []num);
    void iterativeMergeSort(int []num);
    void radixSort(int []num);
    void shakerSort(int []num);
    void bucketSort(int []num, int bucketSize);
    void quickSort(int []num);
}
