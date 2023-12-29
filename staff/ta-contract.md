## Winter 2024

This independent learning contract describes the learning goals, job description, and responsibilities of student teaching assistants for Upper Division Computer Science (Student-Originated Software). The exact topics are subject to change as the needs of the course progresses or student interests evolve and adapt.

## Learning Goals (please update)

Alongside helping students learn Networking and Engineering for the Web:

?? wishes to learn and apply [HTMx](https://htmx.org/), a framework which unifies HTML, websockets, AJAX, CSS transitions, and other web features into a single language.

?? wishes to learn (possibly advanced AWS services like container registry, Kubernetes)

## Job Description
Student teaching assistants are asked to be responsible for:
* Watching assigned videos and doing assigned readings ahead of class, to help students. These will be posted at least one week before the watchings/readings are due.
* Playtesting assignments, labs, and quizzes. These will be posted on GitHub and Canvas at least one week before their due date.
* Holding weekly mock /feedback interviews (about 14 minutes per student) and leading a group critique under faculty guidance to help students practice explaining their code, learning to use terminology confidently, and becoming more effective collaborators for each other.
* Enter results of the interviews as grades on Canvas and GitHub according to faculty-provided rubric
* Organize and facilitate two demo days for the computer science community (including CSF students) at the end of Week 5 and the end of Week 10. Any current CS student may sign up to demo any project, including ILCs and projects from past quarters if they are not currently in SOS.
* Being familiar with, answering student questions on, the academic honesty and course collaboration policy.
 
TAs may record any time spent carrying out these duties on their weekly timesheets. They are only required to spend up to 7 hours each week between the two of them.

## Anticipated Credits

| Credits | Description |
| ---- | ---- |
| 2* | Software Leadership |
| 1* | Software Engineering |
| 3* | Software Infrastructure for Web Engineering |
| 3* | Front End Software for Web Engineering |
| 3* | Computer Networking |

## Interactions Between Tracks
Students may be enrolled in one, two, or all three of the Front End, Infrastructure, and Networking tracks, and will be working in teams across all three tracks on their DSA final projects from fall quarter, or a new project that evolves over winter quarter.

* Front End students will be implementing a web app, with a client-side browser component
* Infrastructure students will be making the web app publicly accessible under a registered domain name, continuously integrated / deployed, reproducibly-built, with maximum uptime.
* Networking students will be analyzing the interactions between client-side and server-side components of the above teams, and helping them debug connectivity and security problems

These emulate product teams in software companies, in which engineers with different specialties divide up work and then integrate them with each other. Students who specialize in one track (e.g. Networking) may choose to take SOS again next year with a different specialization (e.g. Infrastructure).

## Required Textbooks by Track

TAs will receive used desk copies or PDFs of the readings, see Paul at beginning of the quarter.

[_Computer Networking_ by Kurose and Ross (8th edition)](https://www.pearson.com/en-us/subject-catalog/p/computer-networking/P200000003334/9780135928615), ISBN
[_HTML and CSS_ by Duckett](https://htmlandcssbook.com/), ISBN

## Optional Readings (for Seminar)


[_Literate Programming_ by Knuth](http://www.literateprogramming.com/knuthweb.pdf)
[_Computer Lib / Dream Machines_ by Ted Nelson](http://worrydream.com/refs/Nelson-ComputerLibDreamMachines1975.pdf), ISBN
[_The Visual Display of Quantitative Information_ by Edward Tufte](https://www.edwardtufte.com/tufte/books_vdqi), ISBN
## Schedule 
There are 12 hours of class for the standard SOS tracks, and 4 hours extra for the optional
Seminar on Monday afternoon and Hackathon Projects Friday morning. TAs are only asked to come to the 12 hours. Each TA is asked to be "on-duty" for half of the hours, or 6 hours each, and can be a student and focus on their learning for the other 6 hours.

One possible schedule is shown below for TA1 and TA2, but any similar arrangement can work. During their seven hours, the TA "on-duty" will interview students one at a time outside of the lab for about 6 minutes each on a random sample of code / homework problems they are submitting for feedback.

Total, across 2 TAs
5 hours / (~14 minutes including a break in between) = 21 student interviews each week
2 hours of in-class lab help

For each TA
2.5 hours / (~14 minutes) = 10 student interviews each week
1 hour of in-class lab help

|  |  |  |  |  |  |
| ---- | ---- | ---- | ---- | ---- | ---- |
|  | Mon | Tues | Wed | Thurs | Fri |
| 10-12 | Infrastructure  <br>(Evans 2619) | Web  <br>(Evans 2619)<br>TA1 on-duty(1 hour lab help, 0.5 hours student interviews) |  | Web<br>TA2 (2 hours student interviews)  | Projects |
| 1-3 | Seminar | Programming  <br>2610 |  | Infrastructure<br>TA1 (2 hours student interviews) | Networking<br>TA2 (1 hour lab help, 0.5 hours student interviews) |
| 4-6 |  | Networking  <br>2610<br>TA2 (1 ) |  |  |  |
|  |  |  |  |  |  |
|  |  |  |  |  |  |
## Suggested Topics (please add, Richard)
These are subject to slight modification.

| Week | Networking | Front End | Infrastructure | HTMx |
| ---- | ---- | ---- | ---- | ---- |
| 1 | TBD | git review, HTML, CSS | unix review, telnet, curl, nc, netstat, GitHub Pages | TBD |
| 2 | TBD | Layout and Box Model | Databases | TBD |
| 3 | TBD | Buttons and Forms | Continuous Integration | TBD |
| 4 | TBD | CSS / SVG animations | Testing | TBD |
| 5 | TBD | APIs, Auth secrets | Monitoring / alerting | TBD |
| 6 | TBD | Storage, Cookies | Docker / Containers | TBD |
| 7 | TBD | Fetch (AJAX) | AWS instances | TBD |
| 8 | TBD | D3.js | Decentralized services | TBD |
| 9 | TBD | Threejs / WebAudio / ILCs | Our own REST server | TBD |
| 10 | TBD | Final Project / Demo Day | Final Project / Demo Day | TBD |

## Deliverables (Optional)
In addition to helping students with their own final projects, TAs may choose to work on a final project of their choosing (either individually or together) to demonstrate their learning of HTMx or any optional topics they've chosen. The requirements are the same for students and TAs:
* 500 lines of new original code, or original code refactorings, total for all team partners
	* includes comments, display templates, HTML, CSS, Javascript
* 50% code coverage by tests (Jest for react)
* 5 new unit tests
* README file documentation (copy over criteria from Fall Quarter)