# This is an archive version of MNIST code used for study and research purpose. The following comments are from people
# working on it

# AI Lab Thursday 2024-04-11
Ryan & Dee Dee
- Previous group: "We were unable to try running `network.py` because `load_images.py` took too long to run." This was due to a copy of the code from load_labels.py at the bottom of load_images.py. It was running everytime load_images ran, doubling the size from 60k to 120k, causing a crash. We deleted that code and network.py ran with no errors.


# AISH Lab 1

4/4/24
Gavin Bowers & Jonah Eadie

In this lab, one of the tasks was to update a Python2 neural network program (`network.py`) to Python3. Thankfully not much was broken. The program used `xrange()`, which doesn't exist in Python3. Apparently, in Python2 `xrange()` uses a lazy-evaluated list for the range, while `range()` is eager. In Python3, they got rid of `xrange()` and made `range()` lazy-evaluated. The other change was that Python2 `print` statements don't use parentheses.

![](Pasted%20image%2020240404143944.png)
The above image shows network.py after the `print` statements were fixed but before `xrange()` was fixed.

We transcribed the screenshots provided in the notes for today. Here's one of them:

```Python
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

f2 = open("labels.txt", "w")

for i in range(size):
    print(chr(data[8+i]+48))
    f2.write(chr(data[8+i]+48))

f2.close()
f.close()
```

This is one of two scripts which process the MNIST training data into a format that `network.py` can read. This one processes the labels, and the other one processes the images. These scripts are replacements for the `mnist_loader` script from the [Original instructions](http://neuralnetworksanddeeplearning.com/chap1.html#implementing_our_network_to_classify_digits). According to Paul, it had breaking changes in trying to update it from Python2 to Python3.

We were unable to try running `network.py` because `load_images.py` took too long to run.

# 04-25-2024
Duck and Torsten

For some reason, there's some missing files on this prototype instead so we copy and compare what we have to Paul's prototype and fill in the missing file, he tries the new loader that removes the needs of using `load-image.py` so we delete that and change it to his loader and test it then fill in our stuff. It's now a fully functional program now. You can train and have your own model as `model.pbjson` then plug in and play by using `python3 run_model.py` to test it out. 

I also make an another files called `finetunning.py` that's just the same to `train.py` except that I swap the network parameter with the current model to fine-tuning it without having to modify `train.py` so that it cuts short of the work and error. If you want to finetuning the model then use that instead (Currently having error for now, wait for Paul to troubleshoot)