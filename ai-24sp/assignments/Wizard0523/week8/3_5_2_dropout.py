import torch

torch.manual_seed(123)
dropout = torch.nn.Dropout(0.5)
example = torch.ones(6, 6)
print(dropout(example))

torch.manual_seed(123)
print(dropout(attn_weights))

