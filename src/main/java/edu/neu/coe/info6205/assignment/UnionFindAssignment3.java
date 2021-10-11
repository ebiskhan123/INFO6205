package edu.neu.coe.info6205.assignment;

import edu.neu.coe.info6205.union_find.UF_HWQUPC;

import java.util.Random;
import java.util.Scanner;

public class UnionFindAssignment3 {

    UF_HWQUPC uf ;
    Random random;

    public int count(int n){
        this.uf = new UF_HWQUPC(n);
        this.random = new Random();
        int result =0;

        while(uf.components()!=1)
        {
            int node1= random.nextInt(n);
            int node2= random.nextInt(n);
            result++;
            if(!uf.connected(node1,node2))
                uf.union(node1,node2);
        }
        return  result;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        UnionFindAssignment3 ufa = new UnionFindAssignment3();
        int input = 0;
        while(true){
            input = scn.nextInt();
            if(input ==-1)
                break;
            System.out.println("Number of pairs for " + input + " is " + ufa.count(input));
        }
    }
}
