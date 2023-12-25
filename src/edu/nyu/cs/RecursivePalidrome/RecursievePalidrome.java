package edu.nyu.cs.RecursivePalidrome;

// Example of recursive helper method
public class RecursievePalidrome {

    public static boolean isPalindrome(String s) {
        return isPalindrome(s, 0, s.length() - 1);
    }

    private static boolean isPalindrome(String s, int low, int high) {
        if (high <= low) return true; // Base case
        else if (s.charAt(low) != s.charAt(high)) return false; // Base case
        else return isPalindrome(s, low + 1, high - 1);
    }

    public static void main(String[] args) {
        System.out.println("Is moon a palindrome? " + isPalindrome("moon"));
        System.out.println("Is mom a palindrome? " + isPalindrome("mom"));
        System.out.println("is aba a palindrome? " + isPalindrome("aba"));
    }
}
