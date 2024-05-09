from importlib.metadata import version
import tiktoken
import re
print("tiktoken version:", version("tiktoken"))

from splitter import split

with open("../data/mark-twain-autobio.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

tokenizer = tiktoken.get_encoding("gpt2")
integers = tokenizer.encode(raw_text, allowed_special={"<|endoftext|>"})
print(f"Number of token IDs {len(integers)}")
print(f"Byte-pair-encoded token IDs {integers[:100]}")

start = 2900
for i in range(start,3000):
    decoded = tokenizer.decode(integers[start:i+1])
    print(f"{integers[start:i+1]} {decoded}")
