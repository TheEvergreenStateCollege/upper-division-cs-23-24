from dataloader import create_dataloader_v1 
import tiktoken
import torch

with open("../data/catcher.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

tokenizer = tiktoken.get_encoding("gpt2")
enc_text = tokenizer.encode(raw_text)
print(len(enc_text))

# remove first 50 tokens for output...
enc_sample = enc_text[50:]

# Creating (x, y) input-label pairs...
context_size = 4

x = enc_sample[:context_size]
y = enc_sample[1:context_size+1]
print(f"x: {x}")
print(f"y:      {y}")

# Producing a visualization of next-word prediction tasks...
for i in range(1, context_size+1):
    context = enc_sample[:i]
    desired = enc_sample[i]
    print(context, "---->", desired)

# The same as above, but with English instead of tokens...
for i in range(1, context_size+1):
    context = enc_sample[:i]
    desired = enc_sample[i]
    print(tokenizer.decode(context), "---->", tokenizer.decode([desired]))


dataloader = create_dataloader_v1(
    raw_text,
    batch_size=1,
    max_length=4,
    stride=1,
    shuffle=False )

data_iter = iter(dataloader)
first_batch = next(data_iter)
print("First Batch : \n")
print(first_batch) #this contains two tensors: input token IDs, then target token IDs. 

print("Second Batch : \n")
second_batch = next(data_iter)
print(second_batch)