# Pseudocode for Prim's Algorithm Steps

# // Step 1: 
# Create empty tree, MST (Minimum Spanning Tree)
# Put all nodes and edges into a priority queue

# // Step 2: Pull any one node from PQ and add it to MST
# Mark this node as visited

# // Step 3: While PQ is not empty:
#   For each edge that connects a visited node to an unvisited node:
#     If the edge's weight is lower than the current edge weight in PQ for the connected node:
#       Update the edge weight in PQ
#   Choose the edge with the minimum weight that connects to an unvisited node
#   Add this edge to MST
#   Mark the connected node as visited

# // Step 4: 
# Remove the connected node from PQ

# // Step 5: Rinse/Repeat
# Continue looping until PQ is empty (all nodes are visited and added to MST)
