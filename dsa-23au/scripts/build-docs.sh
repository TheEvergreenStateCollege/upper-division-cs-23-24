#!/bin/sh

# Copy GitHub Markdown documents into mkdocs site documentation
# because symlinks don't work
# These are ignored from committing into GitHub with ./docs/.gitignore
cp *.md ./docs/ 

# Build to top-level GitHub Pages source directory 
mkdocs build -d ../docs/dsa-23au

