package edu.nyu.cs.Stack;

import java.util.ArrayList;

public class Stack<T> {
    
    private ArrayList<T> stackList = new ArrayList<>();  
    
    public ArrayList<T> getStackList() {
        return stackList;
    }
    
    public void printStack() {
        for (int i = stackList.size()-1; i >= 0; i--) {
            System.out.println(stackList.get(i));
        }
    }
    
    public boolean isEmpty() {
        return stackList.size() == 0;
    }
    
    public T peek() {
        if (isEmpty()) {
            return null;
        } else {
            return stackList.get(stackList.size() - 1);
        }
    }
    
    public int size() {
        return stackList.size();
    }

    // Coding Exercise: Push for a Stack That Uses an ArrayList
    public void push(T value) {
        stackList.add(value);
    }
    
    // Coding Exercise: Pop for a Stack That Uses an ArrayList
    public T pop() {
        if (stackList.isEmpty()) return null;
        return stackList.remove(stackList.size() - 1);
    }
}
