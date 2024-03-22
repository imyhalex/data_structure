package edu.nyu.cs.AVLTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AVLTree {

    private Node root;
    
    private class Node {
        int value, height;
        Node left, right;

        Node(int value) {
            this.value = value;
        }
    }

    private int height(Node N) {
        if (N == null) {
            return 0;
        }

        return N.height;
    }

    // Helper method to get balanced factor of node N
    private int getBalanced(Node N) {
        if (N == null) {
            return 0;
        }

        return height(N.left) - height(N.right);
    }

    // A utility function to print preorder traversal of the tree.
    // The function also prints height of every node
    private void preOrder(Node node) {
        if (node != null) {
            System.out.printf("%d", node.value);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform Rotation
        x.right = y;
        y.left = T2;

        // update heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        // return new root
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform Rotation
        y.left = x;
        x.right = T2;

        // update heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        // return new root
        return y;
    }

    public Node insert(Node node, int value) {
        // 1. Perform the normal BST insertion
        if (node == null) {
            return (new Node(value));
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }

        // 2. Update the height of its parent node
        node.height = Math.max(height(node.left), height(node.right)) + 1;

        // 3. Get the balanced factor of this parent node to check whether this node become unbalanced
        int balanced = getBalanced(node);

        // If unbalanced, there are 4 cases:

        // LL (left left) case
        if (balanced > 1 && value < node.left.value) {
            return rightRotate(node);
        }

        // RR (right right) case
        if (balanced < -1 && value > node.right.value) {
            return leftRotate(node);
        }

        // LR (left right) case
        if (balanced > 1 && value > node.left.value) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL (right left) case
        if (balanced < -1 && value < node.right.value) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        // Return the (unchanged) node pointer
        return node;
    }

    // Helper Method for delete node
    private Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    public Node deleteNode(Node root, int value) {
        // 1. Perform the standard BST delete
        if (root == null) {
            return root;
        }

        if (value < root.value) {
            root.left = deleteNode(root.left, value);
        } else if (value > root.value) {
            root.right = deleteNode(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } 
            else {
                Node temp = minValueNode(root.right);
                root.value = temp.value;
                root.right = deleteNode(root.right, value);
            }
        }

        // if there is only one node then return
        if (root == null) {
            return root;
        }

        // 2. Update the height on the current node
        root.height = Math.max(height(root.left), height(root.right)) + 1;

        // 3. get the balanced factor of this node
        int balanced = getBalanced(root);

        // Also consider 4 cases:

        // LL (left left) case
        if (balanced > 1 && getBalanced(root.left) >= 0) {
            return rightRotate(root);
        }

        // RR (right right) case
        if (balanced < -1 && getBalanced(root.right) <= 0) {
            return leftRotate(root);
        }

        // LR (left right) case
        if (balanced > 1 && getBalanced(root.right) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // RL (right left) case
        if (balanced < -1 && getBalanced(root.left) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void print(Node root) {

        if(root == null) {
            System.out.println("(XXXXXX)");
            return;
        }

        int height = root.height,
            width = (int)Math.pow(2, height-1);

        // Preparing variables for loop.
        List<Node> current = new ArrayList<Node>(1),
            next = new ArrayList<Node>(2);
        current.add(root);

        final int maxHalfLength = 4;
        int elements = 1;

        StringBuilder sb = new StringBuilder(maxHalfLength*width);
        for(int i = 0; i < maxHalfLength*width; i++)
            sb.append(' ');
        String textBuffer;

        // Iterating through height levels.
        for(int i = 0; i < height; i++) {

            sb.setLength(maxHalfLength * ((int)Math.pow(2, height-1-i) - 1));

            // Creating spacer space indicator.
            textBuffer = sb.toString();

            // Print tree node elements
            for(Node n : current) {

                System.out.print(textBuffer);

                if(n == null) {

                    System.out.print("        ");
                    next.add(null);
                    next.add(null);

                } else {

                    System.out.printf("(%6d)", n.value);
                    next.add(n.left);
                    next.add(n.right);

                }

                System.out.print(textBuffer);

            }

            System.out.println();
            // Print tree node extensions for next level.
            if(i < height - 1) {

                for(Node n : current) {

                    System.out.print(textBuffer);

                    if(n == null)
                        System.out.print("        ");
                    else
                        System.out.printf("%s      %s",
                                n.left == null ? " " : "/", n.right == null ? " " : "\\");

                    System.out.print(textBuffer);

                }

                System.out.println();

            }

            // Renewing indicators for next run.
            elements *= 2;
            current = next;
            next = new ArrayList<Node>(elements);

        }

    }

    public static void main(String args[]) {
        AVLTree t = new AVLTree();
        Node root = null;
        while (true) {
            System.out.println("(1) Insert");
            System.out.println("(2) Delete");

            try {
                BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
                String s = bufferRead.readLine();

                if (Integer.parseInt(s) == 1) {
                    System.out.print("Value to be inserted: ");
                    root = t.insert(root, Integer.parseInt(bufferRead.readLine()));
                }
                else if (Integer.parseInt(s) == 2) {
                    System.out.print("Value to be deleted: ");
                    root = t.deleteNode(root, Integer.parseInt(bufferRead.readLine()));
                }
                else {
                    System.out.println("Invalid choice, try again!");
                    continue;
                }

                t.print(root);
            }
            catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
