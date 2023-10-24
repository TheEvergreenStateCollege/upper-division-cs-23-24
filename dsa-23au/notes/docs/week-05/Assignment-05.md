# Assignment Week 5

Do the [Readings 5](Readings-05.md) and attempt the exercises recommended, committing them into your personal Maven projects.

## 1. AI for Learning

Write a few paragraphs about the following topics, or a related one of your choosing.

How do you think the increasing availability and quality of AI chat and large learning models
will affect the software industry in the near future?

What philosophy or guidelines can you adopt to make your learning effort on the right level of
engagement for a flow state, engagement between boredom and overwhelm?

Here is a reading to give some guidance and define the flow state.
https://alessiobresciani.com/productivity/finding-flow-at-work-20-tips-to-overcoming-overwhelm-eliminating-boredom/

## 2. Learning Java and Git Branches

Complete another 3 lessons in [Codecademy's Java course]() and another 3 lessons in the [Learning Git Branches]() course.
Submit into Canvas for this assignment
a link / URL to your Codecademy profile and a screenshot of your Learning Git Branches modules completed.

## 3. Choose Your Own Sorting Algorithm

In the Readings 5, you were asked to choose to read one of the five sections for a given search algorithm.
You'll be implementing this in class in teams to sort a large database of strings.
In the `dsa-23au/java-dsa/sorting` Maven project and directory, add a file implementing your sort.

You may type it in verbatim from the Weiss book, but modify it to compile and run cleanly in Maven.
Use the example of the `BinarySearchTree` and `BinaryNode` that we completed in class last week.

https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/heaps-trees/src/main/java/dev/codewithfriends/BSTMain.java

## 4. Draw Mermaid diagrams for your design of the DMV project

We will continue to learn the Mermaid diagramming language in class as we refine our design of the DMV example,
in preparation for you applying the same process to your final project.

We will be working with this design, or one of your own choosing:

https://github.com/TheEvergreenStateCollege/upper-division-cs/tree/main/dsa-23au/java-dsa/weiss-pham-dmv

You'll commit your diagrams into a markdown file in this directory, named `MyUserName.md`
where you can substitute your GitHub username for `MyUserName`.

https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/weiss-pham-dmv/src/main/resources/learner-long-life.md

You can read ahead here about the [classDiagram syntax](https://mermaid.js.org/syntax/classDiagram.html)

## 5. Read in DMV data from CSV

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

Submit your responses to the following questions into Canvas:

* What are you looking forward to, or could you look forward to, in the hackathon?
* What are some concerns you have or challenges we might encounter in the hackathon?
* What well-defined, one-weeks' worth of difficulty piece of your final project do you hope to complete for your hackathon submission / entry?
* What resources, work, or tools (software or otherwise) do you think all of us will need to succeed in the hackathon, that we can prepare in advance?
* How will you judge whether the week was a successful experience or not?
