package edu.nyu.cs.Recursion.TowerOfHanoi;

/*
 * Move the first n - 1 disks from tower A to C recursively with the assitance of tower B
 * Move the disk n from A to B 
 * Move n - 1 disks from C to B recursively with the assitance of tower A
 */

import java.util.Scanner;

public class TowerOfHanoi {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("Enter the number of disks: ");
        int n = scn.nextInt();
        
        // Display solution
        System.out.println("The moves are: ");
        moveDisks(n, 'A', 'B', 'C');

        scn.close();
    }

    // The method for finding the solution to move n disks from fromTower to toTower with auxTower
    public static void moveDisks(int n, char fromTower, char toTower, char auxTower) {
        if (n == 1) { // base case that stops the condition
            System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower);
        } else {
            moveDisks(n - 1, fromTower, auxTower, toTower); // step 1
            System.out.println("Move disk " + n + " from " + fromTower + " to " + toTower); // step 2
            moveDisks(n - 1, auxTower, toTower, fromTower); // step 3
        }
    }
}
