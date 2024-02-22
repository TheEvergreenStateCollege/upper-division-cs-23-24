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
