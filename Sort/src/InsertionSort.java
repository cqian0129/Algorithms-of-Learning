//insertion sort algorithm
public class InsertionSort
{
	//sort method
	public static void sort(int[] a)
	{
		int i, j;	
		int temp;

		for (i = 0; i < a.length; i++)			//outer loop
		{
			temp = a[i];						//save element that needs insertion

			for (j = i-1; j>= 0 && temp < a[j]; j--)
				a[j+1] = a[j];					//shift elemetns as necessary

			a[j+1] = temp;						//insert at the appropriate locaito
		}
	}
}