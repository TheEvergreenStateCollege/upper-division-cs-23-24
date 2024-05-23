from importlib.metadata import version
import tiktoken
import re
print("tiktoken version:", version("tiktoken"))

#regex is hard.
def split(text):
    split_text = re.split(r'([.,?_!"()\']|--|&mdash;)|\s', text)
    preprocessed = [item for item in split_text if type(item) == str and item.strip()]
    return preprocessed

with open("../data/mark-twain-autobio.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

#2000th token because we have meaningless trash I guess? Could we just trim it?
raw_text = raw_text[2000:]

tokenizer = tiktoken.get_encoding("gpt2")
integers = tokenizer.encode(raw_text, allowed_special={"<|endoftext|>"})
print(f"Number of token IDs {len(integers)}")
print(f"Byte-pair-encoded token IDs {integers[:100]}")

start = 2900
for i in range(start,3000):
    decoded = tokenizer.decode(integers[start:i+1])
    print(f"{integers[start:i+1]} {decoded}")

enc_sample = integers[50:]

context_size = 4

x = enc_sample[:context_size]
y = enc_sample[1:context_size+1]

print(f"x: {x}")
print(f"y:        {y}")

from dataloader import create_dataloader_v1

dataloader = create_dataloader_v1(raw_text, batch_size=1, max_length=4, stride=1, shuffle=False)

data_iter = iter(dataloader)
first_batch = next(data_iter)
print(first_batch)

second_batch = next(data_iter)
print(second_batch)
