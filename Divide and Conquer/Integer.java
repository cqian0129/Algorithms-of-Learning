
//Efficient integer multiplication algorithm
public class Integer
{
	/*************************************************************************************************/

	//Method multiplies two integers using an efficient algorithm, assumes integers
	//have the same lengths
	public static int[] multiply(int[] u, int[] v)
	{
		int[] p, q, x, y, w, z, a, b, c, d, e, f, g, h;

		//if integer lengths are 1, 2, 3
		if (u.length <= 3)
			return product(u, v);			//perform usual multiplication

		//if integer lengths are greater than 3
		else
		{
			//if integer lengths are odd
			if (u.length % 2 == 1)
			{
				p = preprocess(u);			//append one zero at the beginning 
				q = preprocess(v);			//to make even length
			}
			//if integer lengths are even
			else
			{
				p = u;						//no change in integers
				q = v;
			}

			//find length of modified integers
			int n = p.length;

			//partition integers into halves
			x = left(p);					//p = xy
			y = right(p);
			w = left(q);					//q = wz
			z = right(q);

			//go thru steps of algorithm
			a = add(x, y);					//a = x + y
			b = add(w, z);					//b = w + z
			c = multiply(a, b);				//c = a*b
			d = multiply(x, w);				//d = x*w
			e = multiply(y, z);				//e = y*z
			f = subtract(c, add(d, e));		//f = c - d - e
			g = power(d, n);				//g = d*10^n
			h = power(f, n/2);				//h = f*10^n/2
			return add(g, add(h, e));		//g + h + e
		}
	}

	/*************************************************************************************************/

	//Method appends one zero at the beginning of odd length integer
	private static int[] preprocess(int[] u)
	{
		//create array with length+1
		int[] result = new int[u.length+1];

		//put 0 at beginning
		result[0] = 0;

		//copy digits of integer
		for (int i = 0; i < u.length; i++)
			result[i+1] = u[i];

		//return modified integer
		return result;
	}

	/*************************************************************************************************/

	//Method returns the left half of an even length integer
	private static int[] left(int[] u)
	{
		int n = u.length;

		//create array of half length 
		int[] result = new int[n/2];

		//copy left half of integer
		for (int i = 0; i < n/2; i++)
			result[i] = u[i];

		//return left half
		return result;
	}

	/*************************************************************************************************/

	//Method returns the right half of an even length integer
	private static int[] right(int[] u)
	{
		int n = u.length;

		//create array of half length 
		int[] result = new int[n/2];

		//copy right half of integer
		for (int i = 0; i < n/2; i++)
			result[i] = u[n/2 + i];

		//return right half
		return result;
	}

	/*************************************************************************************************/

	//Method adds two integers, length of result will be one more than maximum
	//of lengths of two integers
	private static int[] add(int[] u, int[] v)
	{
		int i, j, k, size, sum, carry, remainder;

		//find maximum of lengths of two integers
		if (u.length >= v.length)
			size = u.length;
		else 
			size = v.length;

		//create array with length maximum+1
		int[] result = new int[size+1];

		//start adding from the right end
		i = u.length - 1;
		j = v.length - 1;
		k = result.length - 1;

		carry = 0;

		//add digits from right ot left
		while (k >= 0)
		{
			if (i >= 0 && j >= 0)				//both integers have digits
				sum = u[i] + v[j] + carry;
			else if (i >= 0)					//only one integer has digits
				sum = u[i] + carry;			 
			else if (j >= 0)					//only other integer has digits
				sum = v[j] + carry;				
			else
				sum = carry;

			remainder = sum % 10;				//find remainder and carry
			carry = sum / 10;					//of sum of digits
			result[k] = remainder;

			i--;j--;k--;						//keep moving right to left
		}

		return result;							//return sum
	}

	/*************************************************************************************************/

	//Method subtracts two integers, assumes first integer is greater than or equal to second integer, length of result will be equal to maximum of lengths of two integers
	private static int[] subtract(int[] u, int[] v)
	{
		int i, j, k, size;

		//make copy integer which is subtracted from, so as not to change
		//its original digits
		int[] w = new int[u.length];
		for (i = 0; i < u.length; i++)
			w[i] = u[i];

		//find maximum of lengths of two integers
		if (w.length >= v.length)
			size = w.length;
		else
			size = v.length;

		//create array with length maximum
		int[] result = new int[size];

		//start subtracting from the right end
		i = w.length - 1;
		j = v.length - 1;
		k = result.length - 1;

		//subtract digits from right ot left
		while (k >= 0)
		{
			if (i >= 0 && j >= 0)				//both integers have digits
			{
				if (w[i] >= v[j])				//subtract smaller digit from larger digit
					result[k] = w[i] - v[j];	//larger digit
				else
				{
					result[k] = 10 + w[i] - v[j];	//subtract larger digit from smaller digit, need carry from previous digit
					w[i-1] = w[i-1] - 1;
				}
			}
			else if (i >= 0)					//only first integer has digits
				result[k] = w[i];	
			else								//neither integers have digits
				result[k] = 0;					

			i--;j--;k--;						//keep moving right to left
		}

		return result;							//return result
	}

	/*************************************************************************************************/

	//Method multiplies by power of 10
	private static int[] power(int[] u, int n)
	{
		int size = u.length;

		//create array with length + power
		int[] result = new int[size+n];

		//copy digits of integer
		for (int i = 0; i < size; i++)
			result[i] = u[i];

		//append power number of zeros
		for (int i = size; i < size+n; i++)
			result[i] = 0;

		//return result
		return result;
	}

	/*************************************************************************************************/

	//Method multiplies two integers using usual multiplication
	private static int[] product(int[] u, int[] v)
	{
		int a, b, c;

		//convert arrays of digits to integer values
		a = convert(u);
		b = convert(v);

		//find produt of integer values
		c = a*b;

		//convert product value to array of digits
		return convert(c);
	}

	/*************************************************************************************************/

	//Method converts an array of digits to integer value
	private static int convert (int[] u)
	{
		int n = u.length, sum = 0, power = 1;

		//go thru digits, multiply by power of 10, sum up products
		for (int i = n-1; i >= 0; i--)
		{
			sum = sum + u[i]*power;
			power = power * 10;
		}

		//return integer value
		return sum;
	}

	/*************************************************************************************************/

	//Method converts an integer value to array of digits, assumes integer has at most six digits
	private static int[] convert(int n)
	{
		int i, remainder;

		//initial array of digits
		int[] result = {0, 0, 0, 0, 0, 0};

		//convert integer to array of digits from right to left
		i = 5;
		while (n > 0)
		{
			remainder = n % 10;
			result[i] = remainder;
			n = n / 10;
			i--;
		}

		//return array of digits
		return result;
	}

	/*************************************************************************************************/

	//Method displays an array of digits
	private static void display (int[] u)
	{
		for (int i = 0; i < u.length; i++)
			System.out.print(u[i]);
		System.out.println();
	}

	/*************************************************************************************************/

	//Tester program
	public static void main(String[] args)
	{
		int[] u = {2,9,6,5,8};

		int[] v = {9,4,1,2,7,4,5,6,3};

		int[] w = multiply(u, v);

		display(w);
	}
}