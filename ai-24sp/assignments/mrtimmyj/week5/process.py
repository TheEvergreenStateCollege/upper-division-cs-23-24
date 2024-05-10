from tokenizer import SimpleTokenizerV1
from importlib.metadata import version

import tiktoken
#import torch
import re

# Output 1 - Open data and print out amount of lines as well as the first 100 characters.
with open("../data/dracula.txt", "r") as f:
    raw_text = f.read()

#print("Total number of character:", len(raw_text))
#print(raw_text[:100])

# Output 2 - Remove whitespace
#result = re.split(r'([,.:;?_!"()\']|--|\s)', raw_text)
#result = [item.strip() for item in result if item.strip()]
# print(result)

# Output 3 - Assign tokens
#preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
#preprocessed = [item.strip() for item in preprocessed if item.strip()]
# print(preprocessed[:30])
# print(len(preprocessed))

#all_words = sorted(list(set(preprocessed)))
# vocab_size = len(all_words)

# print(vocab_size)

#vocab = {token:integer for integer,token in enumerate(all_words)}

 #for i, item in enumerate(vocab.items()):
    # print(item)
    # if i >= 50:
    #     break

# Output 4 - 
#tokenizer = SimpleTokenizerV1(vocab)

#text = "It's the last he painted, you know, Mrs. Gisburn said with pardonable pride."
#ids = tokenizer.encode(text)
#print(ids)

# Output 5 -
#tokenizer.decode(ids)
#tokenizer.decode(tokenizer.encode(text))

# Output 6 -
#tokenizer = SimpleTokenizerV1(vocab)

#text = "Hello, do you like tea. Is this-- a test?"

#tokenizer.encode(text)

# Output 7 -
# preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
# preprocessed = [item.strip() for item in preprocessed if item.strip()]

# all_tokens = sorted(list(set(preprocessed)))
# all_tokens.extend(["<|endoftext|>", "<|unk|>"])

# vocab = {token:integer for integer,token in enumerate(all_tokens)}

# len(vocab.items())

# for i, item in enumerate(list(vocab.items())[-5:]):
#     print(item)

# Output 8 -
tokenizer = SimpleTokenizerV2(vocab)

text1 = "Hello, do you like tea?"
text2 = "In the sunlit terraces of the palace."

text = " <|endoftext|> ".join((text1, text2))

print(text)

tokenizer.encode(text)

tokenizer.decode(tokenizer.encode(text))