import importlib
import tiktoken
import torch

from torch.utils.data import Dataset, DataLoader

class GPTDatasetV1(Dataset):
    def __init__(self, txt, tokenizer, max_length, stride):
        self.input_ids = []
        self.target_ids = []

        #Tokenize the entire text
        token_ids = tokenizer.encode(txt, allowed_special={"<|endoftext|>"})

        #Use a sliding window to chunk the book into overlapping sequences of max_length
        for i in range(0, len(token_ids) - max_length, stride):
            input_chunk = token_ids[i:i + max_length]
            target_chunk = token_ids[i + 1: 1 + max_length + 1]
            self.input_ids.append(torch.tensor(input_chunk))
            self.target_ids.append(torch.tensor(target_chunk))
    #def add_special_tokens()

    def __len__(self):
        return len(self.input_ids)
    
    def __getitem__(self, idx):
        return self.input_ids[idx], self.target_ids[idx]
    
    def create_dataloader_v1(txt, batch_size=4, max_length=256, stride=128, shuffle=True, drop_last=True, num_workers=0):
        #Initialize the tokenizer
        tokenizer = tiktoken.get_encoding("gpt2")

        #Create dataset
        dataset = GPTDatasetV1(txt, tokenizer, max_length, stride)

        #Create dataloader
        dataloader = DataLoader(
            dataset,
            batch_size=batch_size,
            shuffle=shuffle,
            drop_last=drop_last,
            num_workers=0   
        )
        return dataloader

with open("../data/mark-twain-autobio.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()



tokenizer = tiktoken.get_encoding("gpt2")

#encode
ids = tokenizer.encode(raw_text, allowed_special = {"<|endoftext|>"})
#print(ids)
#print(len(ids))                                                  


#decode
#strings = tokenizer.decode(integers)
#print(strings)

sample = ids[50:]

#context batches
context_size = 4
x = sample[:context_size]
y = sample[1:context_size+1]



#print(f"x: {x}")
#print(f"y:      {y}")

for i in range (1, context_size+1):
    context = sample[:i]
    desired = sample[i]

    print(context, "---->", desired)

for i in range(1, context_size+1):
    context = sample[:i]
    desired = sample[i]
    print(tokenizer.decode(context), "---->", tokenizer.decode([desired]))

dataloader = GPTDatasetV1.create_dataloader_v1(raw_text, batch_size=1, max_length=4, stride=1, shuffle=False)
data_iter = iter(dataloader)
first_batch = next(data_iter)
print(first_batch)
second_batch = next(data_iter)
print(second_batch)

#dataloader = GPTDatasetV1.create_dataloader_v1(raw_text, batch_size=8, max_length=4, stride=4, shuffle=False)

#data_iter = iter(dataloader)
#inputs, targets = next(data_iter)
#print("Inputs:/n", inputs)
#print("\nTargets:\n", targets)

input_ids = torch.tensor([2, 3, 5, 1])

vocab_size = 6
output_dim = 3

torch.manual_seed(123)
embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

print(embedding_layer.weight)

#print(embedding_layer(torch.tensor([0])))

vocab_size = 50257
output_dim = 256

token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

max_length = 4
dataloader = GPTDatasetV1.create_dataloader_v1(raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False)

data_iter = iter(dataloader)
inputs, targets = next(data_iter)

print("Token IDs:\n", inputs)
print("\nInputs shape:\n", inputs.shape)