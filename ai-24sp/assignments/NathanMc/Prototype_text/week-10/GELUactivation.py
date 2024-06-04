import matplotlib.pyplot as plt
import torch
import torch.nn as nn

# Pg 122: 4.3: Implementing the GELU activation function. 
# GELU is a small neural network submodule that is used as part of
# the transformer block in LLMs.
class GELU(nn.Module):
    def __init__(self):
        super().__init__()

    def forward(self, x):
        return 0.5 * x * (1 + torch.tanh(
            torch.sqrt(torch.tensor(2.0 / torch.pi)) *
            (x + 0.044715 * torch.pow(x, 3))
        ))

# Next, compare the GELU & ReLU function
gelu, relu = GELU(), nn.ReLU()

x = torch.linspace(-3, 3, 100)
y_gelu, y_relu = gelu(x), relu(x)
plt.figure(figsize=(8, 3))
for i, (y, label) in enumerate(zip([y_gelu, y_relu], ["GELU", "ReLu"]), 1):
    plt.subplot(1, 2, i)
    plt.plot(x, y)
    plt.title(f"{label} activation function")
    plt.xlabel("x")
    plt.ylabel(f"{label}(x)")
    plt.grid(True)
plt.tight_layout()
plt.savefig("GELU_ReLU.png")
print("Pg 122: Chpt 4.3: Figure saved as 'GELU_ReLU.png'")


