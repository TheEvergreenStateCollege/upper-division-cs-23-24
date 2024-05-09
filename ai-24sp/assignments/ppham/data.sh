#!/bin/sh

if [ ! -f data/mark-twain-autobio.txt ]; then
    wget https://www.gutenberg.org/files/19987/19987-h/19987-h.htm
    html2text 19987-h.htm > data/mark-twain-autobio.txt
    rm  19987-h.htm*
fi