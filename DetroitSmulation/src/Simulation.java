/**
 * Created by chengqian on 1/22/17.
 */
import java.io.*;
import java.util.*;


public class Simulation {
    private final int ZERO = 0;
    private final int GOOD = 1;         //opinion one
    private final int FAIR = 2;         //opinion two
    private final int POOR = 3;         //opinion three
    private final int DEMO = 4;         //opinion four
    private final int VAC = 5;          //opinion five

    private int[][] array;
    private int size;
    private int iterations;
    private Random random;
    private DrawOpinion drawer;

    private final double[][] changeRate = {
            {86.37, 8.42, 1.67, 0.73, 2.80},
            {52.29, 24.82, 8.74, 3.55, 10.60},
            {22.99, 25.25, 16.54, 7.67, 27.55},
            {7.09, 10.24, 11.94, 14.50, 56.24},
            {4.76, 1.18, 0.60, 0.49, 92.97},
    };

    public Simulation (int size, int iterations, int seed)
    {
        this.array = new int[size][size];   //create array
        this.size = size;
        this.iterations = iterations;
        this.random = new Random(seed);
        this.drawer = new DrawOpinion(array, size);
    }

    //Method runs simulation
    public void run() throws IOException
    {
        initialize();

        for (int n = 0; n < iterations; n++)
        {
            draw();

            for (int m = 0; m < size*size; m++)
            {
                int i = random.nextInt(size);
                int j = random.nextInt(size);

                if (array[i][j] == 0)
                {
                    array[i][j] = 0;
                } else {
                    array[i][j] = imitate(i, j);
                }
            }
        }
    }

    private void initialize() throws IOException
    {
        Scanner inFile = new Scanner(new File("outputFile"));

        for (int i = 0; i < size; i++)
            for (int j = 0; j < size; j++)
            {
                array[i][j] = inFile.nextInt();
            }

        inFile.close();
    }

    private int imitate(int i, int j)
    {
        int popular;
        int next;
        double nextDouble;
        int before = array[i][j];
        int itemSouth = array[(i+1)%size][j];
        int itemNorth = array[(i-1+size)%size][j];
        int itemEast = array[i][(j+1)%size] ;
        int itemWest = array[i][(j-1+size)%size];
        int itemNW = array[(i-1+size)%size][(j-1+size)%size];
        int itemNE = array[(i-1+size)%size][(j+1)%size];
        int itemSW = array[(i+1)%size][(j-1+size)%size];
        int itemSE = array[(i+1)%size][(j+1)%size];

        int a[] = new int[8];

        a[0] = itemSouth;
        a[1] = itemNorth;
        a[2] = itemEast;
        a[3] = itemWest;
        a[4] = itemNW;
        a[5] = itemNE;
        a[6] = itemSW;
        a[7] = itemSE;

        popular = count(a);

        //上下变化一个
        if (popular-before > 0)
            next = before+1;
        else if (popular == before || popular == 0)
            next = before;
        else
            next = before-1;


        double rand = random.nextDouble();
        nextDouble = changeRate[before-1][next-1]/100;

        if (rand < nextDouble)
            return next;
        else
            return before;


        /* //变化率+找最多
        double rand = random.nextDouble();
        next = changeRate[before-1][popular-1]/100;

        if (rand < next)
            return popular;
        else
            return before;
        */
    }

    private int count (int[] a)
    {
        int count = 1, tempCount;
        int popular = a[0];
        int temp = 0;
        for (int i = 0; i < (a.length - 1); i++)
        {
            temp = a[i];
            tempCount = 0;
            for (int j = 1; j < a.length; j++)
            {
                if (temp == a[j])
                    tempCount++;
            }
            if (tempCount > count)
            {
                popular = temp;
                count = tempCount;
            }
        }
        return popular;
    }

    private void draw()
    {
        drawer.repaint();

        pause(100);
    }

    private void pause(int milliseconds)
    {
        try
        {
            Thread.sleep(milliseconds);
        }
        catch(InterruptedException e)
        {
            System.exit(0);
        }
    }
}