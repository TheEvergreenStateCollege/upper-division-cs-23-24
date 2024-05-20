from PIL import Image # Python image library

f = open("train-images-idx3-ubyte", "rb")

data = f.read()


# Converting Bytes Array ("convert 4" ~ 4 bytes at a time)
def convert_four(arr):
    results=0
    for i in arr:
        print(f"result {result} {i}")
        result *= 255 # Shifting by 8 bits
        result += i
    return result

size = int(data[6]*256 + data[7])
print(f"number of images: {size}")

width = int(convert_four(data[8:12]))   # Width: 8, 9, 10, 11
height = int(convert_four(data[12:16])) # Heights: 12, 13, 14, 15

print(f"width: {width}")
print(f"height: {height}")

images = []

for i in range(size):
    start = 16 * i * 784
    end = 16 + (i + 1) * 784
    image = data[start:end]
    images.append(image)

i = 0 
for image in images: 
    newImg = Image.new("RGB", (width, height))
    for x in range(height):
        for y in range(width):
            pixel=int(image[x*width+y])
            if pixel > 255: 
                raise Error(f"Invalid 16 bit value: {pixel}")
            newImg.putpixel((x, y), (pixel, pixel, pixel))
    newImg.save(f"mnist-{i}.png")
    i+=1