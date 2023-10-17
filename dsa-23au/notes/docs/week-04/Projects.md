# Final Project Updates

This is a work in progress in Week 4. Please keep evolving, revision, iterating,
refining your projects. We'll check in again in Week 6, and I'll give you 5 minutes
per team to lead a progress update in class, with 5 minute Q&A and discussion.

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

## Duc and Julian and Quinn and Damiana

Changemakers: team ideas

* Make a video game.
* Survey of gamers at Evergreen for their favorite hardware, etc.

Changemakers process:

* Dialogue
* Active listening

Level 4 listening: repeating back to make sure you understood.

"Opening your own pre-existing plans and thoughts to be adjusted by another person."

Iteration: every plan or understanding is going to be subjected to re-iterative revision.
Successive refinement of the idea within that vehicle.

Customer collaboration, customer visits, flexibility, adaptability, user-centered design, cross-functional teams

Prompt for Changemaker students: on Tuesday presentation, consider how to apply
these and your

Possibilities: Agile development, version control systems, code reviews

## Richard Weiss

Code reviews are often stressful and don't reflect the above principles of active listening, understanding human context, open discussion in a group.

## Dawson and Dante
noting which music they listen to, keeping a spreadsheet for a long time.
liked songs on Spotify, 3000?
connected to last.fm which tracks every song listened to. possible last.fm API
is it possible to export your data from Spotify? Ethical / privacy considerations?

### Question from Quinn
Are you also gathering songs that are skipped?

### Question from Paul Swisher
Are Fridays, or days of new music releases, more likely to have songs added?

What is the most likely time of day songs are added?
What music do I dislike?

### Jonathan
working with Dawson, Dante, Trinity
collecting crime reports from agencies in NY
splitting it into index of property crime
murder, rape, aggravated assault
burglary, larceny, motor vehicle theft

Example Input Row:
Crime(isMurder, isRobbery, isAggroAssault, isRape, isPerpetratorKnown, location, time, )
CrimeRegion(isHigherThanNationalAvg, countyName, )

Might limit to high-crime rate regions.

Which counties have the highest crime rate in the state / region?

How does this correlate with firearm registration rate or population (density) for that county?

## David Dunston
open to teammates
personal note-taking from phone, collected from 2012/2017, over 309 
can apply to other people's notes, useful for creatives (writers)
to draw visual synopsys

Example Input Row:
Note(creationDateTime, modificationDateTime, wordCount, isSongIdea, isInformation, isTodo)

Map<Integer,List<Category>> categoriesByGranularity;

Possible auto-tagging, or clustering notes by similarity, suggest keywords

## Joe Granville
circadian system measurements.
helping Stella with EEG

beginning of Global Business Challenge this morning.
some data from farm shipments or proteinaceous animal feed (from insects)

in Changemakers, just broken up into big teams.

timelapse photos from cactus growing

## Jonah Eadie
wanting to join a project or team
possible dataset idea: company profits by country
suggestion: join Juniper's project

## Sean Sellers
Changemakers
possible dataset idea: tree species recommendations, branching, leaf size, soil type, etc.

## Ethical Concerns of Data Gathering Research

If your project involves gathering data from others (not yourself),
you should consider the following and address them in your project
How to ask for and record consent of subjects
Cybersecurity concerns: security / privacy regarding gender, race, ...
HIPAA: government policy
FERPA: 

https://www.evergreen.edu/offices-services/institutional-review-board/evergreen-irb-policy

