```mermaid

----
title: a graph
---

gitGraph
  commit id:"1"
  commit id:"2"
  branch develop
  checkout develop
  commit id:"1"
  commit id:"2"
  checkout main
  merge develop
  commit id:"3"

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