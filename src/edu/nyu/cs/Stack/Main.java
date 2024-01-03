package edu.nyu.cs.Stack;

public class Main {

    // Coding Exercise: Sort Stack
    public static void sortStack(Stack<Integer> stack) {
        Stack<Integer> auxStack = new Stack<>();
        while (!stack.isEmpty()) {
            int temp = stack.pop();

            while (!auxStack.isEmpty() && auxStack.peek() > temp) {
                stack.push(auxStack.pop());
            }

            auxStack.push(temp);
        }
        // Once the main stack is empty, the elements from auxStack should be copied back to stack
        while (!auxStack.isEmpty()) {
            stack.push(auxStack.pop());
        }
    }
    
    // Coding Exercise: Parentheses Balanced
    public static boolean isBalancedParentheses(String testStr) {
        Stack<Character> stack = new Stack<>();
        for (char c : testStr.toCharArray()) {
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    // Coding Exercise: Reverse String
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

        System.out.println("--------------------------");

        testAndPrint("()", true);
        testAndPrint("()()", true);
        testAndPrint("(())", true);
        testAndPrint("()()()", true);
        testAndPrint("(()())", true);
        testAndPrint(")()(", false);
        testAndPrint(")(", false);
        testAndPrint("(()", false);
        testAndPrint("))", false);
        testAndPrint("(", false);
        testAndPrint(")", false);

        System.out.println("-------------------------");

        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);

        System.out.println("Before sorting:");
        stack.printStack();

        sortStack(stack);

        System.out.println("\nAfter sorting:");
        stack.printStack();
 
        /*
            EXPECTED OUTPUT:
            ----------------
            Before sorting:
            4
            1
            5
            2
            3
            
            After sorting:
            1
            2
            3
            4
            5

        */
    }

    public static void testAndPrint(String testStr, boolean expected) {
        // Run the test and store the result
        boolean result = isBalancedParentheses(testStr);
        
        // Print the test string, expected result, and actual result
        System.out.println("Test String: " + testStr);
        System.out.println("EXPECTED: " + expected);
        System.out.println("RESULT: " + result);
        
        // Check if the test passed or failed
        if (result == expected) {
            System.out.println("STATUS: PASS");
        } else {
            System.out.println("STATUS: FAIL");
        }
        
        // Print a separator for better readability
        System.out.println("--------------");
    }
}
