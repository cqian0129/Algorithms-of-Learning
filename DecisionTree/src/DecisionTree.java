/**
 * Created by young on 2017/2/2.
 */
import java.io.*;
import java.util.*;

//Decision tree class
public class DecisionTree {
    /*********************************************************************************************************/

    //Training record class
    private class Record
    {
        private int[] attributes;           //attributes of record
        private int className;              //class of record

        //constructor of record
        private Record(int[] attributes, int className)
        {
            this.attributes = attributes;       //assign attributes
            this.className = className;         //assign class
        }
    }

    /*********************************************************************************************************/

    //Decision tree node class
    private class Node
    {
        private String nodeType;            //node type - internal or leaf
        private int condition;              //condition if node is internal
        private int className;              //class name if node is leaf
        private Node left;                  //left branch
        private Node right;                 //right branch

        //Constructor of node
        private Node(String nodeType, int value, Node left, Node right)
        {
            this.nodeType = nodeType;               //assign node type
            this.left = left;                       //assign left branch
            this.right = right;                     //assign right branch

            if (nodeType.equals("internal"))
            {                                       //if node is internal
                condition = value;                  //assign condition to node
                className = -1;                     //node has no class name
            }
            else
            {                                       //if node is leaf
                className = value;                  //assign class name to node
                condition = -1;                     //node has no condition
            }
        }
    }

    /*********************************************************************************************************/

    private Node root;                              //root of decision tree
    private ArrayList<Record> records;              //list of training records
    private ArrayList<Integer> attributes;          //list of attributes
    private int numberRecords;                      //number of training records
    private int numberAttributes;                   //number of attributes
    private int numberClasses;                      //number of classes

    /*********************************************************************************************************/

    //Constructor of decision tree
    public DecisionTree()
    {
        root = null;                                //initialize root, records,
        records = null;                             //attributes to empty
        attributes = null;
        numberRecords = 0;                          //number of records, attributes,
        numberAttributes = 0;                       //classes are zero
        numberClasses = 0;
    }

    //Method builds decision tree for the whole training data
    public void buildTree()
    {
        root = build(records, attributes);          //initial call to build method

        //byLevel(root);
    }

    //Method print the binary tree
    public void byLevel(Node root) {
        Queue<Node> level = new LinkedList<>();
        level.add(root);
        while(!level.isEmpty()){
            Node node = level.poll();
            if (node.nodeType.equals("internal"))
                System.out.print(node.condition + " |condition| ");
            else
                System.out.print(node.className + " |classname| ");

            if(node.left!= null)
                level.add(node.left);
            if(node.right!= null)
                level.add(node.right);
        }
        System.out.println();
    }

    /*********************************************************************************************************/

    //Method builds decision tree from given records and attributes, returns root of tree that is built
    private Node build(ArrayList<Record> records, ArrayList<Integer> attributes)
    {
        //root node is empty initially
        Node node = null;

        //if all records have same class
        if (sameClass(records))
        {
            //find class name
            int className = records.get(0).className;

            //node is leaf with that class
            node = new Node("leaf", className, null, null);
        }
        //if there are no attributes
        else if (attributes.isEmpty())
        {
            //find majority class of records
            int className = majorityClass(records);

            //node is leaf with that class
            node = new Node ("leaf", className, null, null);
        }
        else
        {
            //find best condition for current records and attributes
            int condition = bestCondition(records, attributes);

            //collect all records which have 0 for condition
            ArrayList<Record> leftRecords = collect(records, condition, 0);

            //collect all records which have 1 for condition
            ArrayList<Record> rightRecords = collect(records, condition, 1);

            //if either left records or right records is empty
            if (leftRecords.isEmpty() || rightRecords.isEmpty())
            {
                //find majority class of records
                int className = majorityClass(records);
                //node is leaf with that class
                node = new Node("leaf", className, null, null);
            }
            else
            {
                //create copies of current attributes
                ArrayList<Integer> leftAttributes = copyAttributes(attributes);
                ArrayList<Integer> rightAttributes = copyAttributes(attributes);

                //remove best condition from current attributes
                leftAttributes.remove(new Integer(condition));
                rightAttributes.remove(new Integer(condition));

                //create internal node with best condition
                node = new Node("internal", condition, null, null);

                //create left subtree recursively
                node.left = build(leftRecords, leftAttributes);

                //create right subtree recursively
                node.right = build(rightRecords, rightAttributes);
            }
        }

        //return root node of tree that is built
        return node;
    }



