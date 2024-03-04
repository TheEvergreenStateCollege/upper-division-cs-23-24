#/usr/bin/env bash

PORT=$(cat port.txt)

# Commands to test your API
curl -X POST http://localhost:${PORT}/signin \
 -H "Content-Type: application/json" \
 -d '{ "username": "xyz", "password": "123" }' \
 | jq .token | tr -d "\"" > token.txt
