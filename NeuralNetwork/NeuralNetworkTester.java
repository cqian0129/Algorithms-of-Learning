/**
 * Created by chengqian on 11/11/16.
 */
import java.io.*;

//program tests neural network
public class NeuralNetworkTester {
    //main method
    public static void main(String[] args) throws IOException
    {
        //construct neural network
        NeuralNetwork network = new NeuralNetwork();

        //load training data
        network.loadTrainingData("trainingfile");


        //set parameters of network
        network.setParameters(3, 1000, 2376, 0.6);
        
        //train network
        network.train();
        //network.validate("validationfile");

        //test network
        network.testData("inputfile", "outputfile");
    }
}
