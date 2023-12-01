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


def test_acyclic():
    cyclic_edges = [("a", "b", 1),
                    ("b", "c", 1),
                    ("c", "a", 1),
                    ("b", "d", 1)]
    cyclic_graph = Graph()
    cyclic_graph.extend(cyclic_edges)
    vs = cyclic_graph.get_vertices()
    res = []
    for v in vs:
        res.append(cyclic_graph.acyclic(v))
    assert not all(res)

    acyclic_edges = [("a", "c", 1),
                     ("a", "d", 1),
                     ("d", "b", 1),
                     ("a", "e", 1)]
    acyclic_graph = Graph()
    acyclic_graph.extend(acyclic_edges)
    vs = acyclic_graph.get_vertices()
    res = []
    for v in vs:
        res.append(acyclic_graph.acyclic(v))
    assert all(res)


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
    vertices = set()
    e = []

    for line in data:
        line = line.split(" ")
        print(line)
        vertices.add(line[0])
        vertices.add(line[1])
        e.append((line[0], line[1], int(line[2])))

    graph = Graph()
    graph.extend(e)
    mst = prims_alg(graph)
    acyclic = [mst.acyclic(v) for v in vertices]
    # data generated was supposed to be 50 but is apparently less
    # i guess there are some unconnected vertices
    assert len(mst.get_edges()) == len(vertices) - 1
    assert all(acyclic)
