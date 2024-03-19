#/usr/bin/env bash 

if [ -z "$(which autossh)" ]; then 
  sudo apt update
  sudo apt install -y autossh jq
fi

TOKEN=$(cat .env | grep "TOKEN" | cut -d "'" -f 2)

if [ -e "./.env" ]; then 
  echo "You've already renamed your .env file."
else
  echo "Renaming your .env file, be sure to change DATABASE_URL accordingly."
  mv .env.RENAME_ME .env
fi

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
ssh  -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 127.0.0.1:5431:localhost:5432 -i ./id_ecdsa ubuntu@rilesbe.arcology.builders

pnpm setup
source ~/.bashrc
pnpm i -g ts-node
pnpm i

ts-node src/index.ts

sudo apt install telnet