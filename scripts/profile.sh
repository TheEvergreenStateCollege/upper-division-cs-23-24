#!/bin/sh

java -javaagent:ap-loader.jar=start,event=cpu,file=profile.html $@