# Assignment Week 5

(100 points total)

This assignment is mirrored in the following locations. They are identical, and you can read either of them to get complete information for completing the assignment.

[Link to public course website.](https://theevergreenstatecollege.github.io/upper-division-cs/dsa-23au/week-05/Assignment-05/)

[Link to GitHub markdown file.](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/docs/week-05/Assignment-05.md)

[Link to Canvas assignment (mirror)](https://canvas.evergreen.edu/courses/5926/assignments/110041
).

This assignment can be completed either using [Traditional Textbook Readings / GitHub Pull Requests](#path-1-traditional-textbook-readings-and-github-pull-requests), or using [zyBooks](#path-2-zybooks). You only have to choose one path.

## Path 1: Traditional Textbook Readings and GitHub Pull Requests


### 1. Readings and Textbook Problems

(30 points)

* 2 pts x 10 problems
* 10 points co-creation

Do the [Readings 5](Readings-05.md) and attempt the exercises recommended, submitting them into the
Canvas text entry for this assignment.

### 1. AI for Learning

(5 points)

Write a few paragraphs about the following topics, or a related one of your choosing,
into this [Co-Creation 5 document](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/notes/docs/week-05/Week5Co-Creation.md).

How do you think the increasing availability and quality of AI chat and large learning models
will affect the software industry in the near future?

What philosophy or guidelines can you adopt to make your learning effort on the right level of
engagement for a flow state, engagement between boredom and overwhelm?

Here is a reading to give some guidance and define the flow state.
https://alessiobresciani.com/productivity/finding-flow-at-work-20-tips-to-overcoming-overwhelm-eliminating-boredom/

### 2. Learning Java and Git Branches

(10 points)

Complete another 3 lessons in [Codecademy's Java course]() and another 3 lessons in the [Learning Git Branches]() course.
Submit into Canvas for this assignment
a link / URL to your Codecademy profile and a screenshot of your Learning Git Branches modules completed.

### 3. Choose Your Own Sorting Algorithm

(35 points)

In the Readings 5, you were asked to choose to read one of the five sections for a given search algorithm.
You'll be implementing this in class in teams to sort a large database of strings.
In the `dsa-23au/java-dsa/sorting` Maven project and directory, add a file implementing your sort.

You may type it in verbatim from the Weiss book, but modify it to compile and run cleanly in Maven.
Use the example of the `BinarySearchTree` and `BinaryNode` that we completed in class last week.

https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/heaps-trees/src/main/java/dev/codewithfriends/BSTMain.java

### 4. Draw Mermaid diagrams for your design of the DMV project

(10 points)

We will continue to learn the Mermaid diagramming language in class as we refine our design of the DMV example,
in preparation for you applying the same process to your final project.

We will be working with this design, or one of your own choosing:

https://github.com/TheEvergreenStateCollege/upper-division-cs/tree/main/dsa-23au/java-dsa/weiss-pham-dmv

You'll commit your diagrams into a markdown file in this directory, named `MyUserName.md`
where you can substitute your GitHub username for `MyUserName`.

https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/weiss-pham-dmv/src/main/resources/learner-long-life.md

You can read ahead here about the [classDiagram syntax](https://mermaid.js.org/syntax/classDiagram.html)

## 5. Read in DMV data from CSV

(5 points)

In your personal / final Maven project that we committed in class,
you'll add a reader for CSV files that will create a Java object per row of your CSV.

You'll ZIP compress your CSV file and commit it to your `src/main/resources` directory.
Let's say you have a number of such files named `project-data-1.csv`, `project-data-2.csv`, and so forth.

You'll be following the example here to read the zipped CSV file, and we'll continue to develop it together in class.

https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/arrays-links/src/main/java/dev/codewithfriends/ReadArray.java#L15

To complete, this part of the assignment, you'll commit code and build a jar file so that,
when you run it as follows

```
mvn clean compile assembly:single
java -jar ./target/<your-project-name>-1.0.jar project-data-1.csv
```

it will emit a single line stating how many records it has read and exit.

```
1504 records read. Bye for now.
```

## 6. Prepare for the Hackathon, Nov 10-16

(5 points)

Register for Major League Hacking's Career Week hackathon here:
https://events.mlh.io/events/10077

It does require registering again with your email address unfortunately.
I've not been able to find a way to register with an existing account like GitHub.

Opt out of marketing and third-party information disclosure:
<img width="510" alt="image" src="https://github.com/TheEvergreenStateCollege/upper-division-cs/assets/148553/cc68a322-14b1-4aec-b994-bf5737898837">

Read through the Code of Conduct
https://static.mlh.io/docs/mlh-code-of-conduct.pdf

This is an online-only hackathon. We will be participating in the hackathon during normal class times during
the week of November 10th-16th and using it to make progress on our final projects for this class.

Submit your responses to the following questions into the [Co-Creation 5](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/notes/docs/week-05/Week5Co-Creation.md) document.

* What are you looking forward to, or could you look forward to, in the hackathon?
* What are some concerns you have or challenges we might encounter in the hackathon?
* What well-defined, one-weeks' worth of difficulty piece of your final project do you hope to complete for your hackathon submission / entry?
* What resources, work, or tools (software or otherwise) do you think all of us will need to succeed in the hackathon, that we can prepare in advance?
* How will you judge whether the week was a successful experience or not?

## Path 2: zyBooks

(100 points)

You may complete the following chapter in the zyBook for DSA to receive credit for this assignment, doing all the challenges and participation activities leading up to the labs. These will give you all the background concepts to complete the labs.

[Chapter 2: Algorithm Analysis and Big-Oh Notation](https://learn.zybooks.com/zybook/EVERGREEN10034PhamWinter2024/chapter/2/section/1)

(this was assigned in a previous assignment, you only have to read it if you haven't done all previous assignments, or you need a review of Big-Oh notation)

[Chapter 3: Sorting](https://learn.zybooks.com/zybook/EVERGREEN10034PhamWinter2024/chapter/3/section/10)

No AI chat is allowed, but you may collaborate with classmates or TAs. After doing the readings of the chapters above, complete the labs at the following zyBook links.

- [Lab 3.10 Natural sort](https://learn.zybooks.com/zybook/EVERGREEN10034PhamWinter2024/chapter/3/section/10)

