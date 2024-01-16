# GitHub Codespaces for Web Engineering

Note: You only need to follow these instructions if you
wish to develop in your web browser, on a school or other
public computer. You can adapt these instructions starting
with Step 3 to your laptop or other private machine,
using your own favorite terminal, IDE like VS Code, etc.

GitHub Codespaces is a hosted cloud development environment.
Similar to Gitpod and VSCode.dev, it is the VS Code C++ desktop application
that is compiled to WebAssembly and meant to run with native performance
in the web browser.

There is nothing to install since it runs in a web browser.
These steps will help you get started on engineering web applications
using GitHub Codespaces and submitting them to the class.

## 0. View Your Usage

[Free personal GitHub accounts get 120 core hours and 15 GB each month](https://docs.github.com/en/billing/managing-billing-for-github-codespaces/about-billing-for-github-codespaces#monthly-included-storage-and-core-hours-for-personal-accounts)

You can view how much you've used here
* https://github.com/settings/billing/summary#usage

You can also view already running codespaces, and can manage and delete them by clicking
the appropriate tab from the green "Code" button.

<img width="948" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/f90b9cdd-34a3-4be3-8d2c-d92b74a75a55">

By clicking the three dots button, you can stop a codespace when you don't need it anymore,
or delete it or rename it.

<img width="375" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/d27bccc0-a3c7-48fe-b68f-e6d030680290">

If you see a running codespace that you want to use, you can click "Open in browser" from
the three dots menu and skip the next step (Step 1).

## 1. Enter a Codespace

Go to the class monorepo:
* https://github.com/TheEvergreenStateCollege/upper-division-cs

Press the `.`

If you don't already have a running codespace, it will build a container image
and launch it. This technology is similar to Docker, which we will learn and use later
in Web Infrastructure.

You may see a screen saying "Setting up codespace" with updating logs
building the initial image. This may take one or two minutes the first time,
but will open instantly afterwards.

## 2. Waiting For Codespace To Start Up

If your codespace has been built but was stopped, you may see this for
30 seconds or so while it starts up again.

<img width="1337" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/6a6ae736-b7a6-496a-b18e-f74dd5f03ea5">

If you're familiar with virtual machines, this process is similar to
loading a snapshot of a virtual machine on disk / SSD into a running image
that you can interact with again in memory and CPUs.

## 3. Learn About Your Environment

Starting from this point in the instructions, you are essentially
using a Linux machine, just like in GitPod, or on your laptop, but with a
connected, high-performance VS Code editor. (This last part is not easy to
achieve with AWS free tier machines).

It may even be much more powerful than GitPod or your laptop
(and is definitely more powerful than AWS free tier machines) depending
on the specs you chose when you launched this workspace.

For example, the base machine of 2 cores, 8 GB RAM is a modest machine,
and some consider the bare minimum for modern Web development, especially
using a hot-reloading bundler and framework like Vite+React, which you
may use in Lap 2 of this class now or in the future.

If you are new to command-line development,
now is a good time to review the *current working directory*.
It is shown in the shell prompt:

```
@learner-long-life ➜ /workspaces/upper-division-cs (main) $ pwd
/workspaces/upper-division-cs
```

and you can also run the `pwd` command at anytime.

You can list what directories are available with the `ls` command.

```
@learner-long-life ➜ /workspaces/upper-division-cs (main) $ ls
Collaboration.md  LICENSE  README.md  config  docs  dsa-23au  my-app  notes  scos-24sp  scripts  staff  web-24wi
```

And you can change to a subdirectory by using the `cd` command.

You can create a new directory, and any number of subdirectories, if it doesn't exist,
by using the `mkdir -p` command.

In the command-below, we're going to create a new directory for your assignments.
That's because during Weeks 1-5, you will primarily be working on individual assignments
to build up skills. In Weeks 6-10, you'll go through a similar process in the `projects`
directory.

Replace the `<your_github_username>` with your github username. Pay special attention
to the casing, as UNIX paths are case-sensitive. Avoid spaces, but you can use underscores
or hyphens to separate words.

```
@learner-long-life ➜ /workspaces/upper-division-cs (main) $ cd web-24wi/assignments/
@learner-long-life ➜ /workspaces/upper-division-cs/web-24wi/assignments (main) $ mkdir -p <your_github_username>
@learner-long-life ➜ /workspaces/upper-division-cs/web-24wi/assignments (main) $ ls
AkinaSS  DawsonWhi  Zachsrob  aquinnallen  faulkdf  harleehair  jeff_cook_w24  nathanMcL  ndeanon25  poperigby  ppham
```

As you change directories and list files, try to identify in the left-hand file explorer
where you are. There is a one-to-one correspondence between directories and files listed
in the left-hand file explorer, and the directories and files you can access on the
command-line. At any point in time, you are only directly in one directory.

<img width="294" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/9dc54526-cba3-4a34-a920-8f3705eae307">

You can also use the `tree` command to triangulate and get your orientation of which
directory you're in, and how it relates to other directories around it.

```
@learner-long-life ➜ /workspaces/upper-division-cs/web-24wi/assignments (main) $ tree
.
├── AkinaSS
│   ├── frontend
│   │   └── week1
│   │       └── index.html
│   └── infra
│       └── week1
│           └── bashHistory.txt
├── DawsonWhi
│   └── frontend
│       └── week1
│           └── index.html
├── Zachsrob
│   └── upper-division-cs
│       └── web-24wi
│           └── assignments
│               └── Zachsrob
│                   └── infra
│                       └── history.txt
├── aquinnallen
│   └── touch.touch
├── faulkdf
│   ├── README.md
│   └── frontend
│       └── week1
│           ├── about.html
│           └── index.html
├── harleehair
│   └── frontend
│       └── week1
│           └── week1.html
├── jeff_cook_w24
│   └── readme.md
├── nathanMcL
│   ├── READme.md
│   └── StudentOriginatedSoftware
│       ├── READme.md
│       └── myBlog
│           ├── NMac's_blog.css
│           ├── index.html
│           └── myBlogResources
│               └── kurokuro.png
├── ndeanon25
│   └── frontend
│       └── week1
│           └── ndeanon25-week1
├── poperigby
│   ├── infra
│   └── rust
│       └── web-server
│           ├── 404.html
│           ├── Cargo.lock
│           ├── Cargo.toml
│           ├── hello.html
│           └── src
│               ├── lib.rs
│               └── main.rs
└── ppham
    └── styles.css
```

Create a directory in your assignments directory called `week1` or Week 01 assignments,
`week2` for the second week assignments, and so on.

 
