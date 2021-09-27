package edu.neu.coe.info6205.assignment;

import edu.neu.coe.info6205.sort.elementary.InsertionSort;
import edu.neu.coe.info6205.util.Benchmark_Timer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BenchmarkAssignment2 {

    private static Benchmark_Timer<Integer[]> btm;

    public static Double log2(Double x)
    {
        return  Math.log(x) ;
    }


    public static void main(String[] args) {
        btm = new Benchmark_Timer<Integer[]>("Insertion Sort",(x)-> new InsertionSort().sort(x,true));
        List<Double> sortedList = new ArrayList<Double>();
        List<Double> reversedList = new ArrayList<Double>();
        List<Double> partiallySortedList = new ArrayList<Double>();
        List<Double> randomList = new ArrayList<Double>();

        List<Integer> nList = new ArrayList<Integer>();

        for( int i = 100; i <= 6400 ; i*=2){
            Integer[] baseArr = new Integer[i];
            nList.add(i);

            for(int j = 0 ; j < i ; j++){
                baseArr[j] = j;
            }
            Integer[] sortedArr = new Integer[i];
            Integer[] reversedArr = new Integer[i];
            Integer[] partiallysortedArr = new Integer[i];
            Integer[] randomArr = new Integer[i];

            Random random = new Random();

            //sorted array
            sortedArr = baseArr;

            //reversed array
            for(int j = 0 ; j < i; j++){
                reversedArr[i -1-j] = j;
            }

            // partially sorted
            for(int j=0;j<i/2;j++){
                partiallysortedArr[j]=0;
            }
            for( int j = i/2 ; j < i ; j++){
                partiallysortedArr[j] = random.nextInt(i)+1;
            }

            //random array
            for(int j = 0 ; j < i; j++){
                randomArr[j] = random.nextInt(i);
            }

            double avgTimeTakenSorted = btm.run(sortedArr,100);
            double avgTimeTakenReversed = btm.run(reversedArr,100);
            double avgTimeTakenPartial = btm.run(partiallysortedArr,100);
            double avgTimeTakenRandom = btm.run(randomArr,100);

            sortedList.add(avgTimeTakenSorted);
            randomList.add(avgTimeTakenRandom);
            partiallySortedList.add(avgTimeTakenPartial);
            reversedList.add(avgTimeTakenReversed);
            System.out.println("Average time to sort a sorted array  of size " + i + " is : " + avgTimeTakenSorted);
            System.out.println("Average time to sort a reversed array  of size " + i + " is : " + avgTimeTakenReversed);
            System.out.println("Average time to sort a partially sorted array  of size " + i + " is : " + avgTimeTakenPartial);
            System.out.println("Average time to sort a random array of size " + i + " is : " + avgTimeTakenRandom);


        }
        System.out.println("List of size of N : " + nList);
        System.out.println("Sorted Time List :" + sortedList);
        System.out.println("Reversed Time List :" + reversedList);
        System.out.println("Partially Sorted Time List :" + partiallySortedList);
        System.out.println("Random Time List :" + randomList);

    }
}
