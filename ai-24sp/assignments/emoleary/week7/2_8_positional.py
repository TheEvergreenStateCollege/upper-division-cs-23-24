import torch
from dataloader import create_dataloader_v1

raw_text = "" 
with open("../data/catcher.txt", "r", encoding="utf-8") as f:
        raw_text = f.read()

output_dim = 256
vocab_size = 50257
token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

max_length = 4
dataloader = create_dataloader_v1(
    raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False)
data_iter = iter(dataloader)
inputs, targets = next(data_iter)
print("\n\nToken IDs:\n", inputs)
print("\n\nInputs shape:\n", inputs.shape)

token_embeddings = token_embedding_layer(inputs)
print("\n\nToken Embeddings Shape:\n", token_embeddings.shape)

context_length = max_length
pos_embedding_layer = torch.nn.Embedding(context_length, output_dim)
pos_embeddings = pos_embedding_layer(torch.arange(context_length))
print("\n\nPositional Embeddings:\n",pos_embeddings.shape)

input_embeddings = token_embeddings + pos_embeddings
print("\n\nInput Embeddings:\n",input_embeddings.shape)