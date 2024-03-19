#!/bin/bash

# Run netstat, filter by port 5000, and extract the process ID
process_id=$(netstat -nlp | grep 5000 | awk '{split($7, a, "/"); print a[1]}')

# Check if the process ID is not empty
if [ -n "$process_id" ]; then
    # Kill the process using the extracted process ID
    echo "Killing process with ID: $process_id"
    kill -9 "$process_id"
else
    echo "No process found for port 5000"
fi

sleep 2
cd /home/ubuntu/Workspace/upper-division-cs/web-24wi/projects/pswish/api-server
npm run dev
