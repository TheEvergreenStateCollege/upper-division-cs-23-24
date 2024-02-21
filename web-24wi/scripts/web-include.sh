#! /bin/sh
# The Evergreen State College - Upper Division CS 
# (c) 2024 The South Sound Upper Division Computer Science Learners' Union (tentatively named) 
#
# Winter 2024 - Student-Originated Software
# Common deploy between development and production workflows.
#
# Uncomment the line below to debug the script and see every command that is run, with variable substitutions.
# This is very verbose.
#
# set -x

NODE_VERSION=$(node -v)
NODE_V=$(echo $NODE_VERSION | cut -d '.' -f 1)

echo $NODE_V
if [[ "$NODE_V" -ne "v20" ]]; then
  echo "Only Node v20 is supported, found v${NODE_V} instead."
  echo "Consider using Node Version Manager (NVM) for your operating system to install Node v20."
  exit 1
fi 

# Extract script args 
SERVER_PATH=$1
SCRIPT_DIR=$2

cd ${SERVER_PATH}

# Validate the given path contains an Express server 
if ! [[ -f "./package.json" ]]; then 
  echo "No package.json found in ${SERVER_PATH}, please check again."
  exit 1
fi 
if ! [[ -f "./server.js" ]]; then 
  echo "No server.js found in ${SERVER_PATH}, please check again."
  exit 1
fi 
if ! [ -f "./.env" ]; then 
  if [[ "${NODE_ENV}" -eq "development" ]]; then 
    ln -s .env.development .env 
  else 
    ln -s .env.production .env 
  fi
fi 

prisma_and_nodemon() {
 
  export PATH=${PATH}:$(pnpm bin)
  pnpm i 
  PRISMA=$(which prisma)
  if [[ -z "${PRISMA}" ]]; then
    pnpm i -g prisma 
  fi
  prisma validate
  prisma generate >> dev.log 2>&1 
  prisma format
  # Pull the current database schema before we attempt to migrate any changes 
  prisma db pull
  prisma migrate dev --name init
  nodemon server.js
}

export prisma_and_nodemon
