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

def generate_response(model, tokenizer, device, input_text, max_tokens=100):
    context = text_to_token_ids(input_text, tokenizer).to(device)
    context_size = model.pos_emb.weight.shape[0]  # Adjust according to your model
    with torch.no_grad():
        generated = generate_text_simple(model, context, max_tokens, context_size)
        response = token_ids_to_text(generated, tokenizer)
    return response

if __name__ == "__main__":
    model_path = "./model.pth"  # Path to your trained model
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

    print("Welcome! Start chatting with the GPT model (type 'exit' to end).")

    while True:
        user_input = input("You: ")
        
        if user_input.lower() == 'exit':
            print("Exiting...")
            break
        
        # Generate response
        response = generate_response(model, tokenizer, device, user_input, max_tokens=50)
        print("FutbolGPT: " + response)
