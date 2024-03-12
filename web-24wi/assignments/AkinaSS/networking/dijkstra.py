'''
Dikjstra's algorithm
'''

class edge:
    def __init__(self, start, end, weight):
        self.start = start
        self.end = end
        self.weight = weight

def graph_init():
    v = {1,2,3,4,5,6}
    e1 = edge(1, 2, 1)
    e2 = edge(1, 3, 5)
    e3 = edge(2, 3, 2)
    e4 = edge(3, 4, 5)

if __name__ == '__main__':
    graph_init()