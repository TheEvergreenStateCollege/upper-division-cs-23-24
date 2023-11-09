# 10.10 from cracking the code
# Rank from Stream:
# Imagine you are reading in a stream of integers. Peridoically myou wish to be able to 
# look up the rank of a number x (the number of values in the stream that are less than or equal to x).
# Implements the data structures and algorithms to support these operations. THat is, implement the method track(int x), 
# which is called when each number is generated, and the method getRnakOfNumber, which returns the number
# of values in the stream that are less than or equal to x(not including x itself).

class TreeNode:
    def __init__(self, value):
        self.value = value
        self.left = None
        self.right = None
        self.rank = 1  # Initial rank is 1

class RankTracker:
    def __init__(self):
        self.root = None

    def track(self, x):
        if not self.root:
            self.root = TreeNode(x)
        else:
            self._insert(self.root, x)

    def _insert(self, node, x):
        node.rank += 1  # Increment the rank for each inserted node
        if x <= node.value:
            if node.left:
                self._insert(node.left, x)
            else:
                node.left = TreeNode(x)
        else:
            if node.right:
                self._insert(node.right, x)
            else:
                node.right = TreeNode(x)

    def _getRank(self, node, x):
        if not node:
            return 0
        if x < node.value:
            return self._getRank(node.left, x)
        elif x > node.value:
            return node.rank + self._getRank(node.right, x)
        else:
            return node.rank
        
    def getRankOfNumber(self, x):
        return self._getRank(self.root, x)

# Example output:
def main():
    tracker = RankTracker()
    tracker.track(9)
    tracker.track(4)
    tracker.track(7)
    tracker.track(1)
    tracker.track(15)
    tracker.track(2)
    tracker.track(9)

    print(tracker.getRankOfNumber(4))
    print(tracker.getRankOfNumber(9))

if __name__ == "__main__":
    main()