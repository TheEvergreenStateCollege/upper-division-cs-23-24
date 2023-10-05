#!/bin/sh

# Pipe the CSV data into stdin of App.java main
cat src/main/resources/data.csv | java -jar target/arrays-links-1.0-SNAPSHOT.jar