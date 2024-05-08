from importlib.metadata import version
import tiktoken
import re
print("tiktoken version:", version("tiktoken"))

from splitter import split

tokenizer = tiktoken.get_encoding("gpt2")
text = "Hello, do you like tea? <|endoftext|> In the sunlit terraces of someunknownPlace."
text = "In the sunlit terraces of someunknownPlace."
split_text = split(text)
print(f"Number of words {len(split_text)}")
integers = tokenizer.encode(text, allowed_special={"<|endoftext|>"})
print(f"Byte-pair-encoded token IDs {integers}")
print(f"Number of token IDs {len(integers)}")

for i in range(10):
    decoded = tokenizer.decode(integers[:i+1])
    print(f"{integers[:i+1]} {decoded}")