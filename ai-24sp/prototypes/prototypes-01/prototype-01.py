from PIL import Image

f = open ("train-images-idx3-ubyte", "rb")

data = f.read()

def convert_four(arr):
    result = 0 
    for i in arr:
        print(f"result {result} {i}")
        result *= 255
        result += i
    return result

size = int(data[6]*256+data[7])
print(f"Number of images {size}")

width = int(convert_four(data[8:12]))
height = int(convert_four(data[12:16]))

print(f"width {width}")
print(f"height {height}")

images = []

for i in range(size):
    start = 16 + i * 784
    end = 16 + (i+1)*784
    image = data[start:end]
    images.append(image)

i = 0 
for image in images:
    newImg = Image.new("RGB", (width, height))
    for x in range(width):
        for y in range(height):
            pixel = int(image[x*width+y])
            if pixel > 255:
                raise Error(f"Invalid 8-bit pixel value {pixel}")
            newImg.putpixel((x,y), (pixel, pixel, pixel))
    newImg.save(f"mnist-{i}.png")
    i += 1

f = open("train-labels-idx1-ubyte", "rb")

data = f.read()

def convert_four(arr):
    result = 0
    for i in arr:
        print(f"result {result} {i}")
        result *= 255
        result += i
    return result

size = int(data[6]*256+data[7])
print(f"Number of images {size}")

labels = []

f2 = open("labels.txt", "w")

for i in range(size):
    print(chr(data[8+i]+48))
    f2.write(chr(data[8+i]+48))

f2.close()
f.close()