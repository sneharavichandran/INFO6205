package edu.neu.coe.info6205.sort.par;


import java.io.*;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * This code has been fleshed out by Ziyao Qiao. Thanks very much.
 * TODO tidy it up a bit.
 */
public class Main {

    public static void main(String[] args) {
        processArgs(args);
        //System.out.println("Degree of parallelism: " + ForkJoinPool.getCommonPoolParallelism());
        Random random = new Random();
        int[] array = new int[2000000];
        int array_size = array.length;
        ArrayList<Long> timeList = new ArrayList<>();
        System.out.println("Enter the cutoff value");
        Scanner s = new Scanner(System.in);
        int cutoff=s.nextInt();
        //int cutoff=Integer.ParseInt(BufferedReader br= new BufferedReader(new InputStreamReader(System.in)));
        for(int th=1;th<=16;th=th*2)
        {
            ForkJoinPool myPool = new ForkJoinPool(th);
            double min = Integer.MAX_VALUE;
            int bestCutoff = 0;
            double avg = 0;
            for (int j = 50; j <100; j++) {
            ParSort.cutoff = cutoff * (j + 1);
            // for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
            long time;
            long startTime = System.currentTimeMillis();

            for (int t = 0; t < 10; t++) {
                for (int i = 0; i < array.length; i++) array[i] = random.nextInt(10000000);
                ParSort.sort(array, 0, array.length, myPool);
            }
            long endTime = System.currentTimeMillis();
            time = (endTime - startTime);
            avg += (time/10);
            if((time/10) < min){
                bestCutoff = cutoff * (j + 1);
                min = time/10;
            }
            timeList.add(time);
            //System.out.println("cutoff：" + (ParSort.cutoff) + "\t\t10times Time:" + time + "ms");
        }
        System.out.println("For threads " + th + " min time = " + min + " at cutoff " + bestCutoff + " and average time is = " + avg / 50 + "ms");
    }
        try {
            FileOutputStream fis = new FileOutputStream("./src/result.csv");
            OutputStreamWriter isr = new OutputStreamWriter(fis);
            BufferedWriter bw = new BufferedWriter(isr);
            int j = 50;
            int threadcounter=1;
            for (long i : timeList) {
                String content = (double) cutoff*(j+1) / array_size + "," + (double) i / 10 + "," + threadcounter + "\n";
                j++;
                if(j==100)
                {
                    j=50;
                    threadcounter=threadcounter*2;
                }
                bw.write(content);
                bw.flush();
            }
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void processArgs(String[] args) {
        String[] xs = args;
        while (xs.length > 0)
            if (xs[0].startsWith("-")) xs = processArg(xs);
    }

    private static String[] processArg(String[] xs) {
        String[] result = new String[0];
        System.arraycopy(xs, 2, result, 0, xs.length - 2);
        processCommand(xs[0], xs[1]);
        return result;
    }

    private static void processCommand(String x, String y) {
        if (x.equalsIgnoreCase("N")) setConfig(x, Integer.parseInt(y));
        else
            // TODO sort this out
            if (x.equalsIgnoreCase("P")) {//noinspection ResultOfMethodCallIgnored
                ForkJoinPool.getCommonPoolParallelism();

            }
    }
    private static void setConfig(String x, int i) {
        configuration.put(x, i);
    }
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private static final Map<String, Integer> configuration = new HashMap<>();
}
