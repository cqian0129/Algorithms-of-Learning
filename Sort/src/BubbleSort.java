public class BubbleSort {
	public static void sort (int[] a) {
		int i, j;
		int temp;
		boolean swap;
		int com = 0;
		int data = 0;

		for (i = a.length-1; i > 0; i--){
			swap = false;

			for (j = 0; j < i; j++){
				com++;
				if (a[j] > a[j+1])
				{
					data += 3;
					temp = a[j+1];
					a[j+1] = a[j];
					a[j] = temp;
					swap = true;
 				}
			}

			if (!swap)
				break;
		}
		System.out.println("com" + com);
		System.out.println("data" + data);

	}
}