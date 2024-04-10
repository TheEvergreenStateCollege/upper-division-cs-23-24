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

    start=16
    end=16 + 784*size
    
    print(f"start image data at byte {start}")
    print(f"end image data at byte {end}")
    data_list = list(data[16:16 + 784 * size]) # I think this is bytearray type
    # we want list of bytes
    print(f"length of sliced 1D image data {type(data_list)}")

    data_arr = np.array(data_list, dtype=np.ubyte).reshape((size, width*height, 1))

    #Expect (60000*784, 1)  (the ,1) is a grayscale pixel value 0-255
    # or (47040000, 1)
    print(f"Shape of data straight from file {data_arr.shape}")

    start = 16 + i * 784
    end = 16 + (i+1)*784
    image = data[start:end]
    save_single_image(image, width, height)
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

load_training_image(127)