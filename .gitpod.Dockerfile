FROM ghcr.io/graalvm/graalvm-community:20-ol7

LABEL maintainer="ComputerClub++ <https://github.com/TheEvergreenStateCollege/ProjectHopper>"

# Fix: https://github.com/hadolint/hadolint/wiki/DL4006
# Fix: https://github.com/koalaman/shellcheck/wiki/SC3014
SHELL ["/bin/bash", "-o", "pipefail", "-c"]

USER root

RUN apt-get update --yes && \
    apt-get install --yes --no-install-recommends \
    # for cython: https://cython.readthedocs.io/en/latest/src/quickstart/install.html
    # for matplotlib anim
    # ffmpeg && \
    apt-get clean && rm -rf /var/lib/apt/lists/*

USER ${NB_UID}

RUN gu install nodejs

RUN mkdir ~/src
RUN cd ~/src; git clone https://github.com/TheEvergreenStateCollege/upper-division-cs

WORKDIR "${HOME}"
