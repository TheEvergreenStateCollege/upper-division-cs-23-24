from importlib.metadata import version
import tiktoken
import torch
import re
import pdfplumber
import os

from dataloader import create_dataloader_v1
from SelfAttentionClass import SelfAttention_v1, SelfAttention_v2  # Import SelfAttention_v2
from CausalAttentionClass import CausalAttention
from MultiHeadAttention import MultiHeadAttentionWrapper

print("PyTorch version:", torch.__version__)
print("tiktoken version:", version("tiktoken"))

data_path = r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/ArtOfWar.pdf"
if not os.path.isfile(data_path):
    raise FileNotFoundError(f"File not found at: {data_path}")

with pdfplumber.open(data_path) as pdf:
    raw_text = ""
    for page in pdf.pages:
        raw_text += page.extract_text()

# Tokenizer Initialization
tokenizer = tiktoken.get_encoding("gpt2")

output_dim = 256
vocab_size = 50257
max_length = 128

# Model and DataLoader Initialization
token_embedding_layer = torch.nn.Embedding(vocab_size, output_dim)

# Dataloader
dataloader = create_dataloader_v1(
    raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False)

data_iter = iter(dataloader)
inputs, targets = next(data_iter)

inputs = torch.randint(0, vocab_size, (2, 6))
inputs = inputs.long()

# Convert token IDs to embeddings
inputs_embedding = token_embedding_layer(inputs)

first_batch = next(data_iter)
second_batch = next(data_iter)

print("Token ID:\n", inputs)
print("\n Inputs shape:\n", inputs.shape)

# Token Embeddings
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
print(f"y: {y}")

for i in range(1, context_size + 1):
    context = enc_sample[:i]
    desired = enc_sample[i]
    print(context, "---->", desired)

for i in range(1, context_size + 1):
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
print(len(vocab.items()))

# Changed the enumerate object to a list and then slice it
for i, item in list(enumerate(list(vocab.items())))[-5:]:
    print(item)
    if i >= 50:
        break

print(result)
print(preprocessed[:30])
print(len(preprocessed))
print(vocab_size)

# Chapter 3 Self-attention
query = inputs_embedding[0]  # Shape (embedding_dim,)

# Check the shapes
print(f"Shape of inputs_embedding:\n {inputs_embedding.shape}")
print(f"Shape of query before reshape:\n {query.shape}")

# Reshape query if it's not already 2D
if len(query.shape) == 2:
    query = query.unsqueeze(0)  # Convert from (6, 256) to (1, 6, 256)

print(f"Shape of query after reshape:\n {query.shape}")

# Compute self-attention scores
attn_scores_2 = torch.empty(inputs_embedding.shape[0], inputs_embedding.shape[1])
for i, x_i in enumerate(inputs_embedding):
    x_i = x_i.unsqueeze(0) # Convert from (6, 256) to (1, 6, 256)
    query_reshaped = query.transpose(1, 2)  # Convert from (1, 6, 256) to (1, 256, 6)

    score = torch.matmul(x_i, query_reshaped)  # Shape: (1, 6, 6)
    score = score.squeeze(0)   # Remove the batch dimension, resulting in shape (6, 6)

    attn_scores_2[i] = score.sum(dim=0)  # Sum over the batch dimension to get the final score

print("Self Attention Score:\n", attn_scores_2)

# Normalization. This helps maintain training stability.
attn_weights_2 = torch.softmax(attn_scores_2, dim=1)

print("Attention Weights:\n", attn_weights_2.detach().numpy())
print("Sum of Attention Weights:\n", attn_weights_2.sum().detach().numpy())

# Compute the context vector using the attention weights
context_vec_2 = torch.zeros(query.squeeze().shape)

for i, x_i in enumerate(inputs_embedding):
    attn_weights_reshaped = attn_weights_2[i].unsqueeze(1)  # Shape: (6, 1)
    context_vec_2 += attn_weights_reshaped * x_i   # Shape: (6, 256)

print("Context Vector:\n", context_vec_2.detach().numpy())

inputs_float = inputs.float()

# Compute all context vectors using matrix multiplication
all_context_vecs = torch.matmul(attn_weights_2, inputs_embedding)

print("All the Context Vectors: \n", all_context_vecs)
print("Previous 2nd context vector: \n", context_vec_2)

# Compute dot products for all pairs of inputs
attn_scores = torch.empty(inputs_embedding.shape[0], inputs_embedding.shape[0])

for i, x_i in enumerate(inputs_embedding):
    for j, x_j in enumerate(inputs_embedding):
        attn_scores[i, j] = torch.dot(x_i.view(-1), x_j.view(-1))

print("Dot products: \n", attn_scores)

row_2_sum = sum([-18.1231, 231.5645, -1.3308, -14.0112, -1.6738, 15.4884])
print("Row 2 sum: \n", row_2_sum)
print("All rows sums: \n", attn_weights_2.sum(dim=0))

x_2 = inputs.unsqueeze(2).float()  # This line makes `inputs` 3-dimensional & converts to a float
x_2 = x_2.permute(0, 2, 1)

d_in = inputs.shape[1]  # B
d_out = 2  # C

torch.manual_seed(123)
W_query = torch.nn.Parameter(torch.rand(d_in, d_out), requires_grad=False)
W_key = torch.nn.Parameter(torch.rand(d_in, d_out), requires_grad=False)
W_value = torch.nn.Parameter(torch.rand(d_in, d_out), requires_grad=False)

query_2 = torch.matmul(x_2, W_query)
key_2 = torch.matmul(x_2, W_key)
value_2 = torch.matmul(x_2, W_value)
print("Query 2: \n", query_2)

keys = torch.matmul(inputs_float, W_key)
values = torch.matmul(inputs_float, W_value)
print("keys.shape:", keys.shape)
print("values.shape:", values.shape)

