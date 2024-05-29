#from dataloader import get_input
import torch.nn
from dataloader import embed

inputs = embed(4)
# This file is intended to simplify the idea of attention mechanisms, by computing only one context vector 'z'.

# Initial Variables: input and output dimensions. Selecting input vector for example. 
inputs = inputs[1]
x_2 = inputs[1]
d_in = inputs.shape[1]
#print("d_in:", d_in)
d_out = 2

# Initializing weight matrices. 
torch.manual_seed(131)
W_query = torch.nn.Parameter(torch.rand(d_in, d_out, requires_grad=False))
W_key = torch.nn.Parameter(torch.rand(d_in, d_out, requires_grad=False))
W_value = torch.nn.Parameter(torch.rand(d_in, d_out, requires_grad=False))

# Computing query, key, and value vectors...
print("d_in:", d_in)
print("inputs.shape():", inputs.shape)
print("W_query.shape():", W_query.shape)
print("x_2.shape():", x_2.shape)

query_2 = x_2 @ W_query 
key_2   = x_2 @ W_key 
value_2 = x_2 @ W_value
print(query_2)

keys = inputs @ W_key 
values = inputs @ W_value
print("keys.shape:", keys.shape)
print("values.shape:", values.shape)

class SelfAttention_v2(nn.Module):
    def __init__(self, d_in, d_out, qkv_bias=False):
        super().__init__()
        self.d_out = d_out
        self.W_query = nn.Linear(d_in, d_out, bias=qkv_bias)
        self.W_Key = nn.Linear(d_in, d_out, bias=qkv_bias)
        self.W_value = nn.Linear(d_in, d_out, bias=qkv_bias)

    def forward(self, x):
        keys = self.W_key(x)
        queries = self.W_query(x)
        values = self.W_value(x)
        attn_scores @ keys.T
        attn_weights = torch.softmax(attn_scores / keys.shape[-1]**0.5, dim=1)
        context_vec = attn_weights @ values
        return context_vec

keys_2 = keys[1]
attn_score_22 = query_2.dot(keys_2)
print(attn_score_22)

attn_scores_2 = query_2 @ keys.T
print(attn_scores_2)

