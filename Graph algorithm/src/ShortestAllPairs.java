/**
 * Created by chengqian on 4/4/17.
 */
import sun.awt.image.ImageWatched;

import java.io.*;
import java.util.*;


//Program finds shortest path lengths between all pairs of vertices in
// a directed graph (Floyd algorithm)
public class ShortestAllPairs
{
    /********************************************************************/

    private int size;                   //number of vertices
    private int[][] matrix;             //adjacency matrix
    private int[][] result;             //matrix containing shortest
                                        // path lengths
    private int[][] verticesGoThru;

    private final int INFINITY = 999999;    //constant represents infinity

    /********************************************************************/

    //Constructor of ShortestAllPairs class, loads adjacency matrix
    // from a file
    public ShortestAllPairs(String inputFile) throws IOException
    {
        //open input file
        Scanner inFile = new Scanner(new File(inputFile));

        //read number of vertices
        size = inFile.nextInt();

        //read adjacency matrix
        matrix = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
                matrix[i][j] = inFile.nextInt();

        verticesGoThru = new int[size][size];
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                verticesGoThru[i][j] = 0;
            }
    }

    /********************************************************************/

    //Method computes shortest path lengths
    public void compute()
    {
        int i, j, k;

        //create two temporary matrices
        int[][] p = new int[size][size];
        int[][] q = new int[size][size];



        //modify adjacency matrix by adding infinity
        modify(matrix, p);

        //perform vertices number of iterations
        for (k = 0; k < size; k++)
        {
            //compute next matrix from current matrix
            for (i = 0; i < size; i++)
                for (j = 0; j < size; j++) {
                    q[i][j] = minimum(p[i][j], p[i][k] + p[k][j]);

                    //if need to change (verticesGoThru Matrix)
                    if (q[i][j] == p[i][k] + p[k][j] && q[i][j] < p[i][j])
                    {
                        verticesGoThru[i][j] = k+1;
                    }
                }

            //next matrix becomes current matrix
            for (i = 0; i < size; i++)
                for (j = 0; j < size; j++)
                    p[i][j] = q[i][j];
        }

        //answer is current matrix
        result = p;
    }

    /********************************************************************/

    //Method prints matrix of shortest path lengths to a file
    public void display(String outputFile) throws IOException
    {
        //open output file
        PrintWriter outFile = new PrintWriter(new FileWriter(outputFile));

        //print matrix of shortest path lengths
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                if (result[i][j] != 0)
                {
                    if (verticesGoThru[i][j] == 0)
                    {
                        int start = i+1;
                        int end = j+1;
                        int length = result[i][j];

                        outFile.println(start + " : " + end + " length: " + length + " | " + start + " --> " + end);
                    }
                    else
                    {
                        int start = i+1;
                        int end = j+1;
                        int length = result[i][j];
                        int point_index = verticesGoThru[i][j] - 1;

                        outFile.print(start + " : " + end + " length: " + length + " | " + start + " --> ");
                        while(verticesGoThru[i][point_index] != 0)
                        {
                            outFile.print(verticesGoThru[i][point_index] + " --> ");
                            point_index = verticesGoThru[i][point_index] - 1;
                        }

                        outFile.println(verticesGoThru[i][j] + " --> " + end);
                    }
                }
            }
        }

        //close file
        outFile.close();
    }

    /********************************************************************/

    //Method modifies adjacency matrix by placing infinities
    private void modify(int[][] matrix, int[][] result)
    {
        //go thru adjacency matrix
        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                //if not diagonal and zero
                if (i != j&& matrix[i][j] == 0)
                    result[i][j] = INFINITY;        //replace by infinity
                else
                    result[i][j] = matrix[i][j];    //otherwise no change
            }
    }

    /********************************************************************/

    //Method finds minimum of two integers
    private int minimum(int a, int b)
    {
        if (a <= b)
            return a;
        else
            return b;
    }

    /********************************************************************/
}