query_2_reshaped = query_2.view(-1)
keys_2 = keys[1].view(-1)

if query_2_reshaped.size() == keys_2.size():
    attn_score_22 = torch.dot(query_2_reshaped, keys_2)
    print("Attention score 22: \n", attn_score_22)
else:
    print(f"Size mismatch: query_2_reshaped size = {query_2_reshaped.size()}, keys_2 size = {keys_2.size()}")

attn_scores_2 = torch.matmul(query_2.view(query_2.shape[0], -1), keys.T)
print("Attention scores 2: \n", attn_scores_2)

d_k = keys.shape[-1]
attn_weights_2 = torch.softmax(attn_scores_2 / d_k**0.5, dim=-1)
print("Dimension of Keys:\n", attn_weights_2)

context_vec_2 = attn_weights_2 @ values
print("Context vectors 2:\n", context_vec_2)

# Simple Tokenizer Class
class SimpleTokenizerV2:
    def __init__(self, vocab):
        self.str_to_int = vocab
        self.int_to_str = {i: s for s, i in vocab.items()}

    def encode(self, text):
        preprocessed = re.split(r'([,.?_!"()\']|--|\s)', text)
        preprocessed = [item.strip() for item in preprocessed if item.strip()]
        preprocessed = [item if item in self.str_to_int else "" for item in preprocessed]
        ids = [self.str_to_int[s] for s in preprocessed]
        return ids

    def decode(self, ids):
        text = " ".join([self.int_to_str[i] for i in ids])
        text = re.sub(r'\s+([,.?!"()\'])', r'\1', text)
        return text

text = """ The general who is skilled in defense hides in the most secret recesses of the earth. """
integers = tokenizer.encode(text, allowed_special={""})
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

print(tokenizer.decode(ids))
print(tokenizer.decode(tokenizer.encode(text)))

# Self-attention class for Version 1
torch.manual_seed(123)
sa_v1 = SelfAttention_v1(inputs_embedding.shape[2], 128)
print(f"Shape before self-attention: {inputs_embedding.shape}")
sa_output = sa_v1(inputs_embedding)
print("Self-Attention Version 1 Output:\n", sa_output)

# Self-attention class for Version 2
torch.manual_seed(789)
sa_v2 = SelfAttention_v2(d_in, d_out)
inputs_float = inputs.float()  # Convert the inputs to float before passing to sa_v2
print("Self Attention Version 2:\n", sa_v2(inputs_float))

# pg 86: We need to create a causal attention mask to obtain the masked attention weights.
queries = sa_v2.W_query(inputs_float)
keys = sa_v2.W_key(inputs_float)
attn_scores = queries @ keys.transpose(-2, -1)
attn_weights = torch.softmax(attn_scores / keys.shape[-1] ** 0.5, dim=1)
print("Masked Attention Weights:\n", attn_weights)

# Implement Pytorch's tril function to create a mask, 
# where the values above the diagonal are zero.
context_length = attn_scores.shape[0]
mask_simple = torch.tril(torch.ones(context_length, context_length))
print(" Simple Mask:\n", mask_simple) 

# Now, we can multiply this mask with the attention weights to zero out the values above
# the diagonal. 
masked_simple = attn_weights*mask_simple
print("Masked Simple zeroed out:\n", masked_simple)

# Renormalize the attention weights to sum up to 1 again in each row.
# We can achieve this by dividing each element in each row by the sum in
# each row. 
row_sums = masked_simple.sum(dim=1, keepdim=True)
masked_simple_norm = masked_simple / row_sums
print("Renormalize Attention Weights:\n", masked_simple_norm)

# Pg 89: 
# We can implement this more efficient masking "trick" by creating a mask with 1's
# above the diagonal and then replacing these 1's with negative infinity (-inf) values.
mask = torch.triu(torch.ones(context_length, context_length), diagonal=1)
masked = attn_scores.masked_fill(mask.bool(), -torch.inf)
print("Masking trick:\n", masked)

attn_weights = torch.softmax(masked / keys.shape[-1] ** 0.5, dim=1)
print(attn_weights)

# 91: Dropout method: 50% = 0.5
# use a drop out rate of 50%, which means masking out half of the attention weights.
torch.manual_seed(123)
dropout = torch.nn.Dropout(0.5)  # A: Choose a dropout rate of 50%
example = torch.ones(6, 6)  # B: create a matrix of 1's

print("Dropout Example:\n", dropout(example))
print("Dropout Matrix:\n", dropout(attn_weights))

batch = torch.stack((inputs, inputs), dim=0)
print("Batch Shape:\n", batch)

# Pg 94: Causal Attention class
torch.manual_seed(123)
context_length = batch.shape[1]
ca = CausalAttention(d_in, d_out, context_length, 0.0)
context_vecs = ca(batch)

print("Context vectors shape:\n", context_vecs.shape)

# Pg 97: Multi-Head Attention
torch.manual_seed(123)
batch_szie, context_length, d_in = batch.shape  # This is the number of tokens
d_in, d_out = 6, 2
mha = MultiHeadAttentionWrapper(d_in, d_out, context_length, 0.0, num_heads=2)
context_vecs = mha(batch)

print("Multi-Head Attention:\n", context_vecs)
print("context_vecs.shape:\n", context_vecs.shape)

# Pg 102: MultiHeadAttention
print("Multi-Head Attention Transpose:\n", inputs_embedding @ inputs_embedding.transpose(1, 2))

# Pg 103: Multiply each head seperately
first_head = inputs_embedding[0, :, :]
first_res = first_head @ first_head.T

print("First Head:\n", first_res)

second_head = inputs_embedding[1, :, :]
second_res = second_head @ second_head.T

print("\nSecond Head:\n", second_res)
