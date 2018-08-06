/*
 * Conversion.java
 * 
 * Change an infix expression to a postfix expression.
 * 
 * Cite: http://www.sanfoundry.com/java-program-construct-expression-tree-given-prefix-expression/
 * Based on it we made some changes to match our purpose.
 * 
 */

class Conversion 
{
    private CharStack s;
    private String input;
    private String output = "";
 
    public Conversion(String str)
    {
        input = str;
        s = new CharStack(str.length());
    }
 
    // change the infix expression into posfix expression
    public String inToPost()
    {
        for (int i = 0; i < input.length(); i++)
        {
            char ch = input.charAt(i);
            switch (ch)
            {
                case '+':
                    gotOperator(ch, 1);
                    break;
                case '*':
                    gotOperator(ch, 2);
                    break;
                case '(':
                    s.push(ch);
                    break;
                case ')':
                    gotParenthesis();
                    break;
                case '~':
                    gotOperator(ch, 3);
                    break;
                default:
                	// it is a value character
                    output = output + ch;
            }
        }
        while (!s.isEmpty())
            output = output + s.pop();

        return output;
    }
 
    // deal with operator
    private void gotOperator(char opThis, int prec1)
    {
        while (!s.isEmpty())
        {
            char opTop = s.pop();
            if (opTop == '(')
            {
                s.push(opTop);
                break;
            } else if (opTop == '~')
            {
                output = output + opTop;
            }
            else
            {
                int prec2;
                if (opTop == '+' || opTop == '-')
                    prec2 = 1;
                else
                    prec2 = 2;
                if (prec2 < prec1)
                {
                    s.push(opTop);
                    break;
                } else
                    output = output + opTop;
            }
        }
        s.push(opThis);
    }
 
    // when it is a ')', keep popping out until find '('
    private void gotParenthesis()
    {
        while (!s.isEmpty())
        {
            char ch = s.pop();
            if (ch == '(')
                break;
            else
                output = output + ch;
        }
    }
}
