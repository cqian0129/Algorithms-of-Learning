import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException
    {
	// write your code here

        Components s = new Components("file7");

        s.compute();

        s.display("outputfile");
    }
}
