import java.util.Random;

public class TestOfSortingAlgorithms {
    static SortingStrategy strategy = new SortingAlgorithms();
    static int []arr = generateArray(10000);
    private static int [] generateArray(int len){
        Random rand = new Random();
        int [] num = new int[len];
        for (int i = 0; i < len; i++) {
            num[i]= rand.nextInt(len);
        }
        return num;
    }

    public static void main(String[] args) {
        testForBubbleSort(arr.clone());
        testForInsertSort(arr.clone());
        testForSelectSort(arr.clone());
        testForIterativeMergeSortSort(arr.clone());
        testForRadixSort(arr.clone());
        testForShakerSort(arr.clone());
        testForQuickSort(arr.clone());
        testForBucketSort(arr.clone());
    }

    public static void testForBubbleSort(int []arr){
        System.out.println("Bubble sort");
        long start = System.currentTimeMillis();
        strategy.bubbleSort(arr);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }

    public static void testForInsertSort(int []arr){
        System.out.println("Insert sort");
        long start = System.currentTimeMillis();
        strategy.insertSort(arr);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }

    public static void testForSelectSort(int []arr){
        System.out.println("Select sort");
        long start = System.currentTimeMillis();
        strategy.selectSort(arr);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }

    public static void testForIterativeMergeSortSort(int []arr){
        System.out.println("Iterative merge sort");
        long start = System.currentTimeMillis();
        strategy.iterativeMergeSort(arr);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }

    public static void testForRadixSort(int []arr){
        System.out.println("Radix sort");
        long start = System.currentTimeMillis();
        strategy.radixSort(arr);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }

    public static void testForShakerSort(int []arr){
        System.out.println("Shaker sort");
        long start = System.currentTimeMillis();
        strategy.shakerSort(arr);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }

    public static void testForQuickSort(int []arr){
        System.out.println("Quick sort");
        long start = System.currentTimeMillis();
        strategy.quickSort(arr);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }

    public static void testForBucketSort(int []arr){
        System.out.println("Bucket sort");
        long start = System.currentTimeMillis();
        strategy.bucketSort(arr, arr.length/2);
        System.out.println(System.currentTimeMillis()-start+" ms\n");
    }
}
