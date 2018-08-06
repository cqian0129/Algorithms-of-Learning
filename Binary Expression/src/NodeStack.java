/*
 * NodeStack.java
 * 
 * Stack for nodes. Used in constructing a tree from a postfix expression.
 * 
 * Cite: http://www.sanfoundry.com/java-program-construct-expression-tree-given-prefix-expression/
 * Based on it we made some changes to match our purpose.
 * 
 */

class NodeStack
{
    private Node[] a;
    private int    top, m;
 
    public NodeStack(int max)
    {
        m = max;
        a = new Node[m];
        top = -1;
    }
 
    public void push(Node key)
    {
        a[++top] = key;
    }
 
    public Node pop()
    {
        return (a[top--]);
    }
 
    public boolean isEmpty()
    {
        return (top == -1);
    }
}