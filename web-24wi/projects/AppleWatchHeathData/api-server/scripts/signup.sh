#/usr/bin/env bash

# Commands to test your API
curl -X POST http://localhost:5000/signup\
 -H "Content-Type: application/json" \
 -d '{ "username": "abc", "password": "123" }' \
 -o token.txt