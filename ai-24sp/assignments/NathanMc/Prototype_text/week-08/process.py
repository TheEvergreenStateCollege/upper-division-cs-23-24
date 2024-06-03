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
    # Reshape x_i and query to ensure compatible shapes for the matmul
    x_i = x_i.unsqueeze(0) # Convert from (6, 256) to (1, 6, 256)
    query_reshaped = query.transpose(1, 2)  # Convert from (1, 6, 256) to (1, 256, 6)

    # Perform a batch matrix multiplication & reduce to required shape
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
    # Reshape the attention weights to match the dimensions of x_i
    attn_weights_reshaped = attn_weights_2[i].unsqueeze(1)  # Shape: (6, 1)

    # Perform element-wise multiplication and summation
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
        # Compute the dot product between x_i and x_j
        attn_scores[i, j] = torch.dot(x_i.view(-1), x_j.view(-1))  # Flatten and compute dot products

print("Dot products: \n", attn_scores)

row_2_sum = sum([-18.1231, 231.5645, -1.3308, -14.0112, -1.6738, 15.4884])
print("Row 2 sum: \n", row_2_sum)
print("All rows sums: \n", attn_weights_2.sum(dim=0))

# Computing the attention weights
x_2 = inputs.unsqueeze(2).float()  # This line makes `inputs` 3-dimensional & converts to a float
x_2 = x_2.permute(0, 2, 1)  # Rearrange dimension to [batch_size, 1, seq_length]

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

# Keys: obtain all keys and values by matrix multiplication
keys = torch.matmul(inputs_float, W_key)
values = torch.matmul(inputs_float, W_value)
print("keys.shape:", keys.shape)
print("values.shape:", values.shape)

# Ensure dimensions match for the dot product
# Reshape query_2 and keys to have the same number of elements
# Compute the attention score ω22:
query_2_reshaped = query_2.view(-1)  # Flatten the query_2 tensor to match keys_2
keys_2 = keys[1].view(-1)  # Flatten the keys_2 tensor

# Make sure they have the same size before performing dot product
if query_2_reshaped.size() == keys_2.size():
    attn_score_22 = torch.dot(query_2_reshaped, keys_2)
    print("Attention score 22: \n", attn_score_22)
else:
    print(f"Size mismatch: query_2_reshaped size = {query_2_reshaped.size()}, keys_2 size = {keys_2.size()}")

# Compute all attention scores for the given query
attn_scores_2 = torch.matmul(query_2.view(query_2.shape[0], -1), keys.T)  # All the attention scores for the given query
print("Attention scores 2: \n", attn_scores_2)

# Pg 78: Keys: third step: Dimension of Keys = ` d_k `
# Compute the attention weights by scaling the attention scores and,
# using the softmax function.
# We now scale the attention scores by dividing them by the square root of the
# embedding dimension of the keys.
d_k = keys.shape[-1]
attn_weights_2 = torch.softmax(attn_scores_2 / d_k**0.5, dim=-1)
print("Dimension of Keys:\n", attn_weights_2)

# Pg 80: keys:
# Compute the context vector as a weighted sum over the value vectors.
# Here, the attention weights serve as a weighting factor that weighs the
# respective importance of each value vector
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
        # Replace the spaces before the specified punctuations
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
