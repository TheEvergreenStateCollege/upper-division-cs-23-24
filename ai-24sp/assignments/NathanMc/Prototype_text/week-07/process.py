from importlib.metadata import version
import tiktoken
import torch
import re
import pdfplumber
import os

from dataloader import create_dataloader_v1

print("PyTorch version:", torch.__version__)
print("tiktoken version:", version("tiktoken"))

data_path = r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/ArtOfWar.pdf"
if not os.path.isfile(data_path):
    raise FileNotFoundError(f"File not found at: {data_path}")

with pdfplumber.open(data_path) as pdf:
    raw_text = ""
    for page in pdf.pages:
        raw_text += page.extract_text()

tokenizer = tiktoken.get_encoding("gpt2")

output_dim = 256
vocab_size = 50257
max_length = 128

token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)


# Dataloader
dataloader = create_dataloader_v1(
    raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False)

data_iter = iter(dataloader)
inputs, targets = next(data_iter)

first_batch = next(data_iter)
second_batch = next(data_iter)

print("Token ID:\n", inputs)
print("\n Inputs shape:\n", inputs.shape)

token_embedding = token_embedding_layer(inputs)
context_length = max_length
pos_embedding_layer = torch.nn.Embedding(context_length, output_dim)

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
print("Total number of characters:", len(raw_text))
print(raw_text[:99])

# Test phrase
text = "Hello, world..? This--, is a test."

# Remove unnecessary characters and white space
result = re.split(r'([,.:;?_!"()\']|--|\s)', text)
result = [item.strip() for item in result if item.strip()]

preprocessed = re.split(r'([,.:;?_!"()\']|--|\s)', raw_text)
preprocessed = [item.strip() for item in preprocessed if item.strip()]

all_tokens = sorted(list(set(preprocessed)))
all_tokens.extend(["", ""])

# Tokenize the words
all_words = sorted(list(set(preprocessed)))
vocab_size = len(all_words)
vocab = {token: integer for integer, token in enumerate(all_words)}
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

class SimpleTokenizerV2:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = {i: s for s, i in vocab.items()}

    def encode(self, text):
        preprocessed = re.split(r'([,.?_!"()\']|--|\s)', text)
        preprocessed = [item.strip() for item in preprocessed if item.strip()]
        preprocessed = [item if item in self.str_to_int else "<|unk|>" for item in preprocessed]
        ids = [self.str_to_int[s] for s in preprocessed]
        return ids

    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        # Replace the spaces before the specified punctuations
        text = re.sub(r'\s+([,.?!"()\'])', r'\1', text)
        return text

    text = """ The general who is skilled in defense hides in the most secret recesses of the earth. """
    integers = tokenizer.encode(text, allowed_special={"<|endoftext|>"})
    ids = tokenizer.encode(text)
    strings = tokenizer.decode(integers)

    text1 = "Hello, do you like cookies?"
    text2 = "In front of the fireplace."
    text = " ".join((text1, text2))
    print(text)



    print(ids)
    print(integers)
    print(strings)
    print("Token embeddings shape: ", token_embedding.shape)
    print("First Batch: ", first_batch)
    print("Second Batch ", second_batch)
    print("Inputs:\n", inputs)
    print("\nTargets:\n", targets)
    
    tokenizer.decode(ids)
    tokenizer.decode(tokenizer.encode(text))
