package com.pswishcorp.app;
// The maximum depth of a binary tree is the number of nodes on the path from the root down to the 
// most distant leaf node. Give an O(n) algorithm to find the max depth of a binary tree with n nodes.

// A python solution for reference
// class TreeNode:
//     def __init__(self, val=0, left=None, right=None):
//         self.val = val
//         self.left = left
//         self.right = right

// def maxDepth(root):
//     if root is None:  # Base case
//         return 0

//     left_depth = maxDepth(root.left)  # inductive case
//     right_depth = maxDepth(root.right)

//     # Return the maximum depth of the left and right subtrees, plus one for the current node.
//     return max(left_depth, right_depth) + 1

// Node class in the same file for simplification
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }
}

public class MaxDepthBinaryTree {
    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0; // Base case: an empty tree has depth 0.
        } else {
            int leftDepth = maxDepth(root.left);
            int rightDepth = maxDepth(root.right);
            
            // The depth of the tree is the maximum of the depths of the left and right subtrees, plus 1 for the root node.
            return Math.max(leftDepth, rightDepth) + 1;
        }
    }

    public static void main(String[] args) {
        MaxDepthBinaryTree maxTree = new MaxDepthBinaryTree();
        
        //  A sample binary tree.
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        int maxDepth = maxTree.maxDepth(root);
        System.out.println("Max Depth of this Binary Tree: " + maxDepth); // exppected output: 3
    }
}