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
            tailSorted = current;]
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