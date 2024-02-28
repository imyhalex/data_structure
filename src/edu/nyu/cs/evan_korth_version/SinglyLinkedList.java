package edu.nyu.cs.evan_korth_version;

public class SinglyLinkedList<E> implements Cloneable {
    private static class Node<E> {
        private E element;            // reference to the element stored at this node
    
        private Node<E> next;         // reference to the subsequent node in the list
    
        public Node(E e, Node<E> n) {
          element = e;
          next = n;
        }
    
        public E getElement() { return element; }
    
        public Node<E> getNext() { return next; }
    
        public void setNext(Node<E> n) { next = n; }

        public void setElement(E e) { element = e;}
    } //----------- end of nested Node class -----------

    public void removeDuplicates() {
        Node<E> current = head;

        while (current != null && current.getNext() != null) {
            if (current.getElement().equals(current.getNext().getElement())) {
                current.setNext(current.getNext().getNext());
                if (current.getElement() == null) {
                    tail = current;
                }
            } else {
                current = current.getNext();
            }
        }
    }

    /* Bubble Sort with LL */
    // public void bubbleSort() {
    //     if (size < 2) return;

    //     Node<E> sortedUntil = new Node<>(null, null);
    //     while (sortedUntil != head.getNext()) {
    //         Node<E> current = head;

    //         while (current.getNext() != sortedUntil) {
    //             Node<E> nextNode = current.getNext();

    //             int cmp = ((Comparable<E>) current.getElement()).compareTo(nextNode.getElement());
    //             if (cmp > 0) {
    //                 E temp = current.getElement();
    //                 current.setElement(nextNode.getElement()); 
    //                 nextNode.setElement(temp); 
    //             }

    //             current = current.getNext();
    //         }

    //         sortedUntil = current;
    //     }
    // }
    
    /* Bubble Sort with LL */
    public void bubbleSort() {
        if (size < 2) return;

        boolean wasSwapped;
        do {
            Node<E> current = head;
            wasSwapped = false;

            while (current.getNext() != null) {
                Node<E> nextNode = current.getNext();

                int cmp = ((Comparable<E>) (current.getElement())).compareTo(nextNode.getElement());
                if (cmp > 0) {
                    E temp = current.getElement();
                    current.setElement(nextNode.getElement());
                    nextNode.setElement(temp);

                    wasSwapped = true;
                }

                current = current.getNext();
            }
        } while (wasSwapped);
    }

    /* Selection Sort with LL */
    public void selectionSort() {
        if (size < 2) return;

        Node<E> current = head;
        while (current.getNext() != null) {
            Node<E> smallest = current;
            Node<E> innerNode = current.getNext();

            while (innerNode != null) {
                int cmp = ((Comparable<E>) (smallest.getElement())).compareTo(innerNode.getElement());
                if (cmp > 0) {
                    smallest = innerNode;
                }

                innerNode = innerNode.getNext();
            }
            
            if (!smallest.equals(current)) {
                E temp = current.getElement();
                current.setElement(smallest.getElement());
                smallest.setElement(temp);
            }

            current = current.getNext();
        }

        tail = current;
    }

    /* Merge two lists */
    public void mergeTwoList(Node<E> head1, Node<E> head2) {
        Node<E> dummy = new Node<E>(null, null);

        Node<E> prev = dummy;
        while (head1 != null && head2 != null) {
            int cmp = ((Comparable<E>) (head1.getElement())).compareTo(head2.getElement());

            if (cmp < 0) {
                prev.setNext(head1);
                head1 = head1.getNext();
            } else {
                prev.setNext(head2);
                head2 = head2.getNext();
            }

            prev = prev.getNext();
        }

        if (head1 != null) {
            prev.setNext(head1);
        } else {
            prev.setNext(head2);
        }

        head = dummy.getNext();
    }

    /* Reverse Between */
    public void reverseBetween(int start, int end) {
        if (head == null) return;

        Node<E> dummy = new Node<>(null, head);
        Node<E> prev = dummy;

        for (int i = 0; i < start - 1; i++) {
            prev = prev.getNext();
        }

        Node<E> current = prev.getNext();
        
        for (int i = 0; i < end - start; i++) {
            Node<E> toMove = current.getNext();
            current.setNext(toMove.getNext());
            toMove.setNext(prev.getNext());
            prev.setNext(toMove);
        }

        head = dummy.next;
    }

    /* Is Palindrome */
    public boolean isPalilndrome(Node<E> head) {
        if (head == null || head.getNext() == null) return true;

        Node<E> endOftheFirstHalf = endOftheFirstHalf(head);
        Node<E> compareHead = reverseList(endOftheFirstHalf.getNext());

        Node<E> p1 = head;
        Node<E> p2 = compareHead;
        boolean result = true;
        while (p1 != null && p2 != null) {
            if (!p1.getElement().equals(p2.getElement())) {
                result = false;
            }

            p1 = p1.getNext();
            p2 = p2.getNext();
        }

        return result;
    }

    private Node<E> reverseList(Node<E> node) {
        Node<E> before = null;
        Node<E> current = node;

        while (current != null) {
            Node<E> after = current.getNext();
            current.setNext(before);
            before = current;
            current = after;
        }

        return before;
    }

