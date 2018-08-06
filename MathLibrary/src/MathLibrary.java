/**
 * Created by chengqian on 1/20/17.
 */
//collection of basic data analysis functions
public class MathLibrary {
    /*******************************************************************************************************/

    //computes sum of values
    public static double sum(double[] array)
    {
        double total = 0;                       //initialization

        for (int i = 0; i < array.length; i++)
            total = total + array[i];           //summation of values

        return total;                           //sum
    }

    /*******************************************************************************************************/

    //computes mean of values
    public static double mean(double[] array)
    {
        return sum(array)/array.length;         //sum
    }

    /*******************************************************************************************************/

    //computes standard deviatioin of values
    public static double stdev(double[] array)
    {
        double mean = mean = mean(array);       //mean of values

        double sum = 0;                         //sum of squares of differences between values and mean
        for (int i = 0; i < array.length; i++)
            sum = sum + (array[i] - mean) * (array[i] - mean);

        sum = sum/array.length;                 //average of sum of squares

        return Math.sqrt(sum);                  //standard deviation
    }

    /*******************************************************************************************************/

    //computes minimum of values
    public static double minimum(double[] array)
    {
        double min = array[0];                  //initial minimum

        for (int i = 0; i < array.length; i++)  //go thru values if smaller value is found change minimum
            if (array[i] < min)
                min = array[i];

        return min;                             //minimum
    }

    /*******************************************************************************************************/

    //computes maximum of values
    public static double maximum(double[] array)
    {
        double max = array[0];                  //initial maximum

        for (int i = 0; i < array.length; i++)  //go thru values if larger value is found change maximum
            if (array[i] > max)
                max = array[i];

        return max;
    }

    /*******************************************************************************************************/

    //computes median of values
    public static double median(double[] array)
    {
        sort(array);                            //sort values

        if (array.length % 2 == 1)              //if odd number of values median is middle value
            return array[array.length/2];
        else                                    //otherwise median is average of two middle values
            return (array[array.length/2-1] + array[array.length/2])/2;
    }

    /*******************************************************************************************************/

    //computes percentile of values
    public static double percentile(double[] array, double percent)
    {
        sort(array);                            //sort values

        return array[(int)Math.ceil(array.length*percent/100)-1];   //find percentile
    }

    /*******************************************************************************************************/

    //sorts values in ascending order
    public static void sort(double[] array)
    {
        for (int i = 0; i < array.length; i++)      //compare each value with each value on the right
            for (int j = i+1; j < array.length; j++)
                if (array[i] > array[j])
                {
                    double temp = array[i];         //if they are in wrong order swap them
                    array[i] = array[j];
                    array[j] = temp;
                }
    }

    /*******************************************************************************************************/

    //computes correlation between two sets of values
    public static double correlation(double[] u, double[] v)
    {
        double mean1 = mean(u);                 //means of twos sets
        double mean2 = mean(v);
        double stdev1 = stdev(u);               //standard deviations of two sets
        double stdev2 = stdev(v);

        double sum = 0;                         //covariance of two sets
        for (int i = 0; i < u.length; i++)
            sum = sum + (u[i] - mean1)*(v[i] - mean2);
        sum = sum/u.length;

        return sum/(stdev1*stdev2);             //correlation coefficient
    }

    /*******************************************************************************************************/

    //computes euclidean distance between two points
    public static double euclidean(double[] u, double[] v)
    {
        double sum = 0;                         //sum of squares of coordinate differences
        for (int i = 0; i < u.length; i++)      //coordinate differences
            sum = sum + (u[i] - v[i])*(u[i] - v[i]);

        return Math.sqrt(sum);                  //distance
    }

    //computes euclidean distance between two points
    public static double euclidean(int[] u, int[] v)
    {
        double sum = 0;                         //sum of squares of coordinate differences
        for (int i = 0; i < u.length; i++)      //coordinate differences
            sum = sum + (u[i] - v[i])*(u[i] - v[i]);

        return Math.sqrt(sum);                  //distance
    }
    /*******************************************************************************************************/

    //computes taxi cab distance between two points
    public static double taxi(double[] u, double[] v)
    {
        double sum = 0;                         //sum of absolute values of
        for (int i = 0; i< u.length; i++)       //coordinate differences
            sum = sum + Math.abs(u[i] - v[i]);

        return sum;                             //distance
    }

    public static double taxi(int[] u, int[] v)
    {
        double sum = 0;                         //sum of absolute values of
        for (int i = 0; i< u.length; i++)       //coordinate differences
            sum = sum + Math.abs(u[i] - v[i]);

        return sum;                             //distance
    }
    /*******************************************************************************************************/

    //computes cosine coefficient between two vectors
    public static double cosine(double[] u, double[] v)
    {
        double product = 0;                     //initialization
        double length1 = 0;
        double length2 = 0;

        for (int i = 0; i < u.length; i++)
        {
            product = product + u[i]*v[i];      //dot product
            length1 = length1 + u[i]*u[i];      //vector length
            length2 = length2 + v[i]*v[i];      //vector length
        }

        length1 = Math.sqrt(length1);           //length of vectors
        length2 = Math.sqrt(length2);

        return product/(length1*length2);       //cosine coefficient
    }

    /*******************************************************************************************************/

    //computes matching coefficient between two binary vectors
    public static double matching(int[] u, int[] v)
    {
        int matches = 0;

        for (int i = 0; i < u.length; i++)      //number of 00, 11 matches
            if (u[i] == v[i])
                matches = matches + 1;

        return (double)matches/u.length;        //mathcing coefficient
    }

    /*******************************************************************************************************/

    //computes jacard coefficient between two vectors
    public static double jacard(int[] u, int[] v)
    {
        int zeroMatches = 0;
        int oneMatches = 0;

        for (int i = 0; i < u.length; i++)
        {
            if (u[i] == 0 && v[i] == 0)         //number of 00 matches
                zeroMatches = zeroMatches + 1;

            if (u[i] == 1 && v[i] == 1)
                oneMatches = oneMatches + 1;    //number of 11 matche
        }

        return (double)oneMatches/(u.length - zeroMatches);     //jacard coefficient
    }

    /*******************************************************************************************************/

    public static double logTwo(double a){
        double result;
        result = Math.log(a)/Math.log((double)2);

        return result;
    }
}
