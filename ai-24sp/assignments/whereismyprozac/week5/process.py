{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": 2,
   "metadata": {},
   "outputs": [],
   "source": [
    "class SimpleTokenizerV1:\n",
    "    def __init__(self, vocab):\n",
    "        self.str_to_int = vocab\n",
    "        self.int_to_str = {i:s for s,i in vocab.items()}\n",
    "    \n",
    "    def encode(self, text):\n",
    "        preprocessed = re.split(r'([,.?_!\"()\\']|--|\\s)', text)\n",
    "        preprocessed = [item.strip() for item in preprocessed if item.strip()]\n",
    "        ids = [self.str_to_int[s] for s in preprocessed]\n",
    "        return ids\n",
    "        \n",
    "    def decode(self, ids):\n",
    "        text = \" \".join([self.int_to_str[i] for i in ids])\n",
    "        # Replace spaces before the specified punctuations\n",
    "        text = re.sub(r'\\s+([,.?!\"()\\'])', r'\\1', text)\n",
    "        return text"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 3,
   "metadata": {},
   "outputs": [
    {
     "ename": "NameError",
     "evalue": "name 'vocab' is not defined",
     "output_type": "error",
     "traceback": [
      "\u001b[0;31m---------------------------------------------------------------------------\u001b[0m",
      "\u001b[0;31mNameError\u001b[0m                                 Traceback (most recent call last)",
      "Cell \u001b[0;32mIn[3], line 1\u001b[0m\n\u001b[0;32m----> 1\u001b[0m tokenizer \u001b[38;5;241m=\u001b[39m SimpleTokenizerV1(\u001b[43mvocab\u001b[49m)\n\u001b[1;32m      3\u001b[0m text \u001b[38;5;241m=\u001b[39m \u001b[38;5;124m\"\"\"\u001b[39m\u001b[38;5;124m\"\u001b[39m\u001b[38;5;124mJon is depicted as being grounded in the material world...\u001b[39m\u001b[38;5;124m\"\"\"\u001b[39m\n\u001b[1;32m      4\u001b[0m ids \u001b[38;5;241m=\u001b[39m tokenizer\u001b[38;5;241m.\u001b[39mencode(text)\n",
      "\u001b[0;31mNameError\u001b[0m: name 'vocab' is not defined"
     ]
    }
   ],
   "source": [
    "tokenizer = SimpleTokenizerV1(vocab)\n",
    "\n",
    "text = \"\"\"\"Jon is depicted as being grounded in the material world...\"\"\"\n",
    "ids = tokenizer.encode(text)\n",
    "print(ids)"
   ]
  }
 ],
 "metadata": {
  "kernelspec": {
   "display_name": "Python 3",
   "language": "python",
   "name": "python3"
  },
  "language_info": {
   "codemirror_mode": {
    "name": "ipython",
    "version": 3
   },
   "file_extension": ".py",
   "mimetype": "text/x-python",
   "name": "python",
   "nbconvert_exporter": "python",
   "pygments_lexer": "ipython3",
   "version": "3.10.13"
  }
 },
 "nbformat": 4,
 "nbformat_minor": 2
}
