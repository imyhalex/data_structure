package edu.nyu.cs.self_written_code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

public class MyRecursiveBinarySearchTree<E extends Comparable<E>> {
    private Node root;

    private class Node {
        E data;
        Node left;
        Node right;

        Node(E data) {
            this.data = data;
        }
    }

    public MyRecursiveBinarySearchTree() {

    }

    /* Recursive Methods */

    // search()
    public boolean search(E data) {
        return search(root, data);
    }

    // helper method search()
    private boolean search(Node node, E data) {
        if (node == null) return false;

        int cmp = data.compareTo(node.data);
        if (cmp == 0)
            return true;
        else if (cmp < 0)
            return search(node.left, data);
        else 
            return search(node.right, data);
    }

    // insert()
    public void insert(E data) {
        if (root == null) root = new Node(data);
        insert(root, data);
    }

    // helper method insert()
    private Node insert(Node node, E data) {
        if (node == null) return new Node(data);

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = insert(node.left, data);
        } else if (cmp > 0) {
            node.right = insert(node.right, data);
        }

        return node;
    }

    // delete()
    public void delete(E data) {
        delete(root, data);
    }

    // helper method delete()
    private Node delete(Node node, E data) {
        if (node == null) return null; // define the base case

        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = delete(node.left, data);
        } else if (cmp > 0) {
            node.right = delete(node.right, data);
        } 
        // dealing with 4 situations here
        else {
            if (node.left == null && node.right == null) { // the node itself is a leaf
                return null;
            } else if (node.left == null) { // no leaf on the left
                node = node.right;
            } else if (node.right == null) { // no leaf on the right
                node = node.left;
            }
            // case 4: the node for deletion is stem
            else {
                E subTreeMin = minValue(node.right);
                node.data = subTreeMin;
                node.right = delete(node.right, subTreeMin);
            }
        }

        return node;
    }

    // another helper method for delete() - minValue()
    private E minValue(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        
        return node.data;
    }

    // contains()
    public boolean contains(E data) {
        return contains(root, data);
    }
    
    // helper method contains()
    private boolean contains(Node node, E data) {
        if (node == null) return false;

        int cmp = data.compareTo(node.data);
        if (cmp == 0) return true; // base case
        else if (cmp < 0) {
            return contains(node.left, data);
        } else {
            return contains(node.right, data);
        }
    }

    /* Tree Traversal */

    // Breath First Search, also can be named levelOrederTraversal(), bfsTraversal()
    public ArrayList<E> BFS() {
        if (root == null) return new ArrayList<>();

        Node current = root;
        Queue<Node> queue = new ArrayDeque<>();
        ArrayList<E> results = new ArrayList<>();
        queue.add(current);

        while (queue.size() > 0) {
            current = queue.remove();
            results.add(current.data);
            
            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return results;
    }

    // There are three types in Depth First Search(DFS), preOrder(), postOrder(), and inOrder(). And we use recursion to solve the problem

    // preOrder()
    public ArrayList<E> preOrder() {
        ArrayList<E> results = new ArrayList<>();

        class Traverse {
            Traverse(Node node) {
                results.add(node.data);

                if (node.left != null) {
                    new Traverse(node.left);
                }
                if (node.right != null) {
                    new Traverse(node.right);
                }
            }
        }

        // invoke the constructor
        new Traverse(root);

        return results;
    }

    // postOrder()
    public ArrayList<E> postOrder() {
        ArrayList<E> results = new ArrayList<>();

        class Traverse {
            Traverse(Node node) {
                if (node.left != null) {
                    new Traverse(node.left);
                }
                if (node.right != null) {
                    new Traverse(node.right);
                }

                results.add(node.data);
            }
        }

        new Traverse(root);

        return results;
    }

    // inOrder()
    public ArrayList<E> inOrder() {
        ArrayList<E> results = new ArrayList<>();

        class Traverse {
            Traverse(Node node) {
                if (node.left != null) {
                    new Traverse(node.right);
                }

                results.add(node.data);

                if (node.right != null) {
                    new Traverse(node.left);
                }
            }
        }

        new Traverse(root);

        return results;
    }

    /* LeetCode Question: Maximun Depth of Binary Tree */
    public int maxDepth(Node root) {
        if (root == null) return 0;

        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);

        return Math.max(leftDepth, rightDepth) + 1;
    }

    /* LeetCode Question: Same Tree */
    public boolean isSameTree(Node p, Node q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.data != q.data) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /* LeetCode Question: Invert Binary Tree */
    public Node invertTree(Node root) {
        if (root == null) return null;
        Node left = invertTree(root.left);
        Node right = invertTree(root.right);
        root.left = right;
        root.right = left;

        return root;
    }

    /* LeetCode Question: Symmetric Tree (Mirror Tree) */
    public boolean isSymmetric(Node root) {
        return isSameTree(root, root);
    }

    // Helper method
    public boolean isSymmetric(Node t1, Node t2) {
        if (t1 == null && t2 == null) return true;
        if (t2 == null || t2 == null) return false;
        return (t1.data == t2.data) && isSymmetric(t1.right, t2.left) && isSymmetric(t1.left, t2.right);
    }
}
