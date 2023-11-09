# 4.11 Random node, cracking the code
# You are implementing a binary tree form scratch which, in addition to insert, find and delete, 
# has a method getRandomNode() which returns a random node for the tree. All nodes should be equally likely to be chosen.
# Design and mplement an algoriythim for get randomNode, and explain how you would implement the rest of the methods
# My opinion: Java seems better for readbility for this problem but i like python as i might use this eventualy.

import random

class TreeNode:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.size = 1  # root node size

def insert(root, value):
    if root is None:
        return TreeNode(value)
    
    if value <= root.value:
        root.left = insert(root.left, value)
    else:
        root.right = insert(root.right, value)
    
    root.size += 1
    return root

def find(root, value):
    if root is None:
        return None

    if value == root.value:
        return root

    if value < root.value:
        return find(root.left, value)
    else:
        return find(root.right, value)

def delete(root, value):
    if root is None:
        return root

    if value < root.value:
        root.left = delete(root.left, value)
    elif value > root.value:
        root.right = delete(root.right, value)
    else:
        if root.left is None:
            return root.right
        elif root.right is None:
            return root.left
        min_node = find_min(root.right)
        root.value = min_node.value
        root.right = delete(root.right, min_node.value)

    root.size = 1 + size(root.left) + size(root.right)
    return root

def find_min(node):
    while node.left is not None:
        node = node.left
    return node

def size(node):
    if node is None:
        return 0
    return node.size

def getRandomNode(root):
    if root is None:
        return None

    # Generate a random index #
    index = random.randint(0, size(root) - 1)

    # Traverses the tree to find the node at the rand index
    return getKthNode(root, index)

def getKthNode(node, k):
    left_size = size(node.left)
    if k < left_size:
        return getKthNode(node.left, k)
    elif k == left_size:
        return node
    else:
        return getKthNode(node.right, k - left_size - 1)

# Test it out:
root = None
values = [20, 50, 70, 10, 90, 40, 60]

for value in values:
    root = insert(root, value)

print("Random Node:", getRandomNode(root).value)
