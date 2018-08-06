/*
 * CharStack.java
 * 
 * Stack for chars. Used in changing a infix expression to a postfix expression.
 * 
 * Cite: http://www.sanfoundry.com/java-program-construct-expression-tree-given-prefix-expression/
 * Based on it we made some changes to match our purpose.
 * 
 */

class CharStack
{
    private char[] a;
    private int    top, m;
 
    public CharStack(int max)
    {
        m = max;
        a = new char[m];
        top = -1;
    }
 
    public void push(char key)
    {
        a[++top] = key;
    }
 
    public char pop()
    {
        return (a[top--]);
    }
 
    public boolean isEmpty()
    {
        return (top == -1);
    }
}
