#!/usr/bin/python3
# graph consists of a list of vertices
# and a list of edges
# edge (end1, end2, weight)
import random
import heapq
from dataclasses import dataclass, field


@dataclass(order=True)
class Edge:
    ends: frozenset = field(compare=False)
    weight: int

    # edge is 3 item tuple (end1, end2, weight)
    def __init__(self, edge):
        self.ends = frozenset(edge[:2])
        self.weight = edge[2]

    # not necessary
    def __hash__(self):
        return hash(self.ends)


# list<string> -> list<Edge> -> (int, set<Edge>)
def prims_alg_heap(vertices, edges):
    vertex = random.choice(vertices)
    visited = set()
    incident_edges = []
    mst_edges = []
    mst_weight = 0
    visited_count = 0

    while visited_count < len(vertices):
        # set up
        visited.add(vertex)
        visited_count += 1

        # could be improved if graph representation had an adjacency list
        connected_edges = filter(lambda x: vertex in x.ends, edges)
        for e in connected_edges:
            heapq.heappush(incident_edges, e)

        # take min, check if min edge crosses boundary
        min_edge = heapq.heappop(incident_edges)
        while min_edge.ends.issubset(visited):
            if not incident_edges:
                break
            min_edge = heapq.heappop(incident_edges)

        diff = min_edge.ends.difference(visited)
        if len(diff) == 1:
            vertex = next(iter(diff))
            mst_edges.append(min_edge)
            mst_weight += min_edge.weight

    return mst_weight, mst_edges


if __name__ == "__main__":
    vertices = ["SB", "IGA", "RG", "KB", "CP", "CK", "M", "GK", "RN"]
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
    edges = [Edge(e) for e in edges]
    original_cost = sum([edge.weight for edge in edges])
    original_edges = edges.copy()
    original_vertices = vertices.copy()
    mst_cost, mst = prims_alg_heap(vertices, edges)
    assert vertices == original_vertices
    assert edges == original_edges
    print("mst_res: {}".format(mst))
    print("mst length: {}".format(len(mst)))
    print("original cost: {}".format(original_cost))
    print("mst cost: {}".format(mst_cost))
