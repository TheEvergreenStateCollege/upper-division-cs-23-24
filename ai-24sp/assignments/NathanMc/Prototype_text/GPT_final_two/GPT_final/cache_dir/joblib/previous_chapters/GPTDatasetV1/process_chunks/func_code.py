# first line: 19
    @staticmethod
    @memory.cache  # Cashe the results of the this method
    def process_chunks(txt, tokenizer, max_length, stride):
        input_ids = []
        target_ids = []

        # Tokenize the entire text
        token_ids = tokenizer.encode(txt)
        
        # Use a sliding window to chunk the text into overlapping sequences of max_length
        for i in range(0, len(token_ids) - max_length, stride):
            input_chunk = token_ids[i:i + max_length]
            target_chunk = token_ids[i + 1:i + max_length + 1]
            input_ids.append(torch.tensor(input_chunk))
            target_ids.append(torch.tensor(target_chunk))
        return input_ids, target_ids
