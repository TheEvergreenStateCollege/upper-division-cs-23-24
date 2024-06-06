import matplotlib.pyplot as plt
import tiktoken
import torch
import torch.nn as nn
import pdfplumber
import os
import logging  # Logging
import multiprocessing as mp  # Parallel Data Loading
from datetime import datetime  # Import datetime for timestamping

from previous_chapters import GPTModel, create_dataloader_v1, generate_text_simple
from GPTLogging import GPTLogging

def text_to_token_ids(text, tokenizer):
    encoded = tokenizer.encode(text)
    encoded_tensor = torch.tensor(encoded).unsqueeze(0)
    return encoded_tensor

def token_ids_to_text(token_ids, tokenizer):
    flat = token_ids.squeeze(0)  # Remove the batch dimension
    return tokenizer.decode(flat.tolist())

def calc_loss_loader(data_loader, model, device, num_batches=None):
    """
    Calculate the average loss over a specified number of batches from the data loader.

    Parameters:
    - data_loader: DataLoader object for the dataset.
    - model: The GPT model to evaluate.
    - device: The device to run the model on (CPU or GPU).
    - num_batches: Optional; the number of batches to evaluate. If None, evaluates the entire loader.

    Returns:
    - Average loss over the specified number of batches.
    """
    total_loss = 0.
    if len(data_loader) == 0:
        return float("nan")
    elif num_batches is None:
        num_batches = len(data_loader)
    else:
        num_batches = min(num_batches, len(data_loader))
    for i, (input_batch, target_batch) in enumerate(data_loader):
        if i < num_batches:
            loss = calc_loss_batch(input_batch, target_batch, model, device)
            total_loss += loss.item()
        else:
            break
    return total_loss / num_batches

def calc_loss_batch(input_batch, target_batch, model, device):
    input_batch = input_batch.to(device)
    target_batch = target_batch.to(device)

    # Forward pass
    output = model(input_batch)

    # Calculate loss
    criterion = nn.CrossEntropyLoss()
    loss = criterion(output.view(-1, output.size(-1)), target_batch.view(-1))

    return loss

def evaluate_model(model, train_loader, val_loader, device, eval_iter):
    model.eval()
    with torch.no_grad():
        train_loss = calc_loss_loader(train_loader, model, device, num_batches=eval_iter)
        val_loss = calc_loss_loader(val_loader, model, device, num_batches=eval_iter)
    model.train()
    return train_loss, val_loss

def generate_and_print_sample(model, tokenizer, device, start_context):
    model.eval()
    context_size = model.pos_emb.weight.shape[0]
    encoded = text_to_token_ids(start_context, tokenizer).to(device)
    with torch.no_grad():
        token_ids = generate_text_simple(
            model=model, idx=encoded,
            max_new_tokens=50, context_size=context_size
        )
        decoded_text = token_ids_to_text(token_ids, tokenizer)
        print(decoded_text.replace("\n", " "))  # Compact print format
    model.train()
    return decoded_text.replace("\n", " ")  # Return the generated sentence

def train_model_simple(model, train_loader, val_loader, optimizer, device, num_epochs,
                       eval_freq, eval_iter, start_context, tokenizer, logger, resource, accumulation_steps=4):
    # Initialize lists to track losses and tokens seen
    train_losses, val_losses, track_tokens_seen = [], [], []
    tokens_seen = 0
    global_step = -1

    # Main training loop
    for epoch in range(num_epochs):
        logger.start_epoch()  # Start timer for the epoch
        model.train()  # Set model to training mode
        total_steps = 0
        epoch_train_loss = []
        optimizer.zero_grad()  # Reset gradients

        for i, (input_batch, target_batch) in enumerate(train_loader):
            loss = calc_loss_batch(input_batch, target_batch, model, device)
            loss.backward()  # Accumulate gradients

            if (i + 1) % accumulation_steps == 0:
                optimizer.step()  # Update model weights
                optimizer.zero_grad()  # Reset gradients

            tokens_seen += input_batch.numel()
            global_step += 1
            total_steps += 1
            epoch_train_loss.append(loss.item())

            # Optional evaluation step
            if global_step % eval_freq == 0:
                train_loss, val_loss = evaluate_model(
                    model, train_loader, val_loader, device, eval_iter)
                train_losses.append(train_loss)
                val_losses.append(val_loss)
                track_tokens_seen.append(tokens_seen)
                print(f"Ep {epoch+1} (Step {global_step:06d}): "
                      f"Train loss {train_loss:.3f}, Val loss {val_loss:.3f}")

        avg_train_loss = sum(epoch_train_loss) / len(epoch_train_loss)
        val_loss = sum(val_losses) / len(val_losses) if val_losses else float('nan')
        generated_sentence = generate_and_print_sample(
            model, tokenizer, device, start_context)
        logger.end_epoch(epoch+1, total_steps, avg_train_loss, val_loss, generated_sentence, resource)

    return train_losses, val_losses, track_tokens_seen, model
    
def plot_losses(epochs_seen, tokens_seen, train_losses, val_losses):
    fig, ax1 = plt.subplots()

    # Plot training and validation loss against epochs
    ax1.plot(epochs_seen, train_losses, label="Training loss")
    ax1.plot(epochs_seen, val_losses, linestyle="-.", label="Validation loss")
    ax1.set_xlabel("Epochs")
    ax1.set_ylabel("Loss")
    ax1.legend(loc="upper right")

    # Create a second x-axis for tokens seen
    ax2 = ax1.twiny()  # Create a second x-axis that shares the same y-axis
    ax2.plot(tokens_seen, train_losses, alpha=0)  # Invisible plot for aligning ticks
    ax2.set_xlabel("Tokens seen")

    fig.tight_layout()  # Adjust layout to make room
    # plt.show()

