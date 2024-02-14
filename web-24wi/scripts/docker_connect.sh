#!/bin/bash

echo "Opening interactive terminal postgresSQL server"
sleep 1

sudo docker exec -u postgres -it database1 psql

