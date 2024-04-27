from PIL import Image

def convert_four(arr):
    """
    Converts a four-byte array to an integer value.

    Parameters:
    arr (bytes): A four-byte array.

    Returns:
    int: The converted integer value.
    """
    result = 0
    for i in arr:
        result *= 255
        result += i
    return result


# read the MNIST image data file
f = open("train-images-idx3-ubyte", "rb")
data = f.read()

# extract metadata: size, width, and height of images
size = int(data[6] * 256 + data[7])
width = int(convert_four(data[8:12]))
height = int(convert_four(data[12:16]))

# display metadata
print(f"Number of images: {size}")
print(f"Width: {width}")
print(f"Height: {height}")

# extract and process each image
images = []
for i in range(size):
    start = 16 + i * 784
    end = 16 + (i + 1) * 784
    image = data[start:end]
    images.append(image)

# convert image data to PNG format and save
i = 0
for image in images:
    newImg = Image.new("RGB", (width, height))
    for x in range(width):
        for y in range(height):
            pixel = int(image[x * width + y])
            if pixel > 255:
                raise Exception(f"Invalid 8-bit pixel value {pixel}")
            newImg.putpixel((x, y), (pixel, pixel, pixel))
    newImg.save(f"data/mnist-{i}.png")
    i += 1

# usage examples
"""
Usage Examples:
1. To convert a four-byte array to an integer:
   arr = b'\x00\x00\x00\x01'
   result = convert_four(arr)
   print(result)  # Output: 1

2. This script reads MNIST image data, extracts metadata such as the number of images, width, and height, 
   and converts each image to PNG format. The converted images are saved in the 'data' directory.
   To execute the script, simply run it in a Python environment:
   python your_script.py
"""
