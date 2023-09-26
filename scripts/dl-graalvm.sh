#!/bin/sh

arch=$(uname -m)

BASE_URL="https://github.com/graalvm/graalvm-ce-builds/releases/download/jdk-20.0.2/"

if [ "${arch}" == "aarch64" ]; then
    export FILE="graalvm-community-jdk-20.0.2_linux-aarch64_bin.tar.gz";
else
    export FILE="graalvm-community-jdk-20.0.2_linux-x64_bin.tar.gz";
fi;

export URL="${BASE_URL}/${FILE}";
mkdir ~/Downloads; cd ~/Downloads;
wget $URL;
cd /opt; zcat ~/Downloads/$FILE | tar xf -