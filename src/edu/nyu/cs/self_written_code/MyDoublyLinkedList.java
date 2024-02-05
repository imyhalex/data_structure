package edu.nyu.cs.self_written_code;

public class MyDoublyLinkedList<E> {
    
    
    private class Node {
        E data;
        Node prev;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }
}
