package edu.nyu.cs.self_written_code;

import java.util.PriorityQueue;
import java.util.Queue;

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

            for (int i = 1; i < index; i++) {
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

    /* LeetCode Queseiont isPalilndrome in LinkedList version */
    // public boolean isPalindrome(ListNode head) {
    //     if (head == null || head.next == null) return true;

    //     ListNode firstHalfEnd = endOfFirstHalf(head);
    //     ListNode secondHalfStart = reverseList(firstHalfEnd.next);

    //     ListNode p1 = head;
    //     ListNode p2 = secondHalfStart;
    //     boolean result = true;
    //     while (result && p2 != null) {
    //         if (p1.val != p2.val) result = false;
    //         p1 = p1.next;
    //         p2 = p2.next;
    //     }

    //     // Restore the list
    //     firstHalfEnd.next = reverseList(secondHalfStart);

    //     return result;
    // }

    // private ListNode reverseList(ListNode head) {
    //     ListNode before = null;
    //     ListNode temp = head;

    //     while (temp != null) {
    //         ListNode after = temp.next;
    //         temp.next = before;
    //         before = temp;
    //         temp = after;
    //     }

    //     return before;
    // }

    // private ListNode endOfFirstHalf(ListNode head) {
    //     ListNode slow = head;
    //     ListNode fast = head;

    //     while (fast.next != null && fast.next.next != null) {
    //         slow = slow.next;
    //         fast = fast.next.next;
    //     }

    //     return slow;
    // }

    /* LeetCode Question: remove Duplicates from sorted List  */
    public Node<Integer> deleteDuplicates(Node<Integer> head) {
        Node<Integer> current = head;

        // We need to make sure both nodes we compare are within the list, not null
        while (current != null && current.next != null) {
            if (current.data == current.next.data) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
        }

        return current;
    }

    /* LeetCode Question: Intersection of Two LinkedList */
    public Node<Integer> getIntersectionNode(Node<Integer> headA, Node<Integer> headB) {
        Node<Integer> pA = headA;
        Node<Integer> pB = headB;

        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }

        return pA;
    }

    /* LeetCode Question: Remove List Elements */
    public Node<Integer> removeElements(Node<Integer> head, int data) {
        Node<Integer> dummy = new Node<Integer>(-1);
        dummy.next = head; // connect the pointer to the list we toggle with

        Node<Integer> prev = dummy;
        Node<Integer> current = head;
        while (current != null) {
            if (current.data == data) {
                prev.next = current.next;
            } else {
                prev = current;
            }

            current = current.next;
        }

        return dummy.next;
    }

    /* LeetCode Question: Merge k sorted list */
    public Node<E> mergeKList(Node<E>[] lists) {
        Node<E> dummy = new Node<>(null);
        Node<E> current = dummy;

        Queue<Node<E>> queue = new PriorityQueue<>();

        if (lists == null || lists.length == 0) return null;

        // Initialize with the head of each list
        for (Node<E> list : lists) {
            if (list != null) {
                queue.add(list);
            }
        }

        while (!queue.isEmpty()) {
            Node<E> minNode = queue.poll();

            current.next = minNode;
            current = current.next;

            if (minNode.next != null) {
                queue.add(minNode.next);
            }
        }

        return dummy.next;
    }

    /* LeetCode Question: mergeTwoLists(Node list1, Node list2) */
    public Node<Integer> mergeTwoList(Node<Integer> list1, Node<Integer> list2) {
        Node<Integer> dummy = new Node<>(-1);

        Node<Integer> prev = dummy;
        while (list1 != null && list2 != null) {
            if (list1.data < list2.data) {
                prev.next = list1;
                list1 = list1.next;
            } else {
                prev.next = list2;
                list2 = list2.next;
            }

            prev = prev.next;
        }

        if (list1 != null) {
            prev.next = list1;
        } else {
            prev.next = list2;
        }

        return dummy.next;
    } 
}  
