#!/bin/sh

mkdir -p data

if [ ! -f data/dracula.txt ]; then
    wget https://www.gutenberg.org/cache/epub/345/pg345-images.html
    html2text pg345-images.html > data/dracula.txt
    rm  pg345-images.html*
fi