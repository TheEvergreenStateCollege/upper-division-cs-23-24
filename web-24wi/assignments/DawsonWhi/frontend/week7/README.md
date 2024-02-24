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
title: Remote Git on Github
---
gitGraph
    commit id:"123"
    commit id:"456"
```

## Develop Locally

Develop locally on your laptop with commit "def" and "000"

```mermaid
---
title: Local Git repo for user 1 on your laptop
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
title: Remote Git on Github
---
gitGraph
    commit id:"123"
    commit id:"456"
```
from local clone new code 
`git branch <develop branch>`

`git checkout <develop branch>`

`git add <filename>`

`git commit -m <message>`

`git add <filename>`

`git commit -m <message>`

## Git Push To Remote *on Branch*, not Main
```mermaid
---
title: Local Git repo for user 1 on your laptop
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
title: Remote Git on Github
---
gitGraph
    commit id:"123"
    commit id:"456"
    branch develop
    checkout develop
    commit id:"789"
    commit id:"abc"
```