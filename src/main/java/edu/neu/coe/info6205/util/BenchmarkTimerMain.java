package edu.neu.coe.info6205.util;
import edu.neu.coe.info6205.sort.elementary.InsertionSort;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Supplier;

public class BenchmarkTimerMain {
    public static void main(String args[])
    {
        int n=1250;
        int runs=500;
        Random random=new Random();
        //Integer[] arr={5,3,1,7,2,9,3,7,1,4};
    for(int i=0;i<1;i++) {
        Integer[] array_sorted = new Integer[n];
        Integer[] array_random = new Integer[n];
        Integer[] array_reverse = new Integer[n];
        Integer[] array_partial = new Integer[n];


        for (int j = 0; j < n; j++) {
            array_random[j] = random.nextInt(n);
            array_sorted[j] = array_random[j];
//            System.out.println(array_sorted[j]);
//            System.out.println("------------------------");

        }
        Arrays.sort(array_sorted);
        for(int l=0;l<array_sorted.length;l++)
        {
            if(l<=n/2)
            {
                array_partial[l]=array_sorted[l];
            }
            else{
                array_partial[l]=array_random[l];
            }
            array_reverse[n - l-1] = array_sorted[l];
        }

        InsertionSort ins=new InsertionSort();
        Benchmark_Timer<Integer[]> timer_r=new Benchmark_Timer<>("Benchmarking",null,(x)->ins.sort(x,0,x.length),null);
        Benchmark_Timer<Integer[]> timer_r2=new Benchmark_Timer<>("Benchmarking",null,(x)->ins.sort(x,0,x.length),null);
        Benchmark_Timer<Integer[]> timer_r3=new Benchmark_Timer<>("Benchmarking",null,(x)->ins.sort(x,0,x.length),null);
        Benchmark_Timer<Integer[]> timer_r4=new Benchmark_Timer<>("Benchmarking",null,(x)->ins.sort(x,0,x.length),null);
        Supplier sup_reverse=() -> array_reverse;
        Supplier sup_random=() -> array_random;

        Supplier sup_sorted=() -> array_sorted;
        Supplier sup_partial=() -> array_partial;

        double time_reverse=timer_r.runFromSupplier(sup_reverse,runs);
        System.out.println("When n is "+n+" mean time is "+time_reverse+" for a reverse array");
        System.out.println();
        double time_random=timer_r.runFromSupplier(sup_random,runs);

        System.out.println("When n is "+n+" mean time is "+time_random+" for a random array");
        System.out.println();

        double time_sorted=timer_r.runFromSupplier(sup_sorted,runs);
        System.out.println("When n is "+n+" mean time is "+time_sorted+" for a sorted array");
        System.out.println();
        double time_partial=timer_r.runFromSupplier(sup_partial,runs);
        System.out.println("When n is "+n+" mean time is "+time_partial+" for a partial sorted array");
        System.out.println();







    }

    }
    final static LazyLogger logger= new LazyLogger(Benchmark_Timer.class);
}
