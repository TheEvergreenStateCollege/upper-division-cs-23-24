import torch
import pdfplumber
import os

#from dataloader import create_dataloader

print("PyTorch version:", torch.__version__)

resources = r"ai-24sp/assignments/NathanMc/Prototype_text/resources/ArtOfWar.pdf"
if not os.path.isfile(resources):
    raise FileNotFoundError(f"File not found at: {resources}")

with pdfplumber.open(resources) as pdf:
    raw_text = ""
    for page in pdf.pages:
        raw_text += page.extract_text()

output_dim = 256
vocab_size = 50257
token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

max_length = 4
dataloader = create_dataloader(
    raw_text, batch_size=8, max_length=max_length, stride=max_length
    )

data_iter = iter(dataloader)

inputs, targets = next(data_iter)

print("Token ID:\n", inputs)
print("\n Inputs shape:\n", inputs.shape)

token_embedding = token_embedding_layer(inputs)
print("Token embeddings shape: ", token_embedding.shape)

context_length = max_length
pos_embedding_layer = torch.nn.Embedding(context_length, output_dim)

