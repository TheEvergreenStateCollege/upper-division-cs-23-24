from mst import prims_alg, prims_alg_heap

vertices = {"SB", "IGA", "RG", "KB", "CP", "CK", "M", "GK", "RN"}
# missing 5 edges
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


def test_prims_alg():
    v = vertices.copy()
    e = edges.copy()
    mst = prims_alg(v, e)
    expected_sum = 87
    # assert the msts are equivalent? or assert the total weight is equivalent?
    mst_sum = sum([e[2] for e in mst])
    assert expected_sum == mst_sum


def test_prims_alg_heap():
    v = vertices.copy()
    e = edges.copy()
    mst = prims_alg_heap(v, e)
    expected_sum = 87
    mst_sum = sum([e.weight for e in mst])
    assert expected_sum == mst_sum
