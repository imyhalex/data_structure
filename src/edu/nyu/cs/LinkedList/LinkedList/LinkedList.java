package edu.nyu.cs.LinkedList.LinkedList;


public class LinkedList<E> {
    private Node head;
    private Node tail;
    private int length;
    
    private class Node {
        E data;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    public LinkedList() {
        this.head = null;
    }

    public Node getHead() {
        return this.head;
    }

    public int size() {
        return this.length;
    }

    // Append an element to the LinkedLikst
    public void add(E data) {
        Node newNode = new Node(data);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = tail;
            tail = newNode;
        }
        length++;
    }

    public E removeLast() {
        if (length == 0) return null;
        Node temp = head;
        Node pre = head;
        while(temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp.data;
    }

    public void addFirst(E data) {
        Node newNode = new Node(data);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else  {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public E removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = temp.next;
        temp.next = null;
        length--;
        if (length == 0) {
            head = null;
            tail = null;
        }
        return temp.data;
    }

    public E get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public E set(int index, E e) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        temp.data = e;
        return temp.data;
    }

    public void add(int index, E e) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Invalid Bound");
        }
        // Two special cases
        if (index == 0) addFirst(e);
        else if (index == length) add(e);
        // While inserting element within list
        Node prev = getNode(index - 1);
        Node newNode = new Node(e);
        newNode.next = prev.next;
        prev.next = newNode;
        length++;
    }

    public E remove(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invald Bound");
        }
        if (index == 0) return removeFirst();
        else if (index == length) return removeLast();
        Node prev = getNode(index - 1);
        Node temp = getNode(index);
        prev.next = temp.next;
        temp.next = null;
        length--;
        return temp.data;
    }

    public void reverse() {
        Node before = null;
        Node temp = head;
        Node after = temp.next;
        head = tail;
        tail = temp;
        for (int i = 0; i < size(); i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Invalid Index");
        }
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }
}
