#!/usr/bin/python3
# graph consists of a list of vertices
# and a list of edges
# edge (end1, end2, weight)
import random
import heapq
from dataclasses import dataclass, field


@dataclass(order=True)
class Edge:
    ends: set = field(compare=False)
    weight: int

    # edge is 3 item tuple (end1, end2, weight)
    def __init__(self, edge):
        self.ends = set(edge[:2])
        self.weight = edge[2]


def prims_alg_heap(vertices, edges):
    vertex = random.choice(tuple(vertices))
    visited = set()
    incident_edges = []
    mst_edges = []

    while vertices:
        # set up
        vertices.discard(vertex)
        visited.add(vertex)

        connected_edges = filter(lambda x: vertex in x, edges)
        for e in connected_edges:
            heapq.heappush(incident_edges, Edge(e))
        edges.difference_update(connected_edges)
        # take min, check if min edge crosses boundary
        min_edge = heapq.heappop(incident_edges)
        while min_edge.ends.issubset(visited):
            if not incident_edges:
                break
            min_edge = heapq.heappop(incident_edges)

        diff = min_edge.ends.difference(visited)
        if diff:
            vertex = diff.pop()
            mst_edges.append(min_edge)

    return mst_edges


def prims_alg(vertices, edges):
    mst_visited_vertices = set()
    mst_edges = []

    # inital selection
    vertex = random.choice(tuple(vertices))
    incident_edges = [e for e in edges if vertex in e[:2]]
    min_edge = min(incident_edges, key=lambda e: e[2])
    mst_edges.append(min_edge)
    while vertices:
        # set add/discard both O(1)?
        for v in min_edge[:2]:
            mst_visited_vertices.add(v)
            vertices.discard(v)

        # there is probably a more efficient way to identify incident edges
        incident_edges = [e for e in edges if (
            e[0] in mst_visited_vertices) ^ (e[1] in mst_visited_vertices)]
        if incident_edges:
            min_edge = min(incident_edges, key=lambda e: e[2])
            mst_edges.append(min_edge)

    return mst_edges


if __name__ == "__main__":
    vertices = {"SB", "IGA", "RG", "KB", "CP", "CK", "M", "GK", "RN"}
    edges = {
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
    }

    v = vertices.copy()
    e = edges.copy()
    original_cost = sum([edge[2] for edge in e])
    mst = prims_alg(v, e)
    print("mst_res: {}".format(mst))
    print("mst length: {}".format(len(mst)))
    print("original cost: {}".format(original_cost))
    print("mst cost: {}".format(sum(edge[2] for edge in mst)))

    v = vertices.copy()
    e = edges.copy()
    original_cost = sum([edge[2] for edge in e])
    mst = prims_alg_heap(v, e)
    print("mst_res: {}".format(mst))
    print("mst length: {}".format(len(mst)))
    print("original cost: {}".format(original_cost))
    print("mst cost: {}".format(sum(edge.weight for edge in mst)))
