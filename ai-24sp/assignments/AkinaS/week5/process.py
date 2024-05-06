from importlib.metadata import version
from tokenizer import SimpleTokenizerV1, SimpleTokenizerV2
from token_creation import tokenize

import tiktoken
import torch

#print("torch version:", version("torch"))
#print("tiktoken version:", version("tiktoken"))

with open("../data/The Complete Works of William Shakespeare.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()
    
excerpt = raw_text[810:1000]
excerpt2 = raw_text[928:2000]
excerpt3 = raw_text[810:2000]

print("Total number of characters:", len(raw_text))
#print(excerpt)
#print(excerpt2)
print(excerpt3)

tokenizer = SimpleTokenizerV2(tokenize(excerpt2))
ids = tokenizer.encode(excerpt3)
print(ids)

text = tokenizer.decode(ids)
print(text)