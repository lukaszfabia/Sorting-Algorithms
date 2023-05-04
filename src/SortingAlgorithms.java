import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortingAlgorithms implements SortingStrategy{

    private void showArray(int []num){
        System.out.print(num[0]);
        for (int i = 1; i < num.length; i++) {
            System.out.print(" " + num[i]);
        }
        System.out.println();
    }

    private void swap(int []num, int i, int j){
        int temp = num[i];
        num[i]=num[j];
        num[j]=temp;
    }

    private int indexOfMaxElement(int []num, int range){
        int index=0;
        for (int i = 1; i < range; i++) {
            if (num[i]>num[index]){
                index=i;
            }
        }
        return index;
    }
    public void bubbleSort(int []num){
        int compressions = 0;
        int assignments = 0;
        for (int i = 0; i < num.length - 1; i++) {
            for (int j = num.length - 1; j > i; j--) {
                compressions++;
                if (num[j] < num[j - 1]) {
                    swap(num, j, j - 1);
                    assignments += 3;
                }
            }
        }
        System.out.println("Compressions: " + compressions);
        System.out.println("Assignments: " + assignments);
    }

    public void insertSort(int []num){
        int j, temp;
        int compressions = 0;
        int assignments = 0;
        for (int i = num.length - 2; i >= 0; i--) {
            j = i;
            temp = num[i];
            while (j < num.length - 1 && temp > num[j + 1]) {
                num[j] = num[j + 1];
                j++;
                compressions++;
                assignments+=2;
            }
            num[j] = temp;
            assignments+=3;
        }
        System.out.println("Compressions: " + compressions);
        System.out.println("Assignments: " + assignments);
    }

    public void selectSort(int []num){
        int maxInd;
        for (int i = num.length - 1; i > 0; i--) {
            maxInd = indexOfMaxElement(num, i);
            if (num[maxInd] > num[i]) {
                swap(num, maxInd, i);
            }
        }
    }

    public void iterativeMergeSort(int[] num) {

        int size = num.length;
        int partition = 2;
        int[] result = new int[size];

        while ((partition / 2) < size) {
            int leftPos = 0;
            int rightPos = partition / 2;
            int stopFlagLeft = rightPos;
            int stopFlagRight = partition;
            int resultArrayIndex = 0;

            // ustawiamy stopFlagRight na rozmiar ostatniej partycji, jesli:
            if (partition > size) {
                stopFlagRight = size;
            }

            //scalamy sasiednie partycje w kazdej iteracji
            for (int i = 0; i < ((size / partition) + 1); i++) {
                while (leftPos < stopFlagLeft && rightPos < stopFlagRight) {
                    if (num[leftPos] <= num[rightPos]) {
                        result[resultArrayIndex] = num[leftPos];
                        leftPos++;
                    } else {
                        result[resultArrayIndex] = num[rightPos];
                        rightPos++;
                    }
                    resultArrayIndex++;
                }

                //porownojemy i kopiujemy z lewej podtablicy do result
                while (leftPos < stopFlagLeft) {
                    result[resultArrayIndex] = num[leftPos];
                    leftPos++;
                    resultArrayIndex++;
                }

                // analogicznej jak wyzej tylko dla prawej podtablicy
                while (rightPos < stopFlagRight) {
                    result[resultArrayIndex] = num[rightPos];
                    rightPos++;
                    resultArrayIndex++;
                }

                // ustawiamy poczatkowe pozycje i koncowe dla kolejnej iteracji
                leftPos = stopFlagRight;
                rightPos = leftPos + (partition / 2);
                if (rightPos > size - 1) {
                    stopFlagLeft = size;
                    stopFlagRight = size;
                } else {
                    stopFlagLeft = rightPos;
                    stopFlagRight = stopFlagRight + partition;
                    if (stopFlagRight > size)
                        stopFlagRight = size;
                }

            }

            System.arraycopy(result, 0, num, 0, size);

            partition = partition * 2;
        }
    }

    public void radixSort(int []num){
        int d = 3;
        int[] result = new int[num.length];
        int[] pos = new int[10]; // 0-9
        int divisor = 1;

        for (int i = 0; i < d; i++) {
            Arrays.fill(pos, 0); // wypelniamy 0, zastapilem za petle

            for (int value : num) {
                pos[(value / divisor) % 10]++;
            }

            pos[0]--; // poprawiamy indexy tablicy
            for (int j = 1; j < pos.length; j++) {
                pos[j] += pos[j - 1];
            }
            for (int j = num.length - 1; j >= 0; j--) {
                result[pos[(num[j] / divisor) % 10]] = num[j];
                pos[(num[j] / divisor) % 10]--;
            }
            System.arraycopy(result, 0, num, 0, num.length);
            divisor *= 10;
        }
    }

    public void shakerSort(int[] num) {
        boolean swapped;
        int left = 0;
        int right = num.length - 1;
        do {
            swapped = false;
            for (int i = left; i < right; i++) {
                if (num[i] < num[i + 1]) {
                    swap(num, i, i + 1);
                    swapped = true;
                }
            }
            right--;
            for (int i = right; i > left; i--) {
                if (num[i] > num[i - 1]) {
                    swap(num, i, i - 1);
                    swapped = true;
                }
            }
            left++;
        } while (swapped);
    }

    @Override
    public void bucketSort(int []num, int bucketSize){
        // szukanie min
        int minValue = Integer.MAX_VALUE;
        int maxValue = 0;
        for (int j : num) {
            if (j < minValue) {
                minValue = j;
            } else if (j > maxValue) {
                maxValue = j;
            }
        }

        // obliczenie liczby kubełków
        int bucketCount = (maxValue - minValue) / bucketSize + 1;
        ArrayList<int[]> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new int[0]);
        }

        // rozmieszczenie liczb w odpowiednich kubełkach
        for (int j : num) {
            int bucketIndex = (j - minValue) / bucketSize;
            int[] bucket = buckets.get(bucketIndex);
            bucket = Arrays.copyOf(bucket, bucket.length + 1);
            bucket[bucket.length - 1] = j;
            buckets.set(bucketIndex, bucket);
        }

        // sortowanie kubełków przez scalanie
        int currentIndex = 0;
        for (int i = 0; i < bucketCount; i++) {
            int[] bucket = buckets.get(i);
            if (bucket.length > 0) {
                iterativeMergeSort(bucket);
                for (int k : bucket) {
                    num[currentIndex++] = k;
                }
            }
        }
    }

    public void quickSort(int[] num) {
        int n = num.length;

        quickSort(num, 0, n - 1);
    }

    private void quickSort(int[] num, int left, int right) {
        if (left >= right) {
            return;
        }


        int border = partitionArray(num, left, right);

        // zmiejszay stos wywolan po przez wybranie miejszej czesci
        if (border - left < right - border) {
            quickSort(num, left, border - 1);
            quickSort(num, border + 1, right);
        } else {
            quickSort(num, border + 1, right);
            quickSort(num, left, border - 1);
        }
    }

    private int partitionArray(int[] num, int left, int right) {
        int pivotValue;

        if (num.length < 100) {
            pivotValue = chooseRandomPivot(num, left, right);
        } else {
            pivotValue = choosePivot(num, left, right);
        }

        int border = left - 1;
        int i = left;

        while (i < right) {
            if (num[i] < pivotValue) {
                border++; // przesuwamy granice
                if (border != i) {
                    swap(num, border, i); // i robimy swapa z i 'tym elementem i liczba
                }
            }
            i++;
        }

        border++;
        if (border != right) {
            swap(num, border, right);
        }

        return border;
    }

    private int choosePivot(int[] num, int left, int right) {
        int mid = left + (right - left) / 2;
        swap(num, mid, right);
        return num[mid];
    }

    private int chooseRandomPivot(int[] num, int left, int right) {
        Random rand = new Random();
        return num[rand.nextInt(right - left)];
    }
}
