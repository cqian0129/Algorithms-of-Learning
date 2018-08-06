package com.company;

/**
 * Created by blachcat on 2/26/17.
 */
//heap sort algorithm
public class HeapSort
{
    //sort method
    public static void sort(int[] a)
    {
        Heap h = new Heap(a.length);			//create empty min heap

        for (int i = 0; i < a.length; i++)		//insert array elements into
            h.insert(a[i]);						//min heap

        for (int i = 0; i < a.length; i++)		//delete min elements from heap
            a[i] = h.deletemin();				//and insert into array
    }
}
