#/usr/bin/env bash

# Commands to test your API
curl -X POST http://localhost:5000/signin\
 -H "Content-Type: application/json" \
 -d '{ "username": "tors", "password": "apple" }' \
 -o token.txt
