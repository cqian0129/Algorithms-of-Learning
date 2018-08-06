/*
 * Node.java
 * 
 * Define basic element in a tree
 * 
 * Cite: http://www.sanfoundry.com/java-program-construct-expression-tree-given-prefix-expression/
 * Based on it we made some changes to match our purpose.
 * 
 */

class Node
{
    public char data;
    public Node leftChild;
    public Node rightChild;
 
    public Node(char x)
    {
        data = x;
    }
 
    public void displayNode()
    {
        System.out.print(data);
    }
}
 