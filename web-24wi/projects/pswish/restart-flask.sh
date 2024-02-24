#!/bin/bash

# Run netstat, filter by port 5000, and extract the process ID
process_id=$(netstat -nlp | grep 5000 | awk '{split($7, a, "/"); print a[1]}')

# Check if the process ID is not empty
if [ -n "$process_id" ]; then
    # If process 5000 is running
    sudo systemctl stop flaskProd.service
else
    echo "No process found for port 5000, starting server interactively"
fi

sleep 2
cd /home/ubuntu/Workspace/upper-division-cs/web-24wi/projects/pswish/prod/driver_flask/
python3 app.py
