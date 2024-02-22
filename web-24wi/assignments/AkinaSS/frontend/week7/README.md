# GitGraph Practice

These are the visualization of our the git branch

```mermaid
gitGraph
    commit id:"initial"
    commit id:"second commit"
    branch first_student
    branch second_student
    branch other_student
    checkout first_student
    commit id:"Submit things"
    checkout second_student
    commit id:"submit other things"
    checkout other_student
    commit id:"submit more other thing"
    checkout first_student
    commit id:"push"
    commit id:"first create pull request and merge"
    checkout second_student
    commit id:"submit"
    commit id:"second create pull request and merge"
    checkout main
    merge first_student tag:"first student push" type:HIGHLIGHT
    merge second_student tag:"second student push" type:HIGHLIGHT
    commit id:"merge"
```

## Start Fresh
Start with a fresh clone, your local repo is the same as the remote, no feature branches, just `main`.

```mermaid
---
title: Local Git Repo for user 1 on your laptop
---
gitGraph
   commit id:"Initial commit"
   commit id:"Initialization"
```

```mermaid
---
title: Remote Git on GitHub
---
gitGraph
   commit id:"Initial commit"
   commit id:"Initialization"
```


## Develop Locally

Develop locally on your laptop with commits "def" and "000"

```mermaid
---
title: Local Git Repo for user 1 on your laptop
---
gitGraph
   commit id:"Initial"
   commit id:"Checkout"
   branch develop
   checkout develop
   commit id:"def"
   commit id:"000"
```

```mermaid
---
title: Remote Git on GitHub
---
gitGraph
   commit id:"Initial"
   commit id:"Branch"
   branch develop
   checkout develop
   commit id:"123"
   commit id:"456"
   commit id:"submit"
```

## Open Pull Request, Code Review, Merge

```mermaid
---
title: Local Git Repo for user 1 on your laptop
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch user1-week7
   checkout user1-week7
   commit id:"submit"
   commit id:"push"
   commit id:"create a pull request"
   checkout main
   merge user1-week7
```

```mermaid
---
title: Remote Git on GitHub
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch develop
   checkout develop
    commit id:"submit"
   commit id:"push"
   commit id:"create a pull request"
   checkout main
   merge develop
```

```mermaid
---
title: Local Git Repo for user 2 on their laptop
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch user2-week7
   checkout user2-week7
   commit id:"check pull request"
   commit id:"check code review"
   checkout main
   merge user2-week7
```

# Some mistake while doing git in this repo

## Git Push To Remote *on Branch*, not main

```mermaid
---
title: Local Git Repo for user 1 on your laptop
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch develop
   checkout develop
   commit id:"789"
   commit id:"abc"
   branch another
   checkout another
   commit id:"234"
   commit id:"push"
   checkout develop
   merge another
```

```mermaid
---
title: Remote Git on GitHub
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch develop
   checkout develop
   commit id:"789"
   commit id:"abc"
```

## User 2 Accidentally Pulls from Main

```mermaid
---
title: Local Git Repo for user 1 on your laptop
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch develop
   checkout develop
   commit id:"789"
   commit id:"abc"
   checkout main
   merge develop type:REVERSE
   commit id:"def"
   commit id:"000"
```

```mermaid
---
title: Remote Git on GitHub
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch develop
   checkout develop
   commit id:"789"
   commit id:"abc"
   checkout main
   merge develop
   commit id:"def"
   commit id:"000"
```

If `user2` is on their own personal branch `user2-week7` and they
call

```
git pull origin main
```
by accident, or even worse, connect the upstream of `user2-week7` with
another branch by accident,
```
git pull
```

 they will get changes that are not theirs,
possibly many of them from the distant past.

```mermaid
---
title: Local Git Repo for user 2 on their laptop
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch user2-week7
   checkout user2-week7
   commit id:"111"
   commit id:"222"
   commit id:"def"
   commit id:"000"
   checkout main
   merge user2-week7 type:REVERSE
```
