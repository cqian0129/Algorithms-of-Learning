/**
 * Created by blachcat on 2/26/17.
 */
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BinarySearchTree b = new BinarySearchTree();
        int[] a = {50,40,70,10,20,80,60,65,62};
        for (int i = 0; i < a.length; i++)
            b.insert(a[i]);

        //b.printM(b.root);

        if (b.searchR(40, b.root))
            System.out.println("true");
        else
            System.out.println("false");
        /*
        while (true) {
            System.out.println("Enter N value and seed value: ");
            Scanner scanner = new Scanner(System.in);
            int N = scanner.nextInt();
            int seed = scanner.nextInt();

            Random r = new Random(seed);

            BinarySearchTree b = new BinarySearchTree();
            for (int i = 0; i < N; i++){
                b.insert(r.nextInt(1000000000));        //10^9
            }

            int times = 1000000;        //10^6
            int[] array = new int[times];
            for (int i = 0; i < times; i++){
                array[i] = r.nextInt(1000000000);        //10^9
            }
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < times; i++){
                b.search(array[i]);
            }
            long endTime = System.currentTimeMillis();
            long totalTime = (endTime - startTime);
            System.out.println("how many nodes? " + b.countNodes());
            System.out.println("height of the tree: " + b.height());
            System.out.println("searching time : " + totalTime + "ms");
        }
        */
    }
}
