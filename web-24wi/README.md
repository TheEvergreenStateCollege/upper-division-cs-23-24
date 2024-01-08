# Student Originated Software
## Web Front End and Web Infrastructure

## Guiding Principle
There are many different parts of Student-Originated Software, including curricula, class meeting times, and software. To help simplify it in your mind and tie all these parts together, keep the following principle in mind:

```
SOS helps you express, render, and understand your dataset application as a web application.
```

What is a dataset application? It is a dataset plus operations.

For students in DSA last quarter, they collected data in tables, with rows and columns, that they saved as Comma-Separated Values (CSVs), Javascript Object Notation (JSON), or similar serialized format. The datasets can include things like
* locations of meteorite collisions on the earth
* Spotify music listening habits
* driving commute times
* Apple watch fitness measurements
* brainwaves
* ...and more.

What are the operations? They are algorithms that can answer questions about your dataset, like the following:
* What is the nearest meteorite collision to me?
* What is my most listened Spotify song?
* How long do I spend on driving?
* What is the day I heard the loudest sound?
* How many times did I experience stressful thoughts?

How is a web application different from a website? They are the same from a user's point-of-view, but for a web-engineer-in-training like you, a web application is implemented with a user-facing part that runs close to us (called the front-end), a remote part that runs on servers (called infrastructure, or the back-end), with communication via an Application Programming Interface (an API) over a network, probably the public internet.

We can visualize these parts and how they communicate via a Web Architecture Diagram.



Why do we wish to make our dataset applications available on a web app? In DSA last quarter, all of our projects had a command-line interface. Users typed characters to run the program. The most user-friendly ones presented a menu of options so users wouldn't have to remember what commands were available, what arguments they required, and in what order. In contrast:
* Most humans (our users, and our wider communities) who could benefit from our dataset application will be non-technical. They do not enjoy or wish to use the command-line.
* Many humans are familiar with web browsers and are used to accessing websites on all their devices.
* Non-humans, like bots and other applications, can mash-up, remix, and otherwise transform existing web applications via their APIs.

## Tracks 

There are three tracks for SOS, corresponding to the web architecture.
Within each track, there are two *laps*,
one for students who have never done any web Engineering
before, and one for students who already have some
experience and want to learn a more advanced topic.

You will be assigned to a lap at the end of the first week
as part of your Week 1 Code Interview, and will stay with 
it for the rest of the quarter. If you take Lap 1 this quarter,
you may take Web Engineering
or its equivalent next year (in Winter 2025) in Lap 2 to learn
more advanced topics and pair program with students in Lap 1.

Both laps count for upper division computer science credits.

* [Track: Front End Engineering for the Web](./Track-FrontEnd)
* [Track: Software Infrastructure for the Web](./Track-Infra)
* Track: Computer Networking




