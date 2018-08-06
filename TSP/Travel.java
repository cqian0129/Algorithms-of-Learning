package Approximation.TSP;


//Program finds approximation solution to traveling salesman problem
public class Travel 
{
	private int[][] matrix;			//adjacency matrix of graph
	private int[] cycle;			//solution cycle
	private boolean[] selected;		//array of selected vertices
	private int size;				//number of vertices
	
	/************************************************************************/
	
	//Constructor of Travel class
	public Travel(int[][] matrix, int size)
	{
		//set adjacency matrix and vertices
		this.matrix = matrix;
		this.size = size;
		
		//create cycle array
		cycle = new int[size];
		
		//create and initialize array of selected vertices
		selected = new boolean[size];
		for(int i = 0; i < size; i++)
			selected[i] = false;
	}
	
	/************************************************************************/
	
	//Method executes greedy algorithm
	public void solve()
	{
		//start with initial vertex
		cycle[0] = 0;
		selected[0] = true;
		
		//select vertices one after the other
		for(int i = 1; i < size; i++)
			greedySelect(i);
		
		//display cycle
		display();
	}
	
	/************************************************************************/
	
	//Method selects the next vertex
	private void greedySelect(int index)
	{
		//find closest vertex to the last vertex in the graph
		int closestVertex = closest(cycle[index-1]);
		
		//place closest vertex at the end of path
		cycle[index] = closestVertex;
		
		//mark closest vertex
		selected[closestVertex] = true;
	}
	
	/************************************************************************/
	
	//Method finds the closest vertex
	private int closest(int vertex)
	{
		//initialize selected vertex and minimum distance
		int minimumDistance = Integer.MAX_VALUE;
		int closestVertex = -1;
		
		//go thru all vertices
		for(int i = 0; i < size; i++)
		{
			//if vertex is not selected and its distance is less than
			//minimum distance
			if(!selected[i] && matrix[vertex][i] < minimumDistance)
			{
				minimumDistance = matrix[vertex][i];
				closestVertex = i;			//update miimum distance and
			}								//closest vertex
		}
		
		//return closest vertex
		return closestVertex;
	}
	
	/************************************************************************/
	
	//Method displays a cycle
	private void display()
	{
		//print cycle
		for(int i = 0; i < size; i++)
			System.out.print(cycle[i] + " ");
		System.out.print(cycle[0] + " ");
		
		//compute cycle cost
		double sum = 0;
		for(int i = 0; i < size; i++)
			sum += matrix[cycle[i]][cycle[(i+1)%size]];
		
		//print cycle cost
		System.out.println(":" + sum);
	}
}
