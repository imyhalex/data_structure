package edu.nyu.cs.BasicSort;

public class LinkedList {
    
    private Node head;
    private Node tail;
    private int length;

    class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public LinkedList(int value) {
        Node newNode = new Node(value);
        head = newNode;
        tail = newNode;
        length = 1;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getLength() {
        return length;
    }

    public void makeEmpty() {
        head = null;
        tail = null;
        length = 0;
    }

    public void printList() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.value);
            temp = temp.next;
        }
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

    // Coding Exercise: Bubble Sort of LinkedList
    public void bubbleSort() {
        if (this.length < 2) return;

        Node sortedUntil = null;
        while (sortedUntil != head.next) {
            Node current = head;

            while (current.next != sortedUntil) {
                Node nextNode = current.next;

                if (current.value > nextNode.value) {
                    int temp = current.value;
                    current.value = nextNode.value;
                    nextNode.value = temp;
                }

                current = current.next;
            }

            sortedUntil = current;
        }
    }

    // Coding Exercise: Selection Sort of LinkedList
    public void selectionSort() {
        if (this.length < 2) return;

        Node current = head;
        while (current.next != null) {
            Node smallest = current;
            Node innerCurrent = current.next;

            while (innerCurrent != null) {
                if (smallest.value > innerCurrent.value) {
                    smallest = innerCurrent;
                }
                innerCurrent = innerCurrent.next;
            }

            if (smallest != current) {
                int temp = smallest.value;
                smallest.value = current.value;
                current.value = temp;
            }
            current = current.next;
        }

        this.tail = current;
    }

    // Coding Exercise: Insertion Sort of LinkedList
	public void insertionSort() {
		if (length < 2) {
			return; // List is already sorted
		}
 
		Node sortedListHead = head;
		Node unsortedListHead = head.next;
		sortedListHead.next = null;
 
		while (unsortedListHead != null) {
			Node current = unsortedListHead;
			unsortedListHead = unsortedListHead.next;
 
			if (current.value < sortedListHead.value) {
				current.next = sortedListHead;
				sortedListHead = current;
			} else {
				Node searchPointer = sortedListHead;
				while (searchPointer.next != null && current.value > searchPointer.next.value) {
					searchPointer = searchPointer.next;
				}
                // Peform the Insertion: insert current into its correct position in the sorted list.
				current.next = searchPointer.next;
				searchPointer.next = current;
			}
		}
 
		head = sortedListHead;
		Node temp = head;
		while (temp.next != null) {
			temp = temp.next;
		}
		tail = temp;
	}
}
