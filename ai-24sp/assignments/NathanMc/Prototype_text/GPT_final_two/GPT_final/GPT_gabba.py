import torch
import tiktoken

from previous_chapters import previous_chapter # GPTModel, generate_text_simple
from GPTLogging import GPTLogging

class GPTGab:
    def __init__(self, model_paths, config, logger):
        self.device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
        self.models = []
        self.tokenizer = tiktoken.get_encoding("gpt2")
        self.context_size = config["context_length"]
        self.logger = logger
        
        for model_path in model_paths:
            model = GPTModel(config)
            model.load_state_dict(torch.load(model_path, map_location=self.device))
            model.to(self.device)
            model.eval()
            self.models.append(model)

    def text_to_token_ids(self, text):
        encoded = self.tokenizer.encode(text)
        encoded_tensor = torch.tensor(encoded).unsqueeze(0).to(self.device)
        return encoded_tensor

    def token_ids_to_text(self, token_ids):
        flat = token_ids.squeeze(0).tolist()
        return self.tokenizer.decode(flat)

    def ask(self, prompt, max_new_tokens=50):
        input_ids = self.text_to_token_ids(prompt)
        
        all_responses = []
        for model in self.models:
            with torch.no_grad():
                token_ids = generate_text_simple(
                    model=model, idx=input_ids,
                    max_new_tokens=max_new_tokens, context_size=self.context_size
                )
            response = self.token_ids_to_text(token_ids)
            all_responses.append(response)
        
        # Aggregate responses 
        final_response = " ".join(all_responses)
        return final_response

if __name__ == "__main__":
    # Load Configuration model and path
    GPT_CONFIG_124M = {
        "vocab_size": 50257,    # Vocabulary size
        "context_length": 256,  # Shortened context length (orig: 1024)
        "emb_dim": 768,         # Embedding dimension
        "n_heads": 12,          # Number of attention heads
        "n_layers": 12,         # Number of layers
        "drop_rate": 0.1,       # Dropout rate
        "qkv_bias": False       # Query-key-value bias
    }
    
    # List of model paths  ## Don't forget to change the model path names after you complete a training session.
    model_paths = ["model.pth", "model_20240605_215107.pth", "model_20240605_061948.pth"]

    # Logging
    logger = GPTLogging(log_file="gabba_log.csv", chat_log_file="chat_log.csv")

    # Create GPTGab
    gpt_gab = GPTGab(model_paths, GPT_CONFIG_124M, logger)

    # Ask questions
    while True:
        question = input("You: ")
        if question.lower() in ['exit', 'quit']:
            break
        response = gpt_gab.ask(question)
        print(f"GPT: {response}")

        # Log the chat
        logger.log_chat(user_query=question, gpt_response=response)
