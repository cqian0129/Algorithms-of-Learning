/**
 * Created by chengqian on 1/30/17.
 */
import java.io.*;
import java.util.*;

public class MatrixTime {
    public static void main(String[] args) throws IOException {
        String hourSet = "23";
        BufferedReader data = new BufferedReader(new FileReader(new File("201606_table_" + hourSet +".csv")));
        Scanner inFile = new Scanner(new File("src/stopNum.txt"));
        PrintWriter outFile_00 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_00.txt"));
        PrintWriter outFile_05 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_05.txt"));
        PrintWriter outFile_10 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_10.txt"));
        PrintWriter outFile_15 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_15.txt"));
        PrintWriter outFile_20 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_20.txt"));
        PrintWriter outFile_25 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_25.txt"));
        PrintWriter outFile_30 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_30.txt"));
        PrintWriter outFile_35 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_35.txt"));
        PrintWriter outFile_40 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_40.txt"));
        PrintWriter outFile_45 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_45.txt"));
        PrintWriter outFile_50 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_50.txt"));
        PrintWriter outFile_55 = new PrintWriter(new FileWriter("Matrix/matrix_" + hourSet + "_55.txt"));

        int size = 102;
        int[][] stopNum = new int[102][3];
        int[][] matrix_00 = new int[size][size];
        int[][] matrix_05 = new int[size][size];
        int[][] matrix_10 = new int[size][size];
        int[][] matrix_15 = new int[size][size];
        int[][] matrix_20 = new int[size][size];
        int[][] matrix_25 = new int[size][size];
        int[][] matrix_30 = new int[size][size];
        int[][] matrix_35 = new int[size][size];
        int[][] matrix_40 = new int[size][size];
        int[][] matrix_45 = new int[size][size];
        int[][] matrix_50 = new int[size][size];
        int[][] matrix_55 = new int[size][size];


        for (int i = 0; i < 102; i++)
        {
            stopNum[i][0] = i+1;
            stopNum[i][1] = inFile.nextInt();
            stopNum[i][2] = inFile.nextInt();
        }
        inFile.close();

        int errCount = 0;
        for (int i = 0; i < 102; i++)
            for (int j = 0; j < 102; j++) {
                matrix_00[i][j] = 0;
                matrix_05[i][j] = 0;
                matrix_10[i][j] = 0;
                matrix_15[i][j] = 0;
                matrix_20[i][j] = 0;
                matrix_25[i][j] = 0;
                matrix_30[i][j] = 0;
                matrix_35[i][j] = 0;
                matrix_40[i][j] = 0;
                matrix_45[i][j] = 0;
                matrix_50[i][j] = 0;
                matrix_55[i][j] = 0;
            }

        String line = "";
        while ((line = data.readLine()) != null) {
            int[] str = new int[4];

            for (int i = 0; i < 4; i++){
                String[] value = line.split(",", 4);
                String value_sec = value[0].replaceAll("\"","");
                String value_third = value[1].replaceAll("\"","");

                str[0] = Integer.parseInt(value_sec);
                str[1] = Integer.parseInt(value_third);
                str[2] = Integer.parseInt(value[2]);
                str[3] = Integer.parseInt(value[3]);

            }

            if (findIndexOn(stopNum, str[0]) == -1 || findIndexOff(stopNum, str[1]) == -1){
                System.out.println("1:" + str[0]);
                System.out.println("2:" + str[1]);
                errCount++;
                continue;
            }

            if (str[3] >= 0 && str[3] < 5){
                matrix_00[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 5 && str[3] < 10) {
                matrix_05[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 10 && str[3] < 15) {
                matrix_10[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 15 && str[3] < 20) {
                matrix_15[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 20 && str[3] < 25) {
                matrix_20[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 25 && str[3] < 30) {
                matrix_25[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 30 && str[3] < 35) {
                matrix_30[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 35 && str[3] < 40) {
                matrix_35[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 40 && str[3] < 45) {
                matrix_40[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 45 && str[3] < 50) {
                matrix_45[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 50 && str[3] < 55) {
                matrix_50[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else if (str[3] >= 55 && str[3] < 60) {
                matrix_55[findIndexOn(stopNum, str[0])][findIndexOff(stopNum, str[1])]++;
            } else {
                errCount++;
            }
        }
        for (int i = 0; i < 102; i++) {
            for (int j = 0; j < 102; j++) {
                outFile_00.print(matrix_00[i][j] + " ");
                outFile_05.print(matrix_05[i][j] + " ");
                outFile_10.print(matrix_10[i][j] + " ");
                outFile_15.print(matrix_15[i][j] + " ");
                outFile_20.print(matrix_20[i][j] + " ");
                outFile_25.print(matrix_25[i][j] + " ");
                outFile_30.print(matrix_30[i][j] + " ");
                outFile_35.print(matrix_35[i][j] + " ");
                outFile_40.print(matrix_40[i][j] + " ");
                outFile_45.print(matrix_45[i][j] + " ");
                outFile_50.print(matrix_50[i][j] + " ");
                outFile_55.print(matrix_55[i][j] + " ");
            }
            outFile_00.println();
            outFile_05.println();
            outFile_10.println();
            outFile_15.println();
            outFile_20.println();
            outFile_25.println();
            outFile_30.println();
            outFile_35.println();
            outFile_40.println();
            outFile_45.println();
            outFile_50.println();
            outFile_55.println();
        }
        System.out.println(errCount);
        outFile_00.close();
        outFile_05.close();
        outFile_10.close();
        outFile_15.close();
        outFile_20.close();
        outFile_25.close();
        outFile_30.close();
        outFile_35.close();
        outFile_40.close();
        outFile_45.close();
        outFile_50.close();
        outFile_55.close();
    }

    public static int findIndexOn (int[][] stopNum, int a) {
        for (int i = 0; i < 102; i++)
        {
            if (stopNum[i][1] == a)
                return i;
        }
        return -1;
    }

    public static int findIndexOff (int[][] stopNum, int a) {
        for (int i = 0; i < 102; i++)
        {
            if (stopNum[i][2] == a)
                return i;
        }
        return -1;
    }
}
