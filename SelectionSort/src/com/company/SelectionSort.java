package com.company;

/**
 * Created by blachcat on 2/25/17.
 */
//selection sort algorithm
public class SelectionSort
{

    //sort method
    public static void sort(int[] a)
    {
        int count_com = 0;
        int count_data = 0;
        int i, j, min;
        int temp;

        for (i = 0; i < a.length; i++)		//outer loop
        {
            min = i; 						//initial location of maximum

            for (j = i+1; j < a.length; j++)		//inner loop
            {
                if (a[j] < a[min])            //if new maximum is found
                {
                    min = j;                //then update location of maximum
                }
                count_com++;
            }

            temp = a[i];
            count_data++;

            a[i] = a[min];					//swap the maximum and outer
            count_data++;

            a[min] = temp;					//loop element
            count_data++;
        }

        System.out.println("count_compare: " + count_com);
        System.out.println("count_data: " + count_data);

    }
}
