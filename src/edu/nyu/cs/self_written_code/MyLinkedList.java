package edu.nyu.cs.self_written_code;

public class MyLinkedList<E extends Comparable<E>> {
    private Node<E> head;
    private Node<E> tail;
    private int size;
    
    private class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    public int size() {
        return this.size;
    }

    public Node<E> getHead() {
        return this.head;
    }

    public Node<E> getTail() {
        return this.tail;
    }

    public void printList() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.data + ", ");
            temp = temp.next;
        }
        System.out.println();
    }

    public String toString() {
        StringBuilder result = new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++) {
            result.append(current.data);
            current = current.next;

            if (current != null) {
                result.append(", ");
            } else {
                result.append("]");
            }
        }

        return result.toString();
    }

    public MyLinkedList() {

    }

    public MyLinkedList(E data) {
        Node<E> newNode = new Node<>(data);
        this.head = newNode;
        this.tail = newNode;
        this.size = 1;
    }

    public E getFirst() {
        if (size == 0) return null;
        else return head.data;
    }

    public E getLast() {
        if (size == 0) return null;
        else return tail.data;
    }

    public void addFirst(E data) {
        Node<E> newNode = new Node<>(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        size++;
    }

    public void addLast(E data) {
        Node<E> newNode = new Node<E>(data);

        if (size == 0) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        size++;
    }

    public void add(int index, E data) {
        if (index == 0) addFirst(data);
        else if (index >= size) addLast(data);
        else {
            Node<E> current = head;

            for (int i = 1; i < index; i++) {
                current = current.next; // loop until just one Node before the node
            }

            Node<E> after = current.next;
            current.next = new Node<>(data);
            current.next.next = after;
            size++;
        }
    }

    public E removeFirst() {
        if (size == 0) return null;

        Node<E> temp = head;
        head = head.next;
        temp.next = null;
        size--;

        if (size == 0) tail = null;

        return temp.data;
    }

    public E removeLast() {
        if (size == 0) return null;

        Node<E> pre= head;
        Node<E> temp = head;
        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }
        tail = pre;
        tail.next = null;

        size--;

        // After removeal, recheck the size
        if (size == 0) {
            head = null;
            tail = null;
        }

        return temp.data;
    }

    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        else if (index == 0) return removeFirst();
        else if (index == size - 1) return removeLast();
        else {
            Node<E> previous = head;

            for (int i = 0; i < index; i++) {
                previous = previous.next; 
            }

            Node<E> toRemove = previous.next;
            previous.next = toRemove.next;
            size--;

            return toRemove.data;
        }
    }

    public void reverse() {
        Node<E> temp = head;

        // Step 1: swap the head and tail, and set tail to null
        head = tail;
        tail = null;
        
        // Initialize the pointer of the first node
        Node<E> after = temp.next;
        Node<E> before = null;

        for (int i = 0; i < size; i++) {
            after = temp.next;
            temp.next = before;
            before = temp; // Move the before up
            temp = after; // Move the temp up
        }
    }

    public Node<E> findMiddleNode() {
        Node<E> slow = head;
        Node<E> fast = head;
        
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // Leetcode reverseList - Singly LinkedList
    // public ListNode reverseList(ListNode head) {
    //     ListNode prev = null;
    //     ListNode current = head;

    //     while (current != null) {
    //         ListNode temp = current.next;
    //         current.next = prev;
    //         prev = current;
    //         current = temp;
    //     }
    //     return prev;
    // }

    public void clear() {
        size = 0;
        head = null;
        tail = null;
    }

    public boolean contains(E data) {
        Node<E> current = head;

        while (current != null) {
            if (current.data.compareTo(data) == 0) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }

        return temp.data; 
    }

    public int indexOf(E data) {
        Node<E> temp = head;

        for (int i = 0; i < size; i++) {
            if (temp.data.equals(data))
                return i;
            temp = temp.next;
        }

        return -1;
    }

    public int lastIndexOf(E data) {
        Node<E> temp = head;
        int currentIndex = 0;
        int matchedIndex = -1;

        while (temp != null) {
            if (temp.data.compareTo(data) == 0) {
                matchedIndex = currentIndex;
            }
            currentIndex++;
            temp = temp.next;
        }

        return matchedIndex;
    }

    /* Replace the element at the speficied position */
    public E set(int index, E data) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }
        Node<E> current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        E oldData = current.data;
        current.data = data;

        return oldData;
    }

    public boolean hasLoop() {
        Node<E> slow = head;
        Node<E> fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }

        return false;
    }

    // public ListNode partition(ListNode head, int x) {
    //     if (head == null) return null;

    //     ListNode dummy1 = new ListNode(0);
    //     ListNode dummy2 = new ListNode(0);
    //     ListNode prev1 = dummy1;
    //     ListNode prev2 = dummy2;

    //     while (head != null) {
    //         if (head.val < x) {
    //             prev1.next = head;
    //             prev1 = prev1.next;
    //         } else {
    //             prev2.next = head;
    //             prev2 = prev2.next;
    //         }

    //         head = head.next;
    //     }

    //     prev2.next = null;
    //     prev1.next = dummy2.next;

    //     return dummy1.next;
    // }
    
}  
