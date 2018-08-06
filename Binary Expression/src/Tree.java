/*
 * Tree.java
 * 
 * Construct a tree from an infix expression. 
 * Get combined (from 2 expressions) and sorted variables without duplication.
 * Get result of a value set.
 * Generate value sets based on number of variables.
 * 
 * function void insert(String s) is cited from: 
 * http://www.sanfoundry.com/java-program-construct-expression-tree-given-prefix-expression/
 * Based on it we made some changes to match our purpose.
 * 
 */
class Tree
{
    private Node root;
    
    public String str = "";
    char[] source;
    
    public Tree()
    {
        root = null;
    }
 
    // construct the tree
    public void insert(String s)
    {
        
        //change the infix expression to postfix expression
        Conversion c = new Conversion(s);
        s = c.inToPost();
        
        NodeStack stk = new NodeStack(s.length());
        
        s = s + "#"; // "#" is the symbol for the end
        int i = 0;
        char symbol = s.charAt(i);
        Node newNode;
        while (symbol != '#')
        {
            if (symbol >= 'A' && symbol <= 'Z' || symbol >= 'a' && symbol <= 'z')
            {	
            	// if current symbol is a value, push it to stack
                newNode = new Node(symbol);
                stk.push(newNode);
                
                // record the value
                str = str + symbol;
                
            } else if (symbol == '+' || symbol == '*')  
            {
            	// if current symbol is AND or OR, pop 2 operands
                Node ptr1 = stk.pop();
                Node ptr2 = stk.pop();
                
                // construct left child and right child for current node
                newNode = new Node(symbol);
                newNode.leftChild = ptr2;
                newNode.rightChild = ptr1;
                
                // push current node back to stack
                stk.push(newNode);
            }
            else if (symbol == '~')
            {
            	// if current symbol is NOT, pop one operand, and make it right child
                Node ptr = stk.pop();
                newNode = new Node(symbol);
                newNode.rightChild = ptr;
                stk.push(newNode);
            }
            // get next child
            symbol = s.charAt(++i);
        }
        
        // get root node
        root = stk.pop();

    }

 
    /*
     * Function: 
     * (1) combine variables from another expression which is str2
     * (2) remove repeated values in str
     * (3) sort variables and record it in source
     * For example:  
     * for a expression like 'c+a+b+b', str = 'cabb'
     * this function can get 'abc' and record it in char[] source
     */
    public void handleVari(String str2)
    {   
    	str = str + str2;

        int len = str.length();
        int k = 0;
        int count = 0;

        char[] tempSource = str.toCharArray();
        
        // remove same char
        for(int i=0;i<len;i++)
        {
                k=i+1;
                while(k<len-count)
                {
                    if(tempSource[i]==tempSource[k])
                    {
                        for(int j=k;j<len-1;j++)
                        {
                        	tempSource[j] = tempSource[j+1];
                        }
                        count++;
                        k--;
                    }
                k++;
                }
             
        }
        
        // change the char array to String
        String tempStr ="";
        
        for(int i=0;i<len-count;i++)
        {
        	tempStr+=String.valueOf(tempSource[i]);
        }
        
        //sort the string
        int size = tempStr.length();
        
        source = tempStr.toCharArray();
   
        char temp;
        for (int i = 0; i < size; i++)
        {
            for (int j = size - 1; j > i; j--) 
            {
                if (source[j] < source[j - 1]) 
                {
                 temp = source[j];
                 source[j] = source[j - 1];
                 source[j - 1] = temp;
                }
            }
        }   
    }

    /*
     * Function: generate the input value set, and get the result set
     * For example: when there are 2 variables, generate {'0','0'}, {'0','1'}, {'1','0'} and {'1','1'} 
     */
    public boolean[] getResult ()
    {   


        int l = source.length;
        int valueSetNumber = (int) Math.pow(2,l);
        boolean[] resultSet = new boolean[valueSetNumber];

        // construct values sets
        char[] dataSet = new char[l]; // Data of each set, for example {'0','0'}
        for(int i=0;i<valueSetNumber;i++) // get every data set
        {
            String binary = Integer.toBinaryString(i); // change integer to binary
                                                    
            char[] binaryTemp = binary.toCharArray();
            
            //change 10 to 0010
            int m = binaryTemp.length-1;
            for(int k=l-1; k>=0; k--){
                if(k>=l-binaryTemp.length)
                {
                    dataSet[k] = binaryTemp[m];
                    m--;
                }
                else
                    dataSet[k] = '0';
            }
            
            // unite the value char and boolean value 
            Object[][] valueUnit = new Object[l][2];
            for(int j=0; j<l; j++){
            	valueUnit[j][0] = source[j];
            	valueUnit[j][1] = dataSet[j];
            }
            
            // get the result of current value unit
            resultSet[i] = evaluate(root,valueUnit);

        }
        
        return resultSet;
    }

    /*
     * Function: by computing the tree, get the result for a single case (data set)
     * For example: for a tree presents 'a+b' and a value unit of {'a','0';'b','0'} return false. 
     */
    private boolean evaluate(Node node, Object[][] valueUnit)
    {
        boolean result = false;
        int l = source.length;
        
        // check whether it is a leaf node 
        if(node.leftChild == null && node.rightChild == null)
        {
        	for(int i = 0; i < l;i++)
        	{
        		// find the corresponding value according to node.data
        		if(node.data==(char)valueUnit[i][0])
        		{
        			if((char)valueUnit[i][1]=='0')
        				
        				result = false;
        			else
        				result = true;
        		}
        	}
        }
        else  // it is a operator
        {   
            char operator = node.data;
            boolean left = false;
            boolean right = false;

            if (operator == '~')
            {
            	// NOT operator only has right child
                right = evaluate(node.rightChild,valueUnit);
            }
            else
            {
            	// evaluate both children
            	left = evaluate(node.leftChild,valueUnit);
            	right = evaluate(node.rightChild,valueUnit);
            }

            // do the operation
            switch (operator)
            {
                case '*': result = left && right; break;
                case '+': result = left || right; break;
                case '~': result = !right; break;
            }

        }

        return result;
    }

}