    /*********************************************************************************************************/

    //Method decides whether all records have the same class
    private boolean sameClass(ArrayList<Record> records)
    {
        //compare class of each record with class of first record
        for (int i = 0; i < records.size(); i++)
            if (records.get(i).className != records.get(0).className)
                return false;

        return true;
    }

    /*********************************************************************************************************/

    //Method finds the majority class of records
    private int majorityClass(ArrayList<Record> records)
    {
        int[] frequency = new int[numberClasses];       //frequency array

        for (int i = 0; i < numberClasses; i++)         //initialize frequencies
            frequency[i] = 0;

        for (int i = 0; i < records.size(); i++)        //find frequencies of classes
            frequency[records.get(i).className - 1] += 1;

        int maxIndex = 0;                               //find class with maximum
        for (int i = 0; i < numberClasses; i++)         //frequency
            if (frequency[i] > frequency[maxIndex])
                maxIndex = i;

        return maxIndex + 1;                            //return majority class
    }

    /*********************************************************************************************************/

    //Method collects records that have a given value for a given attribute
    private ArrayList<Record> collect(ArrayList<Record> records, int condition, int value)
    {
        //initialize collection
        ArrayList<Record> result = new ArrayList<Record>();

        //go thru records and collect those that have given value
        //for given attribute
        for (int i = 0; i < records.size(); i++)
            if (records.get(i).attributes[condition-1] == value)
                result.add(records.get(i));

        //return collection
        return result;
    }

    /*********************************************************************************************************/

    //Method makes copy of list of attributes
    private ArrayList<Integer> copyAttributes(ArrayList<Integer> attributes)
    {
        //initialize copy list
        ArrayList<Integer> result = new ArrayList<Integer>();

        //insert all attributes into copy list
        for (int i = 0; i < attributes.size(); i++)
            result.add(attributes.get(i));

        //return copy list
        return result;
    }

    /*********************************************************************************************************/

    //Method finds best condition for given records and attributes
    private int bestCondition(ArrayList<Record> records, ArrayList<Integer> attributes)
    {
        //evaluate first attribute
        double minValue = evaluate(records, attributes.get(0));
        int minIndex = 0;

        //go thru all attributes
        for (int i = 0; i < attributes.size(); i++)
        {
            double value = evaluate(records, attributes.get(i));        //evaluate attribute

            if (value < minValue)
            {                                                   //if value is less than
                minValue = value;                               //current minimum then
                minIndex = i;                                   //update minimum
            }
        }

        return attributes.get(minIndex);                        //return best attribute
    }

    /*********************************************************************************************************/

    //Method evaluates an attributes using weighted average entropy
    private double evaluate(ArrayList<Record> records, int attribute)
    {
        //collect records that have attribute value 0
        ArrayList<Record> leftRecords = collect(records, attribute, 0);

        //collect records that have attribute value 1
        ArrayList<Record> rightRecords = collect(records, attribute, 1);

        //find class entropy of left records
        double entropyLeft = entropy(leftRecords);

        //find class entropy of right records
        double entropyRight = entropy(rightRecords);

        //find weighted average entropy
        double average = entropyLeft*leftRecords.size()/records.size() +
                         entropyRight*rightRecords.size()/records.size();

        //return weighted average entropy
        return average;
    }

    /*********************************************************************************************************/

