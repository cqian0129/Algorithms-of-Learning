/**
 * Created by young on 2017/2/2.
 */
public class HeapSort {
    //sort method
    public static void sort(int[] a) {
        Heap h = new Heap(a.length);            //create empty min heap

        for (int i = 0; i < a.length; i++)      //insert array elements into
            h.insert(a[i]);                     //min heap

        for (int i = 0; i < a.length; i++)      //delete min elements from heap
            a[i] = h.deletemin();               //and insert into array
    }
}

//Total run time- O(nlogn) in all cases

//Run time = n insertions each costing logn = n deletions each costing logn

//Run time = nlogn + nlogn = 2nlogn = O(nlogn)

//Uses additional space for heap
