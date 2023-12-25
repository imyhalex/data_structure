package edu.nyu.cs.Recursion.RecursiveBinarySearch;

// Rule: binary search needs the element in array is already sorteed in increasing order
public class RecursiveBinarySearch {
    public static int binarySearch(int[] list, int key) {
        int low = 0;
        int high = list.length - 1;
        return binarySearch(list, key, low, high);
    }

    private static int binarySearch(int[] list, int key, int low, int high) {
        if (low > high) return -low - 1; // The list has been exhausted without a match

        int mid = (low + high) / 2;
        if (key < list[mid]) return binarySearch(list, key, low, mid - 1);
        else if (key == list[mid]) return mid;
        else return binarySearch(list, key, mid + 1, high);
    }
}
