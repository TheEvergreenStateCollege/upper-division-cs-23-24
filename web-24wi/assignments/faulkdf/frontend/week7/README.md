# Week 7 readme file with mermaid gitGraph


## Start Fresh

Fresh clone:

```mermaid
---
title: Local 1
---

gitGraph
    commit id:"123"
    commit id:"456"

```
```mermaid
---
title: Remote
---

gitGraph
    commit id:"123"
    commit id:"456"

```
## Develop Locally

Develop new things on branch `example`

```mermaid
---
title: Local 1
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example
    checkout example
    commit id:"abc"
    commit id:"def"

```

```mermaid
---
title: Local 1
---

gitGraph
    commit id:"123"
    commit id:"456"

```

## Git push to remote on branch, not main. 

```mermaid
---
title: Local 1
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example
    checkout example
    commit id:"abc"
    commit id:"def"

```

```mermaid
---
title: Remote
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example
    checkout example
    commit id:"abc"
    commit id:"def"

```
## Open Pull Request, Code Review, Merge

```mermaid
---
title: Local 1
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example
    checkout example
    commit id:"abc"
    commit id:"def"

```

```mermaid
---
title: Remote
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example
    checkout example
    commit id:"abc"
    commit id:"def"
    checkout main
    merge example

```
```mermaid
---
title: Local 2
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example2
    checkout example2
    commit id:"uvw"
    commit id:"xyz"

```

## User 2 Accidentally Pulls from Main

```mermaid
---
title: Local 1
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example
    checkout example
    commit id:"abc"
    commit id:"def"
    checkout main
    merge example
    commit id:"789"
    commit id:"111"

```
```mermaid
---
title: Remote
---

gitGraph
    commit id:"123"
    commit id:"456"
    branch example
    checkout example
    commit id:"abc"
    commit id:"def"
    checkout main
    merge example
    commit id:"789"
    commit id:"111"

```
If `Local 2` is on `example2` and they call 
```
git pull origin main
```
they could get changes that arent theirs, Possibly from the past. 

```mermaid
---
title: Local Git Repo for user 2 on their laptop
---
gitGraph
   commit id:"123"
   commit id:"456"
   branch example2
   checkout example2
   commit id:"uvw"
   commit id:"xyz"
   commit id:"aaa"
   commit id:"bbb"

```