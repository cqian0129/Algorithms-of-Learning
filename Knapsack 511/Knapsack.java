package Backtracking.Knapsack;

import java.io.*;
import java.util.*;

//Program solves 0/1 Knapsack problem
public class Knapsack 
{
	/************************************************************************/
	
	private int size;			//number of items
	private int maxWeight;		//maximum weight
	
	private int[] item;			//array of items
	private int[] weight;		//item weights
	private int[] value;		//item values
	
	/************************************************************************/
	
	//Set class (inner class)
	private class Set
	{
		private int[] array;
		
		//Constructor of set class
		private Set(int size)
		{
			array = new int[size];
			for(int i = 0; i < size; i++)
				array[i] = -1;
		}
	}
	
	/************************************************************************/
	
	//Constructor of Knapsack class
	public Knapsack(String inputFile) throws IOException
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
		for(int i = 0; i < size; i++)
		{
			item[i] = inFile.nextInt();
			weight[i] = inFile.nextInt();
			value[i] = inFile.nextInt();
		}
		
		//read maximum weight
		maxWeight = inFile.nextInt();
	}
	
	/************************************************************************/
	
	//Method finds optimal set of items, weights, values
	public void solve()
	{
		//list of available nodes
		LinkedList<Set> list = new LinkedList<Set>();
		
		//optimal solutions
		Set optimalSet = new Set(size);
		
		//start with empty set and add to list
		Set set = new Set(size);
		list.addFirst(set);
		
		//while list has nodes
		while(!list.isEmpty())
		{
			//remove best nodes
			set = removeBest(list);
			
			//if node is not complete set
			if(complete(set))
			{
				//if value of set is greater than current maximum value
				//update optimal set
				if(value(set) > value(optimalSet))
					optimalSet = set;
			}
			//if node is not complete node
			else
			{
				//generate children of node
				LinkedList<Set> children = generate(set);
				
				//go thru children
				for(int i = 0; i < children.size(); i++)
				{
					Set child = children.get(i);
					
					//if child node is valid and its estimates value is greater
					//than optimal set value then add child to list
					if(valid(child) && estimate(child) > value(optimalSet))
						list.addLast(child);
				}
			}
		}
		
		//display optimal solution
		display(optimalSet);
	}
	
	/************************************************************************/
	
	//Method finds/removes the best node from available nodes
	private Set removeBest(LinkedList<Set> list)
	{
		//start with first node
		int maxValue = estimate(list.get(0));
		int maxIndex = 0;
		
		//go thru nodes and choose the best node
		for(int i = 0; i < list.size(); i++)
		{
			int value = estimate(list.get(i));
			if(value > maxValue)		//if estimated value of node 
			{							//is greater than maximum value
				maxValue = value;		//update maximum value
				maxIndex = i;
			}
		}
		
		//choose the node with maximum estimated value
		Set bestSet = list.get(maxIndex);
		
		//remove the best node from list of available nodes
		list.remove(maxIndex);
		
		//return best node
		return bestSet;
	}
	
	/************************************************************************/
	
	//Method generates children of set
	private LinkedList<Set> generate(Set set)
	{
		//create left and right child
		Set leftChild = new Set(size);
		Set rightChild = new Set(size);
		
		int i;
		//copy bits of parent to both children
		for(i = 0; i < size; i++)
		{
			if(set.array[i] != -1)
			{
				leftChild.array[i] = set.array[i];
				rightChild.array[i] = set.array[i];			
			}
			else
				break;
		}
		
		//assign 0 to left child and 1 to right child
		leftChild.array[i] = 0;
		rightChild.array[i] = 1;
		
		//add both children to a list
		LinkedList<Set> children = new LinkedList<Set>();
		children.addLast(leftChild);
		children.addLast(rightChild);
		
		//return list of children
		return children;
	}
	
	/************************************************************************/
	
	//Method finds total weight of set
	private int weight(Set set)
	{
		int sumWeight = 0;
		
		//go thru items
		for(int i = 0; i < size; i++)
			if(set.array[i] == 1)				//if item is selected
				sumWeight += weight[i];			//add its weight to total weight
		
		return sumWeight;						//return total weight
	}
	
	/************************************************************************/
	
	//Method finds total value of set
	private int value(Set set)
	{
		int sumValue = 0;
		
		//go thru items
		for(int i = 0; i < size; i++)
			if(set.array[i] == 1)				//if item is selected
				sumValue += value[i];			//add its value to total value
				
		return sumValue;						//return total value
	}
	
	/************************************************************************/
	
	//Method finds the maximum value/weight ratio of remaining items
	private double maximumRatio(Set set)
	{
		double ratio, maxRatio = 0;
		
		//go thru items
		for(int i = 0; i < size; i++)
			if(set.array[i] == -1)				//if item has not been selected
			{
				ratio = (double) value[i]/weight[i];
												//compute value/weight ratio
				if(ratio > maxRatio)
					maxRatio = ratio;			//update maximum ratio
			}
		return maxRatio;						//return maximum ratio
	}
	
	/************************************************************************/
	
	//Method computes the upper bound of value of set
	private int estimate(Set set)
	{
		//compute upper bound
		return (int) (value(set) + (maxWeight - weight(set))*maximumRatio(set));
	}
	
	/************************************************************************/
	
	//Method checks whether set is valid
	private boolean valid(Set set)
	{
		//check whether total weight has not exceeded maximum weight
		return weight(set) <= maxWeight;
	}
	
	/************************************************************************/
	
	//Method checks whether set is complete
	private boolean complete(Set set)
	{
		//check whether last item has been considered
		return set.array[size-1] != -1;
	}
	
	/************************************************************************/
	
	//Method displays item-weight-value
	private void display(Set set)
	{
		int sumWeight = 0;
		int sumValue = 0;
		
		//go thru items
		for(int i = 0; i < size; i++)
			if(set.array[i] == 1)			//if item is selected
			{								//print item-weight-value
				System.out.println(item[i] + " " + weight[i] + " " + value[i]);
				sumWeight += weight[i];
				sumValue += value[i];
			}
		
		//print total weight and value
		System.out.println(sumWeight + " " + sumValue);
	}

}
