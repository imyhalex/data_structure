package edu.nyu.cs.Stack.StackUdemy;

import java.util.Stack;

public class Solution {
    
    /*LeetCode Question: Valid Parentheses */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{')
                stack.push(c);
            else {
                if (stack.isEmpty())
                    return false;
                
                char top = stack.peek();
                boolean case1 = c == ')' && top == '(';
                boolean case2 = c == ']' && top == '[';
                boolean case3 = c == '}' && top == '{';

                if (case1 || case2 || case3)
                    stack.pop();
                else
                    return false;
            }
        }
        // finally evaluate if there is anything left
        return stack.isEmpty();
    }

    /* LeetCode Question: Simplify Path */
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        String[] components = path.split("/");

        for (String directory : components) {
            if (directory.equals(".") || directory.isEmpty())
                continue;
            
            else if (directory.equals("..")) { 
                if (!stack.isEmpty())
                    stack.pop();
            } else {
                stack.push(directory);
            }
        }

        StringBuilder simplifyPath = new StringBuilder();
        for (String item : stack) {
            simplifyPath.append("/");
            simplifyPath.append(item);
        }

        return simplifyPath.length() == 0 ? "/" : simplifyPath.toString();
    }

    /* LeetCode Question: Evaluate Reverse Polish Notation */
    public int evalRPN(String[] tokens) {
        Stack<String> stack = new Stack<>();

        for (String token : tokens) {
            if (!isOperator(token))
                stack.push(token);
            else {
                int left = Integer.parseInt(stack.pop());
                int right = Integer.parseInt(stack.pop());
                int result = 0;

                switch (token) {
                    case "+":
                        result = right + left;
                        break;
                    case "-":
                        result = right - left;
                        break;
                    case "*":
                        result = right * left;
                        break;
                    case "/":
                        result = right / left;
                }
                stack.push(Integer.toString(result));
            }
        }

        return Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String token) {
        return token.equals("+") || token.equals("-")
         || token.equals("*") || token.equals("/") || token.equals("^");
    }


    /* LeetCode Question: Longest Valid Parentheses */
    /*
    Example 1:
    Input: s = "(()"
    Output: 2
    Explanation: The longest valid parentheses substring is "()".

    Example 2:
    Input: s = ")()())"
    Output: 4
    Explanation: The longest valid parentheses substring is "()()".

    Example 3:
    Input: s = ""
    Output: 0 

    Example 4:
    Input: s = "()(()"
    Output: 2
    */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); // Base for the first valid substring

        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else {
                stack.pop();

                if (stack.isEmpty()) {
                    // If the stack is empty, push the current index as the base for the next valid substring
                    stack.push(i);
                } else {
                    // Calculate the 
                    maxLength = Math.max(maxLength, i - stack.peek());
                }
            }
        }

        return maxLength;
    } 
}
