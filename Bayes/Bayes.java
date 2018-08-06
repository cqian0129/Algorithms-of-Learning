import java.io.*;
import java.util.*;

//Bayes classifier class
public class Bayes {
	/********************************************************************************/
	
	//Record class
	private class Record
	{
		private int[] attributes;			//attributes of record
		private int className;				//class of record
		
		//Constructor of Record class
		private Record(int[] attributes, int className)
		{
			this.attributes = attributes;	//assign attributes
			this.className = className;		//assign class
		}
	}
	
	/********************************************************************************/
	
	private int numberRecords;				//number of records
	private int numberAttributes;			//number of attributes
	private int numberClasses;				//number of classes
	
	private ArrayList<Record> records;		//list of records
	private int[] attributeValues;			//number of attribute value
	
	double[][][] table; 					//conditional probabilities
	double[] classTable;					//class probabilities
	
	/********************************************************************************/

	//Constructor of Bayes class
	public Bayes()
	{
		numberRecords = 0;					//set number of records, attributes,
		numberAttributes = 0;				//and classes to zero
		numberClasses = 0;
		
		records = null;						//set records and attribute values
		attributeValues = null;				//to empty
		
		table = null;						//set probability tables to empty
		classTable = null;
	}
	
	/********************************************************************************/

	//Method loads records from training file
	public void loadTrainingData(String trainingFile) throws IOException
	{
		Scanner inFile = new Scanner(new File(trainingFile));
		
		//read number of records, attributes, classes
		numberRecords = inFile.nextInt();
		numberAttributes = inFile.nextInt();
		numberClasses = inFile.nextInt();
		
		//read number of attribute values
		attributeValues = new int[numberAttributes];
		for (int i = 0; i < numberAttributes; i++)
			attributeValues[i] = inFile.nextInt();
	
		//list of records
		records = new ArrayList<Record>();
		
		//read each record
		for (int i = 0; i < numberRecords; i++)
		{
			//create attribute array
			int[] attributeArray = new int[numberAttributes];
			
			//read attributes and convert them to numerical form
			for (int j = 0; j < numberAttributes; j++)
			{
				String label = inFile.next();
				attributeArray[j] = convert(label, j+1);
			}
			
			//read class and convert it to numerical form
			String label = inFile.next();
			int className = convert(label);
			
			//create record
			Record record = new Record(attributeArray, className);
			
			//add record to list of records
			records.add(record);
		}
		
		inFile.close();
	}
	
	/********************************************************************************/

	//Method computes probability values necessary for Bayes classification
	public void computeProbability()
	{
		//compute class probabilities
		computeClassTable();
		
		//compute conditional probabilities
		computeTable();
	}
	
	/********************************************************************************/

	//Method computes class probabilities
	private void computeClassTable()
	{
		classTable = new double[numberClasses];
		
		//initialize class frequencies
		for (int i = 0; i < numberClasses; i++)
			classTable[i] = 0;
		
		//compute class frequencies
		for (int i = 0; i < numberRecords; i++)
			classTable[records.get(i).className-1] += 1;
		
		//normalize class frequencies
		for (int i = 0; i < numberClasses; i++)
			classTable[i] /= numberRecords;
	}
	
	/********************************************************************************/

	//Method computes conditional probabilities
	private void computeTable()
	{
		//array to store conditional probabilities
		table = new double[numberAttributes][][];
		
		//compute conditional probabilities of each attribute
		for (int i = 0; i < numberAttributes; i++)
			compute(i+1);
	}
	
	/********************************************************************************/

