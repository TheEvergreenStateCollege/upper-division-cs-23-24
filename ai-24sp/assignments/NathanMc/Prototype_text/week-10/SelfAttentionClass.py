import torch
import torch.nn as nn

# Pg 81: 3.1 A compact self-attention class for Version 1.
class SelfAttention_v1(nn.Module):
    def __init__(self, d_in, d_out):
        super().__init__()
        self.d_out = d_out
        self.W_query = nn.Parameter(torch.rand(d_in, d_out))
        self.W_key = nn.Parameter(torch.rand(d_in, d_out))
        self.W_value = nn.Parameter(torch.rand(d_in, d_out))

    def forward(self, x):
        batch_size, seq_length, d_in = x.shape

        # Linear projections
        keys = torch.matmul(x, self.W_key)  # Shape: [batch_size, seq_length, d_out]
        queries = torch.matmul(x, self.W_query)  # Shape: [batch_size, seq_length, d_out]
        values = torch.matmul(x, self.W_value)  # Shape: [batch_size, seq_length, d_out]

        # Omega: Compute attention scores
        attn_scores = torch.matmul(queries, keys.transpose(-2, -1)) / (self.W_key.shape[-1] ** 0.5)  # Shape [batch_size, seq_length, seq_length]
        
        # Create mask
        mask = torch.triu(torch.ones(seq_length, seq_length), diagonal=1)
        masked_attn_scores = attn_scores.masked_fill(mask.bool(), -torch.inf)

        # Compute attention weights
        attn_weights = torch.softmax(masked_attn_scores, dim=1)  # Shape: [batch_size, seq_length, seq_length]

        # Compute the context vector as a weighted sum of the value of vectors
        context_vec = torch.matmul(attn_weights, values)  # Shape: [batch_size, seq_length, d_out]

        return context_vec

# Pg 83: Self Attention Class version 2: sa_v2
class SelfAttention_v2(nn.Module):
    def __init__(self, d_in, d_out, qkv_bias=False):
        super().__init__()
        self.d_out = d_out
        self.W_query = nn.Linear(d_in, d_out, bias=qkv_bias)
        self.W_key = nn.Linear(d_in, d_out, bias=qkv_bias)
        self.W_value = nn.Linear(d_in, d_out, bias=qkv_bias)

    def forward(self, x):
        keys = self.W_key(x)
        queries = self.W_query(x)
        values = self.W_value(x)
        attn_scores = queries @ keys.transpose(-2, -1)
        attn_weights = torch.softmax(attn_scores / (keys.shape[-1] ** 0.5), dim=1)
        context_vec = attn_weights @ values
        return context_vec