    //Method finds class entropy of records using gini measure
    private double entropy(ArrayList<Record> records)
    {
        double[] frequency = new double[numberClasses];         //frequency array

        for (int i = 0; i < numberClasses; i++)                 //initialize frequencies
            frequency[i] = 0;

        for (int i = 0; i < records.size(); i++)                //find class frequencies
            frequency[records.get(i).className - 1] += 1;

        double sum = 0;                                         //find sum of frequencies
        for (int i = 0; i < numberClasses; i++)
            sum = sum + frequency[i];

        for (int i = 0; i < numberClasses; i++)                 //normalize frequencies
            frequency[i] = frequency[i]/sum;

        sum = 0;
        for (int i = 0; i < numberClasses; i++)                 //find sum of squares
            sum = sum + frequency[i]*frequency[i];

        return 1-sum;
    }
    /*
    //Method finds class entropy of records using Class error's measure
    private double entropy(ArrayList<Record> records)
    {
        double[] frequency = new double[numberClasses];         //frequency array

        for (int i = 0; i < numberClasses; i++)                 //initialize frequencies
            frequency[i] = 0;

        for (int i = 0; i < records.size(); i++)                //find class frequencies
            frequency[records.get(i).className - 1] += 1;

        double sum = 0;                                         //find sum of frequencies
        for (int i = 0; i < numberClasses; i++)
            sum = sum + frequency[i];

        for (int i = 0; i < numberClasses; i++)                 //normalize frequencies
            frequency[i] = frequency[i]/sum;



        int index = max(frequency);
        return 1-frequency[index];
    }

    private int max (double[] frequency)
    {
        double maxValue = Double.MIN_VALUE;
        int index = 0;
        for (int i = 0; i < frequency.length; i++)
        {
            if (frequency[i] > maxValue)
            {
                //System.out.println(i);
                maxValue = frequency[i];
                index = i;
            }
        }
        return index;
    }*/
    /*********************************************************************************************************/

    //Method finds class of given attributes
    private int classify(int[] attributes)
    {
        //start at root node
        Node current = root;

        //go down the tree
        while (current.nodeType.equals("internal"))
        {                                                   //if attribute value
            if (attributes[current.condition - 1] == 0)     //of condition is 0
                current = current.left;                     //go to left
            else
                current = current.right;                     //else go to right
        }

        return current.className;                            //return class name when reaching leaf
    }

    //Method finds class of given attributes
    private int classify_leaveOneOut(int[] attributes, int a)
    {
        ArrayList<Integer> attributes_list = new ArrayList<Integer>();
        for (int i = 0; i< numberAttributes; i++)
            attributes_list.add(i+1);

        //start at root node
        Node current = build(takeAOut(a,records), attributes_list);

        //byLevel(current);
        //go down the tree
        while (current.nodeType.equals("internal"))
        {                                                   //if attribute value
            if (attributes[current.condition - 1] == 0)     //of condition is 0
                current = current.left;                     //go to left
            else
                current = current.right;                     //else go to right
        }

        return current.className;                            //return class name when reaching leaf
    }


    /*********************************************************************************************************/

    //Method loads training records from training file
    public void loadTrainingData(String trainingFile) throws IOException
    {
        Scanner inFile = new Scanner(new File(trainingFile));

        //read number of records, attributes, classes
        numberRecords = inFile.nextInt();
        numberAttributes = inFile.nextInt();
        numberClasses = inFile.nextInt();

        //empty list of records
        records = new ArrayList<Record>();

        //for each record
        for (int i = 0; i < numberRecords; i++)
        {
            //create attribute array
            int[] attributeArray = new int[numberAttributes];

            //for each attribute
            for (int j = 0; j < numberAttributes; j++)
            {
                //read attributes
                String label = inFile.next();
                //convert to binary
                attributeArray[j] = convert(label, j+1);
            }

            //read class and convert to integer value
            String label = inFile.next();
            int className = convert(label);

            //create record using attributes and class
            Record record = new Record(attributeArray, className);

            //add record to list
            records.add(record);
        }

        //create list of attributes
        attributes = new ArrayList<Integer>();
        for (int i = 0; i< numberAttributes; i++)
            attributes.add(i+1);

        inFile.close();
    }

    //Method take index=a record out
    private ArrayList<Record> takeAOut(int a, ArrayList<Record> records)
    {
        ArrayList<Record> list = new ArrayList<Record>();

        list.addAll(records);
        list.remove(a);

        return list;
    }
    /*********************************************************************************************************/

