from loader import load_images, load_labels, save_single_image
from network import Network

nn = Network.fromPBJSON("model-30epochs-10batch-1_0eta.pbjson")

i = 137
(image_data, width, height) = load_images("train-images-idx3-ubyte", which=None, i=i)
image_label = load_labels("train-labels-idx1-ubyte", which=None, i=i)
image_data = image_data.reshape((width*height, 1))
save_single_image(image_data, width, height)
print(f"Saved image {i} as mnist.png")
print(f"Classified image {i} as {nn.single(image_data)}")
print(f"Label {i} is {image_label}")