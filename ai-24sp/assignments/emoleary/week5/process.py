from importlib.metadata import version
import tiktoken
import torch
import re
import random
def read_file(file_path):
    with open("../data/catcher.txt", "r", encoding="utf-8") as f:
        raw_text = f.read()
    return raw_text

def tokenize(raw_text):
    preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
    preprocessed = [item.strip() for item in preprocessed if item.strip()]
    
    preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
    preprocessed = [item.strip() for item in preprocessed if item.strip()]

    all_tokens = sorted(list(set(preprocessed)))
    all_tokens.extend(["<|endoftext|>", "<|unk|>"])

    return all_tokens

def extract_vocab(tokens):
    all_words = sorted(list(set(tokens)))
    vocab_size = len(all_words)

    vocab = {token:integer for integer,token in enumerate(all_words)}
    return vocab


class SimpleTokenizerV1:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = {i:s for s,i in vocab.items()}
    
    def encode(self, text):
        preprocessed = re.split(r'([,.?_!"()\']|--|\s)', text)
        preprocessed = [item.strip() for item in preprocessed if item.strip()]
        ids = [self.str_to_int[s] for s in preprocessed]
        return ids
        
    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        # Replace spaces before the specified punctuations
        text = re.sub(r'\s+([,.?!"()\'])', r'\1', text)
        return text

class SimpleTokenizerV2:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = { i:s for s,i in vocab.items()}
    
    def encode(self, text):
        preprocessed = re.split(r'([,.?_!"()\']|--|\s)', text)
        preprocessed = [item.strip() for item in preprocessed if item.strip()]
        preprocessed = [item if item in self.str_to_int else "<|unk|>" for item in preprocessed]

        ids = [self.str_to_int[s] for s in preprocessed]
        return ids
        
    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        # Replace spaces before the specified punctuations
        text = re.sub(r'\s+([,.?!"()\'])', r'\1', text)
        return text

# Open and read in file
file_name = "../data/catcher.txt"

# Step 1
raw = read_file(file_name)

# Step 2
tokens = tokenize(raw)

# Step 3
vocab = extract_vocab(tokens)

# Step 4
#tokenizer = SimpleTokenizerV1(vocab)
#ids = tokenizer.encode(raw)
#not_included = "cryptogoth"
#ids = tokenizer.encode(not_included)

#Step 5


#Step 6
tokenizer = SimpleTokenizerV2(vocab)
text1 = "Everytime a bell rings"
text2 = " an angel grows a new eye"
text = " <|endoftext|> ".join((text1, text2))
print(f"\nOriginal: \n{text}")
#Step 7
encoded = tokenizer.encode(text)
print(f"\nEncoded: \n{encoded}")
decoded = tokenizer.decode(encoded)
print(f"\nDecoded: \n{decoded}")