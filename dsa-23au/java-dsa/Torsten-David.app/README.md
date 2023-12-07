## Inspiration
The inspiration for this project all came from my apple watch. When we had to brain strom project ideas, I did not know what I wanted to do at all. I was talking with some classmates on the project, they had ideas, I almost wanted to try and join them to make it easier, but I wanted to do something more about me and my interest. So I thought, we are doing data structures and my health app on my phone could easily be stored in a data structure which is what I am sure apples health app does. 

## How we built it
The project was built with Maven.


## How to run our demo
We wanted to answer two questions:
1. Which day had the loudest level of sound?
2. What is the Average Step Count per day?

### Sound Level
to answer this question run our program like this 

```
$ cd /workspace/upper-division-cs/dsa-23au/java-dsa/Torsten-David.app
/workspace/upper-division-cs/dsa-23au/java-dsa/Torsten-David.app$ java -jar ./target/Torsten-1.0-SNAPSHOT.jar sound-levels
```
The program should output the date(s) of the highest sound level.
```
The date of the max sound level was:
Date: 11/4/2023, Sound Level : 84.0

```
### Avg Daily Step Conunt 
to find the average Daily Step Count 
```
$ cd /workspace/upper-division-cs/dsa-23au/java-dsa/Torsten-David.app
/workspace/upper-division-cs/dsa-23au/java-dsa/Torsten-David.app$ java -jar ./target/Torsten-1.0-SNAPSHOT.jar step-count
```
The program should output the average step count.
```
The average daily step count was:
Averge Daily Step Count: 7178.45
```


## Challenges we ran into
With writng any program, it will never be flawless, there will almost certainly be challeneges that come along the day and I can say that for our project. The first challenge was not knowing java syntax well, not knowing the language can certainly hold you back and make writing the program more difficult. I used Ai chat but two problems with that, one, you don't always learn from their answer and two the answer AI gives you does not work sometimes. Another challege we had with the project was with the csv file with our data. I remember spending a whole morning and afternoon with David, Paul, and Richard trying to figure out why my csv file was not reading the data. After many hours though, we figured it out and left the classroom with a smile. Other challlenges we had ran into were with Maven, sometimes, I could not get a successful build, and back at the beginging of the quarter, how to navigate gitpod. 

## Accomplishments that we're proud of
Binary Tree

Consider handling possible errors or edge cases that might occur during the execution of your program. For instance, what happens if the data is missing for a particular day when trying to calculate the average step count or the loudest sound level? Ensure the program handles such scenarios gracefully.

Torsten:
I am proud that we have a working program, this was my first big project in computer science. I am proud how much I have leanred over the weeks such as github, git, and java syntax. 

## What we learned
While working on the final project through out the whole quarter, we learned new material each week to give us options that we could implement in our final project. We learned a variety of data structures, and in our final project, we used a hashmap and a binary search tree. We thought a hashmap would work great because it uses key value pairs, and we used a date and a fitness variable such as steps, we ended having seven hashmaps for each of the vairables. Then, we used a binary search tree to find the maximun sound level because findig the max in a binary search tree is easier because the max is located to the right most node. We leanred how to navigate github and gitpot, we learned how to commmit, push, create, and merge branches. We leanred (still am learning) the improtance of unit tests and how to write them. Unit tests bascially tests certain parts of your program to make sure they work properly. we also have been using AI prompting to help us with the class, particularly how to use ChatGPT. Fianlly, while I still have a lot of practice and things to learn about the langauge java, through out the weeks, I have leanred java snytax a lot better than eleven weeks ago. 

the implementation of the Binary Tree and traversing through each node to find the min and maximum. 


## What's next for the project next quarter
For next quarter, I plan to keep practicing and getting better at java and update the program more. My idea is to include the other vairbles such as resting energy, handwashing etc somehow in the menu option, I want to find a way to exapnd the amount of data I have. At the momment, I have about a months worth of data ending at november 6th 2023. I would love to have two months, three months etc. And just in genreal think of other features to expand the program. The plan is also to use our project to build a web application for users and I plan to have this project part of that. The sos class where we will do this goes through winter and spring quarter. I cannot wait to see what it looks like months from now compared to what it is now.


## Built With (your tech stack)

## IF ALL ELSE FAILS COPY PASTE INTO CMND LINE
Backup Execution Method:
/usr/bin/env /opt/graalvm-community-openjdk-20.0.2+9.1/b/java -XX:+ShowCodeDetailsInExceptionMessages -cp /workspace/upper-division-cs/dsa-23au/java-dsa/Torsten-David.app/target/classes com.ActivityTor.app.Main

## Video Link of our Program running

```
https://asciinema.org/a/AUaPriWXTJ1DXR5z3ffenZQPe 
```