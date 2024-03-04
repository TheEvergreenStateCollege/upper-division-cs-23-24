#!/bin/bash

while true
do
curl -X GET http://sub.arcology.builders:5000/search-hit/1 >> echo
curl -X GET http://sub.arcology.builders:5000/search-hit/2 >> echo
curl -X GET http://sub.arcology.builders:5000/search-hit/3 >> echo

done

