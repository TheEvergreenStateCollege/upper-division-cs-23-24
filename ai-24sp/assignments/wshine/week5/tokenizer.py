from importlib.metadata import version
import tiktoken

print("tiktoken version: ", version("tiktoken"))

with open(
    "../data/Reversing Secrets Of Reverse Engineering (2005).txt", "r", encoding="utf-8"
) as f:
    raw_text = f.read()
print("Total number of charactersi: ", len(raw_text))
# tokenizer
tokenizer = tiktoken.get_encoding("gpt2")


# example 
# text = "Akwwirw ier"
# integers = tokenizer.encode(text, allowed_special={"<|endoftext|>"})
# strings = tokenizer.decode(integers)
# print(integers)
# print(strings)
# print(tokenizer.decode([33901, 1383, 343, 86, 220, 959]))


# tokenizing actual data
enc_text = tokenizer.encode(raw_text,allowed_special={"<|endoftext|>"})
enc_sample = enc_text[50:]


context_size = 4

x = enc_sample[:context_size]
y = enc_sample[1:context_size+1]

for i in range(1, context_size+1):
    context = enc_sample[:i]
    desired = enc_sample[i]
    print(tokenizer.decode(context), "------>", tokenizer.decode([desired]))

