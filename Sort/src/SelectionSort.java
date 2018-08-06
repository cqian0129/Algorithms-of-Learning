//selection sort algorithm
public class SelectionSort
{
	//sort method
	public static void sort(int[] a)
	{
		int i, j, min;
		int temp;

		for (i = 0; i < a.length; i++)		//outer loop
		{
			min = i; 						//initial location of minimum

			for (j = i+1; j < a.length; j++)		//inner loop
				if (a[j] < a[min])			//if new minimum is found
					min = j;				//then update location of minimum

			temp = a[i];	
			a[i] = a[min];					//swap the minimum and outer
			a[min] = temp;					//loop element
		}
	}
}