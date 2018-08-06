//Selection algorithm
public class Selection
{
	//Method selectis kth smallest value of array a[]
	public static int select(int[] a, int k)
	{
		return select(a, k, 0, a.length-1);				//initial recursive call
	}

	//Recursive selection method, finds kth smallest value of array a[]
	//between low and high
	private static int select(int[] a, int k, int low, int high)
	{
		int mid = partition(a, low, high);				//partition array an find pivot location

		if (mid == k)									//if pivot is located at index k
			return a[mid];								//kth smallest value is found

		else if (mid < k)								//if pivot is located left of index k
			return select(a, k, mid+1, high);			//search kth smallest value in right half

		else											//if pivot is located right of index k
			return select(a, k, low, mid-1);			//search kth smallest value in left half
	}

	//Method partitions array a[] between low and high, uses the first value
	//of array as pivot value, return location of pivot value after partition
	private static int partition(int[] a, int low, int high)
	{
		int temp = low;

		for (int i = low+1; i <= high; i++)				//loop that builds partition
			if (a[i] < a[low])
			{											//if value is smaller than pivot 
				temp = temp + 1;						//value then insert into left half
				swap(a, i, temp);
			}											//if value is greater or equal to
														//pivot value then insert into right half

		swap(a, low, temp);								//finally move pivot value to
		return temp;									//the middle of array and return the location of pivot value
	}	

	//Method swaps the two elements of array located at i and j
	private static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}