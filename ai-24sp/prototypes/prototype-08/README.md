```
ProtoType 08, Prototype_text
```


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

You'll need to download the training images, labels, test images and labels, from Yann LeCun's website.

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
## 04/18/2024
FYI: I have to install the dependencies each time I log back into gitpod to work on the code.

## 04/19/2024
I was able to create the trainer on my local machine. It will run for a while to see what happens. <br>

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
...But what is a ```dropout``` function?<br>
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
The dropout function accepts an input x (the activations from the previous layer) and a level, which represents the dropout rate. The dropout rate is the probability that each neuron’s output is set to zero. This rate must be between 0 and 1, where 0 means no dropout and 1 means complete dropout.<br>
## 2. Activation Retention: <br>
The function calculates the retain_prob as 1 - level, which is the probability of retaining a neuron's activation. For example, if the dropout level is 0.5, there’s a 50% chance that any given neuron will be retained (meaning, not dropped).<br>
## 3. Sampling:<br>
It uses a binomial distribution to randomly decide which neurons to keep (sample = np.random.binomial(n=1, p=retain_prob, size=x.shape)). In this sampling process, each neuron has an independent probability retain_prob of being retained.<br>
## 4. Application of Dropout:<br>
The function then applies this mask to the activations (x *= sample). This step sets the activation of dropped neurons to 0.<br>
## 5. Scaling:<br> 
Since on average a proportion retain_prob of the inputs are retained, this could lead to a lower total input to the next layer. To compensate for this reduction in input, the activations are scaled up by dividing by retain_prob (x /= retain_prob). This scaling is needed because it maintains the expected sum of the activations consistent whether dropout is applied or not, in theory, it should... stabilize the learning process.<br>

### Why implement this ```dropout``` function?
Implementing the dropout function helps by the training of mulitple neural networks with different architectures. The function does this by  randomly dropping different sets of neurons. This is repersents sampling from an ensemble of neural networks, which improves the gerneralization of the model.<br>
So... while the program is "training", ```dropout``` is not applied, the neurons are not dropped, and the full capabilities of the trained network are utilized. This helps the network make the most accurate predictions possible with the learned weight and biases.<br> 

## Evaluate Training Accuracy:<br>
I wanted to create an instance once the program is finished "training", that the ```Training Accuracy``` is printed. The Idea is to later, create a CSV log report of the training accuracy amoung other collectable data...<br> 
But for now... ```print```<br>
### Date & Time <br>
04/25/24 0935 <br>
```
   Training Accuracy: 70.29833333333333%
   Training Accuracy: 85.53%
```


```
def evaluate_accuracy(self, data):
        """Evaluate the network's accuracy on the provided data."""
        results = [(np.argmax(self.feedforward(x)), np.argmax(y)) for (x, y) in data]
        accuracy = sum(int(x == y) for (x, y) in results) / len(data) * 100  # I want to calculate the  accuracy as a percentage
        return accuracy
```
What the ```evaluate_accuracy``` function does is calculate how many total training labels are recognized out of the total training imaged used as a percentage.<br>


## 04/26/2024
### Log the Training Results

This function's purpose is to create a CSV file that logs training data once the program is complete.<br>
The ```log_training_results``` create a CSV file with the following headers: <br>
```
Date and Time, Total Epochs, Images per Epoch, and Training Accuracy
```
(sample csv)<br>
```
Date and Time,Total Epochs,Images per Epoch,Training Accuracy
2024-04-26 05:02:08,50,60000,53.059999999999995
```
## 05/06/2024

Implemented two features for the MNIST trainer.<br>
- Training and Validation sets
- Average time spent per Epoch

### Training and Validation
In machine learning, tasks like training a neural network, the dataset can be thought of in three parts:<br>
- Training set: Used to train the model.<br>
- Validation set: Used to adjust the parameters and avoid overfitting.<br>
- Test set: Used to test the mode's performance after the training process.<br>

The `validation_split` parameter in the MNIST trainer’s `network.py` script directly influences how the training data is split into training and validation sets. The parameter specifies the fraction of the training data to be used as the validation set. For example, a `validation_split` of 0.1 means that 10% of the training data is set aside for validation purposes, while the remaining 90% is used for training the model.<br>

### Okay... now how does the `validation_split` work?<br>

Within the Python `network.py` file, in the main method of the script `SGD` where the validation split is used.<br>

```
split_at = int(len(training_data) * (1 - validation_split))
validation_data = training_data[split_at:]
training_data = training_data[:split_at]
```
### nice!.. But what are the benefits of using a Validation Split in the MNIST trainer?<br>

- The validation set allows you to fine-tune the parameters like the learning rate, number of epochs, mini-batch size.<br>

- Overfitting occurs when a model learns the training data too well, including the noise and fluctuations in the data, to the extent that it performs poorly on new data. By monitoring the model’s performance on the validation set, you can stop the training early if the validation error starts increasing.<br>

- The validation process provides a feedback loop during training, giving insights into how well the model is learning and generalizing from epoch to epoch.

### Average time spent per Epoch

