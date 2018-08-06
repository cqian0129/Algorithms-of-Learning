import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.*;
import java.util.*;
/**
 * Created by chengqian on 1/30/17.
 */
public class InquireMatrix {
    public static void main(String[] args) throws IOException {
        BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outFile = new PrintWriter(new FileWriter("matrix.txt"));
        String startH, startM, endH, endM;

      //  while (true) {
            System.out.println("请输入起始时间:");
            String time1 = strin.readLine();
            System.out.println("请输入终点时间:");
            String time2 = strin.readLine();

            String[] value = time1.split(":", 2);
            String[] valueEnd = time2.split(":", 2);
            startH = value[0];
            startM = value[1];
            endH = valueEnd[0];
            endM = valueEnd[1];

            int sizeStart = Integer.parseInt(startH)*12 + Integer.parseInt(startM)/5;
            int sizeEnd = Integer.parseInt(endH)*12 + Integer.parseInt(endM)/5;

            //int size = sizeStart - sizeEnd;
            //System.out.println(-size);

            int start = sizeStart;

            int[][] matrix = new int[102][102];
            for (int i = 0; i < 102; i++)
                for (int j = 0; j < 102; j++)
                    matrix[i][j] = 0;

            int count = 0;
            while (start < sizeEnd){
                Scanner inFile = new Scanner(new File("Matrix/matrix_" + inttoString(start/12) + "_" + inttoString((start%12)*5) + ".txt"));
                int[][] temp = new int[102][102];
                for (int i = 0; i < 102; i++)
                    for (int j = 0; j < 102; j++)
                        temp[i][j] = inFile.nextInt();

                matrix = matrixSum(matrix, temp);

                inFile.close();

                start++;
                count++;
            }

            //System.out.println("count" + count);

            for (int i = 0; i < 102; i++)
            {
                for (int j = 0; j < 102; j++){
                    outFile.print(matrix[i][j] + " ");
                }
                outFile.println();
            }

            outFile.close();
            System.out.println("Finished!");
        //}



    }

    public static String inttoString (int a) {
        String temp;

        if (a < 10)
        {
            temp = "0" + Integer.toString(a);
        } else {
            temp = Integer.toString(a);
        }
        return temp;
    }

    public static int[][] matrixSum (int[][] a, int[][] b) {
        int[][] temp = new int[102][102];
        for (int i = 0; i < 102; i++)
            for (int j = 0; j < 102; j++)
                temp[i][j] = 0;

        for (int i = 0; i < 102; i++)
            for (int j = 0; j < 102; j++)
            {
                temp[i][j] = a[i][j] + b[i][j];
            }

        return temp;
    }
}