import torch

input_ids = torch.tensor([2, 3, 5, 1])

vocab_size = 6
output_dim = 3

torch.manual_seed(123)

embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

print("\n\n----- EMBEDDING LAYER WEIGHT MATRIX -----\n")
print(embedding_layer.weight)

print("\n\n----- SINGLE TOKEN ID, TENSOR([3]) --> EMBEDDED VECTOR -----\n")
print(embedding_layer(torch.tensor([3])))

print("\n\n----- FOUR INPUT ID VALUES: [2, 3, 5, 1] --> MATRIX EMBEDDING -----\n")
print(embedding_layer(input_ids))