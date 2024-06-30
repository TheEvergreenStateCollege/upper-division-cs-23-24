import torch
from three_three_one import inputs
from three_four_one import d_in, d_out
from three_four_two import SelfAttention_v1, SelfAttention_v2

torch.manual_seed(123)
sa_v1 = SelfAttention_v1(d_in, d_out)
print(sa_v1(inputs))

torch.manual_seed(789)
sa_v2 = SelfAttention_v2(d_in, d_out)
print(sa_v2(inputs))