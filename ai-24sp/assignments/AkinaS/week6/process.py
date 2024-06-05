import tiktoken
import torch
from importlib.metadata import version
from dataloader import create_dataloader_v1

print("PyTorch version:", torch.__version__)
print("tiktoken version:", version("tiktoken"))

with open("../data/The Complete Works of William Shakespeare.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()
    
excerpt = raw_text[810:2000]


tokenizer = tiktoken.get_encoding("gpt2")
encoded_text = tokenizer.encode(raw_text)

vocab_size = 50257
output_dim = 256
context_length = 1024


token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)
pos_embedding_layer = torch.nn.Embedding(context_length, output_dim)

max_length = 4
dataloader = create_dataloader_v1(raw_text, batch_size=8, max_length=max_length, stride=max_length)

for batch in dataloader:
    x, y = batch

    token_embeddings = token_embedding_layer(x)
    pos_embeddings = pos_embedding_layer(torch.arange(max_length))

    input_embeddings = token_embeddings + pos_embeddings

    break

print(input_embeddings.shape)