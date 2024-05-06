{
 "cells": [
  {
   "cell_type": "code",
   "execution_count": 1,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "torch version: 2.2.2\n",
      "tiktoken version: 0.6.0\n"
     ]
    }
   ],
   "source": [
    "from importlib.metadata import version\n",
    "\n",
    "import tiktoken\n",
    "import torch\n",
    "\n",
    "print(\"torch version:\", version(\"torch\"))\n",
    "print(\"tiktoken version:\", version(\"tiktoken\"))"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 4,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Total number of character: 48658\n",
      "7/27/1978\n",
      "\n",
      "When I was 18... 18 years old, I saw for the first time in my life... I saw an image of \n"
     ]
    }
   ],
   "source": [
    "with open(\"/workspaces/upper-division-cs/ai-24sp/assignments/whereismyprozac/data/totally-profound-monologue.txt\", \"r\", encoding=\"utf-8\") as f:\n",
    "    raw_text = f.read()\n",
    "    \n",
    "print(\"Total number of character:\", len(raw_text))\n",
    "print(raw_text[:99])"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 8,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "['The', ' ', 'cat', ' ', 'has', ' ', 'the', ' ', 'pipe!']\n"
     ]
    }
   ],
   "source": [
    "import re\n",
    "\n",
    "text = \"The cat has the pipe!\"\n",
    "result = re.split(r'(\\s)', text)\n",
    "\n",
    "print(result)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 9,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "['The', ' ', 'cat', ' ', 'has', ' ', 'the', ' ', 'pipe!']\n"
     ]
    }
   ],
   "source": [
    "result = re.split(r'([,.]|\\s)', text)\n",
    "\n",
    "print(result)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 10,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "['The', 'cat', 'has', 'the', 'pipe!']\n"
     ]
    }
   ],
   "source": [
    "# Strip whitespace from each item and then filter out any empty strings.\n",
    "result = [item for item in result if item.strip()]\n",
    "print(result)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 11,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "['The', 'cat', 'has-', 'the', 'pipe', '!', '?']\n"
     ]
    }
   ],
   "source": [
    "text = \"The cat has- the pipe!?\"\n",
    "\n",
    "result = re.split(r'([,.:;?_!\"()\\']|--|\\s)', text)\n",
    "result = [item.strip() for item in result if item.strip()]\n",
    "print(result)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 12,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "['7/27/1978', 'When', 'I', 'was', '18', '.', '.', '.', '18', 'years', 'old', ',', 'I', 'saw', 'for', 'the', 'first', 'time', 'in', 'my', 'life', '.', '.', '.', 'I', 'saw', 'an', 'image', 'of', 'clarity']\n"
     ]
    }
   ],
   "source": [
    "preprocessed = re.split(r'([,.?_!\"()\\']|--|\\s)', raw_text)\n",
    "preprocessed = [item.strip() for item in preprocessed if item.strip()]\n",
    "print(preprocessed[:30])"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 13,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "11852\n"
     ]
    }
   ],
   "source": [
    "print(len(preprocessed))"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 14,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "2105\n"
     ]
    }
   ],
   "source": [
    "all_words = sorted(list(set(preprocessed)))\n",
    "vocab_size = len(all_words)\n",
    "\n",
    "print(vocab_size)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 15,
   "metadata": {},
   "outputs": [],
   "source": [
    "vocab = {token:integer for integer,token in enumerate(all_words)}"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 16,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "('!', 0)\n",
      "('\"', 1)\n",
      "(\"'\", 2)\n",
      "('(', 3)\n",
      "(')', 4)\n",
      "('*Okamoto', 5)\n",
      "('*fumbles', 6)\n",
      "('*puts', 7)\n",
      "(',', 8)\n",
      "('-', 9)\n",
      "('-Oscar', 10)\n",
      "('.', 11)\n",
      "('000', 12)\n",
      "('1', 13)\n",
      "('1-6', 14)\n",
      "('10', 15)\n",
      "('1111/j', 16)\n",
      "('12th', 17)\n",
      "('1330]', 18)\n",
      "('1600-0773', 19)\n",
      "('18', 20)\n",
      "('1978', 21)\n",
      "('1980', 22)\n",
      "('1981', 23)\n",
      "('1981*', 24)\n",
      "('1983', 25)\n",
      "('1994', 26)\n",
      "('25%', 27)\n",
      "('27th', 28)\n",
      "('33', 29)\n",
      "('6b', 30)\n",
      "('7/27/1978', 31)\n",
      "('75', 32)\n",
      "('7971729', 33)\n",
      "('7:27:78]', 34)\n",
      "('80', 35)\n",
      "(':', 36)\n",
      "('?', 37)\n",
      "('A', 38)\n",
      "('A:', 39)\n",
      "('ALWAYS', 40)\n",
      "('AND', 41)\n",
      "('Add', 42)\n",
      "('Africa', 43)\n",
      "('After', 44)\n",
      "('Again', 45)\n",
      "('Ages', 46)\n",
      "('Ah', 47)\n",
      "('Aha', 48)\n",
      "('All', 49)\n",
      "('Although', 50)\n"
     ]
    }
   ],
   "source": [
    "for i, item in enumerate(vocab.items()):\n",
    "    print(item)\n",
    "    if i >= 50:\n",
    "        break"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 23,
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
    "        text = \"Lasagna\".join([self.int_to_str[i] for i in ids])\n",
    "        # Replace spaces before the specified punctuations\n",
    "        text = re.sub(r'\\s+([,.?\"()\\'])', r'\\1', text)\n",
    "        return text"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 24,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "[1, 79]\n"
     ]
    }
   ],
   "source": [
    "tokenizer = SimpleTokenizerV1(vocab)\n",
    "\n",
    "text = \"\"\"\" Cat\"\"\"\n",
    "ids = tokenizer.encode(text)\n",
    "print(ids)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 25,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "'\"LasagnaCat'"
      ]
     },
     "execution_count": 25,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "tokenizer.decode(ids)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 26,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "'\"LasagnaCat'"
      ]
     },
     "execution_count": 26,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "tokenizer.decode(tokenizer.encode(text))"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 27,
   "metadata": {},
   "outputs": [],
   "source": [
    "preprocessed = re.split(r'([,.?_!\"()\\']|--|\\s)', raw_text)\n",
    "preprocessed = [item.strip() for item in preprocessed if item.strip()]\n",
    "\n",
    "all_tokens = sorted(list(set(preprocessed)))\n",
    "all_tokens.extend([\"<|endoftext|>\", \"<|unk|>\"])\n",
    "\n",
    "vocab = {token:integer for integer,token in enumerate(all_tokens)}"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 28,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "2107"
      ]
     },
     "execution_count": 28,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "len(vocab.items())"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 29,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "('your', 2102)\n",
      "('yourself', 2103)\n",
      "('youthful', 2104)\n",
      "('<|endoftext|>', 2105)\n",
      "('<|unk|>', 2106)\n"
     ]
    }
   ],
   "source": [
    "for i, item in enumerate(list(vocab.items())[-5:]):\n",
    "    print(item)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 35,
   "metadata": {},
   "outputs": [],
   "source": [
    "class SimpleTokenizerV2:\n",
    "    def __init__(self, vocab):\n",
    "        self.str_to_int = vocab\n",
    "        self.int_to_str = { i:s for s,i in vocab.items()}\n",
    "    \n",
    "    def encode(self, text):\n",
    "        preprocessed = re.split(r'([,.?_!\"()\\']|--|\\s)', text)\n",
    "        preprocessed = [item.strip() for item in preprocessed if item.strip()]\n",
    "        preprocessed = [item if item in self.str_to_int \n",
    "                        else \"<|unk|>\" for item in preprocessed]\n",
    "\n",
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
   "execution_count": 36,
   "metadata": {},
   "outputs": [
    {
     "name": "stdout",
     "output_type": "stream",
     "text": [
      "Jon is depicted as being grounded in the material world... <|endoftext|> a world of things; he is surrounded by objects, and he touches these objects, he interacts with them.\n"
     ]
    }
   ],
   "source": [
    "tokenizer = SimpleTokenizerV2(vocab)\n",
    "\n",
    "text1 = \"Jon is depicted as being grounded in the material world...\"\n",
    "text2 = \"a world of things; he is surrounded by objects, and he touches these objects, he interacts with them.\"\n",
    "\n",
    "text = \" <|endoftext|> \".join((text1, text2))\n",
    "\n",
    "print(text)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 37,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "[157,\n",
       " 1116,\n",
       " 693,\n",
       " 397,\n",
       " 444,\n",
       " 961,\n",
       " 1068,\n",
       " 1905,\n",
       " 1243,\n",
       " 2081,\n",
       " 11,\n",
       " 11,\n",
       " 11,\n",
       " 2105,\n",
       " 303,\n",
       " 2081,\n",
       " 1367,\n",
       " 1920,\n",
       " 983,\n",
       " 1116,\n",
       " 1865,\n",
       " 500,\n",
       " 1357,\n",
       " 8,\n",
       " 364,\n",
       " 983,\n",
       " 1960,\n",
       " 1915,\n",
       " 1357,\n",
       " 8,\n",
       " 983,\n",
       " 1105,\n",
       " 2069,\n",
       " 1907,\n",
       " 11]"
      ]
     },
     "execution_count": 37,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "tokenizer.encode(text)"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": 38,
   "metadata": {},
   "outputs": [
    {
     "data": {
      "text/plain": [
       "'Jon is depicted as being grounded in the material world... <|endoftext|> a world of things; he is surrounded by objects, and he touches these objects, he interacts with them.'"
      ]
     },
     "execution_count": 38,
     "metadata": {},
     "output_type": "execute_result"
    }
   ],
   "source": [
    "tokenizer.decode(tokenizer.encode(text))\n"
   ]
  },
  {
   "cell_type": "code",
   "execution_count": null,
   "metadata": {},
   "outputs": [],
   "source": []
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
