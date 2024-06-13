import torch
from gpt_train import GPTModel, text_to_token_ids, token_ids_to_text, generate_text_simple
import tiktoken

def load_model(model_path, gpt_config):
    model = GPTModel(gpt_config)
    model.load_state_dict(torch.load(model_path))
    model.eval()
    return model

def initialize_tokenizer():
    return tiktoken.get_encoding("gpt2")  # Assuming this is how your tokenizer is initialized

def fine_tune_model(model, new_data_loader, optimizer, device, num_epochs=3):
    model.train()
    for epoch in range(num_epochs):
        for input_batch, target_batch in new_data_loader:
            optimizer.zero_grad()
            input_batch, target_batch = input_batch.to(device), target_batch.to(device)
            logits = model(input_batch)
            loss = torch.nn.functional.cross_entropy(logits.flatten(0, 1), target_batch.flatten())
            loss.backward()
            optimizer.step()
    model.eval()

def generate_response(model, tokenizer, device, input_text, max_tokens=100, temperature=1.0, top_k=50):
    context = text_to_token_ids(input_text, tokenizer).to(device)
    context_size = model.pos_emb.weight.shape[0]  # Adjust according to your model
    with torch.no_grad():
        generated = generate_text_simple(model, context, max_tokens, context_size)
        response = token_ids_to_text(generated, tokenizer)
    return response

if __name__ == "__main__":
    model_path = "model.pth"  # Path to your trained model
    GPT_CONFIG_124M = {
        "vocab_size": 50257,
        "context_length": 256,
        "emb_dim": 768,
        "n_heads": 12,
        "n_layers": 12,
        "drop_rate": 0.1,
        "qkv_bias": False
    }

    # Load model
    model = load_model(model_path, GPT_CONFIG_124M)

    # Initialize tokenizer
    tokenizer = initialize_tokenizer()

    # Determine device
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    model.to(device)

    # Example fine-tuning (pseudo-code)
    # new_data_loader = create_dataloader_new_data(...)
    # optimizer = torch.optim.AdamW(model.parameters(), lr=1e-4)
    # fine_tune_model(model, new_data_loader, optimizer, device, num_epochs=3)

    print("Welcome! Start chatting with the GPT model (type 'exit' to end).")

    while True:
        user_input = input("You: ")

        if user_input.lower() == 'exit':
            print("Exiting...")
            break

        # Example of generating response with adjusted parameters
        generated_text = generate_response(model, tokenizer, device, user_input, max_tokens=100, temperature=0.7, top_k=30)
        print("ShakespeareGPT: " + generated_text)
