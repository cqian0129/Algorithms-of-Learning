/**
 * Created by young on 2017/2/2.
 */
import java.io.*;

public class Tester {
    //main method
    public static void main(String[] args) throws IOException
    {
        DecisionTree d = new DecisionTree();

        d.loadTrainingData("trainingfile");

        d.classifyData("testfile", "classfiedfile");
    }
}
