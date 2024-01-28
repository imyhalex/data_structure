package edu.nyu.cs.self_written_code;

public class MyQueueUsingLL<E> {
    private Node<E> first; // represent the fisrt element in the queue
    private Node<E> last; // represent the last element in the queue
    private int size;

    /* Due to FIFO(first in first out), enque should working on node 'last', and deque for node 'first' */

    private class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public MyQueueUsingLL() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public MyQueueUsingLL(E data) {
        Node<E> newNode = new Node<>(data);
        this.first = newNode;
        this.last = newNode;
        this.size = 1;
    }

    // Enqueue operation to add an element at the rear of the queue
    public void enqueue(E data) {
        Node<E> node = new Node<>(data);

        if (size == 0) {
            first = node;
            last = node;
        } else {
            last.next = node;
            last = node;
        }

        size++;
    }

    // Dequeue operation to remove and return an element from the front of the queue
    public E dequeue() {
        if (size == 0) return null;

        Node<E> temp = first;
        first = first.next;
        temp.next = null;
        size--;

        if (size == 0) {
            first = null;
            last = null;
        }

        return temp.data;
    }

    // Peek operation to view the front element of the queue without removing it
    public E peek() {
        if (size == 0) return null;
        return first.data;
    }

    // Check if the queue is empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Return the size of the queue
    public int size() {
        return size;
    }
}
