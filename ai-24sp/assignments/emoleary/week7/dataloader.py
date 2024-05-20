from importlib.metadata import version
from torch.utils.data import Dataset
from torch.utils.data import DataLoader


import tiktoken
import torch


raw_text = "" 
with open("../data/catcher.txt", "r", encoding="utf-8") as f:
        raw_text = f.read()

tokenizer = tiktoken.get_encoding("gpt2")

enc_text = tokenizer.encode(raw_text)
print(f"\n\nEncoded text length: {len(enc_text)}")

enc_sample = enc_text[50:]
context_size = 4
x = enc_sample[:context_size]
y = enc_sample[1:context_size+1]

print(f"x: {x}")
print(f"y:       {y}")

from torch.utils.data import Dataset, DataLoader

class GPTDatasetV1(Dataset):
    def __init__(self, txt, tokenizer, max_length, stride):
        self.input_ids = []
        self.target_ids = []

        # Tokenize the entire text
        token_ids = tokenizer.encode(txt, allowed_special={"<|endoftext|>"})

        # Use a sliding window to chunk the book into overlapping sequences
        for i in range(0, len(token_ids) - max_length, stride):
            input_chunk = token_ids[i:i + max_length]
            target_chunk = token_ids[i + 1: i + 1 + max_length]
            self.input_ids.append(torch.tensor(input_chunk))
            self.target_ids.append(torch.tensor(target_chunk))

    def __len__(self):
        return len(self.input_ids)

    def __getitem__(self, idx):
        return self.input_ids[idx], self.target_ids[idx]

def create_dataloader_v1(txt, batch_size=4, max_length=256, stride=128, shuffle=True, drop_last=True, num_workers=0):

        tokenizer = tiktoken.get_encoding("gpt2")

        dataset = GPTDatasetV1(txt, tokenizer, max_length, stride)

        dataloader = DataLoader(
            dataset,
            batch_size=batch_size,
            shuffle=shuffle,
            drop_last=drop_last,
            num_workers=0
         )

        return dataloader

dataloader = create_dataloader_v1(raw_text, batch_size=1, max_length=4, stride=1, shuffle=False)
data_iter = iter(dataloader)
first_batch = next(data_iter)
print(first_batch)

second_batch = next(data_iter)
print(second_batch)

dataloader = create_dataloader_v1(raw_text, batch_size=8, max_length=4, stride=4, shuffle=False)
data_iter = iter(dataloader)
input, targets = next(data_iter)
print("Inputs:\n", input)
print("\nTargets:\n", targets)

## Creating token embeddings

# 4 input examples:
input_ids = torch.tensor([2, 3, 5, 1])

# vocab size of 5, embeddings of size 3
vocab_size = 6
output_dim = 3

torch.manual_seed(600)
embedding_layer = torch.nn.Embedding(vocab_size, output_dim)
print(embedding_layer.weight)

# convert a token into a 3 dim vector
print(embedding_layer(torch.tensor([3])))

# embed all input id values
print(embedding_layer(input_ids))

## 2.8 Encoding Word Positions
vocab_size = 50257
output_dim = 256

token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

max_length = 4
datalaoder = create_dataloader_v1(raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False)
inputs, targets = next(data_iter)

print("token IDs:\n", inputs)
print("\nInputs shape:\n", inputs.shape)

token_embeddings = token_embedding_layer(inputs)
print(token_embeddings.shape)

# Creating another embedding layer, bc GPT-2 uses "absolute" positional embeddings
context_length = max_length
pos_embedding_layer = torch.nn.Embedding(context_length, output_dim)

pos_embeddings = pos_embedding_layer(torch.arange(max_length))
print(f"\npos_embeddings.shape :{pos_embeddings.shape}\n")
print(f"\ntoken_embeddings.shape :{token_embeddings.shape}\n")

# LLM Input embeddings = token + pos embeddings!
input_embeddings = token_embeddings + pos_embeddings
print(input_embeddings.shape)
