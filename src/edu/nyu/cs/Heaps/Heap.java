package edu.nyu.cs.Heaps;

import java.util.ArrayList;
import java.util.List;

public class Heap {
    
    private List<Integer> heap;
    
    public Heap() {
        this.heap = new ArrayList<>();
    }

    public List<Integer> getHeap() {
        return new ArrayList<>(heap);
    }

    // Coding Exercise: Heap: Kth Smallest Element in an Array
    /* 
     * int[] nums = {7, 10, 4, 3, 20, 15};
     * int k = 3;
     * We want to find the 3rd smallest element in this array. Here's how the method processes this:
     */
    // Insert 7 into the heap. Heap: [7]. Size ≤ k (3), no removal.
    // Insert 10 into the heap. Heap: [10, 7]. Size ≤ k, no removal.
    // Insert 4 into the heap. Heap: [10, 7, 4]. Size = k, no removal.
    // Insert 3 into the heap. Heap: [10, 7, 4, 3]. Size > k, so remove the largest (10). New Heap: [7, 3, 4].
    // Insert 20 into the heap. Heap: [20, 7, 4, 3]. Size > k, so remove the largest (20). New Heap: [7, 4, 3].
    // Insert 15 into the heap. Heap: [15, 7, 4, 3]. Size > k, so remove the largest (15). New Heap: [7, 4, 3].
    // Now Heap is: [7, 4, 3], tthe loop ends -> take out the current largest number, which is 7 (root)
    public int findKthSmallest(int[] nums, int k) {
        Heap heap = new Heap();
        
        for (int num : nums) {
            heap.insert(num);
            if (heap.getHeap().size() > k) {
                heap.remove();
            }
        }

        return heap.remove();
    }

    // Coding Exercise: Heap: Maximum Element in a Stream
    /* Explaination:
     * Suppose nums = [1, 5, 2, 9, 3, 6, 8]. The function should operate as follows:
     * Look at the first element (1). The maximum value so far is 1. Output: [1]
     * Look at the second element (5). The maximum value in [1, 5] is 5. Output: [1, 5]
     * Look at the third element (2). The maximum value in [1, 5, 2] is still 5. Output: [1, 5, 5]
     * Continue this process for each element in the array. After considering the fourth element (9), the output becomes [1, 5, 5, 9], and so on.
     */
    public List<Integer> streamMax(int[] nums) {
        Heap heap = new Heap();
        List<Integer> list = new ArrayList<>();

        // For each number in the given array
        for (int num : nums) {
            // Insert the current number to the heap
            heap.insert(num);
            // The heap root (index = 0) is always maximun, so we add it to the result list
            list.add(heap.getHeap().get(0));
        }

        return list;
    }

    public void insert(int value) {
        heap.add(value);
        int current = heap.size() - 1;

        while (current > 0 && heap.get(current) > heap.get(parent(current))) {
            swap(current, parent(current));
            current = parent(current);
        }
    }

    // Remove the largest number (The tip of the tree)
    public Integer remove() {
        if (heap.size() == 0) return null;

        if (heap.size() == 1) {
            return heap.remove(0);
        }

        int maxValue = heap.get(0);
        heap.set(0, heap.remove(heap.size() - 1));
        sinkDown(0);

        return maxValue;
    }

    ///* Helper Methods *///
    private void sinkDown(int index) {
        int maxIndex = index;
        while(true) {
            int leftIndex = leftChild(index);
            int rightIndex = rightChild(index);

            if (heap.get(leftIndex) > heap.get(rightIndex)) {
                maxIndex = leftIndex;
            }

            if (rightIndex < heap.size() && heap.get(rightIndex) > heap.get(leftIndex)) {
                maxIndex = rightIndex;
            }

            if (maxIndex != index) {
                swap(index, maxIndex);
                index = maxIndex; // Update the parent
            } else {
                return;
            }
        }
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        int temp = heap.get(index1);
        heap.set(index1, heap.get(index2));
        heap.set(index2, temp);
    }
}
