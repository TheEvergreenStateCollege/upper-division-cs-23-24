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
Then there is a slight pause and the following errors are received.

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


## 04/22/2024
I have been able to save the epochs up to 50
```
Epoch 40: 1030 / 10000
Epoch 41: 1015 / 10000
Epoch 42: 1032 / 10000
Epoch 43: 1129 / 10000
Epoch 44: 1063 / 10000
Epoch 45: 1088 / 10000
Epoch 46: 1039 / 10000
Epoch 47: 1044 / 10000
Epoch 48: 1055 / 10000
Epoch 49: 1050 / 10000
```
It is good that I can reach Epoch 50. However, the percentage or accuracy of the images recognized  1050 / of the 10k training images used per Epoch should increase as it trains.

## 04/25/2024
created a ```dropout``` function.<br>
...But what is a does a ```dropout``` function do?<br>
```
def dropout(self, x, level):
        if level < 0. or level >= 1:  # Level is the dropout probability
            raise ValueError('Dropout level must be in interval [0, 1).')
        retain_prob = 1. - level
        # We scale the activations at training time to keep the same expected sum of activations.
        sample = np.random.binomial(n=1, p=retain_prob, size=x.shape)
        x *= sample
        x /= retain_prob
        return x
```
In the context of this MNIST trainer, the ```dropout``` function manages the network's learning process by randomly deactivating a subset of neurons durning the traing phase.<br>
This is meant to prevent ```overfitting```. This ```overfitting``` can occur when a neural network model learns the training data too well and can perform poorly on new unseen data. <br>

But, how does the ```dropout``` function work?<br>
## 1. Probability Setting:<br> 
The dropout function accepts an input x (the activations from the previous layer) and a level, which represents the dropout rate. The dropout rate is the probability that each neuron’s output is set to zero. This rate must be between 0 and 1, where 0 means no dropout and 1 means complete dropout<br>.
## 2. Activation Retention: <br>
The function calculates the retain_prob as 1 - level, which is the probability of retaining a neuron's activation. For example, if the dropout level is 0.5, there’s a 50% chance that any given neuron will be retained (meaning, not dropped).
## 3. Sampling:<br>
It uses a binomial distribution to randomly decide which neurons to keep (sample = np.random.binomial(n=1, p=retain_prob, size=x.shape)). In this sampling process, each neuron has an independent probability retain_prob of being retained.<br>
## 4. Application of Dropout:<br>
The function then applies this mask to the activations (x *= sample). This step sets the activation of dropped neurons to 0.<br>
## 5. Scaling:<br> 
Since on average a proportion retain_prob of the inputs are retained, this could lead to a lower total input to the next layer. To compensate for this reduction in input, the activations are scaled up by dividing by retain_prob (x /= retain_prob). This scaling is needed because it maintains the expected sum of the activations consistent whether dropout is applied or not, in theory, it should... stabilize the learning process<br>

### Why implement this ```dropout``` function?
Implementing the dropout function helps by the training of mulitple neural networks with different architectures. The function does this by  randomly dropping different sets of neurons. This is repersents sampling from an ensemble of neural networks, which improves the gerneralization of the model.<br>
So... while the program is "training", ```dropout``` is not applied, the neurons are not dropped, and the full capabilities of the trained network are utilized. This helps the network make the most accurate predictions possible with the learned weight and biases.<br> 

## Evaluate Training Accuracy:<br>
I wanted to create an instance once the program is finished "training", that ```Training Accuracy``` is printed. The Idea is later, create a CSV log report of the training accuracy amoung other collectable data...<br> 
But for now... ```print```<br>
### Date & Time <br>
04/25/24 0935 <br>
```Training Accuracy: 70.29833333333333%```
```
def evaluate_accuracy(self, data):
        """Evaluate the network's accuracy on the provided data."""
        results = [(np.argmax(self.feedforward(x)), np.argmax(y)) for (x, y) in data]
        accuracy = sum(int(x == y) for (x, y) in results) / len(data) * 100  # I want to calculate the  accuracy as a percentage
        return accuracy
```

## todo:

```
Figure out how to improve the algorithm to increase its recognition
```
