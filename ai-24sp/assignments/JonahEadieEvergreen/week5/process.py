#!/usr/bin/python3

from importlib.metadata import version

import tiktoken
import torch

from tokenizer import SimpleTokenizerV1
import tokenizer as mytokenizer

print("torch version:", version("torch"))
print("tiktoken version:", version("tiktoken"))

with open("../data/capital.txt", "r", encoding="utf-8", errors="ignore") as f:
    raw_text = f.read()

'''
print("Total number of characters:", len(raw_text))
print(raw_text[:99])

preprocessed = mytokenizer.tokenize(raw_text)
#print(preprocessed[:30])

all_words = sorted(list(set(preprocessed)))
vocab_size = len(all_words)

#print(vocab_size)

vocab = {token:integer for integer,token in enumerate(all_words)}


for i, item in enumerate(vocab.items()):
    print(item)
    if i>= 10000:
        break

tokenizer = SimpleTokenizerV1(vocab)

ids = tokenizer.encode("the wealth of societies in which the capitalist mode of production prevails appears as an immense collection of commodities")
print(ids)
print(tokenizer.decode(ids))

ids = tokenizer.encode("FLCL was revolutionary, breaking many conventions of the medium of Japanese animation")
'''

preprocessed = mytokenizer.tokenize(raw_text)

all_tokens = sorted(list(set(preprocessed)))
all_tokens.extend(["<|endoftext\>", "<|unk|>"])

vocab = {token:integer for integer,token in enumerate(all_tokens)}

tokenizer = SimpleTokenizerV1(vocab)

for i, item in enumerate(list(vocab.items())[-5:]):
    print(item)
