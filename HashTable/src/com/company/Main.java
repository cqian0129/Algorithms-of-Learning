package com.company;
import java.util.Random;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
	// write your code here
        int[] a = {35,20,15,55,60,80,40};

        HashTableChain h = new HashTableChain();

        for (int i = 0; i < a.length; i++)
        {
            h.insert(String.valueOf(a[i]),null);
        }

        for (int i = 0; i < a.length; i++)
        {
            System.out.println(((a[i] % 13)*(a[i] % 13)+5)%10);
        }
        /*while (true) {
            System.out.println("Enter N value and seed value: ");
            Scanner scanner = new Scanner(System.in);
            int N = scanner.nextInt();
            int seed = scanner.nextInt();

            Random r = new Random(seed);

            //generate serial number
            String[] serialNum = new String[N];
            for (int i = 0; i < N; i++)
                serialNum[i] = " ";
            for (int i = 0; i < N; i++){
                char UpLetter_first = (char)((int)'A'+r.nextDouble()*((int)'Z'-(int)'A'+1));
                char UpLetter_sec = (char)((int)'A'+r.nextDouble()*((int)'Z'-(int)'A'+1));
                char LowLetter_first = (char)((int)'a'+r.nextDouble()*((int)'z'-(int)'a'+1));
                char LowLetter_sec = (char)((int)'a'+r.nextDouble()*((int)'z'-(int)'a'+1));
                char Num_first = (char)(r.nextInt(9)+48);
                char Num_sec = (char)(r.nextInt(9)+48);
                serialNum[i] = serialNum[i] + UpLetter_first + UpLetter_sec + Num_first + Num_sec + LowLetter_first + LowLetter_sec;
            }
            //generate serial number

            HashTableChain h = new HashTableChain();

            //insert the N randomly constructed serial numbers
            for (int i = 0; i < N; i++)
            {
                h.insert(serialNum[i], null);
            }

            double averageLength = h.averageLen();

            System.out.println("average length: " + averageLength);

            //search
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < N; i++)
            {
                h.find(serialNum[i]);
            }
            long endTime = System.currentTimeMillis();
            double totalTime = (double) (endTime - startTime)/N;
            System.out.println("average search time: " + totalTime + " ms");

        }*/
    }
}
