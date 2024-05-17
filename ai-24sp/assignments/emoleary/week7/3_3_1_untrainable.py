import torch
from dataloader import create_dataloader_v1

raw_text = "" 
with open("../data/catcher.txt", "r", encoding="utf-8") as f:
        raw_text = f.read()

max_length = 4
dataloader = create_dataloader_v1(raw_text, batch_size=8, max_length=max_length, stride=max_length, shuffle=False)
data_iter = iter(dataloader)
inputs, targets = next(data_iter)

query = inputs[1]
print(query)
attn_scores_2 = torch.empty(inputs.shape[0])

# Computing the dot product of the query x
# With all of the other input tokens
for i, x_i in enumerate(inputs):
    attn_scores_2[i] = torch.dot(x_i, query)


print("\n\nAttention Score For inputs[1]: \n",attn_scores_2)

res = 0.


print(torch.dot(inputs[0], query))

#print("\n\nDot Product Computed Using For-Loop: \n", res)
print("\n\nDot Product Computed Using torch.dot(): \n",attn_scores_2)

attn_scores_2_tmp = attn_scores_2 / attn_scores_2.sum() 
print("\n\nAttention Weights: \n",attn_scores_2_tmp)
print("\n\nSum: \n",attn_scores_2)

def softmax_naive(x):
    return torch.exp(x) / torch.exp(x).sum(dim=0)
 
print("\n\n\nSoftmax functions are used to ensure that attention weights are always positive. This way, the output is interpretable as probabilities.\n")
print("\nUsing softmax_naive(): [NOT ADVISABLE]\n")
attn_weights_2_naive = softmax_naive(attn_scores_2)
print("\n\nAttention weights:\n", attn_weights_2_naive)
print("\n\nSum:\n", attn_weights_2_naive.sum())

print("\n\nUsing torch.softmax(): [ADVISABLE]\n")
attn_weights_2 = torch.softmax(attn_scores_2, dim=0)
print("Attention weights:", attn_weights_2)
print("Sum:", attn_weights_2.sum())
 
query = inputs[1] # 2nd input token is the query
context_vec_2 = torch.zeros(query.shape)
for i,x_i in enumerate(inputs):
    context_vec_2 += attn_weights_2[i]*x_i


print("\n\nThe \"context vector\" is calculated by multiply ea input vector by corresponding attention weight.\n")
print("Context Vector: \n",context_vec_2)