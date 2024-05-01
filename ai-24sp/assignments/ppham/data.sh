#!/bin/sh

FILE=wikipedia-en-html.tar

mkdir -p data
cd data/

echo ${FILE}.7z

if [ ! -e ".done" ]; then    
    wget "https://dumps.wikimedia.org/other/static_html_dumps/current/en/${FILE}.7z"
fi 

# On Mac the tool is called 7zz and installable by Homebrew
if [ ! -z "$(which 7zz)" ]; then
    ZIP="7zz x"
fi

# OnUbuntu the tool is called p7zip-fill
if [ ! -z "$(which p7zip)" ]; then
    ZIP="p7zip -d"
fi

${ZIP} "${FILE}.7z"
tar xvf "$FILE"
rm -Rf *.tar *.7z
touch .done
