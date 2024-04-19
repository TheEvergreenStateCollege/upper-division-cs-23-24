## What is Ai Self-Hosting?

AI self-hosting refers to the capability to run AI models on your own infrastructure rather than relying on cloud-based services.<br>
This can involve setting up the necessary hardware and software to train and run AI models locally.<br>
The process is autonomous to an extent but is heavily guided by the initial programming and ongoing training provided by human operators.

When I think about AI, particularly in the context of generating responses to user queries, we're often discussing models trained on large datasets that learn to predict the most likely response based on the input they receive. These AI models, like GPT (Generative Pretrained Transformer), use statistical probabilities to generate responses that are coherent and contextually relevant to the queries they receive.<br>

## 4/16/2024
added prototype-00. ' -00 ' is a current build from Ai-selfhosting course. 
I ran both prototypes within gitpod. Once I executed the first prototype the training files then unzip within the directory. which I thought was fine, I had created a .gitignore file.<br>
I was unable to commit and push my changes after I ran the prototypes.<br>
I had to exit out, get back into gitpod and redo my changes, then commit and push. <br>

Next time commit and push changes before running.<br>

You'll need to download the training images and labels, and test images and labels, from Yann LeCun's website

https://yann.lecun.com/exdb/mnist/ by using the following command:

```
curl http://yann.lecun.com/exdb/mnist/train-images-idx3-ubyte.gz -o train-images-idx3-ubyte.gz &&
curl http://yann.lecun.com/exdb/mnist/train-labels-idx1-ubyte.gz -o train-labels-idx1-ubyte.gz &&
curl http://yann.lecun.com/exdb/mnist/t10k-images-idx3-ubyte.gz -o t10k-images-idx3-ubyte.gz &&
curl http://yann.lecun.com/exdb/mnist/t10k-labels-idx1-ubyte.gz -o t10k-labels-idx1-ubyte.gz
```
After the .gitignore file is created, run the .gz unzip command:
```
gunzip *.gz
```

## Installed libraries
```
pip3 install Pillow  && pip3 install numpy && pip3 install pbjson
```
by creating a file named 'requirements.txt' we can run this command instead: <br> 
```
pip3 install -r requirements.txt
```
## 04/18/2024
Using my personal gitpod branch/account <br>
FYI: I have to install the dependencies each time I log back into gitpod to work on the code.

Once I execute the following command:
``` python3 load_mnist.py ```
The load_mnist.py and network.py run and the following data is what is generated before errors occur.<br>

```
Number of images 60000
result 0 0
result 0 0
result 0 0
result 0 28
result 0 0
result 0 0
result 0 0
result 0 28
width 28
height 28
start image data at byte 16
end image data at byte 47040016
length of sliced 1D image data <class 'list'>
Shape of data straight from file (60000, 784, 1)
Training data shape (60000, 784, 1)
result 0 234
result 234 96
Number of images 59766
Number of images 10000
result 0 0
result 0 0
result 0 0
result 0 28
result 0 0
result 0 0
result 0 0
result 0 28
width 28
height 28
start image data at byte 16
end image data at byte 7840016
length of sliced 1D image data <class 'list'>
Shape of data straight from file (10000, 784, 1)
result 0 39
result 39 16
Number of test labels 9961

```
Then there is a slight pause and the following errors are received(see TODO below).

I do not think they are necessarily errors but more converting from python2 to python3.<br>


## 04/19/2024

Updated ```network.py```<br>
Added ```loader.py```, ```run_model.py```, and ```train.py```<br>

Once libraries were installed...<br>

## 04/19/2024 15:35
I was able to create a running version of the class prototype on my own machine. I will let it run for some time to see what happens.

Using ```python3 train.py``` <br>
This screenshot shows the trainer running.<br>

![Screenshot (509)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/d7785d55-3a86-4232-a85b-4190b2b1c0e8)


## todo:

```

```
