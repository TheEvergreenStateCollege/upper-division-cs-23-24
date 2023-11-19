

# https://en.wikipedia.org/wiki/K-means_clustering

import datetime
import sys
import csv
import json
import xml.etree.ElementTree as ET
import array

sys.setrecursionlimit(5000)

tideXMLs = [
    "./tideData/jan22.xml",
    "./tideData/feb22.xml",
    "./tideData/mar22.xml",
    "./tideData/apr22.xml",
    "./tideData/may22.xml",
    "./tideData/jun22.xml",
    "./tideData/jul22.xml",
    "./tideData/aug22.xml",
    "./tideData/sep22.xml",
    "./tideData/oct22.xml",
    "./tideData/nov22.xml",
    "./tideData/dec22.xml",
]

def buildTideDict():
    outputData = {}
    event_ID = 0

    for file_path in tideXMLs:
        tree = ET.parse(file_path)
        root = tree.getroot()

        # Find all <item> elements
        tideEvents = root.findall('.//item')

        for tideEvent in tideEvents:
            tideEvent_item = {
                'event_ID': event_ID,
                'date': tideEvent.find('date').text,
                'day': tideEvent.find('day').text,
                'time': tideEvent.find('time').text,
                'pred': float(tideEvent.find('pred').text),
                'highlow': tideEvent.find('highlow').text
            }
            outputData[event_ID] = tideEvent_item
            event_ID += 1

    return (outputData, event_ID)

class TreeNode:
    def __init__(self, node_ID, value):
        self.node_ID = node_ID
        self.value = value
        self.left = None
        self.right = None


class TideSearch:

    def __init__(self, tideDict, dictLength):
        self.root = None
        self.searchTree = array.array('f')
        self.tideDict = tideDict
        self.dictLength = dictLength

    def buildTree(self):



        for i in range (self.dictLength):
            
            # print(self.tideDict[i])
            self.insert(i ,self.tideDict[i]['pred'])
            # print(self.root)
        return 0

    def insert(self, node_ID, value):
        if self.root is None:
            self.root = TreeNode(node_ID, value)
        else:
            self._insert_recursive(self.root, node_ID, value)

    def _insert_recursive(self, node, node_ID, value):
        if value < node.value:
            if node.left is None:
                node.left = TreeNode(node_ID, value)
            else:
                self._insert_recursive(node.left, node_ID*2, value)
        else:
            if node.right is None:
                node.right = TreeNode(node_ID, value)
            else:
                self._insert_recursive(node.right, node_ID*2+1, value)


    def find_range(self, low, high):
        return self._find_range_recursive(self.root, low, high)

    def _find_range_recursive(self, node, low, high):
        if node is None:
            return -1

        if low <= node.value <= high:
            return node.value
        elif node.value < low:
            return self._find_range_recursive(node.right, low, high)
        else:
            return self._find_range_recursive(node.left, low, high)

def runMain():
    # Command-line arguments are stored in sys.argv[1:]
    # For example, to get the first argument (if provided):
    if len(sys.argv) > 1:
        first_arg = sys.argv[1]
        print("First argument:", first_arg)
    else:
        print("No arguments provided")

    (tideDict, dictLength) = buildTideDict()
    print(tideDict)

    ts = TideSearch(tideDict, dictLength)
    built = ts.buildTree()

    included = ts.find_range(0.2,3)
    print(included)
    # print("printing found element:  ")  # Example: finds a value between 4.0 and 6.5
    # print(ts.find_range(4.0, 6.5))  # Example: finds a value between 4.0 and 6.5

    return 0

if __name__ == "__main__":
    runMain()