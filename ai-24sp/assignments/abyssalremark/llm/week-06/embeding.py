import torch

#seems silly to put this in its own file... I assume theres a good reason?
output_dim = 256
vocab_size = 50257
token_embedded_layer = torch.nn.Embedding(vocab_size, output_dim)


