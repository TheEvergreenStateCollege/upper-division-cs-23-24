#!/bin/sh

# Can systemd running as root call ubuntu user's NVM 
export NVM_DIR="/home/ubuntu/.nvm"
[ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"
[ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"

/home/ubuntu/.nvm/versions/node/v20.11.0/bin/nodemon server.js
