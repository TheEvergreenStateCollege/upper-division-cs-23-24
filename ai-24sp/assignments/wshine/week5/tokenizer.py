from importlib.metadata import version
import tiktoken
import torch
from torch.utils.data import Dataset, DataLoader


# data set class
class GPTDatasetV1(Dataset):
    def __init__(self, txt, tokenizer, maxlength, stride):
        self.tokenizer = tokenizer
        self.input_ids = []
        self.target_ids = []

        token_ids = tokenizer.encode(txt)

        for i in range(0, len(token_ids) - maxlength, stride):
            input_chunk = token_ids[i : i + maxlength]
            target_chunk = token_ids[i + 1 : i + maxlength + 1]
            self.input_ids.append(torch.tensor(input_chunk))
            self.target_ids.append(torch.tensor(target_chunk))

    def __len__(self):
        return len(self.input_ids)

    def __getitem__(self, idx):
        return self.input_ids[idx], self.target_ids[idx]


# data loader
def create_dataloader_v1(
    txt, batch_size=4, max_length=256, stride=128, shuffle=True, drop_last=True
):
    tokenizer = tiktoken.get_encoding("gpt2")
    dataset = GPTDatasetV1(txt, tokenizer, max_length, stride)
    dataloader = DataLoader(
        dataset, batch_size=batch_size, shuffle=shuffle, drop_last=drop_last
    )
    return dataloader


print("tiktoken version: ", version("tiktoken"))

with open(
    "../data/Reversing Secrets Of Reverse Engineering (2005).txt", "r", encoding="utf-8"
) as f:
    raw_text = f.read()
print("Total number of charactersi: ", len(raw_text))
# tokenizer
tokenizer = tiktoken.get_encoding("gpt2")
enc_text = tokenizer.encode(raw_text, allowed_special={"<|endoftext|>"})


# parameters
vocab_size = len(enc_text)
output_dim = 256

print("Vocab size: ", vocab_size)

# using torch/dataloader
# small batch sizes lead to less memory use in training but adds noise to model updates
# this is one of the hyperparameters we use for optimization
max_length = 4
dataloader = create_dataloader_v1(
    raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False
)
data_iter = iter(dataloader)
inputs, targets = next(data_iter)

print("Token ids:\n", inputs)
print("\nInputs Shape:\n",inputs.shape)

# embedding layer
# embedding layer is a lookup from token id -> weight matrix
token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)
token_embeddings = token_embedding_layer(inputs)
pos_embedding_layer = torch.nn.Embedding(max_length, output_dim)
pos_embeddings = pos_embedding_layer(torch.arange(max_length))

input_embeddings = token_embeddings + pos_embeddings

print("embeddings dimensions: ", token_embeddings.shape)
print("pos embeddings dimensions: ",pos_embeddings.shape)
print("input embeddings dimensions: ",input_embeddings.shape)
