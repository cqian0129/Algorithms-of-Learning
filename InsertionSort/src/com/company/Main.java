package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        InsertionSort i = new InsertionSort();
        int[] a = {50,30,40,60,20,70,10};
        i.sort(a);
        /*while (true) {
            System.out.println("Enter N value and seed value: ");
            Scanner scanner = new Scanner(System.in);
            int N = scanner.nextInt();
            int seed = scanner.nextInt();
            InsertionSort s = new InsertionSort();

            Random r = new Random(seed);
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = r.nextInt(1000000000);       //10^9
            }
            long startTime = System.currentTimeMillis();
            s.sort(a);
            long endTime = System.currentTimeMillis();
            //print the array
            long totalTime = endTime - startTime;
            System.out.println("sorting time : " + totalTime + "ms");
        }
        */
    }
}
