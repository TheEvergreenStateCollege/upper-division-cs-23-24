import re

with open("../data/mark-twain-autobio.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

class SimpleTokenizerV1:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = {i:s for s,i in vocab.items()}
    
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
    

preprocessed = re.split(r'([,.?_!"()\']|--|\s)', raw_text)
preprocessed = [item.strip() for item in preprocessed if item.strip()]
print(preprocessed[:30])

all_words = sorted(list(set(preprocessed)))
all_words.extend(["<|endoftext|>", "<|unk|>"])
#vocab_size = len(all_words)
#print(vocab_size)
vocab = {token:integer for integer,token in enumerate(all_words)}


tokenizer = SimpleTokenizerV1(vocab)


ids = tokenizer.encode(raw_text)
print(ids)

decoded_text = tokenizer.decode(ids)
print(decoded_text)

#for i, item in enumerate(list(vocab.items())[-5:]):
#    print(item)

tokenizer = SimpleTokenizerV1(vocab)

text1 = "This is the end."
text2 = "I'm a new text!"

text = " <|endoftext|> ".join((text1, text2))

print(text)