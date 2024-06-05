with open("../data/The Complete Works of William Shakespeare.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()
    
print(f"Total number of characters: {len(raw_text)}")

excerpt = raw_text[810:2000]