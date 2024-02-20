# GitGraph Practice

One common cause of errors:

## Start Fresh
Start with a fresh clone, your local repo is the same as the remote, no feature branches, just `main`.

```mermaid
---
title: Local Git Repo for user 1 on your laptop
---
gitGraph
   commit id:"123"
   commit id:"456"
```

```mermaid
---
title: Remote Git on GitHub
---
gitGraph
   commit id:"123"
   commit id:"456"
```


## Develop Locally

Develop locally on your laptop with commits "def" and "000"

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
```

```mermaid
---
title: Remote Git on GitHub
---
gitGraph
   commit id:"123"
   commit id:"456"
```

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

## Open Pull Request, Code Review, Merge

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
   commit id:"111"
   commit id:"222"
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
   merge develop
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
```