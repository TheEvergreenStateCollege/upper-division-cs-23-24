#!/usr/bin/env bash 

set -x

PORT=$(cat port.txt)
TOKEN=$(cat token.txt | tr -d "\n")

# Commands to test your API
curl -X GET https://indira.arcology.builders/api/uscities \
 -H "Content-Type: application/json" \
 -H "Authorization: Bearer ${TOKEN}"
