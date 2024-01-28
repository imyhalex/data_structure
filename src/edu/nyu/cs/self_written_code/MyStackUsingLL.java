package edu.nyu.cs.self_written_code;

import java.util.EmptyStackException;

public class MyStackUsingLL<E> {
    private Node<E> top;
    private int size;
    
    private class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public MyStackUsingLL() {
        top = null;
        size = 0;
    }

    public MyStackUsingLL(E data) {
        Node<E> newNode = new Node<>(data);
        this.top = newNode;
        this.size = 1;
    }

    // push(E data)
    public void push(E data) {
        Node<E> newNode = new Node<E>(data);
        
        if (size == 0) {
            top = newNode;
        } else {
            newNode.next = top;
            top = newNode;
        }
    }

    // pop()
    public E pop() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }

        Node<E> temp = top;

        top = top.next;
        temp.next = null;
        size--;

        return temp.data;
    }

    // peek()
    public E peek() {
        if (this.isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
    }

    // isEmpty()
    public boolean isEmpty() {
        return top == null;
    }

    // size()
    public int size() {
        return this.size;
    }

    // clear
    public void clear() {
        this.size = 0;
        this.top = null;
    }
}
