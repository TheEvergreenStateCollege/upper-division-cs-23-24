import torch
from dataloader import create_dataloader_v1 


with open("../data/inverting-the-pyramid.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

max_length = 4
dataloader = create_dataloader_v1(raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False)
data_iter = iter(dataloader)
inputs, targets = next(data_iter)

attn_scores = torch.empty(6, 6)

attn_scores = inputs @ inputs.T
print(attn_scores)

print(type(attn_scores))
attn_weights = torch.softmax(attn_scores, dim=1)
print(attn_weights)

row_2_sum = sum([0.1385, 0.2379, 0.2333, 0.1240, 0.1082, 0.1581])
print("Row 2 sum:", row_2_sum)
print("All row sums:", attn_weights.sum(dim=1))

all_context_vecs = attn_weights @ inputs
print(all_context_vecs)