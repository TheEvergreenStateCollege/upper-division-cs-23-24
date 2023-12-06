#!/bin/sh

# Compile Java programs
for d in java-dsa/*; 
do 
    echo "Building Java project in ${d}"
    if [ ! -d "${d}" ]; then
	    continue
    fi 
    cd $d;
    if [ -f "./build.sh" ]; then
        ./build.sh
    fi
    cd ../..
done
