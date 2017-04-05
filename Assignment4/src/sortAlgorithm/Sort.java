package sortAlgorithm;

import java.awt.AlphaComposite;
import java.util.Random;

/**
 *
 * @author leslie
 */
public class Sort {

    public static void print(int[] data) {
        for (int d : data) {
            System.out.print(d + " ");
        }
        System.out.println();
    }

    //1. Mergesort
    public static void mergeSort(int[] a, int low, int high) {
        if (low < high) {
            mergeSort(a, low, (low + high) / 2);
            mergeSort(a, (low + high) / 2 + 1, high);
            merge(a, low, (low + high) / 2, high);
        }
    }

    private static void merge(int[] a, int low, int mid, int high) {
        int[] sorted = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (a[i] <= a[j]) {
                sorted[k++] = a[i++];
            } else {
                sorted[k++] = a[j++];
            }
        }
        while (i <= mid) {
            sorted[k++] = a[i++];
        }
        while (j <= high) {
            sorted[k++] = a[j++];
        }
        for (int m = 0; m < sorted.length; m++) {
            a[low + m] = sorted[m];
        }
    }

    //2. Quicksort
    public static void quickSort(int[] a, int begin, int end) {
        if (begin < end) {
            int key = a[begin];
            int i = begin;
            int j = end;
            while (i < j) {
                while (i < j && a[j] > key) {
                    j--;
                }
                if (i < j) {
                    a[i] = a[j];
                    i++;
                }
                while (i < j && a[j] < key) {
                    i++;
                }
                if (i < j) {
                    a[j] = a[i];
                    j--;
                }
            }
            a[i] = key;
            quickSort(a, begin, i - 1);
            quickSort(a, i + 1, end);
        }
    }

    //3. Insertion sort
    public static void insertionSort(int[] a) {
        int length = a.length;

        for (int i = 1; i < length; i++) {
            int temp = a[i];
            int j = i;
            for (; j > 0 && a[j - 1] > temp; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;
        }
    }

    //4. Selection sort
    public static void selectionSort(int[] a) {
        int length = a.length;
        for (int i = 0; i < length; i++) {
            int min = i;
            for (int j = i + i; j < length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = a[min];
                a[min] = a[i];
                a[i] = temp;
            }
        }
    }

    //5. Heapsort
    public static void heapSort(int[] a) {
        int length = a.length;
        for (int i = 1; i < length; i++) {
            makeHeap(a, i);
        }
        for (int i = a.length - 1; i > 0; i--) {
            int temp = a[i];
            a[i] = a[0];
            a[0] = temp;
            rebuildHeap(a, i);
        }
    }

    private static void makeHeap(int[] a, int cur) {
        while (cur > 0 && a[cur] > a[(cur - 1) / 2]) {
            int temp = a[cur];
            a[cur] = a[(cur - 1) / 2];
            a[(cur - 1) / 2] = temp;
            cur = (cur - 1) / 2;
        }
    }

    private static void rebuildHeap(int[] a, int length) {
        int cur = 0;
        int right = cur * 2 + 2;
        int left = cur * 2 + 1;
        int max = cur;
        boolean isHeap = false;
        while (!isHeap) {
            if (left < length && a[cur] < a[left]) {
                max = left;
            }
            if (right < length && a[max] < a[right]) {
                max = right;
            }
            if (cur == max) {
                isHeap = true;
            } else {
                int temp = a[cur];
                a[cur] = a[max];
                a[max] = temp;
                cur = max;
                right = cur * 2 + 2;
                left = cur * 2 + 1;
            }
        }
    }

    //Knuth random shuffle
    public static void shuffle(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            // choose index uniformly in [0, i]
            int r = (int) (Math.random() * (i + 1));
            int swap = a[r];
            a[r] = a[i];
            a[i] = swap;
        }
    }

    //main method
    public static void main(String[] args) {

        for (int size = 420000; size <= 1000000; size = size + 10000) {
            
            System.out.println("Selection sort: size" + size + " nanotime");
            //Random generate an array of 'size'
            int[] a = new int[size];
            Random r = new Random(size);
            for (int i = 0; i < size; i++) {
                a[i] = r.nextInt(size);
            }
            
            for (int i = 0; i < 10; i++) {
                long startTime = System.nanoTime();
                Sort.selectionSort(a);
                long runTime = System.nanoTime() - startTime;
                System.out.println(runTime);
                if (i < 10) {
                    Sort.shuffle(a);
                }
            }
        }

    }
}
