import csv
import time
import os

class GPTLogging:
    def __init__(self, log_file, chat_log_file):
        self.log_file = log_file
        self.chat_log_file = chat_log_file
        self.epoch_times = []
        self.log_headers = [
            "Epoch", "Total Steps", "Avg Training Loss", "Avg Validation Loss", "Time per Epoch (seconds)", "Generated Sentence", "Resource"
        ]
        self.chat_log_headers = ["Date and Time", "User Query", "GPT Response"]

        # Create the log files and write the headers
        if not os.path.isfile(self.log_file):
            with open(self.log_file, mode='w', newline='') as file:
                writer = csv.writer(file)
                writer.writerow(self.log_headers)

        if not os.path.isfile(self.chat_log_file):
            with open(self.chat_log_file, mode='w', newline='') as file:
                writer = csv.writer(file)
                writer.writerow(self.chat_log_headers)

    def log_start_time(self):
        self.start_time = time.time()

    def start_epoch(self):
        self.epoch_start_time = time.time()

    def end_epoch(self, epoch, total_steps, avg_train_loss, avg_val_loss, generated_sentence, resource):
        epoch_end_time = time.time()
        epoch_duration = epoch_end_time - self.epoch_start_time
        self.epoch_times.append(epoch_duration)

        avg_epoch_time = sum(self.epoch_times) / len(self.epoch_times)

        # Log the data to CSV
        with open(self.log_file, mode='a', newline='') as file:
            writer = csv.writer(file)
            writer.writerow([
                epoch, total_steps, avg_train_loss, avg_val_loss, avg_epoch_time, generated_sentence, resource
            ])

    def log_chat(self, user_query, gpt_response):
        with open(self.chat_log_file, mode='a', newline='') as file:
            writer = csv.writer(file)
            writer.writerow([time.strftime("%Y-%m-%d %H:%M:%S"), user_query, gpt_response])
