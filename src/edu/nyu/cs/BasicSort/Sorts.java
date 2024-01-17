package edu.nyu.cs.BasicSort;

import java.util.Arrays;

public class Sorts {

    public Sorts() {

    }
    
    public void bubbleSort(int[] array) {
        for (int i = array.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public void selectionSort(int[] array) {
        for (int i = 0 ; i < array.length; i++) {
            int minVal = array[i];
            int minValIndex = i;

            for (int j = i + 1; j < array.length; j++) {
                if (minVal > array[j]) {
                    minValIndex = j;
                }
            }

            if (minValIndex != i) {
                int temp = array[minValIndex];
                array[minValIndex] = array[i];
                array[i] = temp;
            }
        }
    }

    // Insertion sort is a method that always starts with the second item in the array and compare the item before it
    public void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;

            // Have to check the j > -1 first, else results in error
            while (j > - 1 && temp < array[j]) {
                array[j + 1] = array[j];
                array[j] = temp;
                j--; // Looking Backward
            }
        }
    }

    // Merge : it only works when arrays are sorted
    public int[] merge(int[] arr1, int[] arr2) {
        int[] combined = new int[arr1.length + arr2.length];
        int index = 0;
        int i = 0; 
        int j = 0;

        while (i < arr1.length && j < arr2.length) {
            if (arr1[i] < arr2[j]) {
                combined[index++] = arr1[i++];
            } else {
                combined[index++] = arr2[j++];
            }
        }

        while (i < arr1.length) {
            combined[index++] = arr1[i++];
        }

        while (j < arr2.length) {
            combined[index++] = arr2[j++];
        }

        return combined;
    }

    // Merge Sort
    public int[] mergeSort(int[] arr) {
        if (arr.length == 1) return arr; // Pop out from the call stack

        int midIndex = arr.length / 2;
        int[] left = mergeSort(Arrays.copyOfRange(arr, 0, midIndex));
        int[] right = mergeSort(Arrays.copyOfRange(arr, midIndex, arr.length));

        return merge(left, right);
    }

    /* Quick Sort */

    private void swap(int[] array, int firstIndex, int secondIndex) {
        int temp = array[firstIndex];
        array[firstIndex] = array[secondIndex];
        array[secondIndex] = temp;
    }

    // Helper pivot method
    private int pivot(int[] array, int pivotIndex, int endIndex) {
        int swapIndex = pivotIndex;

        for (int i = pivotIndex + 1; i <= endIndex; i++) {
            if (array[i] < array[pivotIndex]) {
                swapIndex++;
                swap(array, swapIndex, i);
            }
        }
        swap(array, pivotIndex, swapIndex);

        return swapIndex; // return the real pivot index
    }

    private void quickSortHelper(int[] array, int left, int right) {
        if (left < right) {
            int pivotIndex = pivot(array, left, right);
            quickSortHelper(array, left, pivotIndex - 1);
            quickSortHelper(array, pivotIndex + 1, right);
        }
    }

    public void quickSort(int[] array) {
        quickSortHelper(array, 0, array.length - 1);
    }
}
