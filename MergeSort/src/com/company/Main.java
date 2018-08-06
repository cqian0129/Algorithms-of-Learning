package com.company;

import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        while (true) {
            System.out.println("Enter N value and seed value: ");
            Scanner scanner = new Scanner(System.in);
            int N = scanner.nextInt();
            int seed = scanner.nextInt();
            MergeSort s = new MergeSort();

            Random r = new Random(seed);
            int[] a = new int[N];
            for (int i = 0; i < N; i++) {
                a[i] = r.nextInt(1000000000);       //10^9
            }
            long startTime = System.currentTimeMillis();
            s.sort(a);
            long endTime = System.currentTimeMillis();
            //print the array
            /*
            for (int i = 0; i < a.length; i++) {
                System.out.println(a[i]);
            }
            */
            long totalTime = endTime - startTime;
            System.out.println("sorting time : " + totalTime + "ms");
        }
    }
}
