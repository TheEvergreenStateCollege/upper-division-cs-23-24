#!/bin/bash

for ((i=1; i<=20; i++)); do
    netstat -nlp | grep tcp

    # Sleep for 3 seconds
    sleep 3
done
