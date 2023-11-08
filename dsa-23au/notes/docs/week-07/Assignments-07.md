# Assignment 07

Read the [Readings-07](Readings-07.md), and write 

## 1. Hackathon

Attend the Major League Hacking Career Week Hackathon Nov. 10-16th.
Schedule is here:
https://ghw.mlh.io/schedule

This is an online-only event with multiple seminars that you can attend.
You are asked to register for each event (they may fill up quickly)
by clicking the "Register" link for each event on the schedule above.

For DSA, we ask you to attend at least 4 events throughout the week,
and take notes to include in the Co-Creation for this week, by editing
the [Co-Creation-07](Co-Creation-07.md) file.

## 2. Make an improvement to the codebase

This problem consists of two parts.

### Code

Commit some code to improve the class monorepo that you think
will help your classmates or that they will enjoy.

Some ideas:
* Look through the pull requests or issues for `help-wanted` tags.
* Add unit tests for someone else's code.
* Look through pull-requests for incomplete code that was started during class, especially
  if it doesn't pass unit tests.
* Write a shell script to automate some tasks that we commonly do.

### Documentation

Document your improvement by adding a Markdown file (like this one, with
any filename ending in .md).

## 3. Practice Pair Programming

During class, ask someone if they would like to practice pair programming
with you for 25 minutes.

You may choose one of the following three tasks:

* Writing both a recursive and an interative fibonacci function from the Java / Weiss book readings
  in your personal Maven project and commit it to a pull request.
* Adding a unit test to [`ListWrapperFactory`](https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/arrays-links/src/test/java/dev/codewithfriends/ListWrapperFactory.java) and modifying `ArrayWrapper` or `LinkedListWrapper`
  to pass the test.
* Add a sorting algorithm of your choice to the [`pixel-sort`](https://github.com/TheEvergreenStateCollege/upper-division-cs/tree/main/dsa-23au/java-dsa/pixel-sort) project.
  (you may copy, paste, and adapt any code that you typed
  in by hand and committed to GitHub on your account).
  Commit any code you write and progress you make, even if it's not complete,
  to your own branch, push it to GitHub, and create a pull request.

## 4. Final Project Data

Commit a section of your data as a CSV file into the [datasets](https://github.com/TheEvergreenStateCollege/upper-division-cs/tree/main/dsa-23au/datasets) directory,
including your GitHub username and your final project name.
(For example, `pham-dmv.csv`).

## 5. Final Project Command-Line Interaction

### Write a Main Method

Write a main method for your final project, in your personal Maven project or other directory that you commit to the GitHub monorepo.
It should demonstrate at least two commands, that you enter on the command-line.
Be sure you can compile and run it so that it behaves as described below.

One of these commands should be answering a question, by processing your CSV data and using
any data structures or algorithms you choose. You can use stub functions (unimplemented functions)
that are empty just so that it will compile.

For example, the Name Crawler currently takes two commands.

* `load` to search for a given name and return its meaning, if found
* `retrieve` to crawl from the website all names beginning with a certain letter, and save it as a HashMap.

Let's say we want to answer a question like, "How many names contain the word 'gifted' in their meanings?"
Then we generalize it to be "How many names contain the word '<arg>' in their meanings?" where `<arg>`
will be taken from the command-line.

Then we choose a sub-command for it, that we will type after the trying to run the Java program.
Let's call it `meaning-search`.

Now we will add this command to the `main` method.
For example, in this main method:
https://github.com/TheEvergreenStateCollege/upper-division-cs/blob/main/dsa-23au/java-dsa/utils/src/main/java/dev/codewithfriends/NameCrawler.java#L211

We would add lines like
```
 } else if (args[0].equals("meaning-search")) {
            List<String> matches = searchMeanings(String searchArg);
        System.out.printf("Found %d matches for meaning %s\n", matches.size(), searchArg);
        for (String match : matches) {
            System.out.printf("  %s\n", match);
        }
}
```

You add an empty sibling method

```
   public static List<String> searchMeanings(String searchArg) {
      throw new RuntimeException("Not yet implemented.");
   }
```

After running `mvn package` to produce a jar file, we can call it like this:

```
java -jar ./target/<your-project-name>.jar meaning-search <search-arg>
```

"meaning-search" and take one argument, a search word.

It would load all the names and their meanings (which are HashMaps) and return a
list of meanings that match the search word.

An example interaction would be:

```
> mvn package
> java -jar ./target/<your-project-name>.jar meaning-search gifted
3 names matched the meaning "gifted".
Abraxis
Beelzebub
Chamois
```

### Write a Unit Test

In one of your corresponding test files in your final project, add a unit test to call
`searchMeanings`. Now you will add a hard-coded response to your implementation like:

```
   public static List<String> searchMeanings(String searchArg) {
      return new LinkedList<>() { 
   }
```


Exchange a code review with one of your classmates, who is not your project partner.
Be sure to 
About 20 lines is sufficient.
