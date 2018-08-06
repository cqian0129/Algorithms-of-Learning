import java.io.*;
import java.util.*;

//Program solves fractional knapsack problem
public class knapsack
{
	/**********************************************************************************************************/
	
	private int size;					//number of items
	private int maxWeight;				//maximum weight

	private int[] item;					//array of items
	private int[] weight;				//item weights
	private int[] value;				//item values

	private int totalWeight;			//total weight and value
	private double totalValue;			//of optimal solution

	/**********************************************************************************************************/

	//Constructor of Knapsack class
	public knapsack(String inputFile) throws IOException
	{
		//open input file
		Scanner inFile = new Scanner(new File(inputFile));

		//read number of items
		size = inFile.nextInt();

		//create array of items, weights, values
		item = new int[size];
		weight = new int[size];	
		value = new int[size];

		//read items, weights, values into arrays
		for (int i = 0; i < size; i++)
		{
			item[i] = inFile.nextInt();
			weight[i] = inFile.nextInt();
			value[i] = inFile.nextInt();
		}

		//read maximum weight
		maxWeight = inFile.nextInt();
	}

	/**********************************************************************************************************/

	//Method solves fractional knapsack problem
	public void solve()
	{
		//sort items, weights, values arrays in descending order value/weight
		sort();

		//initialize total weight and value
		totalWeight = 0;
		totalValue = 0;

		//select tiems in order
		for (int i = 0; i < size; i++)
		{
			//if total weight plus current item weight does not exceed maximum weight
			if (totalWeight + weight[i] < maxWeight)
			{
				totalWeight += weight[i];					//add whole current item
				totalValue += value[i];					

				display(item[i], weight[i], value[i]);		//display item information
			}
			//if total weight plus current item weight equals maximum weight
			else if (totalWeight + weight[i] == maxWeight)
			{
				totalWeight += weight[i];					//add whole current item
				totalValue += value[i];			

				display(item[i], weight[i], value[i]);		//display item information

				break;										//stop selecting items
			}
			//if total weight plus current item weight does exceed maximum weight
			else
			{												//select fractional item
				int fractionalWeight = maxWeight - totalWeight;
				double fractionalValue = (double)value[i]*fractionalWeight/weight[i];

				totalWeight += fractionalWeight;			//add fractional item
				totalValue += fractionalValue;				
															//display item information
				display(item[i], fractionalWeight, fractionalValue);

				break;										//stop selecting items
			}
		}

		//display total weight and value of selected items
		display(totalWeight, totalValue);
	}

	/**********************************************************************************************************/

	//Method sorts items, weights, values arrays in descending order
	//of value/weight radio
	private void sort()
	{
		for (int i = 0; i < size; i++)
			for (int j = i+1; j < size; j++)
			{
				if ((double)value[i]/weight[i] < (double)value[j]/weight[j])
				{
					swap(i, j, item);
					swap(i, j, weight);
					swap(i, j, value);
				}
			}
	}

	/**********************************************************************************************************/

	//Method swaps elements located at two indices of an array
	private void swap(int i, int j, int[] array)
	{
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	/**********************************************************************************************************/

	//Method displays item, weight, value
	private void display(int item, int weight, double value)
	{
		System.out.println(item + " " + weight + " " + value);
	}

	/**********************************************************************************************************/

	//Method displays weight and value
	private void display(int weight, double value)
	{
		System.out.println(weight + " " + value);
	}

	/**********************************************************************************************************/
}
