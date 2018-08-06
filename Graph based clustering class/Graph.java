import com.sun.prism.impl.Disposer;

import java.io.*;
import java.util.*;

//Program performs graph based clustering class
public class Graph
{
	/*******************************************************/

	//Record class
	private class Record
	{
		//attributes of record
		private double[] attributes;

		//constructor of record
		private Record(double[] attributes)
		{
			this.attributes = attributes;
		}
	}	

	/*******************************************************/

	private int numberRecords; 		//number of records
	private int numberAttributes;	//number of attributes
	private double delta;

	private ArrayList<Record> records;		//list of records
	private int[][] matrix;			//adjacency matrix
	private int[] clusters;			//clusters of records

	/*******************************************************/

	//Constructor of clustering
	public Graph() 
	{
		//parameters are aero
		numberRecords = 0;
		numberAttributes = 0;
		delta = 0;

		//lists are empty
		records = null;
		matrix = null;
		clusters = null;
	}

	/*******************************************************/

	//Method loads records from input file
	public void load(String inputFile) throws IOException
	{
		Scanner inFile = new Scanner(new File(inputFile));

		//read number of records, attributes
		numberRecords = inFile.nextInt();
		numberAttributes = inFile.nextInt();

		//empty list of records
		records = new ArrayList<Record>();

		//for each record
		for (int i = 0; i < numberRecords; i++)
		{
			//read attributes
			double[] attributes = new double[numberAttributes];
			for (int j = 0; j < numberAttributes; j++)
				attributes[j] = inFile.nextDouble();

			//create record
			Record record = new Record(attributes);

			//add record to list
			records.add(record);
		}

		inFile.close();
	}

	/*******************************************************/

	//Method sets parameter of clustering
	public void setParameter(double delta)
	{
		//set neighbor threshold
		this.delta = delta;
	}

	/*******************************************************/

	//Method performs clustering
	public void cluster()
	{
		//create adjacency matrix of records
		createMatrix();

		//initialize clusters of records
		initializeClusters();

		//initial record index is 0
		int index = 0;

		//initial cluster name is 0
		int clusterName = 0;

		//while there are more records
		while(index < numberRecords)
		{
			//if record does not have cluster name
			if (clusters[index] == -1)
			{
				//assign cluster name to record and all records connected to it
				assignCluster(index, clusterName);

				//find next cluster name
				clusterName = clusterName + 1;
			}

			//go to next record
			index = index + 1;
		}
	}

	/*******************************************************/

	//Method creates adjacency matrix
	private void createMatrix()
	{
		//allocate adjacency matrix
		matrix = new int[numberRecords][numberRecords];

		//entry (i, j) is 0 or 1 depending on i and j are neighbors or not
		for (int i = 0; i < numberRecords; i++)
			for (int j = 0; j < numberRecords; j++)
				matrix[i][j] = neighbor(records.get(i), records.get(j));
	}

	/*******************************************************/

	//Method decides whether two records are neighbors or not
	private int neighbor(Record u, Record v)
	{
		double distance = 0;

		//find euclidean distance between two records
		for (int i = 0; i < u.attributes.length; i++)
			distance += (u.attributes[i] - v.attributes[i])*
						(u.attributes[i] - v.attributes[i]);

		distance = Math.sqrt(distance);

		//if distance is less than neighbor threshold records are neighbors,
		//otherwise records are not neighbors
		if (distance <= delta)
			return 1;
		else
			return 0;
	}

	/*******************************************************/

	//Method initializes clusters of records
	private void initializeClusters()
	{
		//create array of cluster labels
		clusters = new int[numberRecords];

		//assign cluster -1 to all records
		for (int i = 0; i < numberRecords; i++)
			clusters[i] = -1;
	}

	/*******************************************************/

	//Method assigns cluster name to a record and all records connected to it
	//using breadth first traversal
	private void assignCluster(int index, int clusterName)
	{
		//assign cluster name to record
		clusters[index] = clusterName;

		//list used in traversal
		LinkedList<Integer> list = new LinkedList<Integer>();

		//put record into list
		list.addLast(index);

		//while list has records
		while (!list.isEmpty())
		{
			//remove first record from list
			int i = list.removeFirst();

			//find neighbors of record which have no cluster names
			for (int j = 0; j < numberRecords; j++)
				if (matrix[i][j] == 1 && clusters[j] == -1)
				{
					//assign cluster name to neighbor
					clusters[j] = clusterName;

					//add neighbor to list
					list.addLast(j);
				}
		}
	}

	/*******************************************************/

	//Method writes records and their clusters to output file
	public void display(String outputFile) throws IOException
	{
		PrintWriter outFile = new PrintWriter(new FileWriter(outputFile));

		//for each record 
		for (int i = 0; i < numberRecords; i++)
		{
			//write attributes of record
			for (int j = 0; j < numberAttributes; j++)
				outFile.print(records.get(i).attributes[j] + " ");

			//write cluster
			outFile.println(clusters[i]+1);
		}

		outFile.close();
	}

	/*******************************************************/
}