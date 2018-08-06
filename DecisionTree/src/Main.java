/**
 * Created by young on 2017/2/2.
 */
import java.io.*;

public class Main {
    //main method
    public static void main(String[] args) throws IOException
    {
        DecisionTree d = new DecisionTree();

        d.loadTrainingData("trainingfile");

        d.buildTree();

        d.classifyData("testfile", "classfiedfile");

        d.trainingErr("validationfile");

        d.validate("validationfile");
    }
}
