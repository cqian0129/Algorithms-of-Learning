package com.company;

/**
 * Created by blachcat on 2/25/17.
 */
//insertion sort algorithm
public class InsertionSort
{
    //sort method
    public static void sort(int[] a)
    {
        int com = 0;
        int data = 0;
        int i, j;
        int temp;

        for (i = 0; i < a.length; i++)			//outer loop
        {
            temp = a[i];						//save element that needs insertion
            data++;
            for (j = i-1; j>= 0 && temp < a[j]; j--) {
                com++;
                a[j + 1] = a[j];                    //shift elements as necessary
                data++;
            }

            a[j+1] = temp;						//insert at the appropriate location
            data++;
        }
        System.out.println("data: " + data);
        System.out.println("com: " + com);
    }
}
