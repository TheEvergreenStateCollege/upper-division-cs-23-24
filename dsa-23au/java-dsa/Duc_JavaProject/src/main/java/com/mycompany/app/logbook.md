ATC control system

This project is of course, as both of you know, is me working on it all by myself. The project taking inspiration from the trip went back to my home country last July
It's for a long time that I haven't comeback to my home country since last few years when Covid starts to bloom. I miss my home very much where most of my relatives were there
And now when I came back here, I remember just how I like to check-in each time I went back and forth, it's definitely a breeze when the trip onboard to the plane was easy
So I decided to recreate that moments when I enjoy myself the most, using what I learned, to create this project, a miniature ATC terminal system that controls planes
The code also build up from the base code of the airport ticket system that I wrote as one of the random code I submitted to Gordon for a lab in somewhere at week 2-3, so some of the features like login database and input flight number to check in the system from the previous code are also appears here and inherited from that previous code



Before starting this, there are some assumption that should be made in order to make some sense about the code or explaining some of the technical difficulties that happen:
-Assume that the process are not really automated (although this has been fix with an infinite loop system, it stills need to make sure)
-Assume that the pilot have to explicitly state out the number of passenger and arrival with departure time, and also assume that each plane has a fixed number of passenger (or size of passenger that the plane can handle) and a fixed arrival, departure time (in the future there might be an assigned method to change the time)
-Assume that the ATC are working with just one plane landing at a time, not multiple planes
-It's somewhat you can say is a primitive system, not a really modern system
-Assume that the airport also has extra runway for emergency, which will explain later



Description of the code:
The code start with a login system that saves username and password to the arraylist (user login database). 
Then after login success, a message will pop up and prompt the user to select what choice (operation) that they want to make.
There are 5 main operations and 8 choices related to them. The 5 main operations are add and delete planes, look at the total plane database, look at the airport status (which shows the current status of waitlist, parking lot and runway), search for the plane in the database by the plane itself or the parking space they parked, and emergency system
The 8 choices are just like that, except that they adding some of the display options with a log off to log out of the system and a failsafe system 
The reason for that is because the code will not fully work if it's just doing 1 operation then having to run them again each time, the pervious data will be lost and running them again means that all of the process are redo which is bothersome + the system also won't record data in the arraylist in each run
Which leads to the implementation of an infinite loop system that break out at the log off if the loop algorithm is false and the same thing to the failsafe (prevents the situation that the logoff did not working and we basically stuck in the loop itself)



For the main operation of the airport, which is adding planes and deleting them by arrival and departure session, this is how it's work:
There are 3 person at the airport that takes the important part, they're pilot, ATC operator and controller
When the pilot request ATC to arrive/depart, they will need to:

For arrival
1.The pilot will state the flight number, number of passenger, arrival and departure time to the operator, which the operator will recorded them into the system
2.The system will have 2 different control portion, the runway and the parking lot, the following order will be executed: plane -> runway -> parking lot
3.The use of seperate portion helps to manage planes easier, since in real life, there wouldn't be an airport with infinite parking space for planes to park immediate, the same to runway
4.First, the code will run the check, see that if there's room in the runway and parking lot, if there's no room in the runway (at least 1), the plane will be sent flying around, putting into the waitlist until they have room, if there's no room in the parking lot, the plane will be stay in the runway, putting into the runway waitlist as a management method
5.Both of the waitlist are simply just a kind of infinite while loop to check that for as long as the runway/waitlist is not empty, if there's a space in the runway/parking, doing the same method as how it add things in the runway, exit the loop, then proceed to the next function. 
6.However, the while loop is also one of the factor that cause the 3rd assumption to be explained out in this case, since while it's running, you can't working on another plane in the same time without have to break or exit the code
7.If there's not any problem, the process should work freely, the plane will be add to both ATC/airport database and advance to the next operation of choice by the operator or exit

For departure
1.For this function, the operator now have to work with both the pilot and the controller, unlike the arrival, since the queue system in the runway have assigned each plane to park next to each other in ascending order (one of the exception is the set method I use, which I will explain in the prototype section later)
2.Because the plane setting is already in the database, this time, the pilot just need to state the flight number to the controller, the pilot will then feed the flight number through the system, initiate a search, if they found a plane with that flight number, the system will display the plane's information that relates to that flight number (flight number, passenger, arrival, departure)
3.The plane prior to search will still keep initial information, which explain for the 2nd assumption
4.The operator will now contacted the controller, which controls what planes parking in which parking lot number (ID), and give him the flight number, the controller will then contact back to the operator and give him the number (ID) of the parking lot that have the plane with the flight number parking in, in which the operator will record in, running the ID search in the same time to confirm what the controller said
5.This explain for one of the display line: "Cojoining with the controller, is the tag correct"
6.After that, the confirmation and the plane search will be tested to see if it's true, if both of them is true, the plane will have the authentication to leave the runway by deleting the parking lot and re-adding to the runway, else, the system will terminate the departure call and send back to the operation selection
7.When the plane is at the runway, the pilot will make one last call to the operator to confirm that the plane is at the runway and requesting to leave the airport, the operator will check one last time, and give the authentication to leave, end the session


