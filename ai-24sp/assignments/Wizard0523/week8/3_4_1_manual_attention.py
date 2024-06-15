import torch

#input is a tensor of embedded tokens
#for example: [[][][], [][][], [][][], [][][]]
#where each set of [][][] represent a token
x_2 = inputs[1]
d_in = inputs.shape[1]
d_out = 2

torch.manual_seed(456)
W_query = torch.nn.Parameter(torch.rand(d_in, d_out), requires_grad=False)
W_key = torch.nn.Parameter(torch.rand(d_in, d_out), requires_grad=False)
W_value = torch.nn.Parameter(torch.rand(d_in, d_out), requires_grad=False)

query_2 = x_2 @ W_query
key_2 = x_2 @ W_key
value_2 = x_2 @ W_value
print(query_2)

keys = inputs @ W_key
values = inputs @ W_value 
print("keys.shape:", keys.shape)
print("values.shape:", values.shape)


#compute attention scores
keys_2 = keys[1]
attn_score_22 = query_2.dot(keys_2)
print(attn_score_22)

attn_scores_2 = query_2 @ keys.T
print(attn_scores_2)

d_k = keys.shape[-1]
attn_weights_2 = torch.softmax(attn_scores_2 / d_k**0.5, dim=-1)
print(attn_weights_2)

#compute context vectors
context_vec_2 = attn_weights_2 @ values
print(context_vec_2)