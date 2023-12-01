from mst import prims_alg, Edge, Graph
import os
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


def test_prims_alg():
    graph = Graph()
    graph.extend(edges)
    mst = prims_alg(graph)
    expected_sum = 87
    assert expected_sum == mst.get_weight()


def test_prims_alg2():
    path = os.path.join(os.path.dirname(__file__), "50v80e.txt")
    file = open(path, "r")

    num_vertices, num_edges = file.readline().split(" ")
    data = file.readlines()
    file.close()
    v = set()
    e = []

    for line in data:
        line = line.split(" ")
        print(line)
        v.add(line[0])
        v.add(line[1])
        e.append((line[0], line[1], int(line[2])))

    graph = Graph()
    graph.extend(e)
    mst = prims_alg(graph)
    # data generated was supposed to be 50 but is apparently less
    # i guess there are some unconnected vertices
    assert len(mst.get_edges()) == len(v) - 1
