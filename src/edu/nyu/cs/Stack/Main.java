package edu.nyu.cs.Stack;

public class Main {
    
    public static String reverseString(String myString) {
        Stack<Character> stack = new Stack<>();
        char[] charList = myString.toCharArray();
        for (char c : charList) {
            stack.push(c);
        }
        String newString = "";
        while (!stack.isEmpty()) {
            newString += stack.pop();
        }
        return newString;
    }

    public static void main(String[] args) {

        String myString = "hello";
        String reversedString = reverseString(myString);
        System.out.println(reversedString);

        /*
            EXPECTED OUTPUT:
            ----------------
            olleh
        */

    }
}
