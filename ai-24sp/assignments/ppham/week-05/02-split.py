import re 
text = "Hello, world. Is this-- a test?"
# Capture groups in a split pattern are kept in the resulting list
result = re.split(r'([.,?_!"()\']|--)|\s', text)
result = [item for item in result if type(item) == str and item.strip()]
print(result)
