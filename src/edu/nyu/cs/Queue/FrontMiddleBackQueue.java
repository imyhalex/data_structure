package edu.nyu.cs.Queue;

import java.util.LinkedList;

class FrontMiddleBackQueue {

    private LinkedList<Integer> list;

    public FrontMiddleBackQueue() {
        this.list = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        list.addFirst(val);
    }
    
    public void pushMiddle(int val) {
        int mid = list.size() / 2;
        list.add(mid, val);
    }
    
    public void pushBack(int val) {
        list.add(val);
    }
    
    public int popFront() {
        return list.removeFirst();
    }
    
    public int popMiddle() {
        int mid = (list.size() - 1) / 2;
        return list.remove(mid);
    }
    
    public int popBack() {
        return list.removeLast();
    }
}
