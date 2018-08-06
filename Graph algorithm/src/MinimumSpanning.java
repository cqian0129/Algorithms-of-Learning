import java.io.*;
import java.util.*;

//Program finds minimum spanning tree of graph (Prim algorithm)
public class MinimumSpanning 
{
	/************************************************************************/
	
	private int vertices;				//number of vertices
	private int edges;					//number of edges
	private int[][] matrix;				//table of edges/weights
	private boolean[] vertexSelect;		//select vertices
	private boolean[] edgeSelect;		//selected edges
	private final int INFINITY = 99999;	//constant represents infinity
	
	/************************************************************************/
	
	//Constructor of MinimumSpanning class, reads graph and stores in table
	public MinimumSpanning (String inputFile) throws IOException
	{
		//open input file
		Scanner inFile = new Scanner(new File(inputFile));
		
		//read number of vertices and edges
		vertices = inFile.nextInt();
		edges = inFile.nextInt();
		
		//table of edges/weights (vertex, vertex, weight)
		matrix = new int[edges][3];
		
		//read edges/weights
		for(int i = 0; i < edges; i++)
		{
			matrix[i][0] = inFile.nextInt();	//read one vertex
			matrix[i][1] = inFile.nextInt();	//read other vertex
			matrix[i][2] = inFile.nextInt();	//read edges weight
		}
	}
	
	/************************************************************************/
	
	//Method finds minimum spanning tree
	public void compute()
	{
		//selected vertices, none selected initially
		vertexSelect = new boolean[vertices];
		for (int i = 0; i < vertices; i++)
			vertexSelect[i] = false;
		
		//selected edges, none selected initially
		edgeSelect = new boolean[edges];
		for(int i = 0; i < edges; i++)
			edgeSelect[i] = false;
		
		//select initial vertex 0
		vertexSelect[0] = true;
		
		//repeatedly select vertices-1 number of edges
		for(int i = 0; i < vertices-1; i++)
		{
			int minValue = INFINITY;
			int minIndex = -1;
			
			//go thru edges and select minimum edge
			for(int j = 0; j < edges; j++)
			{
				//if edge is not selected and 2 vertices are selected and unselected
				//and edge weight is less than current minimum
				if(!edgeSelect[j] && vertexSelect[matrix[j][0]] != vertexSelect[matrix[j][1]]
						&& matrix[j][2] < minValue)
				{
					minIndex = j;
					minValue = matrix[j][2];
				}
			}
			
			//select minimum edge and 2 vertices of the edge
			edgeSelect[minIndex] = true;
			vertexSelect[matrix[minIndex][0]] = true;
			vertexSelect[matrix[minIndex][1]] = true;
		}
	}
	
	/************************************************************************/
	
	//Method displays minimum spanning tree
	public void display(String outputFile) throws IOException
	{
		//open output file
		PrintWriter outFile = new PrintWriter(new FileWriter(outputFile));
		
		//total edge weights
		double edgeSum = 0;
		
		//go thru edges
		for(int i = 0; i < edges; i++)
		{
			//if edge belongs to minimum spanning tree
			if(edgeSelect[i])
			{
				//print edge and its weight
				outFile.println(matrix[i][0] + " " + matrix[i][1] + " " + matrix[i][2]);
				edgeSum += matrix[i][2];
			}
		}
		
		//print total edge weight
		outFile.println(edgeSum);
		
		//close output file
		outFile.close();
	}

}
