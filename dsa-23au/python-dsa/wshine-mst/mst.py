#!/usr/bin/python3
# graph consists of a list of vertices
# and a list of edges
# edge (end1, end2, weight)
import random
import heapq
from dataclasses import dataclass, field
from collections import defaultdict


@dataclass(order=True)
class Edge:
    ends: frozenset = field(compare=False)
    weight: int

    # edge is 3 item tuple (end1, end2, weight)
    def __init__(self, edge):
        self.ends = frozenset(edge[:2])
        self.weight = edge[2]

    def __hash__(self):
        return hash(self.ends)


class Graph:
    __vertices: dict[str, list[Edge]]

    def __init__(self):
        self.__vertices = defaultdict(list)
        self.__total_weight = 0

    def add_edge(self, edge: Edge):
        for v in edge.ends:
            self.__vertices[v].append(edge)
        self.__total_weight += edge.weight

    def extend(self, edges: list[tuple[str, str, int]]):
        for e in edges:
            e = Edge(e)
            self.add_edge(e)

    def get_edges(self) -> set[Edge]:
        return set.union(*[set(edges) for edges in self.__vertices.values()])

    def get_vertices(self) -> list[str]:
        return list(self.__vertices.keys())

    def get_weight(self) -> int:
        return self.__total_weight

    def adjacent(self, vertex: str) -> list[Edge]:
        return self.__vertices[vertex]

    def acyclic(self) -> bool:
        return False


# Graph -> Graph
def prims_alg(graph) -> Graph:
    vertices = graph.get_vertices()
    vertex = random.choice(vertices)
    visited = set()
    incident_edges = []
    mst = Graph()
    visited_count = 0

    while visited_count < len(vertices):
        visited.add(vertex)
        visited_count += 1

        connected_edges = graph.adjacent(vertex)
        for e in connected_edges:
            heapq.heappush(incident_edges, e)

        # take min, check if min edge crosses boundary
        min_edge = heapq.heappop(incident_edges)
        while min_edge.ends.issubset(visited):
            if not incident_edges:
                break
            min_edge = heapq.heappop(incident_edges)

        for v in min_edge.ends:
            if v not in visited:
                vertex = v
                mst.add_edge(min_edge)

    return mst


if __name__ == "__main__":
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
    original_cost = sum([edge[2] for edge in edges])
    graph = Graph()
    graph.extend(edges)
    print(graph.get_vertices())
    print(graph.adjacent("RN"))
    mst = prims_alg(graph)
    print("mst_res: {}".format(mst))
    print("mst length: {}".format(len(mst.get_edges())))
    print("original cost: {}".format(original_cost))
    print("mst cost: {}".format(mst.get_weight()))
