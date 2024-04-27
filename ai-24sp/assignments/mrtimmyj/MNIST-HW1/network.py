import numpy as np
import random


class Network(object):
    """
    A class to represent a neural network.

    Attributes:
    sizes (list): A list containing the number of neurons in each layer of the
    network.
    num_layers (int): The number of layers in the network.
    biases (list): A list of NumPy arrays representing the biases for each
    neuron (excluding input layer).
    weights (list): A list of NumPy arrays representing the weights for each
    connection between neurons.
    """

    def __init__(self, sizes):
        """
        Initializes the neural network with random biases and weights.

        Parameters:
        sizes (list): A list containing the number of neurons in each layer of
        the network.
        """
        self.num_layers = len(sizes)
        self.sizes = sizes
        # initialize biases with random values using standard normal distribution
        self.biases = [np.random.randn(y, 1) for y in sizes[1:]]
        # initialize weights with random values using standard normal distribution
        self.weights = [np.random.randn(y, x) for x, y in zip(sizes[:-1], sizes[1:])]

    def feedforward(self, a):
        """
        Return the output of the network for a given input.

        Parameters:
        a (ndarray): Input to the network.

        Returns:
        ndarray: Output of the network.
        """
        # iterate over pairs of biases (b) and weights (w)
        for b, w in zip(self.biases, self.weights):
            # multiply the weight matrix (w) with the input vector (a)
            # add the bias vector (b) to the result
            # apply the sigmoid activation function to produce the output (a)
            a = sigmoid(np.dot(w, a) + b)
        return a

    def sgd(self, training_data, epochs, mini_batch_size, eta, test_data=None):
        """
        Train the neural network using stochastic gradient descent.

        Parameters:
        training_data (list): A list of tuples (x, y) representing training
        inputs and corresponding desired outputs.
        epochs (int): Number of epochs (iterations over the entire training
        data).
        mini_batch_size (int): Size of mini-batches for stochastic gradient
        descent.
        eta (float): Learning rate (step size for gradient descent).
        test_data (list, optional): Test data to evaluate the network's
        performance after each epoch. Default is None.
        """
        # if test data is provided
        if test_data:
            # calculate the number of test samples
            n_test = len(test_data)

        # calculate the number of training samples
        n = len(training_data)

        # loop over each epoch
        for j in range(epochs):

            # shuffle the training data to prevent overfitting
            random.shuffle(training_data)

            # create mini-batches from the shuffled training data
            mini_batches = [
                training_data[k: k + mini_batch_size]
                for k in range(0, n, mini_batch_size)
            ]

            # loop over each mini-batch
            for mini_batch in mini_batches:
                # update the network's weights and biases using the current mini-batch
                self.update_mini_batch(mini_batch, eta)

            # if test data is provided
            if test_data:
                # print the epoch number and the network's evaluation on test data
                print(
                    "Epoch {0}: {1} / {2}".format(j, self.evaluate(test_data), n_test)
                )
            else:
                # print a message indicating completion of the current epoch
                print("Epoch {0} complete".format(j))

    def update_mini_batch(self, mini_batch, eta):
        """
        Update the network's weights and biases using backpropagation.

        Parameters:
        mini_batch (list): A list of tuples (x, y) representing mini-batch
        inputs and corresponding desired outputs.
        eta (float): Learning rate (step size for gradient descent).
        """
        # initialize gradients of biases with zero arrays
        nabla_b = [np.zeros(b.shape) for b in self.biases]
        # initialize gradients of weights with zero arrays
        nabla_w = [np.zeros(w.shape) for w in self.weights]

        # loop over each training example in the mini-batch
        # and calculate the gradients using backpropagation
        for x, y in mini_batch:
            # calculate the change in gradients for biases and weights
            delta_nabla_b, delta_nabla_w = self.backprop(x, y)
            # update the gradients of biases
            nabla_b = [nb + dnb for nb, dnb in zip(nabla_b, delta_nabla_b)]
            # update the gradients of weights
            nabla_w = [nw + dnw for nw, dnw in zip(nabla_w, delta_nabla_w)]

        self.weights = [
            # update the weights using gradient descent
            w - (eta / len(mini_batch)) * nw for w, nw in zip(self.weights, nabla_w)
        ]
        self.biases = [
            # update the biases using gradient descent
            b - (eta / len(mini_batch)) * nb for b, nb in zip(self.biases, nabla_b)
        ]

    # not yet implemented
    def backprop(self, x, y):
        pass

    # not yet implemented
    def evaluate(self, data):
        pass


def sigmoid(z):
    """
    Compute the sigmoid function.

    Parameters:
    z (ndarray): Input to the sigmoid function.

    Returns:
    ndarray: Output of the sigmoid function.
    """
    return 1.0 / (1.0 + np.exp(-z))


net = Network([2, 3, 1])
print(net.sizes)
print(net.biases)
print(net.weights)
