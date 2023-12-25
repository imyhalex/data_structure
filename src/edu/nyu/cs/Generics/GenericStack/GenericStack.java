package edu.nyu.cs.Generics.GenericStack;

import java.util.ArrayList;
import java.util.Arrays;

public class GenericStack<E> {
    private ArrayList<E> list = new ArrayList<>();

    public int getSize() {
        return this.list.size();
    }

    public E peek() {
        return this.list.get(getSize() - 1);
    }

    public void push(E o) {
        this.list.add(o);
    }

    public E pop() {
        E o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public String toString() {
        return "stack: " + list.toString();
    }
}

// Rewrite the question in array
class GenericStackInArray<E> {
    private int capacity;
    private static final int INCREAMENT = 1;
    private int size;
    private E[] list;

    public GenericStackInArray(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.list = (E[]) new Object[capacity];
    }

    private void increaseCapacity() {
        capacity += INCREAMENT;
        E[] newList = (E[]) new Object[capacity];
        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        list = newList;
    }

    public int getSize() {
        return this.size;
    }

    public E peek() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        return this.list[size - 1];
    }

    public void push(E o) {
        this.list[size++] = o;
        if (size == capacity) increaseCapacity();
    }

    public E pop() {
        if (size == 0) {
            throw new IllegalStateException("Stack is empty");
        }
        if (size > 0) return list[--size];
        else return null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "Stack: " + Arrays.toString(list);
    }
}

