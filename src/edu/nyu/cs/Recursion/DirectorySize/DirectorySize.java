package edu.nyu.cs.Recursion.DirectorySize;

import java.io.File;
import java.util.Scanner;

// length() method returns the size of the file
// listFiles() method returns an array of File objects under a directory

// test directory: C:\Users\alex2\Documents\intro_to_java_programming_exercises

public class DirectorySize {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter a directory or a file: ");
        String o = scn.nextLine();
        System.out.println(getSize(new File(o)) + " bytes");
        scn.close();
    }

    public static long getSize(File file) {
        long size = 0;

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; files != null && i < files.length; i++) {
                size += getSize(files[i]);
            }
        } else {
            size += file.length();
        }

        return size;
    }
}
