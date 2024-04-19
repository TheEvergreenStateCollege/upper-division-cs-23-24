import random
import numpy as np
import pbjson 

class Network(object):

    def __init__(self, sizes):
        self.num_layers = len(sizes)
        self.sizes = sizes
        self.biases = [np.random.randn(y, 1) for y in sizes[1:]]  
        self.weights = [np.random.randn(y, x) for x, y in zip(sizes[:-1], sizes[1:])]
    
    @staticmethod
    def sigmoid(z):
        # Clip the value of z to prevent overflow in the exp function
        z = np.clip(z, -500, 500)  # the values can be adjusted
        return 1.0/(1.0+np.exp(-z))

    #@staticmethod
    def sigmoid_prime(self, z):
        """Derivates of the sigmoid function."""
        return self.sigmoid(z) * (1 - self.sigmoid(z))

        
    def SGD(self,
        training_data,
        epochs, 
        mini_batch_size, 
        eta,
        test_data=None):
        if test_data:
            test_data = list(test_data)
            n_test = len(test_data)
        training_data = list(training_data)
        n = len(training_data)

        for j in range(epochs):
            random.shuffle(training_data)
            mini_batches = [
                training_data[k:k + mini_batch_size] for k in range(0, n, mini_batch_size)
            ]

            for mini_batch in mini_batches:
                if mini_batch:  # Ensure mini_batch is not empty
                    self.update_mini_batch(mini_batch, eta)
                if test_data:
                    print("Epoch {0}: {1} / {2}".format(
                        j, self.evaluate(test_data), n_test))
                else:
                    print("Epoch {0} complete".format(j))


    def update_mini_batch(self, mini_batch, eta):
        nabla_b = [np.zeros(b.shape) for b in self.biases]
        nabla_w = [np.zeros(w.shape) for w in self.weights]
        for x, y in mini_batch:
            delta_nabla_b, delta_nabla_w = self.backprop(x, y)
            nabla_b = [nb+dnb for nb, dnb in zip(nabla_b, delta_nabla_b)]
            nabla_w = [nw+dnw for nw, dnw in zip(nabla_w, delta_nabla_w)]
        self.weights = [w-(eta/len(mini_batch))*nw
                        for w, nw in zip(self.weights, nabla_w)]
        self.biases = [b-(eta/len(mini_batch))*nb
                        for b, nb in zip(self.biases, nabla_b)]

    def saveToPBJSON(self, filename):
        model = {
            "weights": self.weights,
            "biases": self.biases,
            "sizes": self.sizes,
            "num_layers": self.num_layers,
        }
        with open(filename, "wb") as f:
            pbjson.dump(model, f)

    @staticmethod
    def fromPBJSON(filename):
        with open(filename, "rb") as f:
            model = pbjson.load(f)
            #print(f"Rehydrating {model}")
            n = Network(model["sizes"])
            n.num_layers = model["num_layers"]
            n.weights = model["weights"]
            n.biases = model["biases"]
            return n 
            
    def feedforward(self, a):
        """Return the output of the network if "a" is input."""
        for b, w in zip(self.biases, self.weights):
            a = self.sigmoid(np.dot(w, a) + b)
        return a 

    def backprop(self, x, y):
        nabla_b = [np.zeros(b.shape) for b in self.biases]
        nabla_w = [np.zeros(w.shape) for w in self.weights]
        # feed forward
        activation = x
        activations = [x] # list to store all the activations, layer by layer
        zs = [] # list to store all the z vectors, layer by layer
        for b, w in zip(self.biases, self.weights):
            z = np.dot(w, activation)+b
            zs.append(z)
            activation = self.sigmoid(z)
            activations.append(activation)
        # backward pass
        delta = self.cost_derivative(activations[-1], y) * self.sigmoid_prime(zs[-1])
        nabla_b[-1] = delta
        nabla_w[-1] = np.dot(delta, activations[-2].transpose())

        for l in range(2, self.num_layers):
            z = zs[-l]
            sp = self.sigmoid_prime(z)
            delta = np.dot(self.weights[-l+1].transpose(), delta) * sp
            nabla_b[-l] = delta
            nabla_w[-l] = np.dot(delta, activations[-l-1].transpose())
        return (nabla_b, nabla_w)

    def evaluate(self, test_data):
        test_results = [(np.argmax(self.feedforward(x)), np.argmax(y))
                            for (x, y) in test_data]
        return sum(int(x == y) for (x, y) in test_results)

    def cost_derivative(self, output_activations, y):
        r"""Return the vector of partial derivatives \partial C_x /
        \partial a for the output activations."""
        return (output_activations-y)


