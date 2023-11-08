#!/bin/sh

# mvn deploy:deploy-file  -Durl=file:///Users/cryptogoth/src/upper-division-cs/dsa-23au/java-dsa/repo  -Dfile=./target/datasets-1.0-SNAPSHOT.jar   -DgroupId=dev.codewithfriends   -DartifactId=datasets   -Dpackaging=jar   -Dversion=1.0

export REPO="~/.m2/repository"
# mvn deploy:deploy-file  -Durl=file://${REPO}  -Dfile=./target/datasets-1.0-SNAPSHOT.jar   -DgroupId=dev.codewithfriends   -DartifactId=datasets   -Dpackaging=jar   -Dversion=1.0
mvn install:install-file  -Durl=file://${REPO}  -Dfile=./target/datasets-1.0-SNAPSHOT.jar   -DgroupId=dev.codewithfriends   -DartifactId=datasets   -Dpackaging=jar   -Dversion=1.0

