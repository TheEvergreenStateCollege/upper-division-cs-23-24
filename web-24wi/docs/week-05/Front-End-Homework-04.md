# Web Front-End Homework 04
## Software Setup

If needed:
Continue working on getting access to FrontEndMasters via the GitHub Student Developer Pack, or setting up GitHub Codespaces / your personal laptop development environment.
  
[Web Engineering Setup](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/web-24wi/Setup.md)

## Develop Locally, Deploy Globally

For this assignment, and all future work, we recommend that you do all development work locally on your laptop, or on GitPod or GitHub Codespaces, and set up an SSH tunnel to your AWS server to access its database.

## Your Final Project

If you have a final project idea that you are excited about, create a project directory as follows with your team and start applying this assignment to it by adding login and database functionality to it.

```
cd <
```
[Final Project Details (work-in-progress)](../../FinalProject)

### Lap 1: Plain HTML / CSS / JS

Our goal in this assignment is to combine our existing knowledge from previous homeworks of
* HTML
* CSS
* JS
* the browser's `fetch` functionality
* an HTTP POST request to a backend API server
and we will now add to it a web `<form>` that lets us register a username and password with our backend server, which will let us login to *authenticate* ourself and *authorize* reading private data.

We will enhance our guessing game "WordMasters" to save words that we guessed correctly, as well as to create a leaderboard of high scores.
#### Readings and Watchings

Watch the following sections from the ["Web Authentication APIs video course ](https://frontendmasters.com/courses/web-auth-apis/)

* [Section 1: Introduction](https://frontendmasters.com/courses/web-auth-apis/introduction/)
* [Section 2: Web Authentication Strategies](https://frontendmasters.com/courses/web-auth-apis/http-auth-password-security/) 
* [Section 3: Classic Login Flow](https://frontendmasters.com/courses/web-auth-apis/enhancing-login-forms/)

You may refer to the [course notes website](https://firtman.github.io/authentication/lessons/classic-login/form-accessibility), however, we ask that keep it (or the video when you pause it) side-by-side with your text editor for typing, and that you do not copy and paste, as explained above.
#### Coding

Do this and all work on your local laptop, GitPod, or GitHub Codespaces (your *development* machine).
Do not work on your AWS server (your *production* machine, which is where we deploy our code).

Before you start working, make sure you are on `main` branch and you start from a clean working repository.

```
$ git status                                                                     On branch main
Your branch is up to date with 'origin/main'.

nothing to commit, working tree clean
```

Follow the git-branch-pull-request workflow that we have been learning in class to submit your work.

We recommend coding, and every 30 minutes to 1 hour, or after you finish a self-contained feature or pass a test, you commit and push.

```
git add .
git commit -m "<Describe your change to your future self>"

```

![[Pasted image 20240203214403.png]]