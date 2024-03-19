#!/bin/bash

# Add all changes, prompt user for commit message, and commit
git add -A
read -p "Enter a commit message: " commit_message
git commit -m "$commit_message"

# Prompt the user for a new branch name
read -p "Enter a name for your new branch: " branch_name

# Create the new branch
git checkout -b "$branch_name"

# Ask user if ready to push
read -p "Are you ready to push changes to main branch? (y/n): " ready_to_push

if [[ $ready_to_push == "y" ]]; then
    # Pull changes from main branch
    git pull origin main || true

    # Echo the push command for the user to run
    echo "Run the following command to push changes: git push -u origin main"
    
    # Monitor commands for errors and halt if anything goes wrong
    if [ $? -ne 0 ]; then
        echo "Error occurred during pull or push. Exiting."
        exit 1
    fi
elif [[ $ready_to_push == "n" ]]; then
    # Display git status
    git status
else
    echo "Invalid input. Exiting."
    exit 1
fi
