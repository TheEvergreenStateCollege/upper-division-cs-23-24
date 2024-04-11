f = open("train-labels-idx1-ubyte", "rb")
data = f.read()

def convert_four(arr):
    result=0
    for i in arr:
        print(f"result {result} {i}")
        result *= 255 # Shifting by 8 bits
        result += i
    return result

size = int(data[6]*256+data[7])
print(f"Number of images {size}")
labels = []

f2 = open("label.txt", "w")

for i in range(size):
    print(chr(data[8+i]+48))
    f2.write(chr(data[8+i]+48))

f2.close()
f.close()