	//Method computes conditional probabilities of an attribute
	private void compute(int attribute)
	{
		//find number of attribute values
		int attributeValues = this.attributeValues[attribute-1];
		
		//create array to hold conditional probabilities
		table[attribute-1] = new double[numberClasses][attributeValues];
		
		//initialize conditional probabilities
		for (int i = 0; i < numberClasses; i++)
			for (int j = 0; j < attributeValues; j++)
				table[attribute-1][i][j] = 0;
		
		//compute class-attribute frequencies
		for (int k = 0; k < numberRecords; k++)
		{
			int i = records.get(k).className - 1;
			int j = records.get(k).attributes[attribute-1] - 1;
			table[attribute-1][i][j] += 1;
		}
		
		//compute conditional probabilities using laplace correction
		for (int i = 0; i < numberClasses; i++)
			for (int j = 0; j < attributeValues; j++)
			{
				double value = (table[attribute-1][i][j] + 1)/
								(classTable[i]*numberRecords + attributeValues);
				table[attribute-1][i][j] = value;
			}
	}
	
	/********************************************************************************/

	//Method classifies an attribute
	private int classify(int[] attributes)
	{
		double maxProbability = 0;
		int maxClass = 0;
		
		//for each class
		for (int i = 0; i < numberClasses; i++)
		{
			//find conditional probability of class given the attribute
			double probability = findProbability(i+1, attributes);
			
			//choose the class with the maximum probability
			if (probability > maxProbability)
			{
				maxProbability = probability;
				maxClass = i;
			}
		}
		
		//return maximum class
		return maxClass + 1;
	}
	
	/********************************************************************************/

	//Method computes conditional probability of a class for given attributes
	private double findProbability(int className, int[] attributes)
	{
		double value;
		double product = 1;
		
		//find product of conditional probabilities stored in stable
		for (int i = 0; i < numberAttributes; i++)
		{
			value = table[i][className-1][attributes[i]-1];
			product = product*value;
		}
		
		//multiply product and class probability
		return product*classTable[className-1];
	}
	
	/********************************************************************************/

	//Method reads test records from test file and writes classes
	//to classified file
	public void classifyData(String testFile, String classifiedFile)
	throws IOException
	{
		Scanner inFile = new Scanner(new File(testFile));
		PrintWriter outFile = new PrintWriter(new FileWriter(classifiedFile));
		
		//read number of records
		int numberRecords = inFile.nextInt();
		
		//read and classify each record
		for (int i = 0; i < numberRecords; i++)
		{
			//create attribute array
			int[] attributeArray = new int[numberAttributes];
			
			//read attributes and convert them to numberical form
			for (int j = 0; j < numberAttributes; j++)
			{
				String label = inFile.next();
				attributeArray[j] = convert(label, j+1);
			}
			
			//find class of attribute
			int className = classify(attributeArray);
			
			//write class label to file
			String label = convert(className);
			outFile.println(label);
		}
		
		inFile.close();
		outFile.close();
	}
	
	/********************************************************************************/

	private int convert(String label, int column)
	{
		int value;
		
		if (column == 1)
			if (label.equals("highschool")) value = 1; else value = 2;
		else if (column == 2)
			if (label.equals("smoker")) value = 1; else value = 2;
		else if (column == 3)
			if (label.equals("married")) value = 1; else value = 2;
		else if (column == 4)
			if (label.equals("male")) value = 1; else value = 2;
		else
			if (label.equals("work")) value = 1; else value = 2;

		//return numerical value
		return value;
	}

	//Method converts attribute values and class labels to numerical values
	//(application specific)
	private int convert(String label)
	{
		int value;

		if (label.equals("highrisk"))
			value = 1;
		else if (label.equals("mediumrisk"))
			value = 2;
		else if (label.equals("lowrisk"))
			value = 3;
		else 
			value = 4;
		
		return value;
	}
	
	/********************************************************************************/

	//Method converts numerical values to class labels (application specific)
	private String convert(int value)
	{
		String label;
		
		
		if (value == 1) 
			label = "highrisk";
		else if (value == 2) 
			label = "mediumrisk";
		else if (value == 3) 
			label = "lowrisk";
		else
			label = "undetermined";
		
		return label;	
	}
}
