import java.io.*;
import java.util.*;
import java.lang.Object;

//Program finds shortest path lengths between single source vertex
//and all other vertices in an undirected graph (Dijkstra algorithm)
public class ShortestSingleSource 
{
	/************************************************************************/
	
	private int size;		//number of vertices
	private int start;		//source vertex
	private int[][] matrix;	//adjacency matrix
	private int[] result;	//array containing shortest path lengths

	private final int INFINITY = 999999;	//constant represents infinity
	
	private StringBuilder[] choose;
	/************************************************************************/
	
	//Constructor of ShortestSingleSource class, loads adjacency matrix
	//and source vertex from a file
	public ShortestSingleSource(String inputFile) throws IOException
	{
		//open input file
		Scanner inFile = new Scanner(new File(inputFile));
		
		//read number of vertices
		size = inFile.nextInt();
		
		//read adjacency matrix
		matrix = new int[size][size];
		for(int i = 0; i < size; i++)
			for(int j = 0; j < size; j++)
				matrix[i][j] = inFile.nextInt();
		
		//read source vertex
		start = inFile.nextInt();

		choose = new StringBuilder[size];
		for (int i = 0; i < size; i++) {
			choose[i] = new StringBuilder("1");
		}
	}
	
	/************************************************************************/
	
	//Method computes shortest path lengths
	public void compute()
	{

		int i, k, minValue, minIndex;
		
		//create 2 temporary arrays for distances
		int[] p = new int[size];
		int[] q = new int[size];
		
		//create select array
		boolean[] select = new boolean[size];
		
		//initialize distance array and select array
		for(i = 0; i < size; i++)
		{
			//initialize distances from start vertex
			if(i == start)						//vertex is start vertex
				p[i] = 0;
			else if (matrix[start][i] == 0)		//vertex is not neighbor
				p[i] = INFINITY;
			else								//vertex is neighbor
				p[i] = matrix[start][i];
			
			//none of vertices are selected
			select[i] = false;
		}
		
		//select start vertex
		select[start] = true;
		
		//perform vertices number of iterations
		for(k = 0; k < size-1; k++)
		{
			//find the vertex with minimum distance value
			minValue = INFINITY;
			minIndex = -1;							//minimum vertex is unknown
			for(i = 0; i < size; i++)
			{
				if (!select[i] && p[i] < minValue)	//if vertex is not selected
				{									//and its distance is less
					minValue = p[i];				//than current minimum then
					minIndex = i;					//update minimum vertex
				}
			}
			
			//select minimum vertex
			select[minIndex] = true;



			//update distances of neighbors of minimum vertex
			for(i = 0; i < size; i++)
			{
				//if vertex is not selected and vertex is neighbor and update 
				//distance is less than current distance then update the distance
				if(!select[i] && matrix[minIndex][i] != 0 && 
						p[minIndex] + matrix[minIndex][i] < p[i]) {
					choose[i].append((char)(minIndex+1));
					q[i] = p[minIndex] + matrix[minIndex][i];
				}
				//otherwise distance is not updates
				else {
					q[i] = p[i];
				}
			}
			
			//update distances become current distances
			for(i = 0; i < size; i++)
				p[i] = q[i];
		}
		
		//answer is current distances
		result = p;
	}
	
	/************************************************************************/
	
	//Method prints array of shortest path lengths to a file
	public void display(String outputFile) throws IOException
	{
		//open output file
		PrintWriter outFile = new PrintWriter(new FileWriter(outputFile));


		
		//print array of shortest path lengths
		for(int i = 0; i < size; i++) {
			outFile.print((start + 1) + " : " + (i + 1) + " | " + result[i] + " | " + (start + 1) + " --> ");
			for (int j = 1; j < choose[i].length(); j++)
			{
				outFile.print((int)choose[i].charAt(j) + " --> ");
			}
			outFile.println((i+1));
		}

		//close file
		outFile.close();
	}
	
	/************************************************************************/
}
