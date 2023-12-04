package com.pswishcorp.app;
// 3-16. (3) Find the storage efficiency ratio (the ratio of data space over total space) for each of the following binary tree implementations on n nodes:
// 	1. All nodes store data, two child pointers, and a parent pointer. The data field requires 4 bytes and each pointer requires 4 bytes.
//  2. Only leaf nodes store data; internal nodes store two child pointers. The data field requires four bytes and each pointer requires two bytes.

import java.util.*;


class TreeNodeWithParent {
    int data;
    TreeNodeWithParent left, right, parent;

    TreeNodeWithParent(int data, TreeNodeWithParent parent) {
        this.data = data;
        this.parent = parent;
    }

    int totalSpace() {
        int space = 4; // Data field size
        if (left != null) {
            space += 4; // Child pointer size
        }
        if (right != null) {
            space += 4; // Child pointer size
        }
        if (parent != null) {
            space += 4; // Parent pointer size
        }
        return space;
    }
}

class TreeNodeWithInternalData {
    TreeNodeWithInternalData left, right;

    int totalSpace() {
        int space = 4; // Data field size (for leaf nodes)
        if (left != null) {
            space += 2; // Child pointer size (for internal nodes)
        }
        if (right != null) {
            space += 2; // Child pointer size (for internal nodes)
        }
        return space;
    }
}

public class BinaryTreeStorageEfficiency {

    public static double calculateStorageEfficiencyRatio(TreeNodeWithParent root) {
        double totalSpace = 0;
        double dataSpace = 0;
        
        // Calculate total space and data space
        // using a breadth-first traversal.
        Queue<TreeNodeWithParent> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNodeWithParent node = queue.poll();
            totalSpace += node.totalSpace();
            
            if (node.data != 0) {
                dataSpace += 4; // Data field size
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        // Calculate the storage efficiency ratio
        return dataSpace / totalSpace;
    }

    public static double calculateStorageEfficiencyRatio(TreeNodeWithInternalData root) {
        double totalSpace = 0;
        double dataSpace = 0;

        // Calculate total space and data space
        // using a breadth-first traversal.
        Queue<TreeNodeWithInternalData> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNodeWithInternalData node = queue.poll();
            totalSpace += node.totalSpace();
            
            if (node.left == null && node.right == null) {
                dataSpace += 4; // Data field size (for leaf nodes)
            }

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        // Calculate the storage efficiency ratio
        return dataSpace / totalSpace;
    }

    public static void main(String[] args) {

        // TreeNodeWithParent
        TreeNodeWithParent root1 = new TreeNodeWithParent(1, null);
        root1.left = new TreeNodeWithParent(2, root1);
        root1.right = new TreeNodeWithParent(3, root1);
        double ratio1 = calculateStorageEfficiencyRatio(root1);
        System.out.printf("Storage Efficiency Ratio for Tree 1: %.2f%%\n", ratio1 * 100);

        // TreeNodeWithInternalData
        TreeNodeWithInternalData root2 = new TreeNodeWithInternalData();
        root2.left = new TreeNodeWithInternalData();
        root2.right = new TreeNodeWithInternalData();
        double ratio2 = calculateStorageEfficiencyRatio(root2);
        System.out.printf("Storage Efficiency Ratio for Tree 2: %.2f%%\n", ratio2 * 100);
    }
}
