import torch
from dataloader import create_dataloader_v1 

with open("../data/inverting-the-pyramid.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

print(f"Total number of characters: {len(raw_text)}")
print(raw_text[:100])


query = inputs[1]
attn_scores_2 = torch.empty(inputs.shape[0])
for i, x_i in enumerate(inputs):
    attn_scores_2[i] = torch.dot(x_i, query)
print(attn_scores_2)


attn_weights_2_tmp = attn_scores_2 / attn_scores_2.sum()
#print("Attention weights:", attn_weights_2_tmp)
#print("Sum:", attn_weights_2_tmp.sum())


def softmax_naive(x):
    return torch.exp(x) / torch.exp(x).sum(dim=0)

attn_weights_2 = torch.softmax(attn_scores_2, dim=0)
print("Attention weights:", attn_weights_2)
print("Sum:", attn_weights_2.sum())

query = inputs[1] # 2nd input is the query
context_vec_2 = torch.zeros(query.shape)
for i, x_i in enumerate(inputs):
    context_vec_2 += attn_weights_2[i]*x_i
print(context_vec_2)

