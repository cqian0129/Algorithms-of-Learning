//Efficient matrix multiplication algorithm
public class Matrix
{
	/*********************************************************************************************************/

	//Method multiplies two matrices using an efficient algorithm, assumes matrices are squeare matrices, same size, and size is power of 2
	public static int[][] multiply (int[][] u, int[][] v)
	{
		int[][] a11, a12, a21, a22, b11, b12, b21, b22;
		int[][] m1, m2, m3, m4, m5, m6, m7, c11, c12, c21, c22, result;

		//if matrices are 1x1
		if (u.length == 1)
		{
			//multiply elements of 1x1 matrices
			result = new int[1][1];
			result[0][0] = u[0][0]*v[0][0];
			return result;
		}
		//if matrices are greater than 1x1
		else
		{
			//partition matrices into quadrants
			a11 = upperLeft(u);
			a12 = upperRight(u);
			a21 = lowerLeft(u);
			a22 = lowerLeft(u);

			//partition matrices into quadrants
			b11 = upperLeft(v);
			b12 = upperRight(v);
			b21 = lowerLeft(v);
			b22 = lowerRight(v);

			//go thru steps of algorithm
			m1 = multiply(sum(a11, a22), sum(b11, b22));			//m1 = (a11 + a22)(b11 + b22)

			m2 = multiply(sum(a21, a22), b11);						//m2 = (a21 + a22)b11

			m3 = multiply(a11, subtract(b12, b22));					//m3 = a11(b12 - b22)

			m4 = multiply(a22, subtract(b21, b11));					//m4 = a22(b21 - b11)

			m5 = multiply(sum(a11, a12), b22);						//m5 = (a11 + a12)b22

			m6 = multiply(subtract(a21, a11), sum(b11, b12));		//m6 = (a21 - a11)(b11 + b12)

			m7 = multiply(subtract(a12, a22), sum(b21, b22));		//m7 = (a12 - a22)(b21 + b22)

			c11 = sum(subtract(sum(m1, m4), m5), m7);				//c11 = m1 + m4 - m5 + m7

			c12 = sum(m3, m5);										//c12 = m3 + m5;

			c21 = sum(m2, m4);										//c21 = m2 + m4;

			c22 = sum(subtract(sum(m1, m3), m2), m6);				//c22 = m1 + m3 - m2 + m6

			result = combine(c11, c12, c21, c22);

			return result;
		}
	}	

	/*********************************************************************************************************/

	//Method returns upper left qudrant of a matrix, size is power of 2
	private static int[][] upperLeft(int[][] u)
	{
		//create quadrant matrix
		int n = u.length/2;
		int[][] result = new int[n][n];

		//copy values from upper left qudrant to quadrant matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = u[i][j];

		//return upper left quadrant
			return result;
	}

	/*********************************************************************************************************/

	//Method returns upper right qudrant of a matrix, size is power of 2
	private static int[][] upperRight(int[][] u)
	{
		//create quadrant matrix
		int n = u.length/2;
		int[][] result = new int[n][n];

		//copy values from upper right quadrant to quadrant matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = u[i][n+j];

		//return upper right quadrant
		return result;
	}

	/*********************************************************************************************************/

	//Method returns lower left quadrant of a matrix, size is power of 2
	private static int[][] lowerLeft(int[][] u)
	{
		//create quadrant matrix
		int n = u.length / 2;
		int[][] result = new int[n][n];

		//copy values from upper right quadrant to quadrant matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = u[n+i][j];

		//return lower left quadrant
		return result;
	}

	/*********************************************************************************************************/

	//Method returns lower right quadrant of a matrix, size is power of 2
	private static int[][] lowerRight(int[][] u)
	{
		//create quadrant matrix
		int n = u.length/2;
		int[][] result = new int[n][n];

		//copy values from lower right quadrant to quadrant matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = u[n+i][n+j];

		//return lower right quadrant
		return result;
	}

	/*********************************************************************************************************/

	//Method makes a matrix by combining four quadrant sub matrixces
	private static int[][] combine(int[][] a, int[][] b, int[][] c, int[][] d)
	{
		//create result matrix
		int n = a.length;
		int[][] result = new int[2*n][2*n];

		//copy values from upper left quadrant to result matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = a[i][j];

		//copy values from upper right quadrant to result matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][n+j] = b[i][j];

		//copy values from lower left quadrant to result matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[n+i][j] = c[i][j];

		//copy values from lower left quadrant to result matrix
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[n+i][n+j] = d[i][j];

		//return result matrix;
		return result;
	}

	/*********************************************************************************************************/

	//Method adds two matrices
	private static int[][] sum(int[][] u, int[][] v)
	{
		//create result matrix
		int n = u.length;
		int[][] result = new int[n][n];

		//add elements fo two matrices
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = u[i][j] + v[i][j];

		//return result matrix
		return result;
	}

	/*********************************************************************************************************/

	//Method subtracts two matrices
	private static int[][] subtract(int[][] u, int[][] v)
	{
		//create result matrix
		int n = u.length;
		int[][] result = new int[n][n];

		//subtract elements of two matrices
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				result[i][j] = u[i][j] - v[i][j];

		//return result matrix
		return result;
	}

	/*********************************************************************************************************/

	//Method displays a matrix
	private static void display(int[][] u)
	{
		int n = u.length;

		//print elements of matrices
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
				System.out.print(u[i][j] + " ");
			System.out.println();
		}
	}

	/*********************************************************************************************************/

	//Tester program
	public static void main(String[] args)
	{
		int[][] u = {{2, 3, 1, 2}, {1, 0, 0, 1}, {2, 1, 0, 2}, {1, 1, 3, 2}};
		int[][] v = {{0, 1, 2, 1}, {1, 2, 2, 3}, {1, 2, 1, 0}, {0, 1, 2, 0}};

		int[][] w = multiply(u, v);

		display(w);
	}

	/*********************************************************************************************************/
}