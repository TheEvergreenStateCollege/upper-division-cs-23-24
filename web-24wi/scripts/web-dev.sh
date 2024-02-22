#! /usr/bin/env bash
#
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
set -x

# From https://stackoverflow.com/a/246128
# An explanation from Google Bard https://g.co/gemini/share/085bc9bc961d
SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
# We get the absolute path of this script so we can include sibling include script below.
#
# Development mode by default
if [ -z "${NODE_ENV}" ]; then
  export NODE_ENV=development 
fi 

if [[ "$#" -lt "2" ]]; then
  echo "Usage: $0 <path_to_server> <hostname>"
  exit 1
fi 

# Common definitions from sibling include script
# Pass the relative path to the server and absolute path to scripts
source ${SCRIPT_DIR}/web-include.sh $1 $SCRIPT_DIR

HOST=$2 

# Start port forwarding for Postgres
ssh -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 ubuntu@${HOST}
echo "...SSH tunnel to ${HOST}:5432 established"
# . ~/.nvm/nvm.sh
# nvm use v20

prisma_and_nodemon

# If we receive a kill signal, cancel our port forwarding
ssh -O exit -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 ubuntu@${HOST}
echo "...SSH tunnel to ${HOST}:5432 exited"
