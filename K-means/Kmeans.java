import java.io.*;
import java.util.*;

//K-means clustering class
public class Kmeans
{
	/************************************************************************************************/

	private int numberRecords;					//number of records
	private int numberAttributes;				//number of attributes
	private int numberClusters;					//number of clusters

	private double[][] records;					//array of records
	private double[][] centroids;				//array of centroids
	private int[] clusters;						//clusters of records
	private Random rand;						//random number generator

	/************************************************************************************************/

	//Constructor of Kmeans class
	public Kmeans()
	{
		//parameters are zero
		numberRecords = 0;
		numberAttributes = 0;
		numberClusters = 0;

		//arrays are empty
		records = null;
		centroids = null;
		clusters = null;
		rand = null;
	}

	/************************************************************************************************/

	//Method loads records from input file
	public void load(String inputFile) throws IOException
	{
		Scanner inFile = new Scanner(new File(inputFile));

		//read number of records, attributes
		numberRecords = inFile.nextInt();
		numberAttributes = inFile.nextInt();

		//create array of records
		records = new double[numberRecords][numberAttributes];

		//for each record
		for (int i = 0; i < numberRecords; i++)
		{
			//read attributes
			for (int j = 0; j < numberAttributes; j++)
				records[i][j] = inFile.nextDouble();
		}

		inFile.close();
	}

	/************************************************************************************************/

	//Method sets parameters of clustering
	public void setParameters(int numberClusters, int seed)
	{
		//set number of clusters
		this.numberClusters = numberClusters;

		//create random number generator with seed
		this.rand = new Random(seed);
	}

	/************************************************************************************************/

	//Method performs k-means of clustering
	public void cluster()
	{
		//initialize clusters of records
		initializeClusters();

		//initialize centroids of clusters
		initializeCentroids();

		//stop condition has not been reached
		boolean stopCondition = false;

		//while stop condition is not reached
		while (!stopCondition)
		{
			//assign clusters to records
			int clusterChanges = assignClusters();

			//update centroids of clusters
			updateCentroids();

			//stop condition is reached if no records changed clusters
			stopCondition = (clusterChanges == 0);
		}
	}

	/************************************************************************************************/

	//Method initializes clusters of records
	private void initializeClusters()
	{
		//create array of cluster labels
		clusters = new int[numberRecords];

		//assign cluster -1 to all records
		for (int i = 0; i < numberRecords; i++)
			clusters[i] = -1;
	}

	/************************************************************************************************/

	//Method initializes centroids of clusters
	private void initializeCentroids()
	{
		//create array of centroids
		centroids = new double[numberClusters][numberAttributes];

		//for each cluster
		for (int i = 0; i < numberClusters; i++)
		{
			//randomly pick a record
			int index = rand.nextInt(numberRecords);

			//use record as centroid
			for (int j = 0; j < numberAttributes; j++)
				centroids[i][j] = records[index][j];
		}
	}

	/************************************************************************************************/

	//Method assigns clusters to records
	private int assignClusters()
	{
		int clusterChanges = 0;

		//go thru records and assign clusters to them
		for (int i = 0; i < numberRecords; i++)
		{
			//find distance between record and first centroid
			double minDistance = distance(records[i], centroids[0]);
			int minIndex = 0;

			//go thru centroids and find closest centroid
			for (int j = 0; j < numberClusters; j++)
			{
				//find distance between record and centroid
				double distance = distance(records[i], centroids[j]);

				//if distance is less than minimum, update minimum
				if (distance < minDistance)
				{
					minDistance = distance;
					minIndex = j;
				}
			}

			//if closest cluster is different from current cluster
			if (clusters[i] != minIndex)
			{
				//change chluster of record
				clusters[i] = minIndex;
				//keep count of cluster changes
				clusterChanges++;
			}

		}

		//return number of cluster changes
		return clusterChanges;
	}

	/************************************************************************************************/

	//Method updates centroids of clusters
	private void updateCentroids()
	{
		//create array of cluster sums and initialize
		double[][] clusterSum = new double[numberClusters][numberAttributes];
		for (int i = 0; i < numberClusters; i++)
			for (int j = 0; j < numberAttributes; j++)
				clusterSum[i][j] = 0;

		//create array of cluster sizes and initialize
		int[] clusterSize = new int[numberClusters];
		for (int i = 0; i < numberClusters; i++)
			clusterSize[i] = 0;

		//for each record
		for (int i = 0; i < numberRecords; i++)
		{
			//find cluster of record
			int cluster = clusters[i];

			//add record to cluster sum
			clusterSum[cluster] = sum(clusterSum[cluster], records[i]);

			//increment cluster size
			clusterSize[cluster] += 1;
		}

		//find centroid of each cluster
		for (int  i = 0; i < numberClusters; i++)
			centroids[i] = scale(clusterSum[i], 1.0/clusterSize[i]);
	}

	/************************************************************************************************/

	//Method finds distance between two records
	private double distance(double[]u, double[] v)
	{
		double sum = 0;

		//find euclidean distance square between two records
		for (int i = 0; i < u.length; i++)
			sum += (u[i] - v[i])*(u[i] - v[i]);

		return sum;
	}

	/************************************************************************************************/

	//Method finds sum of two records
	private double[] sum(double[] u, double[] v)
	{
		double[] result = new double[u.length];

		//add corresponding attributes of records
		for (int i = 0; i < u.length; i++)
			result[i] = u[i] + v[i];

		return result;
	}

	/************************************************************************************************/

	//Method finds scaler multiple of a record
	private double[] scale(double[] u, double k)
	{
		double[] result = new double[u.length];

		//multiply attributes of record by scaler
		for (int i = 0; i < u.length; i++)
			result[i] = u[i]*k;

		return result;
	}

	/************************************************************************************************/

	//Method writes records and their clusters to output file
	public void display(String outputFile) throws IOException
	{
		PrintWriter outFile = new PrintWriter(new FileWriter(outputFile));

		//for each record
		for (int i = 0; i < numberRecords; i++)
		{
			//write attributes of record
			for (int j = 0; j < numberAttributes; j++)
				outFile.print(records[i][j] + " ");

			//write cluster label
			outFile.println(clusters[i]+1);
		}
		outFile.close();


	}

	/************************************************************************************************/

}