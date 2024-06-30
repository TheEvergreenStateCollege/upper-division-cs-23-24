import torch
from three_five_one import attn_weights

torch.manual_seed(123)
dropout = torch.nn.Dropout(0.5) # dropout rate of 50%
example = torch.ones(6, 6) # create a matrix of ones

print(dropout(example))

torch.manual_seed(123)
print(dropout(attn_weights))