The rest of the method are explainable by themselves, I can re-write them here again:
The 2 operation are use to view the ATC plane database, seeing how many planes is parking currently, the total number of plane, and their flight number associate to it
The 3 operation are use to show current information about how many runway/parking lot is used, and what's on the waitlist (this is the waitlist before landing to runway)
The 4 operation is mostly searching for planes in the database, which is already done if you executed the departure options, this is use for explicitly search, although the method will be different since I use the luxury of accessing the ATC database instead of using the airport database
The 5 operation is the same to 4 operation, except that it's execute a parking lot search to search which plane is in that parking lot by listing the parking number, also getting executed in the same time as plane search if initiate the departure options
The 6 and 7 method are emergency system, which I will explain later in the protoype portions, the 7 operation is the same to 3 operation, except that they list the emergency parking used, and it should only be access by admin privilege
The 8 operation is just logging off, pressing any other key will prompt the user to either return by press y or shutdown by press any key



Some things I haven't list out, but it's better to explain:
-The queue and arraylist array, from what I researched, don't have any specific ways to cap the capacity or declared capacity number like how normal arrays did with new int[number], either that or I just don't see a way to, so instead I manually have to cap them by declare a max array variable and cap the size under that in an if-else situation like below, since like I said, there're no infinite amount of parking lot and runway:

if(runway.size() < runwaycap)

-Unlike other projects that I did, this time I decided to make something bizarre in a way to represent real life situation
The airport system that I created also have a different element, as a way to improve security, a police plane, aka 'sky marshal' in my code, has been initially added into the parking lot of my airport and it'll always be called upon initiate an airport variable
The addition of the police plane into the airport system also provide an another way to implement real life counting system (In real life, there is no parking number 0, you usually count them from 1 to maximum amount)
The police plane will have a special flight number and code name, with 6 passenger and working 24 hours, and they always taking up one space at the parking lot, it's the first space, index 0 of the parking array, so initially, if you called the 3 operation, there will always be 1 in the parking lot
The addition also cause the ATC plane database or any passenger plane database to offset to -1, since the police plane takes up a space and we have to remove them out
The police plane however will be deployed out of the parking lot under emergency code in dangerous circumstances by adding in emergency code after initiate a new airport variable
They are represent here:

skymarshal = new Plane("SM911", 6, 000, 000);
emergency = new Plane(911,"SM911");
parkingID.add(emergency);



Special prototypes:
Set plane manually: 
While doing this, I also created a special method to set the incoming plane coming by arrival to a specific parking lot number of choice, this method is used in case if there's a VIP coming from different airport/country and needs to park at a private parking lot or simple far away from normal passenger planes
The method is use after filled in the plane information (created a plane object), there will be a line asking you if you want to manually added them to a specific parking lot, if you choose yes and specify the parking lot that you want to park, the plane will be parked in that
Status: Unsure of stable, which is also a lesson about if i know what method to use, but it should be working 80%

Emergency System:
In case of an emergency, the operation will choose the 6 operation, alerting the airport about danger scenario (i.e, if a plane is stuck on one of the runway for various reason, could be lethal or not)
In this method, the airport system will switch from using queue system (first in, first out) for runway and parking lot to a stack system (first in, last out) with the plane that are rendered inoperable or the plane came first will be the last thing to remove out of the parking lot or runway to maintain safety for any plane on site
Sky marshal will be deployed in this process, which leads to the parking lot will not offset to 1 (use 1 space) like normally
The process is all about deploying plane out to hangar or fly away, so there's not much things to do except to press confirmation
While it does looks like a dooms day simulator, it's one of my only thing to think of the usage of stack function (and no it's not)
Status: stable, although trivial error may occur, it works as intended

Username/Password saving method:
I was thinking about a password management system which saves the username/password on first try, and the next time they log on, they'll already be saved, just need to type the exact password in to continue until choosing operation 8 to logoff
Status: Unstable, the system only save password and username, not using it (I don't have time to develop till deadline, which is something my fault is ay)



Thought process (may not necessary need, you can skip this, unless you want to know more how I develop the code):
ATC control:
They can add planes with runway via arrival
They can remove planes with runway via departure
A plane will have flightcode, number of passenger, arrival, departure time

After having a plane, it'll transfer to the runway queue, seperate from that
When reaching the maximum number of runway, exit to waitlist

the airport will have following method: add plane, delete plane, search, add/del by parking/runway

First they will log in their user and pass
Second they will enter the flight code to search for a plane (beta)

after the enter scan it's complete, we want to put in a check for as long as the list is not empty
keep continue until they meet the condition


What I learned:
don't cast directly, use variable and assign to them, sometimes they recognize that way
be mindful when assigning variable, especially variable using same attributes or "this" method. you may assign the wrong one or wrong type
with terminal control, everything can be solve with an infinite loop and a failsafe
the use of some of the unknown method save me from failing, especially continue
know how to use method or class function, I got a classcastexception, which is something I never have, after tracking, turns out that I use priority queue for the queue, while it should be linkedlist instead
