package edu.nyu.cs.LinkedList.LinkedListUdemy;

public class Main {
    public static void main(String[] args) {
        // Test 1: Normal case
        LinkedList list = new LinkedList(1);
        list.append(3);
        list.append(5);
        list.append(8);
        list.append(5);
        list.append(10);
        list.append(2);
        list.append(1);
        
        System.out.println("Original list:");
        list.printList();

        list.partitionList(5);
        System.out.println("Partitioned list:");
        list.printList();

        LinkedList list2 = new LinkedList(1);
        list.append(3);
        list.append(5);
        list.append(8);
        list.append(5);
        list.append(10);
        list.append(2);
        list.append(1);

        // System.out.println("Original list2: ");
        // list2.printList();

        // list2.reverseBetween(1, 3);
        // System.out.println("After reversed: ");
        // list2.printList();
    }
}
