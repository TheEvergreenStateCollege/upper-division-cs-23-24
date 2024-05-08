from importlib.metadata import version
import tiktoken
import torch
import re
import pdfplumber



print("torch version:", version("torch"))
print("tiktoken version:", version("tiktoken"))

with open("ArtOfWar.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

print("Total number of character:", len(raw_text))
print(raw_text[:99])

# Test phrase
text = "Hello, world..? This--, is a test."

# Remove unnessisary characters and white space
result = re.split(r'([,.:;?_!"()\']|--|\s)', text)
result = [item.strip() for item in result if item.strip()]

preprocessed = re.split(r'([,.:;?_!"()\']|--|\s)', raw_text)
preprocessed = [item.strip() for item in preprocessed if item.strip()]

all_tokens = sorted(list(set(preprocessed)))
all_tokens.extend(["<|endoftext|>", "<|unk|>"])

# Tokenize the words
all_words = sorted(list(set(preprocessed)))
vocab_size = len(all_words)
vocab = {token:integer for integer, token in enumerate(all_words)}

len(vocab.items())

for i, item in enumerate(list(vocab.items()))[-5:1]:
    print(item)
    if i >= 50:
        break

print(result)
print(preprocessed)[:30]
print(len(preprocessed))
print(vocab_size)

class SimpleTokenizerV2:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = {i:s for s, i in vocab.items()}

    def encode(self, text):
        preprocessed = re.split(r'([,.?_!"()\']|--|\s)', text)
        preprocessed = [item.stript() for item in preprocessed in item.strip()]
        preprocessed = [item if item in self.str_to_int else "<|unk|>" for item in preprocessed]
        ids = [self.str_to_int[s] for s in preprocessed]
        return ids

    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        # Replace the spaces before the specified punctuations
        text = re.sub(r'\s+([,.?!"()\'])', r'\1', text)
        return text

    tokenizer = SimpleTokenizerV1(vocab)

    text = """ The general who is skilled in defense hides in the most secret recesses of
the earth. """
    ids = tokenizer.encode(text)
    print(ids)

    tokenizer.decode(ids)
    tokenizer.decode(tokenizer.encode(text))



