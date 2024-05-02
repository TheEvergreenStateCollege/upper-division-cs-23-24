import re

with open(
    "../data/Reversing Secrets Of Reverse Engineering (2005).txt", "r", encoding="utf-8"
) as f:
    raw_text = f.read()

print("Total number of charactersi: ", len(raw_text))

preprocessed = re.split(r'([,.:;?_!"()\']|--|\s)', raw_text)
preprocessed = [w.strip() for w in preprocessed if w.strip()]

print(preprocessed[:99])


print(len(preprocessed))
