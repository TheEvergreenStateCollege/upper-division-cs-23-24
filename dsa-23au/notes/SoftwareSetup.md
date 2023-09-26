# Software Setup

You will only need to go through this Software Setup once at the beginning of
Data Structures & Algorithms. However, if you have never used GitHub and GitPod
before, or if you need a refresher, please budget plenty of time to complete these
steps. You'll need them to complete Assignment 1 in the first week,
as well as all the other assignments.

As you read through, be on the lookout for mistakes, discrepancies, bugs, or anything
that doesn't work out the way you expect. Congrats, you've found an issue that 
you can use to complete Step 9 of the Cloud IDE setup below. 

## GitHub

You'll need to sign up for a GitHub account.

### 1. Go to GitHub

In your browser, enter the url https://github.com

Click on "Register" to create a new account,
or "Login" if you already have an account.

Verify with your phone number.

### 2. Use the class template to create a new private repo.

Go to your main github profile page, replacing `<user-name>`
with your GitHub username.

https://github.com/<user-name>

Click on the green "New" button, and under the `Use template` drop-down menu,
paste this class's template

`https://github.com/TheEvergreenStateCollege/upper-division-cs`

After a few seconds, you'll have your own "template copy" of this repo in your
own profile, which you can view here.

`https://github.com/<user-name>/upper-division-cs`

### 3. Go to your GitHub settings

In your GitHub email settings:
https://github.com/settings/emails

and copy your private, "no-reply" email address that GitHub generated for you,
to avoid leaking your real email address to the public.

It will look similar to mine, which is
`148553+cryptogoth@users.noreply.github.com`

### 4. Create a configuration script

You'll run this script the first time you start up GitPod using this repository (below).

Create the file in this location with this name, which you can do right from your
repo's GitHub page from Step 3 by clicking the "Add file" and "New file" from the
drop-down button.

`https://github.com/<user-name>/upper-division-cs`

Add the following path, notificing that the directories `dsa-23au` (meaning Data Structures & Algorithms, Autumn 2023)
and `scripts` already exist, and you are simply creating a new file called `git-config.sh`

```
./dsa-23au/scripts/git-config.sh`
```

In this file, paste the no-reply email address that you copied previous in Step 4.
Then click "Commit changes" so that this email address is saved for future use.

Don't worry, this is not a real email address, and this is a private repo, so you are not revealing anything
unsafe by saving it here.

### 5. Finish the script

Add the following lines to your newly , and modify it to fit around the no-reply email address that you pasted in Step 5.

```
#!/bin/sh

# Enable us to push, through email privacy features
git config --global user.email <no-reply-email-address>
```

## Cloud IDE 

We will be using GitPod, a online integrated development environment (IDE) that can be
accessed from any web browser, allowing you to easily work on lab computers or on your
home machines.

### 1. Go to GitPod

In your browser, enter the URL https://gitpod.io

This is the first URL you'll enter into a web browser
each time you sit down at a computer to work on this class.

If you've chosen to download and install Desktop IDEs like VSCode or IntelliJ
to your laptop, with the appropriate Gitpod plugins, you can open those IDEs
directly.

Choose to login via your GitHub account, which you've created or logged into
as part of the previous section.

### 2. Link Your LinkedIn Account

There is free plan allowing up to 50 hours per month of use if you create and link
your [LinkedIn](https://linkedin.com) account. You may wish to do this to connect
with alumni, recruiters, or professionals in the field or specialty that you are
interested in working in.

<img width="647" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/a88ddaef-3e6b-4144-b3ef-77db384c63ca">

### 3. Answer the Survey 

Answer some preliminary questions about our use of Gitpod for this class.
You can adjust your answers to suit your preferences, it won't affect your development experience.

<img width="625" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/9dbdf8bb-0bc8-4cba-8543-25af7edea404">

### 4. Open a Workspace

Since this is probably your first time using GitPod for this class,
click on "New workspace" and copy and paste the private repo you created above.

### 5. Open GitPod in Your Browser

Choose "Open in Browser". This will open up VSCode in your browser.

<img width="498" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/3caf24e4-7478-4438-ada7-24114b3d1efe">

<img width="900" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/e78511b9-f307-4af5-bbc8-4e5908963583">

### 6. Enable Write Permissions

Enable permissions in Gitpod's integrations for Github / Bitbucket / Gitlab by navigating to this URL
https://gitpod.io/user/integrations

Next to the "GitHub" integration, click on the three dots and choose "Manager this on github.com"

<img width="1103" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/6e2b9019-00b3-4b5f-bd4e-4073f28b6551">

<img width="703" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/dfe3093a-93c5-4441-9ec3-00798bb29dfb">

<img width="655" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/b7f844af-3fc0-404c-b663-14c5f5fed97f">

### 7. Set your Git Config Email

Run the `git-config.sh` script you set in the `GitHub` section above.

### 8. Source environment variables

```
source ~/scripts/.shrc
```

### 9. Update this Document

Test that everything works by making a change to these instructions, adding
any corrections, insights, jokes, comments, or memes that you think will help future students.

If you are looking for a beginner-friendly change to make,
check out [any open Issues for this GitHub repository](https://github.com/TheEvergreenStateCollege/upper-division-cs/issues).

The file is located at

```
./dsa-23au/SoftwareSetup.md
```

Right-click the tab in GitPod and choose "Open Preview"

All documentation in this class is written using Markdown, a simplified formatting language that is
meant to be plaintext and human-readable.

You can read more about [using Markdown here](https://docs.github.com/en/get-started/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax).

### 10. Add, Commit, Push

After making your changes, you will perform the `version control dance` of hackers everywhere.

First you will *stage your changes for commit*. This helps separate changes that are ready
and others which you may still be working on. In our case, we want to stage our changes to
`SoftwareSetup.md`, since we want others to receive changes and corrections,
but we will ignore our changes to `git-config.sh`, since it contains a no-reply email address
that is specific to just us.

```
git add SoftwareSetup.md
```

Next, you will *check the status* to see that only the files you meant to stage are going to be committed.
You will probably see two files to change.

```
git status
```

Then you'll commit the file that is staged.
```
git commit
```

This will bring up a temporary text editor pane in VSCode where you can type a brief,
typically one-sentence message summarizing the changes you made.

```
git push
```

## TODO

Here is a list where we'll collect beginner-friendly improvements that anyone can make.
If you're looking for a change to make, take one from here
There are a few things we'd like to run in every GitPod session we start up for this repository.

One is to add the GraalVM Java Development Kit to our shell's `PATH` environment variable.

Step 8 above, sourcing environment variables in `.shrc` should be done automatically when
starting a GitPod workspace for this repo.

I think this feature is enabled in the `.gitpod.yml` file.