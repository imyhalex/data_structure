package edu.nyu.cs.Stack.StackUdemy;

public class Solution {
    
    /*LeetCode Question: Valid Parentheses */
    public boolean isValid(String s) {
        java.util.Stack<Character> stack = new java.util.Stack<>();

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
}
