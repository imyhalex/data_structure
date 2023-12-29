package edu.nyu.cs.DoublyLinkedListUdemy;

public class Main {
    
    public static void main(String[] args) {

        DoublyLinkedList myDLL = new DoublyLinkedList(1);
        myDLL.append(3);

        myDLL.insert(1,2);

        myDLL.printList();
    }

}
