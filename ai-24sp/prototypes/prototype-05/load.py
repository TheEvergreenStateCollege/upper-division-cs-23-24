#!/usr/bin/env python3

from PIL import Image
import sys
import os

def convert_four_bytes_to_integer(buf):
    result = 0
    for i in buf:
        result *= 255
        result += i
    return result

def extract_metadata(buf):
    return (int(buf[6] * 256 + buf[7]),
        int(convert_four_bytes_to_integer(buf[8:12])),
        int(convert_four_bytes_to_integer(buf[12:16])))

def extract_training_images():
    os.mkdir("training_images")
    f = open("train-images-idx3-ubyte", "rb")
    data = f.read()
    f.close()
    (n_images, width, height) = extract_metadata(data)
    print(f"Dataset contains {n_images} images {width}x{height} pixels")
    images = []
    for i in range(n_images):
        start = 16 + i * 784
        end = 16 + (i + 1) * 784
        raw_img = data[start:end]
        images.append(raw_img)
    count = 0
    for raw_img in images:
        img = Image.new("RGB", (width, height))
        for x in range(width):
            for y in range(height):
                pixel = int(raw_img[x * width + y])
                if pixel > 255:
                    raise Error(f"Invalid 8-bit pixel value {pixel}")
                img.putpixel((x,y), (pixel, pixel, pixel))
        img.save(f"training_images/mnist-{count}.png")
        count += 1
    print(f"Extracted {count} PNG images to training_images/ directory")

def extract_training_labels():
    f = open("train-labels-idx1-ubyte", "rb")
    data = f.read()
    f.close()
    size = int(data[6] * 256 + data[7])
    print(f"Found {size} images")
    labels = []
    out = open("training_labels.txt", "w")
    for i in range(size):
        out.write(chr(data[8 + i] + 48))
    print("Wrote labels to training_labels.txt")
    out.close()

if __name__ == "__main__":
    print("Expecting training data in train-images-idx3-ubyte and train-labels-idx1-ubyte")
    extract_training_images()
    extract_training_labels()
