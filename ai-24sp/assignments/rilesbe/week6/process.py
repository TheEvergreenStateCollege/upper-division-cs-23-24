raw_text = "" 
with open("../week5/data/betty-crocker.txt", "r", encoding="utf-8") as f:
    raw_text = f.read()

print(f"Total number of characters: {len(raw_text)}")
print(raw_text[:100])