The Average time spent per Epoch or `average_epoch_duration` is pretty straight forward. Creating a timer within the main loop that marks time for each of the Epochs. Once the program has completed its training, the average time it took per Epoch will be printed to the terminal and the CSV file.<br>
```
end_epoch_time = time.time()  # End time for each epoch
epoch_duration = end_epoch_time - start_epoch_time
epoch_durations.append(epoch_duration)  # Append duration to list


 print(f"Average Epoch Duration: {average_epoch_duration} seconds, Final Accuracy: {accuracy}%")
```
![Screenshot (537)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/a0a0e32c-33bc-4881-846b-ac65f2511ef3)

![Screenshot (538)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/e69d9085-8611-41e3-8a79-632e2e4447f0)

## 05/08/2024
### The Art of War, By Sun Tzu 
`Prototype_text.py`

![Screenshot (541)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/ea39b1d7-424d-4bf9-b1ca-5d54a88afdd4)

## 05/12/2024

### Tokenization
Tokenize the words... `tokenizer = tiktoken.get_encoding`
What does it mean to tokenize the words?<br>

Tokenizing a word involves assigning numerical values to each word within the document, enabling the model to process and understand the text. Here's an example of how a large language model might tokenize the sentence "Cat mastering LLMs: Pawsitive progress, zero bugs!":<br>
```
[818] Cat
[818, 262] Cat mastering
[818, 262, 4252] Cat mastering LLM
[818, 262, 4252, 18250] Cat mastering LLMs:
[818, 262, 4252, 18250, 8812] Cat mastering LLMs: Pawsitive
[818, 262, 4252, 18250, 8812, 2114] Cat mastering LLMs: Pawsitive progress
[818, 262, 4252, 18250, 8812, 2114, 286] Cat mastering LLMs: Pawsitive progress,
[818, 262, 4252, 18250, 8812, 2114, 286, 617] Cat mastering LLMs: Pawsitive progress, zero
[818, 262, 4252, 18250, 8812, 2114, 286, 617, 34680] Cat mastering LLMs: Pawsitive progress, zero bugs
[818, 262, 4252, 18250, 8812, 2114, 286, 617, 34680, 27271] Cat mastering LLMs: Pawsitive progress, zero bugs!"
```

By tokenizing, the model converts words into numerical values, which allows it to efficiently process and generate responses based on those values.<br>

## 05/16/2024

## week 7

For process.py

Import dependencies:
```pip3 install --upgrade pip && pip3 install tiktoken && pip3 install pdfplumber```

and then...
```
sudo apt-get update
sudo apt-get install -y python3.9 python3.9-venv python3.9-dev
sudo update-alternatives --install /usr/bin/python3 python3 /usr/bin/python3.9 1
sudo update-alternatives --install /usr/bin/python python /usr/bin/python3.9 1
```

and then... ...
```
python3.9 -m venv venv
source venv/bin/activate

```

ok...and then... ... ...
```
pip install --upgrade pip
pip install torch torchvision torchaudio --extra-index-url https://download.pytorch.org/whl/cu118

```

now I forgot...

## 05/17/2024

I was able to correctly import all the `process.py` & `dataloader.py` dependencies. After some help, I was able to ensure I was working out my correct WSL Ubuntu environment. Using local machine WSL virtual memory terminals... not sure how else to describe it.<br>

To test and see if the imports were installed I created a small python program, `test_torch.y`
 
```
import torch
from torch.utils.data import DataLoader, Dataset

print(torch.__version__)
print(DataLoader, Dataset)

```
After I troubleshot my way through running the program,<br>

I was able to get confirmation.<br>

```
2.3.0+cpu
<class 'torch.utils.data.dataloader.DataLoader'> <class 'torch.utils.data.dataset.Dataset'>
```

Now, I can start testing `process.py` & `dataloader.py`.

errors...<br>
	errors...<br>
		errors...<br>
I like errors...<br>

So, besides resolving my errors, I was able to compile week 7 program files.<br>

![Screenshot (553)](https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/129904249/2f1a0769-0233-4640-b025-a48bb0c87c08)

![Screenshot (554)](https://github.com/nathanMcL/Student.Originated.Software/assets/129904249/f84b097b-cbca-46b8-9cfc-2c25585aef62)


Next up:
What does this `Token ID:` mean?

```
Token ID:
 tensor([[  547,  4030,  3938,  9322],
        [ 4411,   600,   258,  8534],
        [  515,  6738, 45529,    13],
        [   64,  1659,   198, 22602],
        [23893,  2024,    11,   271],
        [  329,   340,   198,  1326],
        [ 4625,  1169,  1941,    21],
        [47247, 41603, 19187,   436]])
```


## todo:

```
Figure out how to improve the algorithm to:
 increase its recognition,
 increase wall-clock speed,

```




## How to run our demo
Example of how you can run the program once you have navigated to the directory.
```python3 prototype-00```
```python3 train.py```



## How we built it






## Challenges we ran into






## Accomplishments we are proud of







## What we learned







## What is next for the project




