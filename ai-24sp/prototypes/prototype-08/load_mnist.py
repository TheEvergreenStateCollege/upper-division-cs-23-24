from PIL import Image 
import numpy as np

def bytes_to_num(arr):
    result = 0
    for i in arr:
        print(f"result {result} {i}")
        result *= 255
        result += i 
    return result

# Save a PNG from a single image in an np.array with shape (784,1)
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
def load_all_training_images():
    f = open("train-images-idx3-ubyte", "rb")

    data = f.read()

    size = int(data[6]*256+data[7])
    print(f"Number of images {size}")

    width = int(bytes_to_num(data[8:12]))
    height = int(bytes_to_num(data[12:16]))

    print(f"width {width}")
    print(f"height {height}")

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

    return (data_arr, width, height) 

# Return ndarray of shape [10000, 784]
def load_all_test_images():
    f = open("t10k-images-idx3-ubyte", "rb")

    data = f.read()

    size = int(data[6]*256+data[7])
    print(f"Number of images {size}")

    width = int(bytes_to_num(data[8:12]))
    height = int(bytes_to_num(data[12:16]))

    print(f"width {width}")
    print(f"height {height}")

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

    return (data_arr, width, height) 

# 0x7  ->  [ 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 ]
# label is integer 0-9 to index output vector
def one_hot_vector_from_label(label):
    e = np.zeros((10, 1))
    e[label] = 1.0
    return e

# Return ndarray of shape [10000, 10]
def load_all_training_labels():
    f = open("train-labels-idx1-ubyte", "rb")

    data = f.read()

    size = bytes_to_num(data[6:8])
    print(f"Number of images {size}")

    return np.array([one_hot_vector_from_label(l) for l in data[8:]])

#    label = data[8+i]
#    one_hot_vector = one_hot_vector_from_label(label)

#    print(f"Label of image {i} is {chr(label+48)} {one_hot_vector}")

# Return ndarray of shape [10000, 10]
def load_all_test_labels():
    f = open("t10k-labels-idx1-ubyte", "rb")

    data = f.read()

    size = bytes_to_num(data[6:8])
    print(f"Number of test labels {size}")

    return np.array([one_hot_vector_from_label(l) for l in data[8:]])


## LOAD TRAINING IMAGES AND LABELS
# shape is (60000, 784, 1)
(original_training_images, width, height) = load_all_training_images()
print(f"Training data shape {original_training_images.shape}")
# save_single_image(original_training_images[127], width, height)

# shape is (60000, 10)
training_labels = load_all_training_labels()
# load_training_label(127)

# shape is (60000, ((784, 1), 10))
# 60000 2-tuples, of (x,y) image,label training pair
original_training_data = zip(original_training_images, training_labels)

# shape is (10000, ((784, 1), 10))
#validation_data = original_training_data[50000:60000]
#print(f"Validation data shape {validation_data.shape}")

# Use all 60000 of original data for training for now
training_data = original_training_data

# shape is (10000, 784, 1)
(test_images, width, height) = load_all_test_images()
# shape is (10000, 10)
test_labels = load_all_test_labels()
# shape is (10000, ((784, 1), 10))
# 10000 2-tuples, of (x,y) image,label training pair
test_data = zip(test_images, test_labels)

from network import Network

nn = Network([784, 100, 10])
nn.SGD(
    training_data=training_data,
    epochs=30,
    mini_batch_size=10,
    eta=0.001,
    test_data=test_data
)

# When this stops, you'll have a model with ~96% success rate (4% error)

# Validate here with validate_data