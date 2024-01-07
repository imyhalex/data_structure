package edu.nyu.cs.HashTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

    public int get(String key) {
        int index = hash(key);
        Node temp = dataMap[index];
        while (temp != null) {
            if (temp.key == key) {
                return temp.value;
            }
            temp = temp.next;
        }
        return 0; // If not contains the key, return 0;
    }

    public ArrayList<String> keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (int i = 0; i < dataMap.length; i++) {
            Node temp = dataMap[i];
            while (temp != null) {
                allKeys.add(temp.key);
                temp = temp.next;
            }
        }
        return allKeys;
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

    // Coding Exercise: Item in Common
    public static boolean itemInCommon(int[] arr1, int[] arr2) {
        HashMap<Integer, Boolean> myHashMap = new HashMap<>();
        for (int i : arr1) {
            myHashMap.put(i, true);
        }
        for (int j : arr2) {
            if (myHashMap.get(j) != null) return true;
        }
        return false;
    }

    // Coding Exercise: Find Duplicates
    public static List<Integer> findDuplicates(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int n : nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        List<Integer> duplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1) {
                duplicates.add(entry.getKey());
            }
        }
        return duplicates;
    }

    // Coding Exercise: First Non-Repeating Character
    public static Character firstNonRepeatingChar(String string) {
        Map<Character, Integer> charCounts = new HashMap<>();
 
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1); // getOrDefault returns the value to which the specified key is mapped
        }
 
        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);
            if (charCounts.get(c) == 1) {
                return c;
            }
        }
 
        return null;
    }

    // Coding Exercise: Group Anagrams
    public static List<List<String>> groupAnagrams(String[] strings) {
        Map<String, List<String>> anagramGroup = new HashMap<>();

        // For each string in strings
        for (String string : strings) {
            char[] charArr = string.toCharArray();
            Arrays.sort(charArr); // Sort the word
            String canonical = new String(charArr);

            if (anagramGroup.containsKey(canonical)) {
                anagramGroup.get(canonical).add(string);
            } else {
                List<String> group = new ArrayList<>();
                group.add(string);
                anagramGroup.put(canonical, group);
            }
        }

        return new ArrayList<>(anagramGroup.values());
    }

    // Coding Exercise: Two Sum
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> numMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            int complement = target - num;

            if (numMap.containsKey(complement)) 
                return new int[]{numMap.get(complement), i};

            numMap.put(num, i);
        }

        return new int[]{};
    }

    // Conding Exercise: Subarray Sum
    /*
     * int[] nums = [1, 2, 3, 4, 5]
     * int target = 9
     * int[] result1 = subarraySum(nums1, target1)
     * This should print [1, 3]
     * System.out.println("[" + result1[0] + ", " + result1[1] + "]");
     */
    // Example Execution:
    // Start with currentSum = 0.
    // Add 1 (first element of nums), currentSum = 1. No matching subarray yet.
    // Add 2 (second element), currentSum = 3. No matching subarray yet.
    // Add 3 (third element), currentSum = 6. No matching subarray yet.
    // Add 4 (fourth element), currentSum = 10. Check if 10 - 9 = 1 is in the map. It's not.
    // Add 5 (fifth element), currentSum = 15. Check if 15 - 9 = 6 is in the map. 
    //      It is, because when the cumulative sum was 6 (after adding the first three numbers), the index was 2. 
    //      So, a subarray from index 2 + 1 to 4 sums to 9.
    // Output: [1, 3] which are the start and end indices of the subarray [2, 3, 4] whose sum is 9.
    public static int[] subarraySum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // Initialize the postion of currentSum is 0
        int currentSum = 0;

        for (int i = 0 ; i < nums.length; i++) {
            currentSum += nums[i];

            if (map.containsKey(currentSum - target))
                return new int[]{map.get(currentSum - target) + 1, i};

            map.put(currentSum, i);
        }

        return new int[]{};
    }

    /* HashSet */

    // Coding Exercise: Remove Duplicates
    public static List<Integer> removeDuplicates(List<Integer> myList) {
        Set<Integer> set = new HashSet<>(myList);
        return new ArrayList<>(set);
    }

    // Coding Exercise: Has Unique Chars
    public static boolean hasUniqueChars(String string) {
        Set<Character> set = new HashSet<>();

        for (char s : string.toCharArray()) {
            if (set.contains(s)) {
                return false;
            } else {
               set.add(s); 
            }
        }

        return true;
    }

    // Coding Exercise: Find Pairs
    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target) {
        List<int[]> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        for (int i : arr1) {
            set.add(i);
        }

        for (int i : arr2) {
            int complement = target - i;
            if (set.contains(complement)) {
                list.add(new int[]{complement, i});
            }
        }
        return list;
    }

    // Coding Exercise: Longest Consecutive Sequence
    public static int longestConsecutiveSequence(int[] nums) {
        Set<Integer> set = new HashSet<>();
        
        for (int n : nums) {
            set.add(n);
        }

        int longestStreak = 0;
        for (int num : set) {
            // Condition: if set does not contains num - 1, it means this element is the start of a new consecutive sequence;
            if (!set.contains(num - 1)) {
                int currentNum = num;
                int currentStreak = 1;

                while (set.contains(currentNum + 1)) {
                    currentNum++;
                    currentStreak++;
                }

                // Condition: continous compare the current one and the previous one;
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }

        return longestStreak;
    }
}
