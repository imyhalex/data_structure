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
    }
}
