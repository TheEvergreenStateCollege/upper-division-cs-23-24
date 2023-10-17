## Input Dataset from CSV
List<T>

Book(author, publisher)

## Nathan McLaughlin, Paul Swisher
Example Input datapoint:
Commute(startCity, endCity, mileage, time)

Map<String,Map<String,Integer>> driverNameToDriverData;
Does weather affect drive time equally?

## Devin 

Example Input datapoint:
DiscordDayMessages(messageCount, day)

Array<Integer> messageCountByDay
have a way of mapping from day to index in array
What are the major events happening on this Discord server and when?

## Juniper
Looking for teammates
Global distribution of value-added for G20 (20 companies that add the most to global economy) from 2016-2022.

Example Input Row:
CountryPercentGlobalValue(countryName, year, percentValue, avgManufacturingWages)

algorithm: loop over combinations of columns, calculate correlations,
https://en.wikipedia.org/wiki/Correlation_coefficient
Map<?,?>

Which change is most correlated with change in wages over time, by country?

## Dylana
Looking for a team
Interested in compbio with plant reflexes

## Torsten
Health tracking on Apple app, steps, calories

Example Input Row:
DayStats(date, stepCount, caloriesBurned, heartRate)

Heap<DayStat> lowestHeartRateSoFar

TBD: question about health

## Nathan Deanon
Open to teammates
Choosing between two projects:

### lottery winning numbers since 2002

Example Input Row:
LotteryDrawing(date, winningNumbers, contest)

Array<Integer> mostFrequentNumbers 

### Hangman game
dictionary of 400,000 words

tree, breaks into letters with A's and not A's

LotteryDrawing(date, winningNumbers, contest)

Example Input Row:
Word(term, definition, ...)

Tree<Letter,Word> wordsByBeginningLetter

## Damiana
Changemakers idea: survey of students and community makers

Example Input Row:
CommunitySurveyResult(respondent, whatAreProblemsAtEvergreen, possibleSolution1, ...)

### Suggestion from Stella:
matplotlib in python for graphing

Suggestion from Joe:
Graph with edges waited by number of responses

Can put into a SQL server.

Map<?,Integer> surveyProblemFrequencyCount

### Suggestion from NathanMCL

Tri-plot, from matplotlib

### Suggestion from Quinn:
Use graph, weighting of edges is how frequently those two nodes appear in same response.
Nodes are different survey problems.

### Suggestion from Paul Pham

## Stella 
open to teammates
map brainwaves, collect raw data, clean it up, put it into graph
Muse headset, need to register SDK

Example Input Row:
EEGMeasurement(subject, timestamp, deltaDTP9, deltaAF8, ... )

Does any of it matter?

## Dominic Severe
open to teammates
Look at data on a hard drive in a meaningful way

Example Input Row:
WholeHardDriveMetadata(id, snapshotTime, ssdOrHD, systemCpuLoad, systemIOLoad, )
HardDriveFile(hardDriveId, filename, location, size, lastModTime, lastReadTime)

What files are taking up the most space, or are unused for the longest time?

## Quinn Allen
open to teammates
Changemakers, supporting IT needs for students, helping teams determine minimum viable 

Example Input Row:
ProjectSupported(teammate1, teammate2, projectType, ITneed1, ITneed2,)

## Ethical Concerns of Data Gathering Research

If your project involves gathering data from others (not yourself),
you should consider the following and address them in your project
How to ask for and record consent of subjects
Cybersecurity concerns: security / privacy regarding gender, race, ...
HIPAA: government policy
FERPA: 

https://www.evergreen.edu/offices-services/institutional-review-board/evergreen-irb-policy

