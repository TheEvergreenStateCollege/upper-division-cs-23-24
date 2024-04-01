#!/usr/bin/env bash

i=0

for i in {01..15}; do
	echo $i
	git submodule add "https://github.com/TheEvergreenStateCollege/game-${i}"
done

#while [ "${i}" -lt 16 ]; do
#	echo $i
#	i=$((i + 1));
#	git submodule add "git@github.com:TheEvergreenStateCollege/game-${i}.git"
#done
