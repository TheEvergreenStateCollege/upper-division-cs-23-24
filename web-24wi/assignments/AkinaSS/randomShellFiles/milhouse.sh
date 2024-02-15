#!/bin/bash
sek=0
echo "Milhouses incoming!"
while true
do
   echo -ne "---> $sek Milhouses have occured... \r"
   sleep 1
   sek=$[$sek+1]
done
