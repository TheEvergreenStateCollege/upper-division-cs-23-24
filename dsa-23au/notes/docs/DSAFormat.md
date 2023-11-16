# Steppenwolfenstein: a Data Structures and Algorithms Format 

We define a serialization / deserialization format for recording
data structures and algorithms, both as a snapshot and as an execution
trace as they progress from an input to an output.

This language, and the engine that runs it, is known as Steppenwolfenstein 
abbreviated (SWS).

It steps through DSA and allows them to come to glorious blocky, pixelated life.
Also, you can enjoy it to the classic 1968 song "Magic Carpet Ride" popularized
in the film Star Trek: Generations, performed by Steppenwolf.

SWS is only a display language. It is not a complete representation of the 
computation of the algorithm on the data structure.

Our goals for this format are:

* To be both human-readable and machine-interpretable, like JSON.
* Unlike JSON, its syntax should mimic ASCII art of the underlying DSA.
* Used as an interchange format in a REST API
  * web servers should emit this format to reflect the DSA in internal memory.
  * web clients should render this format to visualize DSA in a browser.

The formats for Data Structures and Algorithms are separate, but designed to complement
each other.

All lines are in ASCII text and limited to a maximum of 78 characters in length.
The first lines are of the form 

```
<algo type>
l: <line numbers>
id: 
n: <size of dataset>
<body>
...
... <end-of-data after <l> lines of body>
```

Each of Data Structure and Algorithm categories contains a snapshot type and an update type.
The first time an entity appears in a history, it is a snapshot type, to give a complete 
description of the entity at time step 0. At every timestep later, only an update type frame 
is needed to indicate what has changed since the last frame.

## Algorithms 

Algorithm entities consist of lines of pseudocode, variables, inputs, outputs, and a current 
line of execution, which starts at 0.

The lines of pseudocode may be of several types:
* an assignment of a value (or a reference)
* an assignment with a function call, which may be the current function (a recursive call) or another function 
  * SWS functions are lazily evaluated, so you may refer to a function that is yet to be defined
* an if statement with a boolean condition, opening a code block
* a while / for loop, with a boolean condition, opening a code block
* ending a code block
* return values from the function

```
Function
id: zzz 
name: <name of algo> 
args: <number of args>
arg1: 
arg2:
l: <number of lines>
<statement1>
...
<statementl>
```

### Step Frame

This is SWS's main joy in life: to step through an algorithm. 
These step frames tell the displayer how to update its display of both the
algorithm (any functions currently defined and steppable through)
and any data structures it is operating on.

```
Step
count: <step_count>
nextFunc: <id of function at end of this step>
nextLine: <line number of function at end of this step>
returnVal: <value to assign variable on current line>
l: 3 
<id1 of ds>: highlight <index1> # highlight the value at index1 
<id2 of ds>: value <index2> <value2> # change the value at index2 to value2
<id3 of ds>: highlight <index3> value <index4> <value4>
```
### Assignment Statement 

Adds a new variable called `var1` to the function scope, initialized to `value1`.

```
var1 <-- value 
```

### Function Call 

```
var1, var2 <-- call <function id> arg1, arg2, ...
```

### If Statement 

```
if <expression1> 
    <body>
end
```

### While Loop 

```
while <expression>
```

### Do While Loop

Begins a `while` loop which executes at least once, and evaluates its continue condition.

```
do 
    <body>
while <expression>
```

### End Code block

Ends a code block begun by an `if` statement or a `while / for` loop.

## Data Structures

There are x built-in data structures in this first version, and more may be added
in the future. Each can be oriented "Down", meaning drawn vertically with increasing indices
going down, or "Right", meaning draw horizontally with increasing indices going to the right.

```
<ds type>
id: zzz
l: xx
n: yyy
<body>
...
... <end-of-data after <l> lines of body>
```

### Arrays

Our most fundamental data structure, underlying all the others and allowing
us to focus on just one memory range in our RAM model, is the array.

We wish for it to label indices, and to contain references to objects of
arbitrary types, not just integers as often appear in DSA textbooks.

All object references have names beginning with `$` followed by a unique abbreviation
of their identifier on the server (in Java, it is the memory address of the object on the heap).

#### 1D Arrays

```
Array
id: <id>
n: 143
dim: 1
l1: 3
$af $b3 $12 ...                                                              | 
$g5 $b3 $12 ...                                                              | 
$af $83 $7z
refs: 99
$af: <dev.codewithfriends.ClassA {a: 1, b:2}>
$b3: <java.lang.Integer {3}>
$12: <java.util.Map.Entry {k: $b3, v: $af}>
```

#### 2D Arrays

```
Array
id: <id>
n: 143
dim: 2
l1: 3
$af $b3 $12 ...                                                              | 
$g5 $b3 $12 ...                                                              | 
$af $83 $7z
l2: 2 
$af $b3 $12 ...                                                              | 
$g5 $b3 $12 ...                                                              | 
$af $83 $7z
refs: 182
$af: <dev.codewithfriends.ClassA {a: 1, b:2}>
$b3: <java.lang.Integer {3}>
$12: <java.util.Map.Entry {k: $b3, v: $af}>
```

## Linked List

```
LinkedList
id: <id>
n = 76
l: 183 
$
```

### Tree

```
Tree
id: <id>
n: 993 
0: $g5 1 2 3 
1: $8f 4 -
2: $c6 6 7
...
993: - -
```

### Graph

```
Graph 
id: <id>
n: 1025
0: $s3 1 2 
1: - 5 6 7 
...
1025: $95 0
```
