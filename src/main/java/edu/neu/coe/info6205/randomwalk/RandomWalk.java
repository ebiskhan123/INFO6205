/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomWalk {

    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    /**
     * Private method to move the current position, that's to say the drunkard moves
     *
     * @param dx the distance he moves in the x direction
     * @param dy the distance he moves in the y direction
     */
    private void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    /**
     * Perform a random walk of m steps
     *
     * @param m the number of steps the drunkard takes
     */
    private void randomWalk(int m) {
        while(m-->0){
            randomMove();
        }
    }

    /**
     * Private method to generate a random move according to the rules of the situation.
     * That's to say, moves can be (+-1, 0) or (0, +-1).
     */
    private void randomMove() {
        boolean ns = random.nextBoolean();
        int step = random.nextBoolean() ? 1 : -1;
        move(ns ? step : 0, ns ? 0 : step);
    }

    /**
     * Method to compute the distance from the origin (the lamp-post where the drunkard starts) to his current position.
     *
     * @return the (Euclidean) distance from the origin to the current position.
     */
    public double distance() {
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    /**
     * Perform multiple random walk experiments, returning the mean distance.
     *
     * @param m the number of steps for each experiment
     * @param n the number of experiments to run
     * @return the mean distance
     */
    public static double randomWalkMulti(int m, int n) {
        double totalDistance = 0;
        for (int i = 0; i < n; i++) {
            RandomWalk walk = new RandomWalk();
            walk.randomWalk(m);
            totalDistance = totalDistance + walk.distance();
        }
        return totalDistance / n;
    }

    public static void main(String[] args) {
        if (args.length == 0)
            throw new RuntimeException("Syntax: RandomWalk steps [experiments]");

        List<Double> list = new ArrayList<Double>();// list for plotting
        List<Double> sqroot = new ArrayList<Double>();// list for plotting
        List<Integer> numbers = new ArrayList<Integer>();// list for plotting
        for( String arg : args){
            int m = Integer.parseInt(arg);
            int count =10;
            double totalMeanDistance = 0d;
            while(count-- >0) {
                int n = 30;
                double meanDistance = randomWalkMulti(m, n);
                totalMeanDistance += meanDistance;
                System.out.println(" Count : " + count + " " +  m + " steps: with distance : " + meanDistance + " over " + n + " experiments");
            }
            list.add(totalMeanDistance/10);// list for plotting
            numbers.add(m);// list for plotting
            sqroot.add(Math.sqrt(m));// list for plotting
        }

        System.out.println(list);// list for plotting
        System.out.println(numbers);// list for plotting
        System.out.println(sqroot);// list for plotting
    }

}
