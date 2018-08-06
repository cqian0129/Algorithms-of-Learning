import java.io.*;
import java.util.*;

//Program finds components of graph
public class Components 
{
	/************************************************************************/
		
	private int vertices;			//number of vertices
	private int edges;				//number of edges
	private LinkedList[] array;		//adjacency list
	private int[] result;			//array of components
	
	/************************************************************************/
	
	//Constructor of Traversal class, reads graph from file
	//and stores in adjacency list
	public Components (String inputFile) throws IOException
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
	
	//Method finds components of graph
	public void compute()
	{
		//create array of components and initialize, components are
		//unknown initially
		result = new int[vertices];
		for(int i = 0; i < vertices; i++)
			result[i] = -1;
		
		//start at vertex 0, component name starts at 0
		int index = 0;
		int componentName = 0;
		
		//go thru all vertices
		while(index < vertices)
		{
			//if vertex has not been assigned component
			if(result[index] == -1)
			{
				//assign current component name to vertex and all vertices
				//connected to it
				assignComponent(index, componentName);
				
				//get next component name
				componentName ++;
			}
			
			//go to next vertex
			index ++;
		}
	}
	
	/************************************************************************/
	
	//Method assigns  component name to a vertex and all vertices connected to it
	//using  breadth first traversal
	private void assignComponent(int index, int componentName)
	{
		//assign component name to vertex
		result[index] = componentName;
		
		//list used in traversal
		LinkedList<Integer> list = new LinkedList<Integer>();
		
		//put vertex to list
		list.addLast(index);
		
		//while list has vertices
		while(!list.isEmpty())
		{
			//remove first vertex from list
			int i = list.removeFirst();
			
			//go thru neighbors of vertex
			for(int j = 0; j < array[i].size(); j++)
			{
				//get neighbor
				int neighbor = (Integer)array[i].get(j);
				
				//if neighbor has not been assigned component
				if(result[neighbor] == -1)
				{
					//assign component name to neighbor
					result[neighbor] = componentName;
					
					//add neighbor to list
					list.addLast(neighbor);
				}
			}
		}
	}
	
	/************************************************************************/
	
	//Method writes vertices and their components to file
	public void display(String outputFile) throws IOException
	{
		//open output file
		PrintWriter outFile = new PrintWriter(new FileWriter(outputFile));
		
		//print vertices and components
		for(int i = 0; i < vertices; i++)
			outFile.println(i + " " + result[i]);
		
		//close output file
		outFile.close();
	}
	
	/************************************************************************/
		
}
