package edu.nyu.cs.self_written_code;

public class MyDoublyLinkedList<E extends Comparable<E>> {
    private Node head;
    private Node tail;
    private int size;

    private class Node {
        E data;
        Node prev;
        Node next;

        Node(E data) {
            this.data = data;
        }
    }

    public MyDoublyLinkedList(E data) {
        Node newNode = new Node(data);
        head = newNode;
        tail = newNode;
        size = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return size;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public void append(E data) {
        Node newNode = new Node(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        
        size++;
    }

    public Node removeLast() {
        Node temp = tail;

        if (size == 0) return null;

        tail = tail.prev;
        temp.prev = null;
        tail.next = null;
        size--;

        if (size == 0) {
            head = null;
            tail = null;
        }

        return temp;
    }

    public void prepend(E data) {
        Node newNode = new Node(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        size++;
    }

    public Node removeFirst() {
        if (size == 0) return null;

        Node temp = head;
        head = head.next;
        temp.next = null;
        head.prev = null;
        size--;

        if (size == 0) {
            head = null;
            tail = null;
        }

        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= size) return null;

        Node temp = head;
        if (index < size / 2) {
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
        }

        return temp;
    }

    public Node set(int index, E data) {
        if (index < 0 || index >= size) return null;

        Node node = get(index);
        node.data = data;
        return node;
    }

    public void insert(int index, E data) {
        if (index < 0 || index >= size) return;
        else if (index == 0) prepend(data);
        else if (index == size - 1) append(data);
        else {
            Node newNode = new Node(data);
            Node previous = get(index - 1);
            Node after = previous.next;

            newNode.prev = previous;
            newNode.next = after;
            previous.next = newNode;
            after.prev = newNode;
            size++;
        }
    }

    public Node remove(int index) {
        if (index < 0 || index >= size) return null;
        else if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else {
            Node toRemove = get(index);
            Node previous = toRemove.prev;
            Node after = toRemove.next;

            toRemove.prev = null;
            toRemove.next = null;
            previous.next = after;
            after.prev = previous;
            size--;

            return toRemove;
        }
    }

    public void reverse() {
        Node current = head;
        Node temp = null;

        while (current != null) {
            temp = current.prev;
            current.next = current.prev;
            current.prev = temp;
            current = current.prev;
        }

        temp = head;
        head = tail;
        tail = temp;
    }
}
