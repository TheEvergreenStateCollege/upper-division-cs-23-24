# Array and Links

This first project is a programming
exercise to teach you about using one of
two fundamental data structures, which can
be used to build virtually every other
data structure in existence:

* arrays, which directly access a computer's memory in contiguous address spaces
* linked lists, which are one layer of abstraction higher than arrays, letting you jump around in the address space for  flexibility 

This project will also introduce you to the
idea of *unit tests*, which are pieces of
code that test other code.

## Software Tools

We will be introducing the following tools
in this first assignment and using them
throughout this year.

### Maven

Maven is widely-used open source 
project management
tool used by many Java projects.
It includes a *build system* and a
*dependency manager*. It relies on
following a *convention vs. configuration*.
That means it relies on projects (and the
developers who develop them) to put
files in certain directory locations,
rather than giving you the flexibility to
design a project structure from scratch.
This simplifies understanding.
Once you've learned one Maven project,
you can figure out every other Maven project.
Maven is an older tool, and as a result,
most Java libraries have Maven packages
that you can download and use. Its feature
set is stable and hasn't changed in recent
years, so there is less of a chance that
newer libraries will rely on a specific
Maven feature or require you to download
a different Maven version than you have.
It usually *just works*.

The build system
compiles all your Java source files into
bytecode that can be executed, even in
large directory trees with many packages
and subpackages.

The dependency manager is a global
repository of useful Java libraries,
usually packaged into JAR (Java Archive)
files, that you can download and use in
your project. For example, we'll be making
use of the following libraries in this
quarter:

* junit - for unit tests
* jsoup - for web scraping

## How to Run It

You'll only need to know two Maven
commands to complete all these projects.
You can open the terminal of your IDE
(either VSCode in GitPod, or IntelliJ
if you have it installed locally)

### Compiling Without Tests

The following command compiles all your
Java files (but not your unit tests) and notifies you of any
errors.
```
mvn -Dmaven.test.skip=true package
```

This is the command you'll be running the
most. As with many development cycles,
you'll often repeat the following
steps until your code has the
functionality that you want.

* 

You can find the resulting JAR file
in
`/target/arrays-links-1.0-SNAPSHOT.jar`

If you're curious, you can change the
`SNAPSHOT` to a specific version
number in your `pom.xml` but we won't
worry about it for now.

### Compiling Project Including Tests
### Testing

```
mvn test
```

## List Specs

We will practice the idea of *interfaces* vs.
*implementations*, and the "CRUD" heuristic
for data structures, on the *list*.

A *list* is a data structure interface
which represents an everyday list,
that can grow and shrink as you add and
remove items to it while retaining any
items not being added or removed.
It starts out empty, and has a maximum
capacity.

### Global Invariant

items before and after the deletion index `k`
remain in the same order after deleting
an existing item at index `k`.

### Create (Insert)

Invariants:

if the list is not already at a maximum
size, then inserting will increase
the list by one.

items before and after the insertion index `k`
remain in the same order after inserting
a new item at index `k`.

The length of a list equals all the
insertions minus all the deletions
that occur since the list was created.

Let `L = [ x_0, x_1, ..., x_{n-1} ]`
be a list with `n` ordered items
(size `n`).

We insert a new item `x_j` at index `j`,
where `0 <= j < n`.

```
L` = L.insert(j, x_j)
```

All previous items are 
`L' = [x_0, ... x_j, x_{j+1}, ... x_{n-1} ]`

For all `k < j`,

```
L'[k] = L[k]
```

For `k > j`,

```
L'[k] = L[k-1]
```

### Read (Search)

Invariants:

items before and after reading stay
in the same order.


Let `L = [ x_0, x_1, ..., x_{n-1} ]`
be a list with `n` ordered items
(size `n`).

We read an item `x_j` at index `j`,
where `0 <= j < n`.

```
[L`,x_k] = L.read(k)
```

After a read, all items are in the same
order as before.
`L' == L`

For all `0 <= j < n`,

```
L'[j] = L[j]
```

#### Special Cases of Read

* First (read of `x_0`)
* Last (read of `x_{n-1}`)

### Update

Invariants:

items with indices before and after
updating an item `x_k` stay in the same order.

Let `L = [ x_0, x_1, ..., x_k, ... x_{n-1} ]`
be a list with `n` ordered items
(size `n`).

We update an item `x_k` at index `k`,
where `0 <= k < n`.

```
[L`,x'_k] = L.update(k, x'_k)
```

For all `k < j`,

```
L'[k] = L[k]
```

For `k > j`,

```
L'[k] = L[k+1]
```

### Delete

The length of a list equals all the
insertions minus all the deletions
that occur since the list was created.

Let `L = [ x_0, x_1, ..., x_{n-1} ]`
be a list with `n` ordered items
(size `n`).

We delete an existing item `x_k` at index `k`,
where `0 <= k < n`.

```
[L`, k] = L.delete(k)
```

All previous items are
`L' = [x_0, ... x_j, x_{j+1}, ... x_{n-1} ]`

For all `k < j`,

```
L'[k] = L[k]
```

For `k > j`,

```
L'[k] = L[k+1]
```
