# Assignment Week 9

For all work on your final project below, commit these code changes to your project directory in
`java-dsa` or `python-dsa`, depending on which language you are using.

1. Readings

Complete [Readings-09.md] and add your responses to [Co-Creation-09.md].

2. Final Project UML Diagrams



3. Final Project Dataset Operations

Based on your work in Week 08, you defined two operations you'd like to perform on your dataset,
equivalent to two questions you'd like to answer. Please read [Final Project Command-line Interface](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/notes/docs/week-07/Assignments-07.md#5-final-project-command-line-interaction)
to review. To get your project to compile and print out some basic responses on the command-line,
you were asked to implement *stub methods*, methods that just print out a message and return.

In this homework, you are now asked to implement these methods. To accomplish this implementation, you might find the following steps helpful:

* First write out in English what each method should do
* Will it need loops to iterate through the CSV data that it reads? How many loops (or passes through the data)? Do any of the loops need to be nested?
* What data structures will it need, and what type?
* What is the return type of each method, that is, the final answer you expect?
* Remember, your final project will need to use at least 4 total of either data structures or algorithms, besides linked lists and arrays.

You are free to create, use, and count towards this number your own custom data structures and algorithms.

For example, if you use two heaps, one as a min-heap and one as a max-heap, these count as two data structures, if they are both needed.
If you create a custom algorithm to calculate the average of a particular column in your CSV file, that is also an algorithm.

Examine your classmates' [pull requests](https://github.com/TheEvergreenStateCollege/upper-division-cs/pulls), both closed and open, to see examples of how they chose
their method names and how they have implemented them.

4. Final Project Time and Space Analysis

Analyze your two operations above. What is their complexity in Big-Oh notation, for both running time (CPU) and space (memory)?
Justify your answer by referring to specific lines of code.

5. Final Project Unit Test

Write at least two of your unit tests, one to exercise each of your two dataset operations above,
with a simple input and its epexcted output.

Use `assertEquals` and related methods in your `AppTest.java` or files in the same location.
Make sure they pass when you run

```
mvn test
```

(You will need to create 3 more unit tests, for a total of five, before you submit your final project).

6. Final Project Documentation

Create a README.md in your project directory describing your project and how to demo it, so that
one of your classmates can completely duplicate your two operations, on the limited CSV data you can
commit into the class monorepo. Follow the template from Devpost Hackathons:

```
Inspiration

How we built it

Challenges we ran into

Accomplishments that we're proud of

What we learned

What's next for the project next quarter

Built With (your tech stack)

```
