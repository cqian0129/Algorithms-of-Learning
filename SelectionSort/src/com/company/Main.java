package com.company;
import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        SelectionSort s = new SelectionSort();

        int[] a = {50,40,30,10,20};
        s.sort(a);

        /*
        while (true) {
            System.out.println("Enter the input size: ");
            Scanner scanner = new Scanner(System.in);
            int N = scanner.nextInt();

            SelectionSort s = new SelectionSort();

            Random r = new Random(123411);
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = r.nextInt(1000000000);       //10^9
            }
            long startTime = System.currentTimeMillis();
            s.sort(a);
            long endTime = System.currentTimeMillis();
            //print the array

            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
            long totalTime = endTime - startTime;
            System.out.println("sorting time : " + totalTime + "ms");
        }
        */
    }
}
