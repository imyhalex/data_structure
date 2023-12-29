package edu.nyu.cs.LinkedList.LinkedListUdemy;

import java.util.HashSet;

public class LinkedList {
    
    private Node head;
    private Node tail;
    private int length;

    private class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        this.head = newNode;
        this.tail = newNode;
        this.length = 1;
    }

    public Node getHead() {
        return this.head;
    }

    public Node getTail() {
        return this.tail;
    }

    public int getLength() {
        return this.length;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.value + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public void printAll() {
        if (length == 0) {
            System.out.println("Head: null");
            System.out.println("Tail: null");
        } else {
            System.out.println("Head: " + head.value);
            System.out.println("Tail: " + tail.value);
        }
        System.out.println("Length:" + length);
        System.out.println("\nLinked List:");
        if (length == 0) {
            System.out.println("empty");
        } else {
            printList();
        }
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void append(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        length++;
    }

    public Node removeLast() {
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
        return temp;
    }

    public void prepend(int value) {
        Node newNode = new Node(value);
        if (length == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        length++;
    }

    public Node removeFirst() {
        if (length == 0) return null;
        Node temp = head;
        head = head.next;
        temp.next = null;
        length--;
        if (length == 0) tail = null;
        return temp;
    }

    public Node get(int index) {
        if (index < 0 || index >= length) return null;
        Node temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public boolean set(int index, int value) {
        Node temp = get(index);
        if (temp != null) {
            temp.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value) {
        if (index < 0 || index > length) return false;
        if (index == 0) {
            prepend(value);
            return true;
        } else if (index == length) {
            append(value);
            return true;
        }
        Node newNode = new Node(value);
        Node temp = get(index - 1);
        newNode.next = temp.next;
        temp.next = newNode;
        length++;
        return true;
    }

    public Node remove(int index) {
        if (index < 0 || index >= length) return null;
        if (index == 0) return removeFirst();
        else if (index == length -1) return removeLast();
        Node before = get(index - 1);
        Node temp = before.next;
        before.next = temp.next;
        temp.next = null;
        length--;
        return temp;
    }

    public void reverse() {
        Node temp = head;
        head = tail;
        tail = temp;
        Node after = temp.next;
        Node before = null;
        for (int i = 0; i < length; i++) {
            after = temp.next;
            temp.next = before;
            before = temp;
            temp = after;
        }
    }

    public Node findMiddleNode() {
        Node slow = head; // this will cover half of the list
        Node fast = head; // this will cover the whole list
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // Find out if a list holds a pattern
    public boolean hasLoop() {
        Node slow = head;
        Node fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    public Node findKthFromEnd(int k) {
        Node slow = head;
        Node fast = head;
        // A for loop to take fast k steps forward
        for (int i = 0; i < k; i++) {
            if (fast == null) return null;
            fast = fast.next;
        }
        // A while loop until fast is null, then for slow the rest is kth number from the end
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public void partitionList(int x) {
        // Step 1: Check for an empty list.
        // If the list is empty, there is nothing
        // to partition, so we exit the method.
        if (head == null) return;

        // Step 2: Create two dummy nodes.
        // We create dummy nodes to act as the
        // starting points for our two new lists.
        Node dummy1 = new Node(0);
        Node dummy2 = new Node(0);

        // Step 3: Initialize list pointers.
        // We'll use 'prev1' and 'prev2' to keep track
        // of the last nodes in our two new lists.
        Node prev1 = dummy1;
        Node prev2 = dummy2;

        Node current = head;

        while(current != null) {
            if (current.value < x) {
                prev1.next = current;
                prev1 = current;
            } else {
                prev2.next = current;
                prev2 = current;
            }
            current = current.next;
        }

        prev2.next = null;
        prev1.next = dummy2.next; // connect two partition together

        head = dummy1.next;
    }

    // Use the HashSet
    public void removeDuplicates() {
        HashSet<Integer> values = new HashSet<>();
        Node previous = null;
        Node current = head;
        while (current != null) {
            if (values.contains(current.value)) {
                previous.next = current.next;
                length -= 1;
            } else {
                values.add(current.value);
                previous = current;
            }
            current = current.next;
        }
    }

    // Without using HashSet
    public void removeDuplicates2() {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (runner.next.value == current.value) {
                    runner.next = runner.next.next;
                    length -= 1;
                } else {
                    runner = runner.next;
                }
            }
            current = current.next;
        }
    }

    public int binaryToDecimal() {
        Node current = head;
        int num = 0;
        while (current != null) {
            num = num * 2 + current.value;
            current = current.next;
        }
        return num;
    }

    public void reverseBetween(int start, int end) {
        if (length == 0) return;
        Node dummyNode = new Node(0);
        dummyNode.next = head;
        Node previousNode = dummyNode;
        for (int i = 0; i < start; i++) {
            previousNode = previousNode.next;
        }
        Node currentNode = previousNode.next;
        for (int i = 0; i < end; i++) {
            Node nodeToMove = currentNode.next;
            currentNode.next = nodeToMove.next;
            nodeToMove.next = previousNode.next;
            previousNode.next = nodeToMove;
        }
        head = dummyNode.next;
    }
}

