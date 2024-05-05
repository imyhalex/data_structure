## Important Sorts with LinkedList
```java
void bubbleSort() 
{
    if (this.size < 2) 
        return;

    boolean wasSwapped;
    do {
        Node<E> current = head;
        wasSwapped = false;

        while (current.getNext() != null) {
            Node<E> nextNode = current.getNext();

            if (current.getElement().compareTo(nextNode.getElement()) > 0) {
                E temp = current.getElement();
                current.setElement(nextNode.getElement());
                nextNode.setElement(temp);

                wasSwapped = true;
            }
            current = current.getNext();
        }
    } while (wasSwapped);
}
```

```java
void insertionSort() 
{
    if (this.head == null || this.head.getNext() == null)
        return;

    Node<E> dummy = new Node<>(null, head);
    Node<E> current = head.getNext();
    Node<E> tailSorted = head;

    while (current != null) {
        if (tailSorted.getElement().compareTo(current.getElement()) <= 0)
            tailSorted = current;
        else {
            Node<E> prev = dummy;
            while (prev.getNext().getElement().compareTo(current.getElement() < 0)
                prev = prev.getNext();
            // Conduct swap
            tailSorted.setNext(current.getNext());
            current.setNext(prev.getNext());
            prev.setNext(current);
        }

        current = tailSorted.getNext();
    }

    this.head = dummy.getNext();
}
```

```java
void selectionSort()
{
    if (this.size < 2)
        return;

    Node<E> currnet = head;
    while (current.getNext() != null) {
        Node<E> smallest = current;
        Node<E> innerNode = current.getNext();

        while (innserNode != null) {
            if (smallest.getElement().compareTo(innerNode.getElement()) > 0)
                smallest = innerNode;
            innerNode = innerNode.getNext();
        }

        if (!smallest.equals(current)) {
            E temp = current.getElement();
            current.setElement(smallest.getElement());
            smallest.setElement(temp);
        }

        current = current.getNext();
    }

    this.tail = current;
}
```
```java
void mergeSort(Node<E> head1, Node<E> head2)
{
    Node<E> dummy = new Node<>(null, null);

    Node<E> prev = dummy;
    while (head1 != null && head2 != null) {
        if (head1.getElement().compareTo(head2.getElement()) < 0) {
            prev.setNext(head1);
            head1 = head1.getNext();
        }
        else {
            prev.setNext(head2);
            head2 = head2.getNext();
        }

        prev = prev.getNext();
    }

    if (head1 != null)
        prev.setNext(head1);
    else 
        prev.setNext(head2);
    
    this.head = dummny.getNext();
}
```