FROM ubuntu:20.04
ENV SHELL=/usr/bin/bash

LABEL maintainer="Upper Division CS <https://github.com/TheEvergreenStateCollege/upper-division-cs>"

USER root

# Install keys for yggdrasil to access machines at Evergreen on different subnets
# via IPv6 tunneling
# From https://yggdrasil-network.github.io/installation-linux-deb.html
RUN mkdir -p /usr/local/apt-keys

RUN apt-get update --yes && \
    apt-get install --yes --no-install-recommends
RUN apt-get install -yqq gpg

RUN mkdir /keys
ADD ./config/yggdrasil-key.txt /keys/yggdrasil-key.txt
RUN gpg --import /keys/yggdrasil-key.txt
# Fetching doesn't work when building the image in GitPod for some reason
# RUN gpg --fetch-keys https://neilalexander.s3.dualstack.eu-west-2.amazonaws.com/deb/key.txt
RUN gpg --export BC1BF63BD10B8F1A | tee /usr/local/apt-keys/yggdrasil-keyring.gpg > /dev/null

RUN echo 'deb [signed-by=/usr/local/apt-keys/yggdrasil-keyring.gpg] http://neilalexander.s3.dualstack.eu-west-2.amazonaws.com/deb/ debian yggdrasil' | tee /etc/apt/sources.list.d/yggdrasil.list

RUN apt-get update --yes && \
    apt-get install --yes --no-install-recommends

RUN apt-get install -yqq wget
RUN apt-get install -yqq ca-certificates
RUN apt-get install -yqq ssh
RUN apt-get install -yqq git
RUN apt-get install -yqq sudo
RUN apt-get install -yqq unzip
RUN apt-get install -yqq gcc
RUN apt-get install -yqq zlib1g-dev
RUN apt-get install -yqq htop
RUN apt-get install -yqq asciinema
RUN apt-get install -yqq python3-pip
RUN apt-get install -yqq curl
RUN apt-get install -yqq tcpdump
RUN apt-get install -yqq netcat
RUN apt-get install -yqq telnet
RUN apt-get install -yqq net-tools
RUN apt-get install -yqq nodejs
RUN apt-get install -yqq npm

# For pdf2text
RUN apt-get install -yqq poppler-utils
RUN apt-get install -yqq pkg-config

# For AI assignments
RUN pip3 install html2text
RUN pip3 install tiktoken
RUN pip3 install torch
RUN pip3 install numpy

ENV PATH=${PATH}:/home/gitpod/.local/bin
# add gitpod user
RUN useradd -l -u 33333 -G sudo -md /home/gitpod -s /bin/bash -p gitpod gitpod
USER gitpod
WORKDIR /home/gitpod

# install rust toolchain
RUN curl https://sh.rustup.rs -sSf >> rustup.sh
RUN chmod 700 rustup.sh
RUN ./rustup.sh --default-toolchain stable -y
RUN rm rustup.sh
# WASM assignments in Software Construction
ENV PATH=${PATH}:/home/gitpod/.cargo/bin
RUN cargo install wasm-pack
RUN cargo install cargo-generate

# install node version manager
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.7/install.sh | bash
# We use Node v14 which is quite old for cargo-generate to work 
# It would be better to find an alternative to cargo-generate for rustwasm game-of-life 
RUN /bin/bash -c ". ${HOME}/.nvm/nvm.sh && nvm install v14"

USER root

# install Rustlings
COPY scripts/install-rustlings.sh scripts/install-rustlings.sh
RUN ./scripts/install-rustlings.sh

RUN apt-get clean && rm -rf /var/lib/apt/lists/*

RUN mkdir ~/scripts
COPY ./scripts/.shrc /home/gitpod/.shrc

RUN ssh-keyscan github.com

WORKDIR "${HOME}"
