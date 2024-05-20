import torch

input_ids = torch.tensor([2, 3, 5, 1])

print(f"Shape {input_ids.shape}")

vocab_size = 7
output_dim = 4

torch.manual_seed(123)
embedding_layer = torch.nn.Embedding(vocab_size, output_dim)
print(embedding_layer.weight)

# For a single token ID
print(embedding_layer(torch.tensor([3])))

# For a chunk of token IDs in order
# Such as they might appear in a book
print(embedding_layer(input_ids))
