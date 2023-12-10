# Faulkdf & DawsonWhi's Spotify Data Analysis Project.

## **Overview:**

The goal of this project was to learn how to search through our individual Spotify data, that we acquired directly from our accounts usage of the platform. For those who are unaware, Spotify is a music streaming platform that is available cross-platform and on the web. Users pay a subscription to access millions of songs and podcasts that can be streamed whenever the user has an internet connection, or they can download for offline use. More information can be found at [spotify.com](https://www.spotify.com/us/about-us/contact/)

Both Dante(faulkdf) and Dawson(DawsonWhi) have been using Spotify for daily music streaming for a number of years. We thought it would be fun to download a copy of our personal data, and see if we find anything that surprises us. 

These scripts can be very easily modified to search through your own Spotify dataset, Reachout to [faulkdf](https://github.com/faulkdf) or [DawsonWhi](https://github.com/DawsonWhi) on GitHub if you would like more information on using this project to search your data. 

To run this project, you will want to set your terminal path to be `/workspace/upper-division-cs/dsa-23au/java-dsa/faulkdf-app/DataProject` and run command `python ProjectMenu-Dawson.py` for Dawsons data and `python ProjectMenu-Dante.py` for Dantes data.

## **Running the project:**

Once the project is running, you will get dialog printed to the terminal, with options on which script to run for specific data analysis. The prompt will look like this:

```
Select a script to run:
1. Show Longest Played Track
2. Show Track With Longest Time Listened To
3. Show Shortest Played Track
4. Show Most Played Track
5. Search Tracks Per Country
0. Exit

Enter the number of the script to run (or 0 to exit): 
```
#### **Option 1:**
`Show Longest Played Track` searches the data for an *individual* track with the highest value of `ms_played` that it finds in the corresponding dataset.  Sample output might look like this:

```
Best match with the highest 'ms_played':
Track Name: Free Bird
Album Artist: Lynyrd Skynyrd 
Time Played: 548000 milliseconds (9.13 minutes)
```

#### **Option 2:**
`Show Track With Longest Time Listened to` searches the dataset for the track with the highest cumulative number of `ms_played` across all of its occurrences, and prints the best result to the terminal.  Sample output looks like this: 

```
Longest time spent on a single track:
Track Name: Seasons of Love
Artist: Original Broadway Cast of Rent
Total Time Played: 31536000000 milliseconds (525600.00 minutes)
```

#### **Option 3:**
`Show Shortest Played Track` Searches the dataset, and displays the best match for a track with the least `ms_played` Sample output looks like this:

```
Best match with the least 'ms_played':
Track Name: Friday
Album Artist: Rebecca Black
Time Played: 0 milliseconds (0.00 minutes)
```

#### **Option 4:**
`Show Most Played Track` Finds and displays the track that has the most occurrences out of all the tracks in the dataset, and it prints the the track name and artist, as well as the total cumulative number of times the track was played. Example:

```
Most played track:
Track Name: Never Gonna Give You Up
Artist: Rick Astley
Count: 256
```

#### **Option 5:**
`Search Tracks Per Country` opens up a sub-dialog box that allows users to input a two letter country code to search how many tracks were played in a specific country. 

```
Enter two letter country code: 
```
Example inputs: 

- US = United States
- CA = Canada
- FR = France
- IT = Italy

User input is *not* case sensitive. 
Example output:
```
Enter two letter country code: US
Tracks listened to in ‘US’: 1776
```
#### **Option 0:**
`Exit` Does exactly what is expected. It terminates the processes of this script. 

## **The Dataset:**

The data this project is searching through is stored in JavaScript Object Notation (JSON) files that were downloaded through a request of our personal Spotify data. These files were modified to remove the decrypted IP address of the devices that streamed the track originally. The script that was used to delete these entries can be found at `/upper-division-cs/dsa-23au/java-dsa/faulkdfdawsonwhi-app/PythonMassDelete/mass_delete.py` 

A sample entry from the dataset looks like this:

```JavaScript
{
      "ts": "2023-09-21T01:05:32Z",
      "username": "dantefaulk2002",
      "platform": "ios",
      "ms_played": 252641,
      "conn_country": "US",
      "user_agent_decrypted": "unknown",
      "master_metadata_track_name": "September",
      "master_metadata_album_artist_name": "Earth, Wind & Fire",
      "master_metadata_album_album_name": "The Best Of Earth, Wind & Fire Vol. 1",
      "spotify_track_uri": "spotify:track:2grjqo0Frpf2okIBiifQKs",
      "episode_name": null,
      "episode_show_name": null,
      "spotify_episode_uri": null,
      "reason_start": "clickrow",
      "reason_end": "trackdone",
      "shuffle": false,
      "skipped": false,
      "offline": false,
      "offline_timestamp": 1695343479,
      "incognito_mode": false
    },
```

All of the data was processed and put into `/finalVersion-danteData/SpotifyData` or `/finalVersion-dawsonData/SpotifyData` The Dataset is broken up into multiple files, with each one having ~17,000 entries. 

## **Flowchart of the project:**

```Mermaid
graph TD
  subgraph finalVersion-Dante or DawsonData
    A[LongestPlayed.py]
    B[LongestTimeSpentListening.py]
    C[ShortestPlayed.py]
    D[MostPlayed.py]
    E[SearchCountry.py]
  end

  A -->|Run| F[Display Data]
  B -->|Run| F
  C -->|Run| F
  D -->|Run| F
  E -->|Run| F1[User Input Country Code]

  F1 -->|Continue| F

  F -->|Return to ProjectMenu| G[Select Script 1-5, or Exit 0]

  G -->|Select| A
  G -->|Exit| H[Process Terminated]

```

## **Tests:**

A whole array of test cases for each individual script in the project can be found at `upper-division-cs/dsa-23au/java-dsa/faulkdfdawsonwhi-app/DataProject/tests`

These tests test each individual script that was written for the project, and gives you runtime of the processes. The test dataset is still in JSON format, with very clearly readable cases for what each script is designed to find. 
Upon completion of the test, relevant information will be printed to the console. 

To run the tests, set your terminal path to be `upper-division-cs/dsa-23au/java-dsa/faulkdfdawsonwhi-app/DataProject/tests` and run command `python -m unittest <FILENAME>.py`
