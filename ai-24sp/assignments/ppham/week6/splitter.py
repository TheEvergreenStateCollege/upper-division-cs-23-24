import re

def split(text):
    split_text = re.split(r'([.,?_!"()\']|--|&mdash;)|\s', text)
    preprocessed = [item for item in split_text if type(item) == str and item.strip()]
    return preprocessed