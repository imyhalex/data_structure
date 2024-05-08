## Sorts with LinkedList
```java
void bubbleSort() 
{
    if (this.size < 2) 
        return;

    boolean wasSwapped;
    do {
        Node<E> current = head;
        wasSwapped = false;

        while (current.getNext() != null) {
            Node<E> nextNode = current.getNext();

            if (current.getElement().compareTo(nextNode.getElement()) > 0) {
                E temp = current.getElement();
                current.setElement(nextNode.getElement());
                nextNode.setElement(temp);

                wasSwapped = true;
            }
            current = current.getNext();
        }
    } while (wasSwapped);
}
```

```java
void insertionSort() 
{
    if (this.head == null || this.head.getNext() == null)
        return;

    Node<E> dummy = new Node<>(null, head);
    Node<E> current = head.getNext();
    Node<E> tailSorted = head;

    while (current != null) {
        if (tailSorted.getElement().compareTo(current.getElement()) <= 0)
            tailSorted = current;
        else {
            Node<E> prev = dummy;
            while (prev.getNext().getElement().compareTo(current.getElement() < 0)
                prev = prev.getNext();
            // Conduct swap
            tailSorted.setNext(current.getNext());
            current.setNext(prev.getNext());
            prev.setNext(current);
        }

        current = tailSorted.getNext();
    }

    this.head = dummy.getNext();
}
```

```java
void selectionSort()
{
    if (this.size < 2)
        return;

    Node<E> currnet = head;
    while (current.getNext() != null) {
        Node<E> smallest = current;
        Node<E> innerNode = current.getNext();

        while (innserNode != null) {
            if (smallest.getElement().compareTo(innerNode.getElement()) > 0)
                smallest = innerNode;
            innerNode = innerNode.getNext();
        }

        if (!smallest.equals(current)) {
            E temp = current.getElement();
            current.setElement(smallest.getElement());
            smallest.setElement(temp);
        }

        current = current.getNext();
    }

    this.tail = current;
}
```
```java
void mergeSort(Node<E> head1, Node<E> head2)
{
    Node<E> dummy = new Node<>(null, null);

    Node<E> prev = dummy;
    while (head1 != null && head2 != null) {
        if (head1.getElement().compareTo(head2.getElement()) < 0) {
            prev.setNext(head1);
            head1 = head1.getNext();
        }
        else {
            prev.setNext(head2);
            head2 = head2.getNext();
        }

        prev = prev.getNext();
    }

    if (head1 != null)
        prev.setNext(head1);
    else 
        prev.setNext(head2);
    
    this.head = dummny.getNext();
}
```

## Tree
```java
/*
 * Given the root of a binary tree, return its maximum depth.
 * A binary tree's maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
*/

int maxDepth(TreeNode root)
{
    if (root == null)
        return 0;
    
    int leftDepth = maxDepth(root.left);
    int rightDepth = maxDepth(root.right);

    return Math.max(leftDepth, rightDepth) + 1;
}
```

```java
/*
 * Given the roots of two binary trees p and q, write a function to check if they are the same or not.
 * Two binary trees are considered the same if they are structurally identical, and the nodes have the same value.
*/

boolean isSameTree(TreeNode p, TreeNode q)
{
    if (p == null && q == null)
        return true;

    if (p == null || q == null) 
        return false;

    if (p.val != q.val)
        return false;
    
    return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```
```java
/*
 * Invert a Binary Tree
 * Given the root of a binary tree, invert the tree, and return its root.
*/

TreeNode invertTree(TreeNode root)
{
    if (root == null) return null;
    
    TreeNode left = invertTree(root.left);
    TreeNode right = invertTree(root.right);

    root.left = right;
    root.right = left;

    return root;
}
```
```java
/*
 * Given the root of a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).
 * This question is similar to if two tree are mirrored (isMirror)
*/

boolean isSymmetric(TreeNode root)
{
    return isSymmetric(root, root);
}

private boolean isSymmetric(TreeNdoe t1, TreeNode t2)
{
    if (t1 == null && t2 == null)
        return true;
    
    if (t1 == null || t2 == null)
        return false;
    
    return (t1.val == t2.val) && isSymmetric(t1.left, t2.right) && isSymmetric(t1.right, t2.left);
}
```

```java
/*
 * Given the root of a binary tree and an integer targetSum, 
 * return true if the tree has a root-to-leaf path such that adding up all the values along the path equals targetSum.
*/

boolean hasPathSum(TreeNode root, int targetSum)
{
    if (root == null) return false;
    
    targetSum -= root.val;

    if (root.left == nulll && root.right == null) return targetSum == 0;
    
    return hasPathSum(root.left, targetSum) || hasPathSum(root.right, targetSum);
}
```

