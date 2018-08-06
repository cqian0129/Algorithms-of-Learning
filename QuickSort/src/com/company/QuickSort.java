package com.company;

/**
 * Created by blachcat on 2/26/17.
 */
/**
 * Created by young on 2017/2/3.
 */
//Quick sort algorithm
public class QuickSort {
    //sort method
    public static void sort(int[] a) {
        quickSort(a, 0, a.length - 1);        //initial recursive call
    }

    //recursive quick sort method, sorts array a[] between low and high
    private static void quickSort(int[] a, int low, int high) {
        if (low < high)                               //if array length is at least two
        {
            int mid = partition(a, low, high);          //partition the array
            quickSort(a, low, mid - 1);               //sort left half recursively
            quickSort(a, mid + 1, high);              //sort right half recursively
        }
    }

    //partitions array a[] between low and high, uses value at the beginning
    //of array as pivot value, returns location of pivot value after partition
    private static int partition(int[] a, int low, int high) {
        int temp = low;

        for (int i = low + 1; i <= high; i++)              //loop that builds partition
            if (a[i] > a[low]) {                                           //if value is smaller than pivot
                temp = temp + 1;                        //value then insert into left half
                swap(a, i, temp);
            }                                           //if value is greater or equal to
        //pivot value then insert int right half
        //right half

        swap(a, low, temp);                             //finally move pivot value to
        return temp;                                    //the middle of array and return
    }                                                   //the location of pivot value

    //swaps the two elements of array located at i and j
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
//Best/average case = O(nlogn)
//Partition typically divides array into equal halves
//Unsorted or randomized array
//T(n) = n + T(n-1) + T(1)

//Wort case = O(n^2)
//Partition may not divide array into equal halves
//Sorted array
//T(n) = n + T(n-1) + T(1)

//Does not require additional array during partition, in place sorting
