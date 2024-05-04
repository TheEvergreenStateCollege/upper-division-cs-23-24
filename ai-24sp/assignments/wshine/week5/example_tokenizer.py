import re
from importlib.metadata import version
import tiktoken
print("tiktoken version: ", version("tiktoken"))


# tokenizer
class SimpleTokenizerV2:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = {i: s for s, i in vocab.items()}

    def encode(self, text):
        preprocessed = re.split(r'([,.:;?_!"()\']|--|\s)', text)
        preprocessed = [w.strip() for w in preprocessed if w.strip()]
        preprocessed = [
            item if item in self.str_to_int else "<|unk|>" for item in preprocessed
        ]
        ids = [self.str_to_int[s] for s in preprocessed]
        return ids

    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        text = re.sub(r'\s+([,.?!"()\'])', r"\1", text)
        return text


with open(
    "../data/Reversing Secrets Of Reverse Engineering (2005).txt", "r", encoding="utf-8"
) as f:
    raw_text = f.read()

print("Total number of charactersi: ", len(raw_text))

preprocessed = re.split(r'([,.:;?_!"()\']|--|\s)', raw_text)
preprocessed = [w.strip() for w in preprocessed if w.strip()]
print(preprocessed[:99])
print(len(preprocessed))


all_tokens = sorted(set(preprocessed))
all_tokens.extend(["<|endoftext|>", "<|unk|>"])
vocab = {token: integer for integer, token in enumerate(all_tokens)}
print(len(vocab.items()))


tokenizer = SimpleTokenizerV2(vocab)
text = """ Hello World do you like tea?"""
text2 = """ some sample text for sanity"""

ids = tokenizer.encode("<|endoftext|>".join((text, text2)))
print(ids)
print(tokenizer.decode(ids))


# tiktoken

tokenizer = tiktoken.get_encoding("gpt2")
