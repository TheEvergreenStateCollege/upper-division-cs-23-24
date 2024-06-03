import torch
# This looks like a 3-dimensional embedding for
# this test chunk
# ["Your", "journey", "starts", "with", "one", "step"]
inputs = torch.tensor(
  [[0.43, 0.15, 0.89], # Your     (x^1)
   [0.55, 0.87, 0.66], # journey  (x^2)
   [0.57, 0.85, 0.64], # starts   (x^3)
   [0.22, 0.58, 0.33], # with     (x^4)
   [0.77, 0.25, 0.10], # one      (x^5)
   [0.05, 0.80, 0.55]] # step     (x^6)
)

# We query the second token x^2 first it looks like
query = inputs[1]

# If we normalize the query token, we would
# expect its dot product with itself to be 1 

norm_query = query / torch.norm(query)
self_overlap = torch.dot(norm_query, norm_query)
print(f"Overlap of query token with itself {self_overlap}")

# Practice understanding dot product 
# by taking overlap with query
result = 0. 

for idx, elt in enumerate(inputs[0]):
    result += inputs[0][idx] * query[idx]
test_overlap = torch.dot(inputs[0], query)
print(f"Overlap should be 0.9544: {test_overlap}")

attn_scores_2 = torch.empty(inputs.shape[0])
for i, x_i in enumerate(inputs):
    attn_scores_2[i] = torch.dot(x_i, query)
print(attn_scores_2)

# We normalize all the weights from the query token
# not normalizing each vector, but normalizing the dot product / overlaps
# First by getting the sum of the weights
attn_weights_2_tmp = attn_scores_2 / attn_scores_2.sum()
print("Attention weights:", attn_weights_2_tmp)
print("Sum: ", attn_weights_2_tmp.sum())

def softmax_naive(x):
    return torch.exp(x) / torch.exp(x).sum(dim=0)

attn_weights_2_naive = softmax_naive(attn_scores_2)
print("Attention weights:", attn_weights_2_naive)
print("Sum:", attn_weights_2_naive.sum())

