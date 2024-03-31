#!/usr/bin/env /bin/sh

mkdir ./rustlings-tmp
cd ./rustlings-tmp
curl -L https://raw.githubusercontent.com/rust-lang/rustlings/main/install.sh >> rustlings.sh
chmod 700 rustlings.sh
./rustlings.sh # clones rustlings repo in order to build rustlings binary, we delete it below
rm rustlings.sh
cd ..
rm -Rf ./rustlings-tmp