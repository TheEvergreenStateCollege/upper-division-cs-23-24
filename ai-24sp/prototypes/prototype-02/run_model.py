from loader import load_images, load_labels, save_single_image
from network import Network

nn = Network.fromPBJSON("model-50epochs-10batch-0_2eta-80_18success.pbjson")

# Added this 4/18 - Morgan 
# Doesn't account for non-int inputs; tread carefully
i = int(input("Give me an index: "))
while i<0 or i>=60000:
    print("Index value must be in the range 0-59,999")  
    i = int(input("Give me an index: "))  

(image_data, width, height) = load_images("train-images-idx3-ubyte", which=None, i=i)
image_label = load_labels("train-labels-idx1-ubyte", which=None, i=i)
image_data = image_data.reshape((width*height, 1))
save_single_image(image_data, width, height)
print(f"Saved image {i} as mnist.png")

# Made output more readable 4/18 - Morgan.
print(f"Computer: I think #{i} is the number {nn.single(image_data)}")
if nn.single(image_data)==int.from_bytes(image_label, 'little'):
    print("...\nGood work computer! That's right\n")
else:
    print(f"\nWRONG\nLabel #{i} is the number {int.from_bytes(image_label, 'little')}")