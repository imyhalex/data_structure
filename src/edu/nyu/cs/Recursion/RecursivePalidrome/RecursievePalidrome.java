package edu.nyu.cs.Recursion.RecursivePalidrome;

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

    // linear version
    public static boolean checkPalindrom(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(right) != s.charAt(left)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {
        System.out.println("Is moon a palindrome? " + isPalindrome("moon"));
        System.out.println("Is mom a palindrome? " + isPalindrome("mom"));
        System.out.println("is aba a palindrome? " + isPalindrome("aba"));
        System.out.println("is aba a palindrome? " + isPalindrome("awdedwa"));
    }
}
