package edu.nyu.cs.BasicSort;

import java.util.Arrays;

public class Main {
    
    public static void main(String[] args) {

        int[] arr = {64, 34, 25, 12, 22, 11, 90};
        int[] arr2 = {64, 34, 25, 12, 22, 11, 90};

        Sorts sort = new Sorts();

        sort.bubbleSort(arr);
        sort.selectionSort(arr2);

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(arr2));
    }
}
