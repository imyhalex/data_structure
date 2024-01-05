package edu.nyu.cs.HashTable;

public class HashTable {
    private int size = 7; // Set as prime number to reduce the likelihood of collision
    private Node[] dataMap;

    private class Node {
        String key;
        int value;
        Node next;

        Node(String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTable() {
        dataMap = new Node[size];
    }

    public void printTable() {
        for (int i = 0 ; i < dataMap.length; i++) {
            System.out.println(i + ":");
            Node temp = dataMap[i];
            while (temp != null) {
                System.out.println("    {" + temp.key + "= " + temp.value + "}");
                temp = temp.next;
            }
        }
    }

    public void set(String key, int value) {
        int index = hash(key);
        Node newNode = new Node(key, value);
        if (dataMap[index] == null) {
            dataMap[index] = newNode;
        } else {
            Node temp = dataMap[index]; // pointer that point to the first node (euqals to Node temp = head;)
            // Takes to the current last node
            while (temp.next != null) {
                temp = temp.next;
            }
            // Add the new node
            temp.next = newNode;
        }
    }

    // Hash Mehtod is private
    private int hash(String key) {
        int hash = 0;
        char[] keyChars = key.toCharArray();
        for (int i = 0; i < keyChars.length; i++) {
            int asciiValue = keyChars[i];
            hash = (hash + asciiValue * 23) % dataMap.length; // multiplication by 23 is a strategy to improve the distribution of hash values and reduce collisions. 
        }
        return hash;
    }
}
