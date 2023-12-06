package com.pswishcorp.app;
/*Problem Cracking the code 4.4 
Check Balanced: Implement a function to check if a binary tree is balanced. For the purpose of this quesiton, 
a balanced tree is defnied to be a tree such that the height of the two subtrees of any node never differ by more than one.
*/

class TreeNode {  // Embedded Node class for compact file structure
    int value;
    TreeNode left;
    TreeNode right;

    TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }
}

public class BalancedBinaryTree {  // problem 4.4

    public boolean isBalanced(TreeNode root) {
        if (root == null) {  // Base Case
            return true;
        }

        // Inductive Case: Calculate the height of left, right subtrees
        // Check absolute heights of left and right subtrees to determine if balanced
        // Recursively check for balanced subtrees

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (Math.abs(leftHeight - rightHeight) > 1) {
            return false;
        }
        
        return isBalanced(root.left) && isBalanced(root.right);
    }

    public int getHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = getHeight(node.left);
        int rightHeight = getHeight(node.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static void main(String[] args) {
        BalancedBinaryTree checker = new BalancedBinaryTree();

        // Test output:
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(8);

        boolean isBalanced = checker.isBalanced(root);
        System.out.println("The tree in balanced... " + isBalanced);
    }
}
