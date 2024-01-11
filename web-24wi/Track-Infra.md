# Track: Web Infrastructure

![[Pasted image 20240107191611.png]]
Web Infrastructure refers to all software, systems, teams of people, and engineering considerations of a web app that live on servers (in the cloud), not running on the user's machine or web browser. It includes the term "back-end", which usually refers to the server process that listens to and handles client connections and databases. Web Infrastructure also encompasses "devops" or developer operations that allow developers to run unit / integration tests, deploy and version their code, provision (virtual) hardware and environments reproducibly, scale them based on user traffic, logging and tracing events for performance and bug-fixing, and much more.

GitPod is an example of infrastructure which happens to work in a web browser.

4 credits

*Class Meeting Times:*
Monday 10am-12noon
Thursday 1pm-3pm
LIB 2619

This track is not compatible with the Changemakers Lab afternoon sessions.
If you are in Changemakers, you should take the Web Front End track 
## Lap 1: Cloud

We use Amazon Web Services (AWS) as the primariy cloud service provider in this class, and invite all students to join our AWS Organization to avoid having to set up billing, credit card details, etc. We also will spend some time studying self-hosting, and we encourage students in the Web Infrastructure track to host their own services on their own hardware, either at home, in the Physical Computing Center (PCC), or even on Raspberry Pi's and single-board computers that they bring to class and plug in each time. We especially encourage students to use their knowledge of cybersecurity to install firewalls and implement best practices to secure networked-machines against attackers.

Thus, AWS and self-hosting (the "self cloud") are the two supported paths through Lap 1.

AWS is the largest and most common cloud ecosystem in the world, but there are many competitors catching up or serving a large existing customer base such as Microsoft's Azure, Google Cloud, Linode, DigitalOcean, Heroku, and more. There are multi-cloud or cloudless solutions like Snowflake that are meant to be agnostic of any single cloud provider for ease of migrating between vendors, negotiating costs, maximizing uptime, and minimizing economic dependence. These are all interesting developments, and if you have a preference for a different cloud provider, you may use it to perform the infrastructure tasks in this class. Support from teaching staff will be provided on a best-effort basis, but it is not the focus of the class to be cloud-agnostic. Be warned! You are mostly on your own if you choose a non-supported path.
## Lap 2: Rust

Lap 2 is a *completely optional* parallel multi-goal for students who, alongside Lap 1, want to commit to an extra 2 hours of preparation time each week to learn a strongly-typed systems programming language, [Rust](). The reason we include Rust in a class on web engineering is that it is a fast, performant, compiled language used in many tools that pre-process and complement the Javascript and Typescript ecosystem. It has a more strict type system than Java, including concepts of memory ownership and lifetimes to sidestep Java's unpredictable garbage collection, but also better type inference that is often able to figure out a reference's type without the user explicitly annotation it.

If, when you were learning Java, you had any of the following thoughts, then Rust is for you.
* "well all these nested generics and compile errors are great, but how can I introduced even more syntax and compile errors to achieve speed and memory safety?"
* "it is wasteful to just abandon all these references after creating a bunch of objects. Sounds like a memory leak nightmare to me."
* "I should learn C++ instead."

If that doesn't make much sense to you yet, but seems exciting, don't worry. Rust is also for you. We'll get to learn a lot more about these in Lap 2.

There is no additional credit awarded for completing Lap 2, but if you choose it, the positive aspects of your work in Lap 2 and Rust will be described in detail in your evaluation.

### Readings and Watchings
Main Text:
https://rust-book.cs.brown.edu/

Each week, the homework will include a section of reading (and practice coding)

Supplementary Video Source:

### Deliverable
While Lap 2 won't have much interaction or teamwork with the other laps or Web Front End, the curriculum of the Rust book above will guide you in producing two projects: a command-line tool, and a single-threaded Web (REST) server. Based on these, students in this lap will be asked to adapt these projects as follows and present them in Code Interviews:
1. Command-line tool that fetches data from an API for your team's final project and stores it into a database.
	* This will coincide with the Lap 1 week on databases
2. REST server that presents an API with two endpoints that Front End's can call
	* This API should be compatible with Lap 1's back-end Express server written in Javascript

