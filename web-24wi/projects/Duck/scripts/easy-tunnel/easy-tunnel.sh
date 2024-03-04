#!/bin/bash
#
#This script is for the purpose of opening and holding open a ssh tunnel to forward your postgres db running on your  server to your local machine
#Usage:
#to use this script execute it with two command line arguments, the first of which is your server's domain name or IP, the second which is the path to your .pem file
#example:
# $sudo ./easy-tunnel.sh mysite.com /home/username/path-to-pem/pemfile.pem
#

serverloc=$1
pemloc=$2

on_exit(){
 ssh -O exit -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 -i ${pemloc} ubuntu@${serverloc}
 
  echo "closing tunnel!"
}

trap on_exit EXIT

ssh -o ControlMaster=auto -o ControlPath=/tmp/mysshcontrolpath -fNT -L 5432:localhost:5432 -i ${pemloc} ubuntu@${serverloc}


sek=0
echo "Tunnel is open...use ctl+c to kill"
while true
do
   echo -ne "---> $sek seconds have passed... \r"
   sleep 1
   sek=$[$sek+1]
done