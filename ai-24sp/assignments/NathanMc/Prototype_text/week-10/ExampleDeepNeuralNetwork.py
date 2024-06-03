import torch
import torch.nn as nn

from GELUactivation import GELU

# Pg 128: Shortcut Connections: 4.5.
# A shortcut connection creates an alternative, shorter path for the gradient to flow through
# the network by skipping one or more layers, which is achieved by
# adding the output of one layer to the output of a later layer. 
# This is why these connections are also known as skip connections. 
# They play a crucial role in preserving the flow of gradients during the backward pass in training.

# A neural network to illustrate shortcut connections
class DeepNeuralNetwork(nn.Module):
    def __init__(self, layer_sizes, use_shortcut):
        super().__init__()
        self.use_shortcut = use_shortcut
        self.layers = nn.ModuleList([
            # Implement 5 layers
            nn.Sequential(nn.Linear(layer_sizes[0], layer_sizes[1]), GELU()),
            nn.Sequential(nn.Linear(layer_sizes[0], layer_sizes[2]), GELU()),
            nn.Sequential(nn.Linear(layer_sizes[0], layer_sizes[3]), GELU()),
            nn.Sequential(nn.Linear(layer_sizes[0], layer_sizes[4]), GELU()),
            nn.Sequential(nn.Linear(layer_sizes[0], layer_sizes[5]), GELU()),
        ])

    def forward(self, x):
        for layer in self.layers:
            # Compute the output of the current layer
            layer_output = layer(x)
            # Check if shortcut can be applied
            if self.use_shortcut and x.shape == layer_output.shape:
                x = x + layer_output
            else:
                x = layer_output
        return x