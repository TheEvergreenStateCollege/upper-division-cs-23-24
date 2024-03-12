<h2>Easy Tunnel, a persistent tunnel solution</h2>

In response to the ample trouble we seemed to have today with the ssh tunnel script I have created a more simple solution.
To use this script you will need to make it executable with ```chmod 777 easy-tunnel.sh```
This script takes two command line arguments, for your server's domain name and the path to your pem file.

You can execute it as follows:
```
sudo ./easy-tunnel.sh mysite.com /home/username/path-to-pem/pemfile.pem
```

Replace the mysite.com and /home/.../pemfile.pem portions with the correct info for your site.
This script keeps the tunnel open until you terminate the script (ctrl-c) and will use an entire terminal window.
Although this may seem annoying or undesirable, it is easy to use an additional terminal window or use tmux to cut a small window out for it to run in.

As an alternative to this script you can just use the ssh command itself to open the tunnel:
```
ssh -fNT -L 5432:localhost:5432 -i {PATH_TO_PEM} ubuntu@{SEVER_LOCATION}
```
If you do this, you will need to manually kill the process.

To do this you can use this command:
```
sudo pkill $(pgrep ssh)
```
Caution -> this will kill all running ssh processes

Unlike the script which paul presented this script does not run any automations (see his web-include.sh script) and just opens the tunnel, which seemd to be a reasonable portion of the overall confusion.

This is handy because when you are trying to develop from your laptop or a remote machine, all you really need is the server port exposed on localhost.
