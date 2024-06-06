import pandas as pd

file_path = r"/mnt/d/myPython/SpringStudentOriginatedSoftware/AI_SelfHosting/Prototype_LLM/GPT_final/chat_log.csv"

try:
    # Load the chat log CSV file
    chat_log_df = pd.read_csv(file_path)
    # Display the first few rows of the dataframe
    print(chat_log_df.head(10))
except Exception as e:
    print(f"An error occurred: {e}")
