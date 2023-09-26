#!/bin/sh

# Copy GitHub Markdown documents into mkdocs site documentation
cp ../*.md ./docs/ 
#
# Build to top-level GitHub Pages source directory 
mkdocs build -d ../../docs/dsa-23au

