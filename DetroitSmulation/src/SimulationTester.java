import java.io.IOException;

/**
 * Created by chengqian on 1/22/17.
 */
public class SimulationTester {
    public static void main(String[] args) throws IOException
    {
        Simulation s = new Simulation(101, 1000, 46235);
        s.run();
    }
}
