from importlib.metadata import version
import tiktoken
import re
print("tiktoken version:", version("tiktoken"))

with open("../data/mark-twain-autobio.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

# Start with 2000th token because the beginning of the dataset is full of filler material
raw_text = raw_text[2000:]

tokenizer = tiktoken.get_encoding("gpt2")
integers = tokenizer.encode(raw_text, allowed_special={"<|endoftext|>"})
print(f"Number of token IDs {len(integers)}")
print(f"Byte-pair-encoded token IDs {integers[:100]}")

start = 2900
for i in range(start,3000):
    decoded = tokenizer.decode(integers[start:i+1])
    print(f"{integers[start:i+1]} {decoded}")

enc_sample = integers[50:]

context_size = 4

x = enc_sample[:context_size]
y = enc_sample[1:context_size+1]

print(f"x: {x}")
print(f"y:        {y}")

from dataloader import create_dataloader_v1

dataloader = create_dataloader_v1(raw_text, batch_size=1, max_length=4, stride=1, shuffle=False)

data_iter = iter(dataloader)
inputs, targets = next(data_iter)
print("First batch of size 1")
print("Inputs:\n", inputs)
print("\nTargets:\n", targets)

