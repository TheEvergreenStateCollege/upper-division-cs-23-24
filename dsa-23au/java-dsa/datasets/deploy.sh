#!/bin/sh

mvn deploy:deploy-file  -Durl=file:///Users/cryptogoth/src/upper-division-cs/dsa-23au/java-dsa/repo  -Dfile=./target/datasets-1.0-SNAPSHOT.jar   -DgroupId=dev.codewithfriends   -DartifactId=datasets   -Dpackaging=jar   -Dversion=1.0

