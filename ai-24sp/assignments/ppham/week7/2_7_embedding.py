import torch 
                    # The quick brown fox
input_ids = torch.tensor([2, 3, 5, 1])

print(f"Shape {input_ids.shape}")

vocab_size = 7 
output_dim = 4

torch.manual_seed(123)
# What size matrix represents this embedding?
# takes vector of size 7 to a vector of size 4
embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

# Embedding matrix should be output_dim (rows) by vocab_size (col)
#                                     4       x      7

print("Embedding Matrix", embedding_layer)
# print("Embedding Matrix Shape", embedding_layer.shape)

# For a single token ID
print(embedding_layer(torch.tensor([3])))

batch = torch.tensor([[2, 3, 5, 1],
    [2, 3, 5, 1],
    [4, 3, 5, 1],
    [2, 6, 5, 1],
    [2, 3, 1, 1],
    [2, 3, 5, 1],
    [2, 4, 5, 1],
    [0, 3, 5, 1],
])
print("Shape of a batch", batch.shape)

batch_embedding = embedding_layer(batch)
print("Batch embedding", batch_embedding)
print("Batch embedding shape", batch_embedding.shape)

