#!/bin/sh

# mvn deploy:deploy-file  -Durl=file:///Users/cryptogoth/src/upper-division-cs/dsa-23au/java-dsa/repo  -Dfile=./target/arrays-links-1.0-SNAPSHOT.jar   -DgroupId=dev.codewithfriends   -DartifactId=arrays-links   -Dpackaging=jar   -Dversion=1.0
export REPO="~/.m2/repository"
mvn install:install-file  -Durl=file://${REPO}  -Dfile=./target/arrays-links-1.0-SNAPSHOT.jar   -DgroupId=dev.codewithfriends   -DartifactId=arrays-links   -Dpackaging=jar   -Dversion=1.0
