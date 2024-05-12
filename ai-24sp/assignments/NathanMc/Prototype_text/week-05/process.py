from importlib.metadata import version
import tiktoken
import torch
import re
import pdfplumber
import os


print("torch version:", version("torch"))
print("tiktoken version:", version("tiktoken"))

data_path = r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_text/data/ArtOfWar.pdf"
if not os.path.isfile(data_path):
    raise FileNotFoundError(f"File not found at: {data_path}")


with pdfplumber.open(data_path) as pdf:
    raw_text = ""
    for page in pdf.pages:
        raw_text += page.extract_text()

tokenizer = tiktoken.get_encoding("gpt2")

# Encode the raw text data
enc_text = tokenizer.encode(raw_text)

enc_sample = enc_text[50:]
context_size = 4

x = enc_sample[:context_size]
y = enc_sample[1:context_size+1]

print(f"x: {x}")
print(f"y:      {y}")

for i in range(1, context_size+1):
    context = enc_sample[:i]
    desired = enc_sample[i]

    print(context, "---->", desired)

for i in range(1, context_size+1):
    context = enc_sample[:i]
    desired = enc_sample[i]

    print(tokenizer.decode(context), "---->", tokenizer.decode([desired]))

print(len(enc_text))
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

# Changed the enumerate object to a list and then slice it
for i, item in list(enumerate(list(vocab.items())))[-5:]:
    print(item)
    if i >= 50:
        break

print(result)
print(preprocessed[:30])
print(len(preprocessed))
print(vocab_size)

class SimpleTokenizerV1:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = {i:s for s, i in vocab.items()}

    def encode(self, text):
        preprocessed = re.split(r'([,.?_!"()\']|--|\s)', text)
        preprocessed = [item.strip() for item in preprocessed if item.strip()]
        preprocessed = [item if item in self.str_to_int else "" for item in preprocessed]
        ids = [self.str_to_int[s] for s in preprocessed]
        return ids

    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        # Replace the spaces before the specified punctuations
        text = re.sub(r'\s+([,.?!"()\'])', r'\1', text)
        return text

    
    text = """ The general who is skilled in defense hides in the most secret recesses of
the earth. """
    integers = tokenizer.encode(text, allowed_special={"<|endoftext|>"})
    ids = tokenizer.encode(text)
    strings = tokenizer.decode(integers)

    print(ids)
    print(integers)
    print(strings)

    tokenizer.decode(ids)
    tokenizer.decode(tokenizer.encode(text))



