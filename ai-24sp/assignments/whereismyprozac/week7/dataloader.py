import torch
from torch.utils.data import Dataset, DataLoader 

import tiktoken

class GPTDatasetV1(Dataset):
    def __init__(self, txt, tokenizer, max_length, stride):
        self.input_ids = []
        self.target_ids = []

        # Tokenize the entire text 
        token_ids = tokenizer.encode(txt, allowed_special={"<|endoftext|>"})

        # Use a sliding window to chunk the book into overlapping sequences
        for i in range(0, len(token_ids) - max_length):
            input_chunk = token_ids[i:i + max_length]
            target_chunk = token_ids[i+1:i+max_length + 1]
            self.input_ids.append(torch.tensor(input_chunk))
            self.target_ids.append(torch.tensor(target_chunk))
    
    def __len__(self):
        return len(self.input_ids)

    def __getitem__(self, idx):
        return self.input_ids[idx], self.target_ids[idx]

def create_dataloader_v1(txt, batch_size=8, max_length=4, stride=4, shuffle=True, drop_last=True, num_workers=1):

    #Initialize the tokenizer
    tokenizer = tiktoken.get_encoding("gpt2")

    # Create dataset 
    dataset = GPTDatasetV1(txt, tokenizer, max_length, stride)

    # Create dataloader
    dataloader = DataLoader(
        dataset,
        batch_size=batch_size,
        shuffle=shuffle,
        drop_last=drop_last,
        num_workers=0
    )

    return dataloader