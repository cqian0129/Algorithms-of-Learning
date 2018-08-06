import java.io.*;
import java.util.*;

//Program finds optimal scheduling of multiple jobs on single machine
//that minimizes average wait time
public class wait
{
	/**********************************************************************************************************/

	private int size;						//number of jobs
	private int[] job;						//array of jobs
	private int[] time;						//execution time of jobs
	private double averageWait;				//average wait time

	/**********************************************************************************************************/

	//Constructor of wait class
	public wait(String inputFile) throws IOException
	{
		//open input file
		Scanner inFile = new Scanner(new File(inputFile));

		//read number of jobs
		size = inFile.nextInt();

		//create arrays of jobs and execution times
		job = new int[size];
		time = new int[size];

		//read jobs and execution times into arrays
		for (int i = 0; i < size; i++)
		{
			job[i] = inFile.nextInt();
			time[i] = inFile.nextInt();
		}
	}

	/**********************************************************************************************************/

	//Method finds optimal scheduling
	public void solve()
	{
		//sort jobs and execution times in ascending order of execution times
		sort();

		//find average wait time of jobs
		average();

		//display solution
		display();
	}

	/**********************************************************************************************************/

	//Method sorts jobs and execution times in the ascending order of exection times
	private void sort()
	{
		for (int i = 0; i < size; i++)
			for (int j = i+1; j < size; j++)
			{
				if (time[i] > time[j])
				{
					swap(i, j, job);
					swap(i, j, time);
				}
			}
	}

	/**********************************************************************************************************/

	//Method finds average wait time of jobs
	private void average()
	{
		//array of wait times jobs
		int[] wait = new int[size];

		//find wait times of jobs from their execution times and order
		wait[0] = time[0];
		for (int i = 1; i < size; i++)
			wait[i] = wait[i-1] + time[i];

		//find sum of wait times
		int totalWait = 0; 
		for (int i = 0; i < size; i++)
			totalWait = totalWait + wait[i];

		//find average wait tiem
		averageWait = (double)totalWait/size;
	}

	/**********************************************************************************************************/

	//Method swaps elemetns located at two indices of an array
	private void swap(int i, int j, int[] array)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**********************************************************************************************************/

	//Method displays jobs, execution times, and average wait time
	private void display()
	{
		//display jobs and execution times
		for (int i = 0; i < size; i++)
			System.out.println(job[i] + " " + time[i]);

		//display average wait time
		System.out.println(averageWait);
	}
}