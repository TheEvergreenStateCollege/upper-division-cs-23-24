#!/bin/bash

# ANSI color codes for text decoration
RED='\033[0;31m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Run netstat, filter by port 5000, and extract the process ID
process_id=$(netstat -nlp | grep 5000 | awk '{split($7, a, "/"); print a[1]}')

# Function to restart the system process
restart_system_process() {
    echo ""
    echo "Restarting system process..."
    sudo systemctl restart flaskProd.service
    sleep 2
    sudo systemctl status flaskProd.service
}

# Check if the process ID is not empty
if [ -n "$process_id" ]; then
    # If process 5000 is running, stops it if it is
    sudo systemctl stop flaskProd.service
    echo -e "${RED}Stopping running flask service${NC}"
else
    echo "No process found for port 5000"
fi

# Prompt the user to choose an option to begin the interaction
echo "Press 1 to restart the system process."
echo "Press 2 to start the server interactively."
echo "Press 3 to exit."

# Read user input
read -n 1 option
echo ""

# Process user input
case $option in
    1)
        restart_system_process
        ;;
    2)
        echo "Starting server interactively..."
        cd /home/ubuntu/Workspace/upper-division-cs/web-24wi/projects/pswish/prod/driver_flask/
        python3 app.py

        # Interactive terminal option
	# Delayed function to prompt the user to restart the service when interactive server is killed
        
	sleep 1
        echo -e " ...${BLUE}Do you want to restart the system process? (y/n)${NC}"
        echo ""
        read -n 1 restart_option

        if [ "$restart_option" == "y" ]; then
            restart_system_process
        fi
        ;;
    3)
        echo "Exiting."
        exit 0
        ;;
    *)
        echo "Invalid option. Exiting."
        exit 1
        ;;
esac
