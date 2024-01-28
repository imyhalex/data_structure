package edu.nyu.cs.self_written_code;

public class MyBinarySearchTree<E extends Comparable<E>> {
    Node root;
    
    private class Node {
        E data;
        Node left;
        Node right;

        Node(E data) {
            this.data = data;
        }
    }

    public MyBinarySearchTree() {

    }

    public boolean search(E data) {
        Node current = root;

        while (current != null) {
            if (data.compareTo(current.data) < 0) {
                current = current.left;
            } else if (data.compareTo(current.data) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }

        return false;
    }
    
    /* Insert the given element data into the binary search tree. Return true if the data is inserted successfully */
    public boolean insert(E data) {
        // Chek if the binary tree is empty
        if (root == null) {
            root = new Node(data);
            return true;
        }

        Node parent = null;
        Node current = root;
        while (current != null) {
            int cmp = data.compareTo(current.data);

            if (cmp < 0) {
                parent = current;
                current = current.left;
            } else if (cmp > 0) {
                parent = current;
                current = current.right;
            } else {
                // The data is already in the tree
                return false;
            }
        }

        // Attach the node under the finded parent in the right position
        if (data.compareTo(parent.data) < 0) {
            parent.left = new Node(data);
        }else {
            parent.right = new Node(data);
        }

        return true;
    }

    public boolean contains(E data) {
        if (root == null) return false;

        Node current = root;
        
        while (current != null) {
            int cmp = data.compareTo(current.data);

            if (cmp == 0) return true;

            if (cmp < 0) 
                current = current.left;
            else 
                current = current.right;
        }

        return false;
    }
} 
