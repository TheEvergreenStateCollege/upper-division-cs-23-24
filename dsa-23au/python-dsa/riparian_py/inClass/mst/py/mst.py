import string

nodeDict = {}
edgePQ = []

edgeRoute = []
routeCost = 0


# node IDs are a single uppercase letter ABC...XYZ (node limit 26!)
alphabet = string.ascii_uppercase

# the strategy should be to keep all the edges in a priority queue
# based on their cost.  then i pick from the queue (the front)
# and get the reference to the edge.  then i look to see if the
# edge leads to a visited node, and if so, remove it from the PQ (and do nothing)
# otherwise, 'travel' to the node and repeat

# revision:  only add edges to the queue that lead to unvisited nodes
# 

class Node:
    def __init__(self, id: str):
        self.id = id
        self.localEdges = []
        self.visited = False


class Edge:
    def __init__(self, originNodeID:str, destNodeID:str, edgeCost:int):
        self.id = str(originNodeID)+str(destNodeID)
        self.origin = nodeDict[originNodeID]
        self.destination = nodeDict[destNodeID]
        self.cost = int(edgeCost)
    

class Graph:
    def __init__(self, graphNodes):

        nodeA = Node('a')
        nodeB = Node('b')
        nodeC = Node('c')
        nodeD = Node('d')
        nodeE = Node('e')
        nodeF = Node('f')
    # we must assume our graph is populated with
    # nodes connected by edges

    # the very minumum we need to start is one node
    
    # to continue we need another node, and a cost
    # travel from one node to another (on an edge)




def findMST(graph):
    return graph
