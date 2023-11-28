from mst import Graph, prims_alg


def test_prims_alg():
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
    mst = prims_alg(g)
    expected_sum = 87
    mst_sum = sum([e[2] for e in mst])
    # assert the msts are equivalent? or assert the total weight is equivalent?
    assert expected_sum == mst_sum
