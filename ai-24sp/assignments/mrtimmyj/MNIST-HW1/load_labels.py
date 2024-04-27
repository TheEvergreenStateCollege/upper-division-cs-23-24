"""
Reads MNIST label data and creates a text file containing the labels.

Reads the MNIST label data file in binary mode, extracts the number of
labels (images) from the     data, and creates a text file containing the
labels in ASCII format. Each label is converted to ASCII character and
written to the text file. The ASCII value of the label is adjusted by
adding 48 (ASCII value of '0').

Parameters:
None

Returns:
None
"""

# open the MNIST labels data file in binary mode
f = open("train-labels-idx1-ubyte", "rb")

# read the entire content of the file
data = f.read()

# extract the number of labels (images) from the data
size = int(data[6] * 256 + data[7])

# display the number of images
print(f"Number of images: {size}")

# create an empty list to store the labels
labels = []

# open a text file to write the labels in ASCII format
f2 = open("data/labels.txt", "w")

# iterate through each label in the data
for i in range(size):
    # convert the label to ASCII character and write it to the file
    # ASCII value of the label is adjusted by adding 48 (ASCII value of '0')
    f2.write(chr(data[8 + i] + 48))

# close the text file
f2.close()

# close the labels data file
f.close()