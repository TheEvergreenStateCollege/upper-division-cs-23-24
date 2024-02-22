#! /bin/sh
# The Evergreen State College - Upper Division CS 
# (c) 2024 The South Sound Upper Division Computer Science Learners' Union (tentatively named) 
#
# Winter 2024 - Student-Originated Software
# Web Infra Checklist and Server wrapper script
# This is meant to be run on your personal laptop / GitPod workspace / GitHub Codespace (your Local Dev Environment)
# and bridge to your AWS server. 
#
# Usage: web-dev.sh <path_to_server> <hostname>
#
# <path_to_server> is a relative or absolute path to your Express API / UI Server, containing server.js and package.json 
#   Ex: If you are in <repo_dir>/web-24wi and your assignments directory is `ppham` then your
#   <path_to_server> is ./assignments/ppham/infra/api-server 
# <hostname> is the subdomain of your AWS server, that you registered as part of Week 2 Infra class activities 
#   Ex: sub.arcology.builders , however you log into your server with ssh -i <path/to/key.pem> ubuntu@<hostname>
#
# For the examples above, a user would change to `<repo_dir>/web-24wi` and run on their Local Dev Environment:
# ./web-dev.sh ./assignments/ppham/infra/api-server sub.arcology.builders 

# The script will set up an SSH tunnel to the AWS server, run any necessary Prisma DB migrations and verify schema, 
# and then start a server that will listen on http://localhost:<port>
# which is TCP 5000 by default, and may bump to 5001 or higher if you are on Mac OSX because of the Control Center service.

# In the near future, we will add a checklist script that will test the user's AWS server, give them immediate feedback
# on any discrepancies or TODO's remaining to create a recommended server for the class, give them links to documentation 
# on the wiki to read in order to catch up, and also send feedback to 
# a class server so teaching staff know the overall and particular progress of the class.
#
# Uncomment the line below to debug the script and see every command that is run, with variable substitutions.
# This is very verbose.
#
# set -x

# Production mode 
export NODE_ENV=production

if [[ "$#" -lt "2" ]]; then
  echo "Usage: $0 <path_to_server> <hostname>"
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

# Extract script args 
SERVER_PATH=$1
HOST=$2

cd ${SERVER_PATH}

# Validate the given path contains an Express server 
if ! [[ -e "./package.json" ]]; then 
  echo "No package.json found in ${SERVER_PATH}, please check again."
  exit 1
fi 
if ! [[ -e "./server.js" ]]; then 
  echo "No server.js found in ${SERVER_PATH}, please check again."
  exit 1
fi 
if ! [[ -e "./.env" ]]; then 
  if [[ "${NODE_ENV}" -eq "development" ]]; then 

fi 

prisma_and_nodemon() {
  
  pnpm i 
  PRISMA=$(which prisma)
  if [[ -z "${PRISMA}" ]]; then
    pnpm i -g prisma 
  fi
  prisma generate >> prod.log 2>&1 
  prisma migrate prod --name init
  nodemon server.js
}

prisma_and_nodemon
