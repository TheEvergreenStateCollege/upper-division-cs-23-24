import re

def tokenize(text):
    preprocessed = re.split(r'([,.?_!"()\']|--|\s)', text)
    preprocessed = [item.strip() for item in preprocessed if item.strip()]
    print(preprocessed[:50])
    print(len(preprocessed))
    
    all_words = sorted(list(set(preprocessed)))
    all_words.extend(["<|endoftext|>", "<|unk|>"])
    vocab_size = len(all_words)

    print(all_words[:50])
    print(vocab_size)
    
    vocab = {token:integer for integer,token in enumerate(all_words)}
    
    for i, item in enumerate(vocab.items()):
        print(item)
        if i >= vocab_size:
            break
        
    return vocab