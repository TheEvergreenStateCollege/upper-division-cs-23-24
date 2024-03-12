#!/usr/bin/env bash 

PORT=$(cat port.txt)
TOKEN=$(cat token.txt | tr -d "\n")

# Commands to test your API
curl -X GET http://localhost:${PORT}/api/uscities \
 -H "Content-Type: application/json" \
 -H "Authorization: Bearer ${TOKEN}"