```java
/*
 * Minimum Absoulute Difference in BST
 * Given the root of a Binary Search Tree (BST), return the minimum absolute difference between the values of any two different nodes in the tree.
*/

int getMinimumDifference(TreeNode root) 
{
    ArrayList<Integer> list = new ArrayList<>();
    list = infix(root, list);

    int minDiff = Integer.MAX_VALUE;
    for (int i = 1; i < list.size(); i++) { // i should start from 1
        minDiff = Math.min(minDiff, list.get(i) - list.get(i - 1));
    }

    return minDiff;
}

private ArrayList<Integer> infix(TreeNode root, ArrayList<Integer> list) 
{
    if (root == null) return list;
    infix(root.left, list);
    list.add(root.val);
    infix(root.right, list);
    return list;
}
```

```java
// Binary Tree Right Side View
// Given the root of a binary tree, imagine yourself standing on the right side of it, return the values of the nodes you can see ordered from top to bottom

// Objective: get the rightmost node in each level
// Concepts: BFS
List<Integer> rightSideView(TreeNode root)
{
    List<Integer> list = new ArrayList<>();
    if (root == null) return list;

    Queue<TreeNode> currentLevel = new LinkedList<>();
    currentLevel.add(root);

    while (!currentLevel.isEmpty()) {
        int currentSize = currentLevel.size();

        for (int i = 0; i < currentSize; i++) {
            TreeNode node = currentLevel.poll();

            // Action 1: get the rightmost node when reach the max size of the queue
            if (i == currentSize - 1)
                list.add(node.val);
            
            // Action 2: Treaverse the tree to the next level and update the currentLevel
            if (node.left != null)
                currentLevel.add(node.left);
            
            if (node.right != null)
                currentLevel.add(node.right);
        }
    }

    return list;
}
```

```java
/*
    Average of levels in Binary Tree
    Given the root of a binary tree, return the average value of the nodes on each level in the form of an array. Answers within 10-5 of the actual answer will be accepted.
*/

List<Double> averageOfLevel(TreeNode root)
{
    List<Double> averages = new ArrayListM<>();
    if (root == null) return averages;

    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        Double sum = 0.0;
        int levelSize = queue.size();

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();

            if (node.left != null) 
                queue.add(node.left);

            if (node.right != null)
                queue.add(node.right);
            
            sum += node.vall;
        }

        Double avearge = sum / levelSize;
        averages.add(average);
    }

    return averages;
}
```
```java
/*
    Given the root of a binary tree, return the zigzag level order traversal of its nodes' values. (i.e., from left to right, then right to left for the next level and alternate between).
    Input: root = [3,9,20,null,null,15,7]
    Output: [[3],[20,9],[15,7]]
*/
List<List<Integer>> zigzagLevelOrder(TreeNode root)
{
    List<List<Integer>> zigOrder = new ArrayList<>();
    Queue<TreeNode> queue = new LinkedList<>();

    if (root = null) return zigOrder;
    queue.add(root);
    boolean leftToRight = true;

    while (!queue.isEmpty()) {
        int levelSize = queue.size();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < levelSize; i++) {
            TreeNode node = queue.poll();
            
            if (leftToRight)
                list.add(node.val);
            else
                list.add(0, node.val);

            if (node.left != null)
                quene.add(node.left);
            
            if (node.right != null)
                queue.add(node.right);
        }
        leftToRight = !leftToRight;
        zigOrder.add(list);
    }

    return zigOrder;
}
```

```java
/**
 * Covnert Sorted Array to Binary Search Tree
 * Given an integer array nums where the elements are sorted in `ascending order`, convert it to a height-balanced binary search tree.
 * 
 **/

TreeNode sortedArrayBST(int[] nums)
{
    return helper(numns, 0, nums.length - 1);
}

private TreeNode helper(int[] nums, int left, int right)
{
    Random rand = new Random();

    if (left > right) return null;

    // find the middle node
    int p = (left + right) / 2;
    // If left + right is odd, add randomly 0 or 1 to p-index; randomly choose another middle node
    if ((left + right) % 2 == 1)
        p += rand.nextInt(2);

    // do preorder traversal
    TreeNode root = new TreeNode(nums[p]);
    root.left = helper(nums, left, p - 1);
    root.right = helper(nums, p + 1, right);
    
    return root;
}
```
```java
/***
 * Construct BST from Sorted Linked List 
 * Given the head of a singly linked list where elements are sorted in ascending order, convert it to a height-balanced binary search tree.
*/

TreeNode linkedListToBST(ListNode head)
{
    if (head == null) return null;

    ListNode mid = findTheMiddleNode(head);

    TreeNode node = new TreeNode(mid.val);

    // if there is only one element in the linked list
    if (head == mid) {
        return node;
    }

    node.left = linkedListToBST(head);
    node.right = linkedListToBST(mid.next);
    return node;
}

private ListNode findTheMiddleNode(ListNode head)
{
    ListNode prev = null; // the pointer used to disconnect the left half from the mid node
    ListNode slow = head;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
        prev = slow;
        slow = slow.next;
        fast = fast.next.next;
    }

    if (prev != null) {
        prev.next = null;
    }

    return slow;
}
```