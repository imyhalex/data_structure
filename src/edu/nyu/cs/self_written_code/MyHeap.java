package edu.nyu.cs.self_written_code;

import java.util.ArrayList;
import java.util.List;

public class MyHeap {
    
    private List<Integer> heap;

    public MyHeap() {
        this.heap = new ArrayList<>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    // get the left and right child representaion in heap (arraylist)
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }


    // helper method for remove() and insert()
    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;

        while (current > 0 && heap.get(current) > heap.get(parent(current))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }


    // private method for remove()
    private void sinkDown(int index) {
        int maxIndex = index;

        while (true) {
            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);

            if (heap.get(leftIndex) > heap.get(rightIndex) && leftIndex < heap.size()) {
                maxIndex = leftIndex;
            } 
            
            if (heap.get(rightIndex) > heap.get(leftIndex) && rightIndex < heap.size()) {
                maxIndex = rightIndex;
            }

            if (maxIndex != index) {
                swap(index, maxIndex);
                index = maxIndex; // update the parent
            } else {
                return;
            }
        }
    }

    // The heap only remove the value that at the top (regardless if it is a max heap or min heap)
    public Integer remove() {
        if (heap.size() == 0) return null;

        if (heap.size() == 1) {
            return heap.remove(0);
        }

        int maxValue = heap.get(0);

        // remove the last item, and put it in the index of 0 (top)
        heap.set(0, heap.remove(heap.size() - 1));
        // now we need a method to 'sink down' the current top by swap the real largest item up to the top
        sinkDown(0);

        return maxValue;
    }

    public static void main(String[] args) {
        MyHeap heap = new MyHeap();
        heap.insert(10);
        heap.insert(9);
        heap.insert(20);
        heap.insert(30);
        heap.insert(113);
        heap.insert(70);
        heap.insert(45);
        heap.insert(29);

        System.out.println(heap.getHeap());
    }
}
