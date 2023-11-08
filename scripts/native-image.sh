#!/bin/sh

native-image --native-image-info --enable-monitoring=jfr,heapdump -jar $1