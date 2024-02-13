# AWS Elastic Compute Instance Setup
AWS, and any cloud provider, lets you instantiate a virtual private server,
which acts as your own dedicated machine connected to the public internet.
It works just as if you had a Linux (or other operating system) machine sitting on the desk in front of you, connected with a network cable to host your services.
On AWS, this virtual private server is called an Elastic Cloud Compute (EC2), and there is a free tier of a basic machine with modest resources that you can use without cost for the first 12 months after you sign up for an account.

We suggest you create a new AWS account with your Evergreen email address for the purpose of this class.

However, but with a web interface to remotely turn it on and off, reboot it, monitor its CPU and disk usage.

## Week 1: Initial Setup of Your Virtual Private Server 

Follow these steps below to create your first EC2 instance.
### 1. Go to the AWS Website
Click on "Create a new Account"

### 2. Search for EC2 Service
There are many services at AWS. You can search at the top for "EC2", or click the button which looks like a grid of 9 squares, choosing "EC2" from the list.

![[../../images/Pasted image 20240108041959.png]]
### 3. Launch a New Instance
Click the yellow "Launch Instance" button below.

![[../../images/Pasted image 20240108041757.png]]

### 4. Choose Ubuntu
In this class, we'll be using the Ubuntu distribution of Linux, version 22.04 with Long-Term Support (LTS). Name your instance something easy-to-remember. This is something only you and your team members see in the AWS console.

![[../../images/Pasted image 20240108042344.png]]

### 5. Read Your System Stats
Scroll down and accept the default of x86 64-bit achitecture.

There is an option to run an ARM processor, which has a Reduced Instruction Set Computer architecture that saves power. However, it has less software support, and you're likely to run into more problems later when you try to run software that hasn't been compiled for ARM yet. However, the ARM core is already widespread among all major smartphone processors, self-driving EV cars, and possibly among dedicated AI chips.

We will be using the instance type `t2.micro` which has 1 virtual CPU (more about this later, but it is likely 1 core of a multi-core server processor like an Intel Xeon with 12 or more physical cores). You also receive 1 GiB (1 billion bytes even). If you've bought a laptop recently or glanced at the system specs of the school computer you may be using, you know that 1 core and 1 GB of RAM is very modest. An average desktop would have 4 cores and 4 GB of RAM, and machines for gamers or heavy multimedia use can have 12+ cores or 8+ GB.

However, a `t2.micro` is more than enough for hosting the web demos in this class, for datasets of a few million datapoints. As the infrastructure engineer for your team, you will be characterizing the performance and capacity.

Don't worry about the costs listed for "on-demand" pricing based on different operating systems. These are the charges when you run out of your 750 free hours over the first 12 months of your account, and we'll cancel and close this account by the end of spring quarter.

![[../../images/Pasted image 20240108042603.png]]

### 6. Create a new KeyPair

PEM is a file format for storing the private key of a public-private (asymmetric) cryptographic keypair.

Choose "Create new key pair" and save this file ending in `.pem` to your local hard drive. AWS will lose this private key after you launch the instance, so this will be your only copy.

It's the main way you'll remotely login to this server, so don't lose it. It's appropriate to add it to your favorite password manager.

If you do lose it, you'll still be able to login into AWS console and switch to a different keypair for this instance, but it will take a few extra steps and can be avoided.

![[../../images/Pasted image 20240108045403.png]]
![[../../images/Pasted image 20240108050652.png]]
Choose the ED25519 cryptosystem, which is a more compact elliptic curve cryptosystem that guarantees equivalent security with a much shorter key length than the older RSA cryptosystem based on factoring large integers.

If you know that you will primarily be logging into this server from a Windows machine, choose `.ppk` as the format to use with the popular PuTTY terminal software on Windows.

Note where your keyfile is downloaded.
On a school computer, you can move this to your OneDrive directory so you can access it from home as well, instead of leaving it behind on that particular computer, and readable by anyone with Administrator permissions.
![[../../images/Pasted image 20240108050929.png]]
### 7.  Create a new security group


The security group is AWS's term for a list of firewall rules, that are primarily important for limiting which ports your EC2 instance will listen on, or accept incoming connections from clients.

