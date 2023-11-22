Changes in Version 1.2:

Refactored v1 to be more structured and logical
Added a function to find the last edited version of both csv files
Added a retry limit of 10 to prevent too many enters
Added a function to output averages, prints out time, distance and cost totals and averages for 4 city ori/destinations
Refactored DataStructure logic to be much easier to read into DriverData2

TODO
1. Need to see if my config logic will pickup the latest csv files in main or shared pipeline
4. Need to adjust option 8 to be more like option 4 (pick keys by index) and maybe add indexing to option 4 keys instead of typing