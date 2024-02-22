#! /bin/bash

# commands to spit out status of import web server services
echo '__ Npm service for api server (not currently in use)'
sudo systemctl status pswish-corp-npm.service | grep -e "Active"

sleep 2
echo '__ Flask server for current build and hosting of dashboard'
sudo systemctl status flaskProd.service | grep -e "Active"

sleep 2
echo '__ Current running dns registration to renew ip associaion with arcology builders'
sudo systemctl status dynamic-dns.service | grep -e "Active"

sleep 2
echo '__ Current ssl ceritfication engine'
sudo systemctl status nginx.service | grep -e "Active"

sleep 2

echo '*****************************************' 
echo  Port checker, This runs for a while, control-c to stop
echo '*****************************************'

source /home/ubuntu/Workspace/upper-division-cs/web-24wi/scripts/netstat-check.sh | grep -e 5000 -e 5432
