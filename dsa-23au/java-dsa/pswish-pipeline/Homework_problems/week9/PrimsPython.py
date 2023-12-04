class Edge:
    def __init__(self, source, destination, weight):
        # Set up the Edge Node
        self.source = source
        self.destination = destination
        self.weight = weight

class Graph:
    def __init__(self):
        self.BAG = {}

    def add_edge(self, source, destination, weight):  # add each edge to the bag
        leading_edge = Edge(source, destination, weight)
        trailing_edge = Edge(destination, source, weight)

        self.BAG.setdefault(source, []).append(leading_edge)  # set default is a complex form of get()
        self.BAG.setdefault(destination, []).append(trailing_edge)

    # Getter functions
    def get_nodes(self):
        return set(self.BAG.keys())

    def get_edges(self, node):
        return self.BAG.get(node, [])

class PrimsMST:
    # Main prims class
    def __init__(self):
        pass

    def find_MST(self, graph):  # main function
        try:
            is_visited = set()
            MST = set()

            if not graph.get_nodes():  # error check
                return MST

            start_node = next(iter(graph.get_nodes()))
            is_visited.add(start_node)

            while len(is_visited) < len(graph.get_nodes()):  # escapes when is_visted = lend of graph set keys
                min_edge = None

                for node in is_visited:
                    for edge in graph.get_edges(node):
                        if edge.destination not in is_visited and (min_edge is None or edge.weight < min_edge.weight):
                            min_edge = edge

                if min_edge:
                    is_visited.add(min_edge.destination)
                    MST.add(min_edge)

            return MST
        except Exception as e:
            print(e)


def main():
    graph = Graph()  # instantiate the graph class 
    # Could add this to a test to make it more usable and just pass in a source, dest, and weight
    graph.add_edge("IGA","CK",16)
    graph.add_edge("IGA","M",14)
    graph.add_edge("M","CK",15)
    graph.add_edge("M","GK",12)
    graph.add_edge("GK","CK",14)
    graph.add_edge("RG","IGA",14)
    graph.add_edge("IGA","KB",19)
    graph.add_edge("RG","KB",7)
    graph.add_edge("CK","KB",10)
    graph.add_edge("KB","CP",4)
    graph.add_edge("CP","CK",6)
    graph.add_edge("GK","CP",17)
    graph.add_edge("SB","KB",8)
    graph.add_edge("RG","SB",10)
    graph.add_edge("RG","CK",12)
    graph.add_edge("IGA","CP",20)
    graph.add_edge("CP","RN",22)
    graph.add_edge("RN","KB",24)

    prims = PrimsMST() # instantiate the prims class
    MST = prims.find_MST(graph)

    print("Prims MST list of edges:")
    for edge in MST:
        print(f"{edge.source} on edge:({edge.weight}) -to- {edge.destination}")

if __name__ == "__main__":
    main()
