import java.io.*;
import java.util.*;

//Program performs breadth first traversal of graph
public class Traversal 
{
	/************************************************************************/
	
	private int vertices;			//number of vertices
	private int edges;				//number of edges
	private LinkedList[] array;		//adjacency list
	
	/************************************************************************/
	
	//Constructor of Traversal class, reads graph from file
	//and stores in adjacency list
	public Traversal (String inputFile) throws IOException
	{
		//open input file
		Scanner inFile = new Scanner(new File(inputFile));
		
		//read number of vertices and edges
		vertices = inFile.nextInt();
		edges = inFile.nextInt();
		
		//create array of lists
		array = new LinkedList[vertices];
		
		//lists are empty initially
		for(int i = 0; i < vertices; i++)
			array[i] = new LinkedList();
		
		//read edges
		for(int i = 0; i < edges; i++)
		{
			//read pair of vertices
			int u = inFile.nextInt();
			int v = inFile.nextInt();
			
			//store them in adjacency list
			array[u].addFirst(v);
			array[v].addFirst(u);
		}
	}
	
	/************************************************************************/
	
	//Method performs breadth first traversal
	public void breadthFirst() throws IOException
	{
		//list used in traversal
		LinkedList<Integer> list = new LinkedList<Integer>();
		PrintWriter outFile = new PrintWriter(new File("outputfile"));
		//array of visited vertices, no vertices have been visited
		boolean[] visited = new boolean[vertices];
		for(int i = 0; i < vertices; i++)
			visited[i] = false;
		
		//start at initial vertex
		int initial = 0;
		visited[initial] = true;
		list.addLast(initial);
		
		//while list has vertices
		while(!list.isEmpty())
		{
			//remove the first vertex from list
			int u = list.removeFirst();
			
			//print the vertex
			outFile.println(u);
			
			//go thru neighbors of the vertex
			for (int i = 0; i < array[u].size(); i++)
			{
				//get the neighbor
				int v = (Integer) array[u].get(i);
				
				//if neighbor is not already visited
				if(!visited[v])
				{
					//mark neighbor as visited
					visited[v] = true;
					
					//add the neighbor at the end of list
					list.addLast(v);
				}
			}
		}

		outFile.close();
	}
	
	/************************************************************************/
}
