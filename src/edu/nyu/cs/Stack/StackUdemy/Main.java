package edu.nyu.cs.Stack.StackUdemy;

public class Main {
    
    public static void main(String[] args) {
        Stack myStack = new Stack(4);

        myStack.push(1);

        myStack.printStack();
        System.out.println();

        Stack myStack2 = new Stack(7);
        myStack2.push(23);
        myStack2.push(3);
        myStack2.push(11);
        
        myStack2.pop();
        myStack2.printStack();
    }

}
