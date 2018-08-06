//Median finding algorithm
public class Median
{
	//Method finds median of array a[]
	public static int median(int[] a)
	{
		return median(a, 0, a.length-1);			//initial recursive call
	}

	//Recursive median finding methd, finds median of array a[] between low and high
	private static int median(int[] a, int low, int high)
	{
		int mid = partition(a, low, high);			//partition array and find pivot location

		if (mid == a.length/2)						//if pivot is located at mid index median is found
			return a[mid];

		else if (mid < a.length/2)					//if pivot is located left of mid
			return median(a, mid+1, high);			//index search median in right half

		else										//if pivot is located right of mid
			return median(a, low, mid-1);			//index search median in left half
	}

	//Method partitions array a[] between low and high, uses the first value of array as pivot value, returns location of pivot value after partition
	private static int partition(int[] a, int low, int high)
	{
		int temp = low;

		for (int i = low+1; i <= high; i++)			//loop that builds partition
			if (a[i] < a[low])	
			{										//if value is smaller than pivot	
				temp = temp + 1;					//value then insert into left half
				swap(a, i, temp);	
			}										//if value is greater or equal to 
													//pivot value then insert into right half

		swap(a, low, temp);							//finally move pivot value to
		return temp;								//the middle of array and return 
	}												//the location of pivot value

	//Method swaps the two elements of array located at i and j
	private static void swap(int[] a, int i, int j)
	{
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
}