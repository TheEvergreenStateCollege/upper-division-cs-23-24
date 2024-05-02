import re
import importlib
import tiktoken


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
        preprocessed = [item if item in self.str_to_int 
                        else "<|unk|>" for item in preprocessed]

        ids = [self.str_to_int[s] for s in preprocessed]
        return ids
        
    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        # Replace spaces before the specified punctuations
        text = re.sub(r'\s+([,.?!"()\'])', r'\1', text)
        return text
    
# 2.2

with open("../data/betty-crocker.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()
    
print("Total number of character:", len(raw_text))
print(raw_text[:99])
# Output 1:
# Total number of character: 120284
# The Project Gutenberg eBook of Betty Crocker picture cooky book
    
# This ebook is for the use of 

preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
preprocessed = [item.strip() for item in preprocessed if item.strip()]
print(preprocessed[:30])

# Output 2:
# ['\ufeffThe', 'Project', 'Gutenberg', 'eBook', 'of', 'Betty', 'Crocker', 'picture', 'cooky', 'book', 'This', 'ebook', 'is', 'for', 'the', 'use', 'of', 'anyone', 'anywhere', 'in', 'the', 'United', 'States', 'and', 'most', 'other', 'parts', 'of', 'the', 'world']

# 2.3
all_words = sorted(list(set(preprocessed)))
vocab = {token:integer for integer,token in enumerate(all_words)}
for i, item in enumerate(vocab.items()):
    print(item)
    if i >= 50:
        break

# Output 3
# ('!', 0)
# ('"', 1)
# ('#72443]', 2)
# ('$1', 3)
# ('$5', 4)
# ('(', 5)
# (')', 6)
# ('***', 7)
# ('*2', 8)
# ('*4', 9)
# ('*In', 10)
# ('*Or', 11)
# ('*⅓', 12)
# (',', 13)
# ('-', 14)
# ('--', 15)
# ('.', 16)
# ('000', 17)
# ('1', 18)
# ('1-cup', 19)
# ('10', 20)
# ('11', 21)
# ('12', 22)
# ('128', 23)
# ('12″', 24)
# ('13', 25)
# ('14', 26)
# ('14-15', 27)
# ('15', 28)
# ('15-oz', 29)
# ('1500', 30)
# ('16', 31)
# ('16-21', 32)
# ('17', 33)
# ('175', 34)
# ('18', 35)
# ('19', 36)
# ('1939', 37)
# ('1948', 38)
# ('1¼', 39)
# ('1¼″', 40)
# ('1½', 41)
# ('1½″', 42)
# ('1¾', 43)
# ('1″', 44)
# ('1⅓', 45)
# ('1⅔', 46)
# ('1⅛', 47)
# ('1⅜', 48)
# ('2', 49)
# ('20', 50)


# Output 4
# [1, 58, 2, 872, 1013, 615, 541, 763, 5, 1155, 608, 5, 1, 69, 7, 39, 873, 1136, 773, 812, 7]

# Output 5
# '" It\' s the last he painted, you know," Mrs. Gisburn said with pardonable pride.'

# Output 6
# Traceback (most recent call last):
#   File "/workspaces/upper-division-cs/ai-24sp/assignments/rilesbe/week5/tokenizer.py", line 135, in <module>
#     tokenizer.encode(text)
#   File "/workspaces/upper-division-cs/ai-24sp/assignments/rilesbe/week5/tokenizer.py", line 14, in encode
#    ids = [self.str_to_int[s] for s in preprocessed]
# File "/workspaces/upper-division-cs/ai-24sp/assignments/rilesbe/week5/tokenizer.py", line 14, in <listcomp>
#  ids = [self.str_to_int[s] for s in preprocessed]
# KeyError: 'Hello'

preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
preprocessed = [item.strip() for item in preprocessed if item.strip()]

all_tokens = sorted(list(set(preprocessed)))
all_tokens.extend(["<|endoftext|>", "<|unk|>"])

vocab = {token:integer for integer,token in enumerate(all_tokens)}

for i, item in enumerate(list(vocab.items())[-5:]):
    print(item)

# Output 7
# ('\ufeffThe', 3375)
# ('<|endoftext|>', 3376)
# ('<|unk|>', 3377)

tokenizer = SimpleTokenizerV2(vocab)

text1 = "Hello, do you like tea?"
text2 = "In the sunlit terraces of the palace."

text = " <|endoftext|> ".join((text1, text2))

print(text)

tokenizer.encode(text)
tokenizer.decode(tokenizer.encode(text))

# Output 8
# Hello, do you like tea? <|endoftext|> In the sunlit terraces of the palace.