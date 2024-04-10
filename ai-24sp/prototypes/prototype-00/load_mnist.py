from PIL import Image 
import numpy as np

def bytes_to_num(arr):
    result = 0
    for i in arr:
        print(f"result {result} {i}")
        result *= 255
        result += i 
    return result

def save_single_image(image_data, width, height):
    newImg = Image.new("RGB", (width, height))
    for x in range(width):
        for y in range(height):
            pixel = int(image_data[x*width+y])
            if pixel > 255:
                raise Error(f"Invalid 8-bit pixel value {pixel}")
            newImg.putpixel((x,y), (pixel, pixel, pixel))
    newImg.save(f"mnist.png")

# Return ndarray of shape [60000, 784]
def load_training_image(i):
    f = open("train-images-idx3-ubyte", "rb")

    data = f.read()

    size = int(data[6]*256+data[7])
    print(f"Number of images {size}")

    width = int(bytes_to_num(data[8:12]))
    height = int(bytes_to_num(data[12:16]))

    print(f"width {width}")
    print(f"height {height}")

    # images = [] 

    data_arr = np.array(data[16:16 + 784 * size])

    #for i in range(size):
        start = 16 + i * 784
        end = 16 + (i+1)*784
        image = data[start:end]
        save_single_image
        #images.append(image)

# Return ndarray of shape [60000, 10]
def load_training_labels():
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

    #for i in range(size):
    #    print(chr(data[8+i]+48))
    #    f2.write(chr(data[8+i]+48))

    #f2.close()
    #f.close()


def load_and_write_one(image):
    # i = 0
# for image in images:
#     newImg = Image.new("RGB", (width, height))
#     for x in range(width):
#         for y in range(height):
#             pixel = int(image[x*width+y])
#             if pixel > 255:
#                raise Error(f"Invalid 8-bit pixel value {pixel}")
#            newImg.putpixel((x,y), (pixel, pixel, pixel))
#    newImg.save(f"mnist-{i}.png")
#    i += 1
