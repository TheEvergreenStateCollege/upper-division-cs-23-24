#!/bin/sh

# Compile into JAR file without running tests

mvn -Dmaven.test.skip=true package