    //Method reads test records from test file and writes classified records
    //to classified file
    public void classifyData(String testFile, String classifiedFile) throws IOException
    {
        Scanner inFile = new Scanner(new File(testFile));
        PrintWriter outFile = new PrintWriter(new FileWriter(classifiedFile));

        //read number of records
        int numberRecords = inFile.nextInt();

        //for each record
        for (int i = 0; i < numberRecords; i++)
        {
            //create attribute array
            int[] attributeArray = new int[numberAttributes];

            //read attributes and convert to binary
            for (int j = 0; j < numberAttributes; j++)
            {
                String label = inFile.next();
                attributeArray[j] = convert(label, j+1);
            }

            //find class of attributes
            int className = classify(attributeArray);

            //find class name from integer value and write to output file
            String label = convert(className);
            outFile.println(label);
        }

        inFile.close();
        outFile.close();
    }

    /*********************************************************************************************************/

    //Method validates decision tree using validation file and displays error rate
    public void validate(String validationFile) throws IOException
    {
        Scanner inFile = new Scanner(new File(validationFile));

        //read number of records
        int numberRecords = inFile.nextInt();

        //initialize number of errors
        int numberErrors = 0;

        //for each record
        for (int i = 0; i < numberRecords; i++)
        {
            //create attribute array
            int[] attributeArray = new int[numberAttributes];

            //read attributes and convert to binary
            for (int j = 0; j < numberAttributes; j++)
            {
                String label = inFile.next();
                attributeArray[j] = convert(label, j+1);
            }

            //read actual class from validation file
            String label = inFile.next();
            int actualClass = convert(label);

            //find class predicted by decision tree
            int predictedClass = classify_leaveOneOut(attributeArray, i);

            //error if predicted and actual classes do not match
            if (predictedClass != actualClass)
                numberErrors += 1;
        }

        //find and print error rate
        double errorRate = 100.0*numberErrors/numberRecords;
        System.out.println("validation error: " + errorRate + " percent error");

        inFile.close();
    }

    public void trainingErr(String validationFile) throws IOException
    {
        Scanner inFile = new Scanner(new File(validationFile));

        //read number of records
        int numberRecords = inFile.nextInt();

        //initialize number of errors
        int numberErrors = 0;

        //for each record
        for (int i = 0; i < numberRecords; i++)
        {
            //create attribute array
            int[] attributeArray = new int[numberAttributes];

            //read attributes and convert to binary
            for (int j = 0; j < numberAttributes; j++)
            {
                String label = inFile.next();
                attributeArray[j] = convert(label, j+1);
            }

            //read actual class from validation file
            String label = inFile.next();
            int actualClass = convert(label);

            //find class predicted by decision tree
            int predictedClass = classify(attributeArray);

            //error if predicted and actual classes do not match
            if (predictedClass != actualClass)
                numberErrors += 1;
        }

        //find and print error rate
        double errorRate = 100.0*numberErrors/numberRecords;
        System.out.println("training error: " + errorRate + " percent error");

        inFile.close();
    }
    /*********************************************************************************************************/

    //Method converts attribute labels to binary values, hard coded for specific application
    private int convert(String label, int column)
    {
        int value;

        //convert attribute labels to binary values
        if (column == 1)
            if (label.equals("cs")) value = 0; else value = 1;
        else if (column == 2)
            if (label.equals("java")) value = 0; else value = 1;
        else if (column == 3)
            if (label.equals("c/c++")) value = 0; else value = 1;
        else if (column == 4)
            if (label.equals("gpa>3")) value = 0; else value = 1;
        else if (column == 5)
            if (label.equals("large")) value = 0; else value = 1;
        else
            if (label.equals("years<5")) value = 0; else value = 1;


        //return numerical value
        return value;
    }

    /*********************************************************************************************************/

    //Method converts class labels to integer values, hard coded for specific application
    private int convert(String label)
    {
        int value;

        //convert class labels to integer values
        if (label.equals("hire"))
            value = 1;
        else
            value = 2;

        //return integer value
        return value;
    }

    /*********************************************************************************************************/

    //Method converts integer values to class labels, hard coded for
    //specific application
    private String convert(int value)
    {
        String label;

        //convert integer values to class labels
        if (value == 1)
            label = "hire";
        else
            label = "no";

        //return class label
        return label;
    }
}
