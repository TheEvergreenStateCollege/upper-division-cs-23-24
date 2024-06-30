from PIL import Image 
import numpy as np

def bytes_to_num(arr):
    result = 0
    for i in arr:
        #print(f"result {result} {i}")
        result *= 256
        result += i 
    return result

# Save a PNG from a single image in an np.array with shape (784,1)
def save_single_image(image_data, width, height):
    newImg = Image.new("RGB", (width, height))
    for x in range(width):
        for y in range(height):
            pixel = int(image_data[y*width+x])
            if pixel > 255:
                raise Error(f"Invalid 8-bit pixel value {pixel}")
            newImg.putpixel((x,y), (pixel, pixel, pixel))
    newImg.save(f"mnist.png")

def load_images(filename, which="all", i=0):
    f = open(filename, "rb")

    data = f.read()

    size = int(data[6]*256+data[7])
    print(f"Number of images {size}")

    width = int(bytes_to_num(data[8:12]))
    height = int(bytes_to_num(data[12:16]))

    print(f"width {width}")
    print(f"height {height}")

    start = 16
    end = 16 + 784*size
    
    if (which != "all" and (i >= 0) and (i < size)):
        start = 16 + 784*i 
        end = 16 + 784*(i+1)
        size = 1
    
    print(f"start image data at byte {start}")
    print(f"end image data at byte {end}")
    data_list = list(data[start:end]) # I think this is bytearray type
    # we want list of bytes
    print(f"length of sliced 1D image data {type(data_list)}")

    data_arr = np.array(data_list, dtype=np.ubyte).reshape((size, width*height, 1))

    #Expect (60000*784, 1)  (the ,1) is a grayscale pixel value 0-255
    # or (47040000, 1)
    print(f"Shape of data straight from file {data_arr.shape}")

    return (data_arr, width, height) 


# Return ndarray of shape [60000, 784]
def load_all_training_images():
    return load_images("train-images-idx3-ubyte")

# Return ndarray of shape [10000, 784]
def load_all_test_images():
    return load_images("t10k-images-idx3-ubyte")

# 0x7  ->  [ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 ]
# label is integer 0-9 to index output vector
def one_hot_vector_from_label(label):
    e = np.zeros((10, 1))
    e[label] = 1.0
    return e

# Load all labels by default,
# or if "which" parameter is not the default, then 
# load a single label at index i from [0, size)
def load_labels(filename="", which="all", i=0):
    f = open(filename, "rb")
    data = f.read()

    size = bytes_to_num(data[4:8])
    print(f"Number of training labels {size}")

    image_data = None
    if (which == "all"):
        image_data = data[8:]
    elif ((i >= 0) and (i < size)):
        image_data = data[8+i:8+i+1] 
    return image_data

# Return ndarray of shape [10000, 10]
def load_all_training_labels():
    labels = load_labels("train-labels-idx1-ubyte")
    return np.array([one_hot_vector_from_label(l) for l in labels])

# Return ndarray of shape [10000, 10]
def load_all_test_labels():
    return np.array(list(load_labels("t10k-labels-idx1-ubyte")))
