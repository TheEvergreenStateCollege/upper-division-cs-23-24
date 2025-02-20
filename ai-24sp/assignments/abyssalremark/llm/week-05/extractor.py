from pypdf import PdfReader as reader

input = "../data/Malware, Rootkits And Botnets - A Beginner's Guide (2013).pdf"
output = "../data/extract.txt"
doc = reader(input)

with open(output, "w", encoding="utf=8") as outFile:

	for page in doc.pages:
		text = page.extract_text()
		outFile.write(text)

