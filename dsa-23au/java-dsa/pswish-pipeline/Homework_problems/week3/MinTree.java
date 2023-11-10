// package com.pswishcorp.app;
// 4.2 Minimal Tree
// Given a sorted array (increasing order) with unique integer elements, 
// write an algorythim to create a binary search tree with min height
public class MinTree {

    public TreeNode buildTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return buildTree(nums, 0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
            }

            int mid = start + (end - start) / 2;
            TreeNode root = new TreeNode(nums[mid]);
            root.left = buildTree(nums, start, mid - 1);
            root.right = buildTree(nums, mid + 1, end);
            return root;
        }
}