## Inspiration

## How we built it

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
syntax errors 
## Accomplishments that we're proud of

## What we learned

## What's next for the project next quarter

## Built With (your tech stack)

## IF ALL ELSE FAILS COPY PASTE INTO CMND LINE
/usr/bin/env /opt/graalvm-community-openjdk-20.0.2+9.1/b/java -XX:+ShowCodeDetailsInExceptionMessages -cp /workspace/upper-division-cs/dsa-23au/java-dsa/Torsten-David.app/target/classes com.ActivityTor.app.Main