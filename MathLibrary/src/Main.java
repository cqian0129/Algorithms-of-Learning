/**
 * Created by young on 2017/2/10.
 */
import java.lang.Math;
public class Main {
    public static void main(String[] args){
        MathLibrary m = new MathLibrary();
        //int[] a = {3,2,10};
        //int[] b = {3,1,2};
        //int[] c = {5,18,9};
        //int[] d = {2,5,4};
        //int[] e = {4,3,2};
        //int[] f = {4,17,12};

        int[] a = {1,0,0,1,0};
        int[] b = {0,1,0,1,0};
        int[] c = {1,1,1,0,0};
        int[] d = {0,1,1,1,0};
        int[] e = {1,0,0,1,0};

        int[] g = {1,0,1,0,1};

        //System.out.println(m.matching(g,a));
        //System.out.println(m.matching(g,b));
        //System.out.println(m.matching(g,c));
        //System.out.println(m.matching(g,d));
        //System.out.println(m.matching(g,e));
        //System.out.println(m.taxi(g,f));
        //System.out.println((1/7.48)+(1/9.17));
        System.out.println(0.3*m.logTwo(0.3)+0.5*m.logTwo(0.5)+0.2*m.logTwo(0.2));
    }

}
