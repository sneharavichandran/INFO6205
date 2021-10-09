package edu.neu.coe.info6205.union_find;
import java.util.Random;
public class UFmain {
    public static void main(String[] args)
    {
        /*arraysites has n values*/
        int arraysites[]={5000,10000,20000,40000,80000,160000,320000};
for(int i=0;i<arraysites.length;i++) {
    int run=0;
    int n1=0,n2=0;
    UF_HWQUPC compobj = new UF_HWQUPC(arraysites[i], true);
    Random random = new Random();
    while (true) {
        run++;
        n1 = random.nextInt(arraysites[i]);
        //System.out.println(n1);
        n2 = random.nextInt(arraysites[i]);
        //System.out.println(n2);
        if (compobj.connected(n1, n2) == false) {
            compobj.union(n1, n2);
        }
        if (compobj.components() == 1) {
            System.out.println(run+" pairs generated for " +arraysites[i] + " sites");
            break;
        }
    }
}
    }
}
