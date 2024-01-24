


# Web Infrastructure - Homework 03

## Lap 1: Cloud Hosting

All students in SOS will do Lap 1 for Web Infra in order to host their projects in a high-availability way.

Our goal in this homework for Lap 1 is
* connect our Postgres database to our Express API server using the Prisma Object Relational Modeling.
* design a data schema for our Postgres database.
	* you may design a schema for your final project idea if you have one instead of the general product idea in the FrontendMasters tutorial
* create services for two projects using systemd, that will automatically restart if the service is not running, for high-availability
	* the python HTTP server for one your front-end projects, or the front-end project for a classmate who isn't taking Web Infra
	* your Express API server that is offering data
Install `tmux` for terminal multiplexing.

### Readings and Watchings

Read and/or watch these two sections of the API design with NodeJS and Express course.
[Section 3: Object Relational Mapping with Prisma](https://frontendmasters.com/courses/api-design-nodejs-v4/object-relational-mapper-orm/)
[Section 4: Routes and Middleware](https://frontendmasters.com/courses/api-design-nodejs-v4/defining-routes/)

The entire course, just for reference.
https://frontendmasters.com/courses/api-design-nodejs-v4/

Some short notes accompanying the above.
https://hendrixer.github.io/API-design-v4/

### Tasks for Code Interview


https://frontendmasters.com/courses/complete-intro-containers/

https://frontendmasters.com/courses/fullstack-v3/

## Lap 2: Rust (API)

This is an optional lap for those wishing more practice with Rust on the backend, using it as a high-performance API for web applications.
### Reading

[Rust Book: Chapter 07 - Packages, Crates, Modules](https://rust-book.cs.brown.edu/ch07-00-managing-growing-projects-with-packages-crates-and-modules.html)

[Rust Book: Chapter 08 - Common Collections](https://rust-book.cs.brown.edu/ch08-00-common-collections.html)

### Tasks

In addition to passing all the quizzes for the Rust Book above, we ask you to extend your web crawler from Web Infra Homework 02 in the following two ways:
* Using a hashmap to avoid repeatedly crawling the same URL
* Create a second module with database functionality that writes an index into Postgres.

Create the following directory in your assignments directory in the class monorepo, and commit your work there. Add a README.md file describing your design and implementation, especially any refactorings from week2, any tutorials or resources you found helpful, and a reflection on your process.

```
cd <repo_root>/web-24wi/assignments/<github_username>/rust
cargo new week3
```

## Lap 3: Rust (WebAssembly)

This is an optional lap for those wishing more practice with Rust and Web Assembly, for creating high-performance front-end applications that run in the user's browser. It is deliberately open-ended to allow you to adapt it to your interests, but you must also do your own research for tutorials, libraries, and resources to use.

### Tasks
The goal for this week in Lap 3 is to implement [Conway's Game of Life](https://playgameoflife.com/), with a graphical grid-based display, or any similar data visualization that supports your final project, running in a browser as a front-end WebAssembly app. You may use any of the techniques you learned in Week 02 homework. 

Commit your work in your assignment directory

```
cd <repo_root>/web-24wi/assignments/<github_username>/rust
cargo new week3
```
