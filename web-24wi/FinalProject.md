# Final Project and Independent Contract

This document describes a Team Final Project, worth 1/3 of your credit and evaluation (the other 1/3 being your co-creation, pair programming, and attendance, and the last 1/3 being weekly code interviews).

You may view examples of team final projects from [Data Structures & Algorithms students in Autumn 2023 quarter here](), many with README files that show you how to run them in GitPod.

 progressively realized project demonstrating your data structures and algorithms skills
and also your ability to find and develop them in a Real-World Application throughout the quarter.

For independent students, I'd like to see the ability to apply your computer science skills to a
problem that is personally interesting to you. It may be to make progress on a project that
you have previously started. Your goal during this quarter is to show software engineering
judgment for *time estimation* and your *circle of competence*.

That is, it's an attempt to carve out a smaller piece of a possibly bigger project or life goal and make a commitment to another person
(a faculty member, and optionally a classmate that you exchange your contract with).
It requires knowledge of yourself and your current capabilities, as well as the desire to steer
where you want to go.

## Deliverables for All Projects (DSA and Independent)

You can apply the criteria below, or present a case in your proposal for alternative
criteria that meets the intent of showing substantial effort for upper division computer science credit.

Per team member:

For the whole project:
* 500 lines of original code, newly written or refactored while maintaining existing functionality, written this quarter
* 5 tests showing how to run your code and demonstrating 5 different modes of operation or functionality.
  * These can be unit tests written in code, integration tests written as a script, screenrecording videos, screenshots
* 800 word or longer README.md in your GitHub repo

You can incorporate some or all of the above sections into your required Evergreen self evaluation.

For example: if you have two members of your team, you'll each need to contribute 500 lines of code or code changes.

## Progression Through 10 Weeks 

While each week we will introduce new concepts, data structures, and algorithms, and 
self-contained homework exercises to help you learn, you will be progressively synthesizing these 
learnings, adding them to your final project.

At the end, you will have the following deliverables to submit to instructors as well as a 
portfolio piece that you can display on your Git profiles, your websites and social media,
and as a starting point for your work in later quarters (Purpose Driven Web in Winter 2024
and Software Construction in Spring 2024).

## Criteria and Rubric

Final assignment on canvas:
[200 points]

* Use and combine at least four data structures (beyond an array or a linked list) or algorithms. [5 pts x4 = 20 pts]
* Be compelling for you and your team. It doesn't have to appeal to anyone else but you and your teammates.
You'll be working on this problem for 10 weeks, so when you think of the project, you want to feel 
a certain eagerness to make progress.
* Describe one *invariant* of your code, in both plain English and in math or code. This invariant is true 
of your data structures at every step along the way from input to output, and your algorithms 
should preserve it. [10 pts]
  * Example: if you are inserting new data items into a min-heap call `x` by a date field, you can use the invariant of the heap to say that the top of `x` is always the earliest date of all your datapoints.
* Contains at least 5 unit tests that verify your solution works as intended. [5pts x5 = 20 pts]
* Contain at least 500 lines of code (total, not per partner) that compiles, runs, and passes your tests.
  * Please have each partner commit roughly the same number of lines of code each. [50 pts]
* Contain at least 100 data items, either real or mocked, in your CSV / JSON / other data file. [20 pts]
* Have a 800 word README.md file describing your project, including a link to your video [40 pts]
  * You were asked to start this README file in [Assignment 9]()
* Both project partners will receive the same credit for the project.
* At least a 2 minute screenrecording video [40 pts]
  * If you use asciinema, Paul will record your voice narration to play accompanying the screencast.

These numbers of lines of code is scaled for 10 credits of Data Structures & Algorithms,
and 2 credits of Technical Software Interview Skills (Cracking the Coding Interview),
for 12 credits total.

If you are working towards more (for example, 14 credits of DSA), you will need to write
proportionally more code and tests (for example, 40% more).

If you don't complete as many tests or lines of code, you will still receive partial credit
for the tests and lines of code you do complete. For example, if you have 3 unit tests
out of 5 required, and 300 lines of code instead of 500, you would receive 60% (3/5ths)
of the credit for those items.

## Real-World Application

The guiding force and north star that will help align your work and give you direction is
your Real-World Application (RWA).

Data structures like arrays and binary search trees, and algorithms like heapsort or 
finding the shortest path, only exist in the abstract, conceptually clean and simple.
When you use them to solve a Real-World Application, you may encounter the following challenges
and need to discuss and work with your team on them.

* Identifying a Real-World Application of a data structure or algorithm in the first place.
* Removing unnecessary details and simplifying (abstraction).
* Modeling the problem with relevant details.
* Generating mock data or collecting real data.
* Code that creates data structures based on the data.
* Algorithms that transform the data in an automated way to a more useful form.
* Examples (at least three) demonstrating running your code on the data you collected and why the 
returned output is expected.
* Instructions on how to run your code.

## Collecting Real Data

Traditionally, we deal with data in table form.
Each table can be called an "entity". Think of a spreadsheet. It has a number of columns, also called "fields",
and a number of rows, which are analogous or "equal" items that all have the same fields, though they may have
different values.

In fact, you may wish to open up a spreadsheet (Excel, or Google Sheets online, or any similar program)
and start collecting data here. When you're ready, somewhere around Week 4 or 5, you can export this data as a 
CSV (Comma-Separated Values) file, read it into your program, and then "hydrate" or "reinflate"

Real data is preferred to mock data in this class, but they each have their uses.

The "smell" of real data and its rough surfaces, distribution of digits, and messy incompleteness
contribute a compelling beauty and internal consistency to your work that will immediately shine 
forth to whoever reads it.

You can start collecting data on Day 1 of the class. The best data is gathered a little at a time,
rather than gradually all at once. You may only vaguely be aware of what to collect, but that's okay.
If you add new columns later on and your early data is missing those columns, you can go back and "backfill"
them as time permits. In conjunction, you can modify your algorithm to gracefully handle missing columns.

Handling missing, incomplete, or invalid data, and also preprocessing or "cleaning up" data is a very 
common operation in Real-World Applications.

