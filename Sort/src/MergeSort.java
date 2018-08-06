/**
 * Created by young on 2017/2/3.
 */
public class MergeSort {
    //sort method
    public int count_mergesort = 0;
    public int count_merge = 0;
    public void sort(int[] a)
    {
        mergeSort(a, 0, a.length-1);                //initial recursive call
        System.out.println(count_mergesort);
        System.out.println(count_merge);
    }

    //recursive merge sort method, sorts array a[] between low and high
    private void mergeSort(int[] a, int low, int high)
    {
        count_mergesort++;
        if (low < high)                                       //if array length is at least two
        {
            int mid = (low + high) / 2;                         //find mid point
            mergeSort(a, low, mid);                             //sort left half recursively


            mergeSort(a, mid+1, high);                      //sort right half recursively
            merge(a, low, mid, high);                           //merge two sorted halves
            count_merge++;
        }
    }

    //merges two sorted halves of array a[], left sorted half is between
    //low and mid, right sorted half is between mid+1 and high
    private static void merge(int[] a, int low, int mid, int high)
    {
        int[] tmp = new int[high-low+1];                        //temporary array
        int p = low;                                            //p traverses left half
        int q = mid+1;                                          //q traverses right array
        int r = 0;                                              //r traverses tmp array

        while (p <= mid && q <= high)                           //while left and right half has elements
        {
            if (a[p] < a[q])                                    //copy smaller of left or right half
                tmp[r++] = a[p++];                              //element into imp array
            else
                tmp[r++] = a[q++];
        }

        if (p > mid)                                            //if left half is finished and right
        {                                                       //half is not finished then copy the
            while (q <= high)                                   //rest of right half into tmp array
                tmp[r++] = a[q++];
        }
        else
        {
            while (p <= mid)                                    //if right half is finished and left
                tmp[r++] = a[p++];                              //half is not finished then copy the
        }                                                       //rest of left half into tmp array

        for (p = 0; p < high-low+1; p++)                        //copy elements from tmp array into
            a[low+p] = tmp[p];                                  //original array a[]
    }
}


//Total run time - O(nlogn) in all cases
//Requires additional array during merge operation
