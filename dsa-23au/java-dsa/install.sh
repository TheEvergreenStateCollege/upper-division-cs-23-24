#!/bin/sh

# mvn deploy:deploy-file  -Durl=file:///Users/cryptogoth/src/upper-division-cs/dsa-23au/java-dsa/repo  -Dfile=./target/arrays-links-1.0-SNAPSHOT.jar   -DgroupId=dev.codewithfriends   -DartifactId=arrays-links   -Dpackaging=jar   -Dversion=1.0
export REPO="$HOME/.m2/repository"

BASE=$(pwd)
export BASE

for project in *
do 
  if [ ! -d $project ]; then
    continue;
  fi
  cd $BASE/$project || exit "No directory $BASE/$project"
  if [ -e build.sh ]; then 
    ./build.sh
  fi
  if [ -e install.sh ]; then 
    ./install.sh
  fi
done
