#!/bin/bash

export NVM_DIR="$HOME/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"

# Port for the web server
PORT=5000

# Check if the port is in use
if ! lsof -i :$PORT > /dev/null
then
  echo "Port $PORT is not in use. Starting the application..."
  cd ~/src/upper-division-cs/web-24wi/assignments/ppham/infra/api-server
  git checkout main
  git pull
  /home/ubuntu/.nvm/versions/node/v20.11.0/bin/node server.js
else
  echo "Port $PORT is already in use. Nothing to do. If this is a zombie process, run>>> . ~/port-checker.sh"
fi
