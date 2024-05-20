import torch

output_dim = 256
vocab_size = 50257
token_embedded_layer = torch.nn.Embedding(vocab_size, output_dim)


