import tiktoken
import torch
import pdfplumber
import os

from torch.utils.data import Dataset, DataLoader


class GPTDatasetV1(Dataset):
    def __init__(self, txt, tokenizer, max_length, stride):
        self.input_ids = []
        self.target_ids = []

        # Tokenize the entire text
        token_ids = tokenizer.encode(txt, allowed_special={"<|endoftext|>"})

        # Use a sliding window to chunk the book into overlapping sequences of max_length
        for i in range(0, len(token_ids) - max_length, stride):
            input_chuck = token_ids[i:i + max_length]
            target_chunk = token_ids[i + 1: i + max_length + 1]
            self.input_ids.append(torch.tensor(target_chunk))

    def __len__(self):
        return len(self.input_ids)

    def __getitem__(self, idx):
        return self.input_ids[idx], self.target_ids[idx]


def create_dataloader_v1(txt, batch_size=4, max_length=256, stride=128, shuffle=True, drop_last=True, num_workers=0):
    tokenizer = tiktoken.get_encoding("gpt2")  # Initialize the tokenizer
    dataset = GPTDatasetV1(txt, tokenizer, max_length, stride)  # Create dataset
    dataloader = DataLoader(dataset, batch_size=batch_size, shuffle=shuffle, drop_last=drop_last, num_workers=0)

    return dataloader


data_path = r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/ArtOfWar.pdf"
if not os.path.isfile(data_path):
    raise FileNotFoundError(f"File not found at: {data_path}")

with pdfplumber.open(data_path) as pdf:
    raw_text = ""
    for page in pdf.pages:
        page_text = page.extract_text()
        if page_text:
            raw_text += page.extract_text()

tokenizer = tiktoken.get_encoding("gpt2")
encoding_text = tokenizer.encode(raw_text)

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

    imput_embeddings = token_embeddings + pos_embeddings

    break

print(input_embeddings.shape)
