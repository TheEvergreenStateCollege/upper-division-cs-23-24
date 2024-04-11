f = open("train-labels-idx1-ubyte", "rb")
data = f.read()

size = int(data[6]*256+data[7])
print(f"Number of images {size}")
labels = []
f2 = open("data/labels.txt","w")

for i in range(size):
    f2.write(chr(data[8+i]+48))

f2.close()
f.close()
