#/usr/bin/env bash 


TOKEN=$(cat .env | grep "TOKEN" | cut -d "'" -f 2)

if [ -e "./id_ecdsa.pub" ]; then 
  echo "You've already retrieved a public key."
else
  curl -s -X GET http://localhost:5000/api/ssh-key \
    -H "Content-Type: application/json" \
    -H "Authorization: Bearer ${TOKEN}" \
    -o id_ecdsa.pub
fi

# Commands to test your API

# Get an SSH keypair after logging in 
ssh -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 ubuntu@tor.arcology.builders 

pnpm i

pnpm i -g ts-node

node index.js