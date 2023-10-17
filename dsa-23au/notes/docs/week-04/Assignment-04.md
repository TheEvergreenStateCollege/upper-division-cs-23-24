# Assignment and Co-Creation Week 04

This week's reading and co-creation assignment has five parts

## 1. Reading Week 04

[Reading-04.md](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/learner-array-links/dsa-23au/notes/docs/week-04/Readings-04.md)

## 2. Ask Questions on Other Pull Requests

Pick three pull requests (open or closed) from here:
https://github.com/TheEvergreenStateCollege/upper-division-cs/pulls?q=is%3Apr

Two that were opened by your classmates, and one from the instructor (learner-long-life).

For each one:

1. Go to their "Files changed" view
2. Click on three lines that will help you in your final project, that you have questions about, that you would like to ask the code authors.
3. For each line, type a question and submit it to add to the conversation thread for this pull request.
4. The questions can be because you want to learn more about how to write the code yourself, because you notice what you think may be a mistake or something new or interesting. Remember to use respectful communication.

## 3. Respond to Questions on Your Pull Requests

Navigate to this URL and find PRs that mention you by name. Find your classmates, or message them on Discord,
and discuss their questions and your code.
When you think you have at leat a 2-3 sentence response to summarize your conversation,
type it into the PR and connect it to your final project.

Respond in the spirit of helping your classmates learn and also that they may be wanting to help improve the code you wrote that we will all use.

https://github.com/TheEvergreenStateCollege/upper-division-cs/pulls?q=is%3Apr+mentions%3A%40me

## 4. Work on One of Your Pull Requests, Ask for a Review

We are still implementing data structures in the Java / Maven projects, getting them to compile and pass tests.
Work on this step with a pair programming partner. Set up a time to meet on Discord in one of the Study Rooms if you're not able to meet up
in class or on-campus.

Pick one of your PRs (it could be the one you just had a conversation about above in the previous part)
and make at least one commit to it. Your goal is to pass one more unit test. Ask for a review from one or more classmates, and include the instructor as
well (@learner-long-life).

## 5. Add an Entry to the Periodic Table

Pick an empty entry in the Periodic Table that you have worked on or that you have studied and want to take an educated guess on.
Edit this file to include your answer in Big-Oh notation, and include a link to lines of code in the monorepo that back up your answer.

https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/PeriodicTable.md

## 6. Datasets and Data Modeling (Final Project)

Convert any data you have been collecting from a spreadsheet into a CSV file (comma-separated values) and add to the monorepo here.

If you have found a data source online that you'd like to use, download and convert to CSV.

In your PR, perform the steps of Data Modeling and Analysis following the DMV example in class.


* Pick a partner for this activity and take turns presenting your dataset to each other.
  * If you are doing a team project, pick someone on a different team.
  * Be sure to tell the other person how it fits within your goals in life, or for computer science learning
  * When you respond, reflect back what you understood from your partner, and then present something related to it from your own life.

* Consider your Dataset as living inside a Data Structure with CRUD operations and work through the following steps. Start with a sheet of paper.
  1. Define CRUD operations with parameters and types for your dataset, similar to the Periodic Table.
  2. The Create / Delete operations should be small and simple. What does a record / datapoint look like?
  3. What are the columns, if this were a spreadsheet?
  4. Is there more than one table involved? (For example, multiple sheets in a spreadsheet, each with different columns) 
  5. The Read / Update operations are probably the most complex part and will answer questions you are interested in. For example:
    * What record / datapoint is the most <blah> or least <blah>?
    * What operation would rearrange the dataset into a more useful form? (Sorting, for example)
    * What operation would produce useful statistics? (for example, the two most correlated columns)
  6. What data structures that we've learned so far (arrays, linked lists, stacks, queues, heaps, trees) would be useful?
    * you can use more than one and combine them. you can add extra variables, like size, tail pointers, etc.
  7. Write pseudo-code (code that doesn't have to compile, but with variable names, loops, assignments, etc.)
    * include an overall class name. This would be the main class in Java, the one that has the `main` method that kicks everything else off.
    * include Java class methods for all the CRUD operations you defined above
    * include instance / member variables for your internal data structure representations in the previous step
  8. Create an empty Maven project in the `dsa-23au/java-dsa` with a name for your team and [following instructions here].
