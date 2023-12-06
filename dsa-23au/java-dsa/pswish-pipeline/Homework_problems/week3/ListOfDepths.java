// package com.pswishcorp.app;
// 4.3 
// List of depths:
// given a binary tree, design an algorithm which creates a linked list of all the nodes at each depth 
// (eg, if you have a tree with depth D, you'll have D linked list).

public class ListOfDepths {
    public List<List<Integer>> listOfDepths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);
                if (node.left!= null) {
                    queue.offer(node.left);
                }
                if (node.right!= null) {
                    queue.offer(node.right);
                }
            }
            result.add(list);
        }
        return result;
    }
}