# Timestamp
def get_timestamp():
    return datetime.now().strftime("%Y%m%d_%H%M%S")

# Logging for the file operations and model training
def setup_logging(log_file):
    logging.basicConfig(filename=log_file, level=logging.INFO, 
                        format='%(asctime)s - %(levelname)s - %(message)s')

#  Parallel Data Loading using multiprocessing
def load_data_parallel(resource_files):
    pool = mp.Pool(mp.cpu_count())  # Create a pool with the number of available CPU cores
    raw_text_list = pool.map(load_single_file, resource_files)  # Map the load_single_file function to each file
    pool.close()
    pool.join()  # Close the pool and wait for the task to complete
    return ''.join(raw_text_list)  # Concatenate the text from all files

def load_single_file(data_path):
    if not os.path.isfile(data_path):
        raise FileNotFoundError(f"File not found at: {data_path}")
    with pdfplumber.open(data_path) as pdf:
        raw_text = ""
        for page in pdf.pages:
            raw_text += page.extract_text()
    return raw_text

# Main 
def main(gpt_config, settings):
    setup_logging("training.log")

    torch.manual_seed(123)
    device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
    logging.info("Using device: %s", device)

    ###########################
    #         Data Set
    ###########################
    # Replaced the single-thread loading with the parallel version
    try:
        resource_files = [
            r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/ArtOfWar.pdf",
            r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/BlueBookTP600-4.pdf",
            r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/LDRSHIP_ARN36735-FM_6-22-000-WEB-1.pdf",
            r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/DrillandCer_ARN32297-TC_3-21.5-000-WEB-1.pdf",
            r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/resources/PhysFitARN30964-FM_7-22-001-WEB-4.pdf"
        ]

        raw_text = load_data_parallel(resource_files)
        logging.info("Data loaded successfully")

    except Exception as e:
        logging.error("Error processing files:\n %s", e)
        raise

    text_data = raw_text
    resource_name = ", ".join([os.path.basename(file) for file in resource_files])  # Include all the source names

    # Initialize model
    try:
        model = GPTModel(gpt_config)
        model.to(device)
        optimizer = torch.optim.AdamW(
            model.parameters(), lr=settings["learning_rate"], weight_decay=settings["weight_decay"]
        )

        # Set up dataloaders
        # Train/validation ratio
        train_ratio = 0.90
        split_idx = int(train_ratio * len(text_data))

        train_loader = create_dataloader_v1(
            text_data[:split_idx],
            batch_size=settings["batch_size"],
            max_length=gpt_config["context_length"],
            stride=gpt_config["context_length"],
            drop_last=True,
            shuffle=True,
            num_workers=4  # Use multiple worker threads
        )

        val_loader = create_dataloader_v1(
            text_data[split_idx:],
            batch_size=settings["batch_size"],
            max_length=gpt_config["context_length"],
            stride=gpt_config["context_length"],
            drop_last=False,
            shuffle=False,
            num_workers=4  # Use multiple worker threads
        )

        # Train model
        tokenizer = tiktoken.get_encoding("gpt2")
        logger = GPTLogging("training_log.csv", "chat_log.csv")
        logger.log_start_time()  # Log the start date and time

        train_losses, val_losses, tokens_seen, model = train_model_simple(
            model, train_loader, val_loader, optimizer, device,
            num_epochs=settings["num_epochs"], eval_freq=5, eval_iter=1,
            start_context="I will guard everything within the limits of my post and quit my post only when properly relieved", tokenizer=tokenizer, logger=logger, resource=resource_name, accumulation_steps=4
        )

        # Save the model with a timestamp
        timestamp = get_timestamp()
        model_filename = f"model_{timestamp}.pth"
        torch.save(model.state_dict(), model_filename)
        logging.info("Model saved as %s", model_filename)

    except Exception as e:
        logging.error("Error during model training: %s", e)
        raise e

    return train_losses, val_losses, tokens_seen, model

if __name__ == "__main__":

    GPT_CONFIG_124M = {
        "vocab_size": 50257,    # Vocabulary size
        "context_length": 256,  # Shortened context length (orig: 1024)
        "emb_dim": 768,         # Embedding dimension
        "n_heads": 12,          # Number of attention heads
        "n_layers": 12,         # Number of layers
        "drop_rate": 0.1,       # Dropout rate
        "qkv_bias": False       # Query-Key-Value bias
    }

    OTHER_SETTINGS = {
        "learning_rate": 3e-4,  # Original value was: 5e-4,
        "num_epochs": 20,       # Increase number of epochs from 10 to 20
        "batch_size": 2,        # Increase the batch size or Decrease. 
        "weight_decay": 0.1
    }

    # Initiate training
    train_losses, val_losses, tokens_seen, model = main(GPT_CONFIG_124M, OTHER_SETTINGS)

    # After training
    # Plot results
    epochs_tensor = torch.linspace(0, OTHER_SETTINGS["num_epochs"], len(train_losses))
    plot_losses(epochs_tensor, tokens_seen, train_losses, val_losses)
    plt.savefig("gpt_train_loss.pdf")

    # Save and load model
    torch.save(model.state_dict(), "model.pth")
    model = GPTModel(GPT_CONFIG_124M)
    model.load_state_dict(torch.load("model.pth"))
