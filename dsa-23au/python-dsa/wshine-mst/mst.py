#!/usr/bin/python3
# graph consists of a list of vertices
# and a list of edges
# edge (end1, end2, weight)
import random


class Graph:
    def __init__(self, vertices, edges):
        self.vertices = vertices
        self.edges = edges


def prims_alg(graph):
    mst = Graph([], [])
    vertex = random.choice(graph.vertices)
    mst.vertices.append(vertex)
    incident_edges = [e for e in graph.edges if vertex in e[:2]]
    min_edge = min(incident_edges, key=lambda e: e[2])
    mst.edges.append(min_edge)

    while len(mst.vertices) < len(graph.vertices):
        mst.vertices.extend([v for v in min_edge[:2] if v not in mst.vertices])
        incident_edges = [e for e in graph.edges if (
            e[0] in mst.vertices) ^ (e[1] in mst.vertices)]
        if incident_edges:
            min_edge = min(incident_edges, key=lambda e: e[2])
            mst.edges.append(min_edge)

    return mst.edges


vertices = ["SB", "IGA", "RG", "KB", "CP", "CK", "M", "GK", "RN"]
# missing 5 edges
edges = [
    ("RN", "KB", 24),
    ("RN", "CP", 22),
    ("KB", "CP", 4),
    ("SB", "KB", 8),
    ("RG", "KB", 7),
    ("SB", "RG", 10),
    ("RG", "IGA", 14),
    ("RG", "CK", 12),
    ("CK", "KB", 10),
    ("CK", "CP", 6),
    ("IGA", "KB", 19),
    ("IGA", "CP", 20),
    ("IGA", "CK", 16),
    ("IGA", "M", 14),
    ("M", "GK", 12),
    ("M", "CK", 15),
    ("GK", "CK", 14),
    ("GK", "CP", 17)
]
g = Graph(vertices, edges)
original_cost = sum([e[2] for e in g.edges])
mst = prims_alg(g)
print("mst_res: {}".format(mst))
print(len(mst))
print("original cost: {}".format(original_cost))
print("mst_cost: {}".format(sum(e[2] for e in mst)))
