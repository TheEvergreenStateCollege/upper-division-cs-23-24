import torch
import torch.nn as nn
from CausalAttentionClass import CausalAttention

# The main idea behind multi-head attention is to run the attention
# mechanism multiple times (in parallel) with different, learned linear projections -- the
# results of multiplying the input data (like the query, key, and value vectors in attention
# mechanisms) by a weight matrix.

# Pg 97: Multi-Head Attention
class MultiHeadAttentionWrapper(nn.Module):
    def __init__(self, d_in, d_out, context_length, dropout, num_heads, qkv_bias=False):
        super().__init__()
        assert d_out % num_heads == 0, "d_out must be divisible by num_heads"

        self.d_out = d_out
        self.num_heads = num_heads
        self.head_dim = d_out // num_heads  # A: Reduce the projection dim to match desired output dim
        self.W_query = nn.Linear(d_in, d_out, bias=qkv_bias)
        self.W_key = nn.Linear(d_in, d_out, bias=qkv_bias)
        self.W_value = nn.Linear(d_in, d_out, bias=qkv_bias)
        self.out_proj = nn.Linear(d_out, d_out)  # B: Use a Linear layer to combine head outputs
        self.dropout = nn.Dropout(dropout)
        self.register_buffer(
            'mask',
            torch.triu(torch.ones(context_length, context_length), diagonal=1)
        )

    def forward(self, x):
        x = x.float()  # The input needs to be of float type
        b, num_tokens, d_in = x.shape
        keys = self.W_query(x)  #C Tensor shape: (b, num_tokens, d_out)
        queries = self.W_query(x)
        values = self.W_value(x)

        # D: We implicitly split the matrix by adding a `num_heads` dimension.
        # Then we unroll last dim: 
        # (b, num_tokens, d_out) -> (b, num_tokens, num_heads, head_dim)
        keys = keys.view(b, num_tokens, self.num_heads, self.head_dim)
        values = values.view(b, num_tokens, self.num_heads, self.head_dim)
        queries = queries.view(b, num_tokens, self.num_heads, self.head_dim)

        # E: Transpose from shape:
        # (b, num_tokens, num_heads, head_dim) to (b, num_heads, num_tokens, head_dim)
        keys = keys.transpose(1, 2)
        queries = queries.transpose(1, 2)
        values = values.transpose(1, 2)

        attn_scores = queries @ keys.transpose(2, 3)  #F Compute dot product for each head
        mask_bool = self.mask.bool()[:num_tokens, :num_tokens]  #G Mask truncated to the number of tokens

        attn_scores.masked_fill_(mask_bool, -torch.inf)  #H Use the mask to fill attention scores

        attn_weights = torch.softmax(
            attn_scores / keys.shape[-1] ** 0.5, dim=-1)
        attn_weights = self.dropout(attn_weights)

        context_vec = (attn_weights @ values).transpose(1, 2)  #I Tensor shape: (b, num_tokens, n_heads, head_dim)

        #J Combine heads, where self.d_out = self.num_heads * self.head_dim
        context_vec = context_vec.contiguous().view(b, num_tokens, self.d_out)
        context_vec = self.out_proj(context_vec)
        return context_vec



    