Check the three checkboxes for the common services we will be using:
* Secure shell (SSH) for remotely logging in
* HTTP, for insecure web access (we'll need this at first to get a certificate)
* HTTPS, for secure web access with a TLS certificate

![[../../images/Pasted image 20240108045732.png]]

### 8. Choose 30 GB storage

Increase your storage to 30 GB, as 8 GB will run out too quickly, especially when installing web server software and even moderate-sized datasets.

![[../../images/Pasted image 20240108050326.png]]

### 9. Launch the Instance

Now you can click the orange "Launch instance" button on the bottom right.

![[../../images/Pasted image 20240108051304.png]]

### 10. Click to view 
![[../../images/Pasted image 20240108051409.png]]

### 11. Copy its public IPv4 address

## Week 2

Add dynamic DNS to map a subdomain to your server so that you can log into it,
and your users can find it, at the same hostname.

But at first, you will need to copy the public IPv4 from the AWS web console.

<img width="1231" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/160b2cef-3987-403b-9b13-2729a038e92e">

We will be using subdomains of `.arcology.builders` for now, but around week 5 or 6
you will be registering a domain of your choice by using a [GoDaddy offer from
a Major League Hacking event](https://ghw.mlh.io/challenges).

We will be following [these slides from class about REST APIs](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/web-24wi/docs/week-03/slides-rest.html).

### 0. Log into your AWS server by its IPv4 address

Let's say your server's public IPv4 address is 1.2.3.4

You would log into it, substituting the path to your private keypair file below, as
```
$ ssh -i <path/to/key.pem> ubuntu@1.2.3.4
```

### 1. Register the hostname once with curl

First, make sure the subdomain you want is available. We'll denote your chosen
subdomain below with `<YOUR_SUBDOMAIN>`.arcology.builders, and you should substitute it
with your chosen subdomain.

You don't want to clash with one of your classmates.

Use the username and password given in class. If you were not present that day,
ask for help from the instructor, TAs, or one of your classmates.

Substitute it into the command below. (Do not type the `<` and `>` symbols, they just
delimit the placeholder.)

```
$ curl -X GET --user <email@domain.com:password> https://mail.domain.com/admin/dns/custom/<YOUR_SUBDOMAIN>.arcology.builders 
```

You should get back an empty JSON object if the address is free.

```
[]
```

Now you'll change from a GET (which is a read-only command) to a PUT (which will overwrite any existing IPv4 address value that was bound to the given subdomain.

```
$ curl -X PUT --user <email@domain.com:password> https://mail.domain.com/admin/dns/custom/sub.arcology.builders
```


### 2. Create a cron job to run this command automatically on reboot

cron is a UNIX facility for scheduling jobs to run on a server,
either at regular intervals, or on a special system event.
In this case, we want to schedule the above command to run once when the
machine finishes rebooting.

Creating a file in your home directory called `.crontab`

```
$ cd ~
$ vi ~/.crontab
```

Type the contents of the following into it
```
@reboot curl -X PUT --user <email@domain:password> https://mail.arcology.builders/admin/dns/custom/<YOUR_SUBDOMAIN>.arcology.builders
```

Save the file and then run `crontab` to load this file.

```
$ sudo crontab ~/.crontab
```

Now, comes the moment of truth. Reboot your server and cross your fingers.

```
$ sudo reboot
```

After about one minute, try logging in again with your private key as at the beginning,
but now use the subdomain you registered.

```
$ ssh -i <path/to/key.pem> ubuntu@<YOUR_SUBDOMAIN>.arcology.builders
```

## Week 3

This week, our main task will be to install NodeJS development tools
that will help us build the main piece of backend infrastructure for our web applications, the *API server*.

We will also install Docker and a Postgres database for our APIs.

### 0. Log into your AWS server

Using your subdomain from above, if you're not already logged in.

### 1. Install tmux

`tmux`, the terminal multiplexer, will help you split up your terminal into panes and
windows, allowing you to run multiple processes at once. This is very useful for
infrastructure tasks, as you often start long-running server processes like the Python
HTTP server that occupy the shell, a text editor like `vim`, and potentially a few
other terminals that let you navigate around your server to inspect files or
run other commands.

```
$ sudo apt install -y tmux
```

### 2. Install Docker

Docker is a container engine for reproducible virtual environments. Similar to virtual
machines, that virtualize an entire computer including its hardware, a container only
virtualizes the operating system, but uses the same hardware as the host operating system.

We will use Docker to easily install and run the Postgres database, which is often a
complex package involving many configuration steps. Later on, we will Docker to deploy
our own web applications.

```
$ sudo apt install -y docker.io
```

Add the current user to the docker group.
```
$ sudo gpasswd -a $USER docker
Adding user ubuntu to group docker
```

Then log out and log back in. You can use the up arrow in your
shell history to repeat the login command.

This is to refresh what groups the shell thinks the current user is in.

When you've logged back in, run the `docker info` command to make sure your
docker installation is running correctly, and to get some information about it
your system.

```
$ docker info                                                                                                                                                          [8/561]
Client:
 Version:    24.0.5
 Context:    default
 Debug Mode: false

Server:
 Containers: 0
  Running: 0
  Paused: 0
  Stopped: 0
 Images: 0
 Server Version: 24.0.5
 Storage Driver: overlay2
  Backing Filesystem: extfs
  Supports d_type: true
  Using metacopy: false
  Native Overlay Diff: true
  userxattr: false
 Logging Driver: json-file
 Cgroup Driver: systemd
 Cgroup Version: 2
 Plugins:
  Volume: local
  Network: bridge host ipvlan macvlan null overlay
  Log: awslogs fluentd gcplogs gelf journald json-file local logentries splunk syslog
 Swarm: inactive
 Runtimes: io.containerd.runc.v2 runc
 Default Runtime: runc
 Init Binary: docker-init
 containerd version:
 runc version:
 init version:
 Security Options:
  apparmor
  seccomp
   Profile: builtin
  cgroupns
 Kernel Version: 6.2.0-1017-aws
 Operating System: Ubuntu 22.04.3 LTS
 OSType: linux
 Architecture: x86_64
 CPUs: 1
 Total Memory: 949.7MiB
 Name: ip-172-31-31-151
 ID: bf6009f7-a0d5-4bc3-acb4-46c16c6c25df
```
Like `git` and other UNIX commands that are the interface to a complex
distributed ecosystem, `docker` takes subcommands to perform different
functions. Below, we'll learn

```
docker pull - to download new images
docker run - to start a container from a downloaded image
docker exec - run a process on an already running container
docker kill - stop a running container
```

You'll learn more about Docker in a future week from
[FrontEnd Masters](), where we'll learn to do things like
run our own image registry to keep our code private and to
provide a useful service for the Evergreen community.

For now, you can think of a Docker image as like a program that you
download from the internet, and a Docker container as a running instance
when you start up the Docker image on your computer.

Docker conveniently checksums the images, and by default it pulls it from
a public registry called DockerHub (hub.docker.com).

You can run multiple Docker containers from the same image.

### 3. Install the Postgres database and start it

We are going to follow the Postgres installation instructions from the
[FrontEnd Masters Introduction to SQL and Postgres course](https://frontendmasters.com/courses/sql/)

```
$ docker pull postgres:14
14: Pulling from library/postgres
2f44b7a888fa: Pull complete
e2ab7b7507be: Pull complete
08eba411caa3: Pull complete
100bc654a5e4: Pull complete
79f317c402ed: Pull complete
da33959ecdf4: Pull complete
2ab35e615a89: Pull complete
037064cd96ec: Pull complete
70fa2242f609: Pull complete
4bec7ae19ef2: Pull complete
2c2443a45c46: Pull complete
326fd7e1c69e: Pull complete
2215c9c253b7: Pull complete
e636426454d9: Pull complete
Digest: sha256:ee0c3153cf67af84ac2a8d974cd5b01a6497b35209c175d8f94f7b8ad276d844
Status: Downloaded newer image for postgres:14
docker.io/library/postgres:14
```

You can see that the Postgres docker image contains 15 immutable filesystem layers,
each one built on top of the previous one, and checksummed to make sure it contains
the exact contents that the author intended when they built and uploaded the Docker
image in the first place.

### 4. Start up the Docker Container

Now that we have the image, let's start the container.
You'll get a different resulting hash from your container,
simply because you'll be running at a different time of day,
or other factors that go into hashing the contents of the container.

```
$ docker run -e POSTGRES_PASSWORD=lol --name=pg --rm -d -p 5432:5432 postgres:14
32627d6136f901690683ce1012dfbd90b20b99110a90a42d77af11880689487e
```

The container is named `pg`, which we'll use to refer to it later instead of the
long hash, which also uniquely identifies it on our system.

The container is running in the background (the `-d` above is to run a Docker
container in *daemon* or service mode), so it returns you to the shell without
tying it up. [This is similar to running a shell command with the `&` flag at
the end to background the process.]()

However, how can we access this container running in a background, and
add other processes to run alongside it, some of them interactive so that
they wait for our response?

We use the `docker exec` command 

```
$ docker exec -u postgres -it pg psql
psql (14.10 (Debian 14.10-1.pgdg120+1))
Type "help" for help.

postgres=#
```

You can type the `\d` Postgres command to show all existing tables. There won't be any to start with.


## Week 5

This week, we will build on our empty database by building our ORM (Object Relational Mapping) with Prisma. The ORM includes the following parts
* A schema, or model, of what the data looks like.
	* This is the `M` in the MVC paradigm, and are the column names in our CSV.
	* It's the fields, their names and types, that every row or datapoint or record in our dataset will have.
	* This will rule out a whole class of errors and messy data as soon as possible. The database, and the ORM, will help us by doing this "typechecking" every time we enter in new data, to make sure our dataset begins and stays in a valid state.
* A client
	* This is tailored to our database type (Postgres) and our programming language of choice (Javascript).
* Migrations
	* Starting with Ruby on Rails in the 2000s, web startups discovered that a key part of keeping consistent data and making reliable web apps was, counter-intuitively, keeping track of all changes to the schema over time.
	* This may seem weird, 