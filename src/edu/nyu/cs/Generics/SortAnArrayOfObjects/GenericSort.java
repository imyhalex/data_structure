package edu.nyu.cs.Generics.SortAnArrayOfObjects;

public class GenericSort {
    
    // Sort an array of comparable objects (a generic type version of selection sort)
    public static <E extends Comparable<E>> void sort(E[] list) {
        E currentMin;
        int currentMinIndex;

        for (int i = 0; i < list.length; i++) {
            currentMin = list[i];
            currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin.compareTo(list[j]) > 0) {
                    currentMin = list[j];
                    currentMinIndex = j;
                }
            }

            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
            }
        }
    }

    // Print an array of objects
    public static void printList(Object[] list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Integer[] intList = {2, 4, 3};

        Double[] doubleList = {3.4, 1.3, -22.1};

        Character[] charList = {'a', 'b', 'c', 'j', 'r'};

        System.out.print("Unsorted int array: ");
        printList(intList);
        System.out.print("Sorted int array: ");
        sort(intList);
        printList(intList);


        System.out.print("Unsorted double array: ");
        printList(doubleList);
        System.out.print("Sorted double array: ");
        sort(doubleList);
        printList(doubleList);


        System.out.print("Unsorted char array: ");
        printList(charList);
        System.out.print("Sorted cahr array: ");
        sort(charList);
        printList(charList);
    }
}
