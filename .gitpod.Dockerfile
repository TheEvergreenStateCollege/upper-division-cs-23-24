FROM ubuntu:20.04

LABEL maintainer="ComputerClub++ <https://github.com/TheEvergreenStateCollege/ProjectHopper>"

USER root

RUN apt-get update --yes && \
    apt-get install --yes --no-install-recommends

RUN apt-get install -yqq wget
RUN apt-get install -yqq ca-certificates
RUN apt-get install -yqq ssh
RUN apt-get install -yqq git

RUN apt-get clean && rm -rf /var/lib/apt/lists/*

RUN mkdir ~/scripts
COPY ./scripts/dl-graalvm.sh /root/scripts/dl-graalvm.sh
COPY scripts/.shrc /root/.shrc

RUN ssh-keyscan github.com

# Download the right GraalVM for the given architecture
RUN . /root/scripts/dl-graalvm.sh

RUN . /root/.shrc; gu install nodejs

RUN mkdir ~/src
RUN cd ~/src; git clone https://github.com/TheEvergreenStateCollege/upper-division-cs

WORKDIR "${HOME}"