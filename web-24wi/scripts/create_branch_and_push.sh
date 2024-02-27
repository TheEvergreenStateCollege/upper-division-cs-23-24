#!/bin/bash

# Prompt the user for a new branch name
read -p "Enter a name for your new branch: " branch_name

# Create the new branch
git checkout -b "$branch_name"

# Ask user if ready to push
read -p "Are you ready to push changes to main branch? (y/n): " ready_to_push

if [[ $ready_to_push == "y" ]]; then
    # Push changes to main branch
    git pull orign main
    sleep 3
    git push -u origin main
elif [[ $ready_to_push == "n" ]]; then
    # Display git status
    git status
else
    echo "Invalid input. Exiting."
    exit 1
fi

