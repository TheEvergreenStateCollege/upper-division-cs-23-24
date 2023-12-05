#!/usr/bin/python3
# graph consists of a list of vertices
# and a list of edges
# edge (end1, end2, weight)
import random
import heapq
from pprint import pprint
from dataclasses import dataclass, field
from collections import defaultdict, deque


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

    def dest(self, vertex) -> str:
        for v in self.ends:
            if v != vertex and vertex in self.ends:
                return v
        return ""


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

    def get_edges(self):
        res = set()
        for x in self.__vertices.values():
            for edge in x:
                res.add(edge)
        return list(res)

    def get_vertices(self) -> list[str]:
        return list(self.__vertices.keys())

    def get_weight(self) -> int:
        return self.__total_weight

    def connected(self, vertex: str) -> list[Edge]:
        return self.__vertices[vertex]

    def adjacent_vertices(self, vertex: str) -> list[str]:
        edges = self.connected(vertex)
        return [e.dest(vertex) for e in edges]

    def acyclic(self, start) -> bool:
        vertices = self.get_vertices()
        visited = {v: False for v in vertices}
        parent = {v: "" for v in vertices}
        queue = deque()

        visited[start] = True
        queue.append(start)

        while queue:
            current = queue.pop()

            for v in self.adjacent_vertices(current):
                if not visited[v]:
                    visited[v] = True
                    queue.append(v)
                    parent[v] = current
                elif parent[current] != v:
                    return False
        return True


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

        connected_edges = graph.connected(vertex)
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
    mst = prims_alg(graph)
    print("mst_res:")
    pprint(mst.get_edges())
    print("mst length: {}".format(len(mst.get_edges())))
    print("original cost: {}".format(original_cost))
    print("mst cost: {}".format(mst.get_weight()))
    vertices = mst.get_vertices()
    acyclic = [mst.acyclic(v) for v in vertices]
    print(acyclic)
    print("\noriginal")
    for x in graph.get_edges():
        print(hex(id(x)))
    print("\nmst")
    for x in mst.get_edges():
        print(hex(id(x)))
