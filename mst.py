# graph consists of a list of vertices
# and a list of edges
# edge (end1, end2, weight)
import random
class Graph:
    def __init__(self, vertices, edges):
        self.vertices = vertices
        self.edges = edges

def prims_alg():
    pass

# incomplete
def prims_test(graph):
    initial_vertex = random.choice(graph.vertices)
    
    # mst is list of edges
    incident_edges = [ e for e in graph.edges if initial_vertex in e[:1] ]
    print(incident_edges)
    min = min()


    pass
    return mst
vertices = [ "SB", "IGA", "RG", "KB", "CP", "CK", "M", "GK", "RN" ]
# missing 5 edges
edges = [
    ("RN", "KB", 24),
    ("RN", "CP", 22),
    ("KB", "CP", 4),
    ("SB", "KB", 8),
    ("SB", "RG", 10),
    ("RG", "IGA", 14),
    ("IGA", "M", 14),
    ("M", "GK", 12),
    ("M", "CK", 15),
    ("GK", "CK", 14),
    ("GK", "CP", 17)
]
graph = Graph()
mst = []
