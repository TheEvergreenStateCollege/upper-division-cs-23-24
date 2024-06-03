from dataloader import create_dataloader_v1 
import tiktoken
import torch


with open("../data/inverting-the-pyramid.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

tokenizer = tiktoken.get_encoding("gpt2")
encoded_text = tokenizer.encode(raw_text)
print(len(encoded_text))

enc_sample = encoded_text[50:]

context_size = 11

x = enc_sample[:context_size]
y = enc_sample[1:context_size + 1]
print(f"x: {x}")
print(f"y:      {y}")

# numbers (tokens) instead of words
for i in range(1, context_size + 1):
    context = enc_sample[:i]
    desired = enc_sample[i]
    #print(context,"---->", desired)

# Convert to words in stead of numbers
for i in range(1, context_size + 1):
    context = enc_sample[:i]
    desired = enc_sample[i]
    print(tokenizer.decode(context), "---->", tokenizer.decode([desired]))


dataloader = create_dataloader_v1(raw_text, batch_size=1, max_length=7, stride=3, shuffle=False)

data_iter = iter(dataloader)
first_batch = next(data_iter)
print(first_batch)

second_batch = next(data_iter)
print(second_batch)


dataloader = create_dataloader_v1(raw_text, batch_size=8, max_length=4, stride=4)

data_iter = iter(dataloader)
inputs, targets = next(data_iter)
print("Inputs:\n", inputs)
print("\nTargets:\n", targets)
