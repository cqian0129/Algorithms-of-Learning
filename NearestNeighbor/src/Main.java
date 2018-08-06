/**
 * Created by chengqian on 2/2/17.
 */
import java.io.*;

public class Main {
    //main method
    public static void main(String[] args) throws IOException
    {
        //construct nestest neighbor classifier
        NearestNeighbor classifier = new NearestNeighbor();

        //load training data
        classifier.loadTrainingData("trainingfile");

        //classify data
        classifier.classifyData("testfile", "classfiedfile");

        classifier.trainingErr("validationfile");

        classifier.validate("validationfile");
    }
}