    private Node<E> endOftheFirstHalf(Node<E> head) {
        Node<E> slow = head;
        Node<E> fast = head;

        while (fast.getNext() != null && fast.getNext().getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }


    /* Partition */
    public void partition(E e) {
        if (head == null) return;

        Node<E> dummy1 = new Node<>(null, null);
        Node<E> dummy2 = new Node<>(null, null);
        Node<E> prev1 = dummy1;
        Node<E> prev2 = dummy2;

        while (head != null) {
            int cmp = ((Comparable<E>) (head.getElement())).compareTo(e);

            if (cmp < 0) {
                prev1.setNext(head);
                prev1 = prev1.getNext();
            } else {
                prev2.setNext(head);
                prev2 = prev2.getNext();
            }

            head = head.getNext();
        }

        prev2.setNext(null); // set the newly tail to null
        prev1.next = dummy2.next; // connect two part of the list

        head = dummy1.next;
    }

    // instance variables of the SinglyLinkedList
    /** The head node of the list */
    private Node<E> head = null;               // head node of the list (or null if empty)
  
    /** The last node of the list */
    private Node<E> tail = null;               // last node of the list (or null if empty)
  
    /** Number of nodes in the list */
    private int size = 0;                      // number of nodes in the list
  
    /** Constructs an initially empty list. */
    public SinglyLinkedList() { }              // constructs an initially empty list
  
    // access methods
    /**
     * Returns the number of elements in the linked list.
     * @return number of elements in the linked list
     */
    public int size() { return size; }
  
    /**
     * Tests whether the linked list is empty.
     * @return true if the linked list is empty, false otherwise
     */
    public boolean isEmpty() { return size == 0; }
  
    /**
     * Returns (but does not remove) the first element of the list
     * @return element at the front of the list (or null if empty)
     */
    public E first() {             // returns (but does not remove) the first element
      if (isEmpty()) return null;
      return head.getElement();
    }
  
    /**
     * Returns (but does not remove) the last element of the list.
     * @return element at the end of the list (or null if empty)
     */
    public E last() {              // returns (but does not remove) the last element
      if (isEmpty()) return null;
      return tail.getElement();
    }
  
    // update methods
    /**
     * Adds an element to the front of the list.
     * @param e  the new element to add
     */
    public void addFirst(E e) {                // adds element e to the front of the list
      head = new Node<>(e, head);              // create and link a new node
      if (size == 0)
        tail = head;                           // special case: new node becomes tail also
      size++;
    }
  
    /**
     * Adds an element to the end of the list.
     * @param e  the new element to add
     */
    public void addLast(E e) {                 // adds element e to the end of the list
      Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
      if (isEmpty())
        head = newest;                         // special case: previously empty list
      else
        tail.setNext(newest);                  // new node after existing tail
      tail = newest;                           // new node becomes the tail
      size++;
    }
  
    /**
     * Removes and returns the first element of the list.
     * @return the removed element (or null if empty)
     */
    public E removeFirst() {                   // removes and returns the first element
      if (isEmpty()) return null;              // nothing to remove
      E answer = head.getElement();
      head = head.getNext();                   // will become null if list had only one node
      size--;
      if (size == 0)
        tail = null;                           // special case as list is now empty
      return answer;
    }
  
    @SuppressWarnings({"unchecked"})
    public boolean equals(Object o) {
      if (o == null) return false;
      if (getClass() != o.getClass()) return false;
      SinglyLinkedList<E> other = (SinglyLinkedList<E>) o;   // use nonparameterized type
      if (size != other.size) return false;
      Node<E> walkA = head;                               // traverse the primary list
      Node<E> walkB = other.head;                         // traverse the secondary list
      while (walkA != null) {
        if (!walkA.getElement().equals(walkB.getElement())) return false; //mismatch
        walkA = walkA.getNext();
        walkB = walkB.getNext();
      }
      return true;   // if we reach this, everything matched successfully
    }
  
    @SuppressWarnings({"unchecked"})
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
      // always use inherited Object.clone() to create the initial copy
      SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone(); // safe cast
      if (size > 0) {                    // we need independent chain of nodes
        other.head = new Node<>(head.getElement(), null);
        Node<E> walk = head.getNext();      // walk through remainder of original list
        Node<E> otherTail = other.head;     // remember most recently created node
        while (walk != null) {              // make a new node storing same element
          Node<E> newest = new Node<>(walk.getElement(), null);
          otherTail.setNext(newest);     // link previous node to this one
          otherTail = newest;
          walk = walk.getNext();
        }
      }
      return other;
    }
  
    public int hashCode() {
      int h = 0;
      for (Node<E> walk=head; walk != null; walk = walk.getNext()) {
        h ^= walk.getElement().hashCode();      // bitwise exclusive-or with element's code
        h = (h << 5) | (h >>> 27);              // 5-bit cyclic shift of composite code
      }
      return h;
    }
  
    /**
     * Produces a string representation of the contents of the list.
     * This exists for debugging purposes only.
     */
    public String toString() {
      StringBuilder sb = new StringBuilder("(");
      Node<E> walk = head;
      while (walk != null) {
        sb.append(walk.getElement());
        if (walk != tail)
          sb.append(", ");
        walk = walk.getNext();
      }
      sb.append(")");
      return sb.toString();
    }
  }
