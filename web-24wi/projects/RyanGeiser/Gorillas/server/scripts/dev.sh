#!/bin/sh
#Server wrapper script
# Development mode by default

# Turn on to help debug for now
# set -x

if [ -z "${NODE_ENV}" ]; then
  export NODE_ENV=development 
fi 

if [[ "$#" -lt "1" ]]; then
  echo "Usage: $0 <hostname>"
  exit 1
fi 

NODE_VERSION=$(node -v)
NODE_V=$(echo $NODE_VERSION | cut -d '.' -f 1)

echo $NODE_V
if [[ "$NODE_V" -ne "v20" ]]; then
  echo "Only Node v20 is supported, found v${NODE_V} instead."
  echo "Consider using Node Version Manager (NVM) for your operating system to install Node v20."
  exit 1
fi 

HOST=$1

prisma_and_nodemon() {
  pnpm i 
  PRISMA=$(which prisma)
  if [[ -z "${PRISMA}" ]]; then
    pnpm i -g prisma 
  fi
  prisma generate >> dev.log 2>&1 
  prisma migrate dev --name init
  nodemon server.js
}

# Start port forwarding for Postgres
ssh -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 -i ~/Downloads/web-infra.pem ubuntu@${HOST}
# . ~/.nvm/nvm.sh
# nvm use v20

prisma_and_nodemon

# If we receive a kill signal, cancel our port forwarding
ssh -O exit -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 -i ~/Downloads/web-infra.pem ubuntu@${HOST}
