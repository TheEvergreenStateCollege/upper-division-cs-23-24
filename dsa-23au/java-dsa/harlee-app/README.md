# Meteorite Data Analyzer

Meteorite Data Analyzer is a Java program that allows users to analyze and visualize data from a meteorite dataset provided in a CSV file. It provides various options to explore the dataset, such as displaying specific columns, analyzing meteorite masses, exploring meteorite occurrences in a specific year, and viewing credits.

## Inspiration

Astronomy is a passion of mine. Every kid dreams of exploring the stars, to go where no man has gone before. I wanted to learn and absorb everything I could that was related to space. Fiction and non fiction. Every elective in school, every Star Trek series. I loved it all. One of the first things I searched for was a dataset involving space. My research took me to the NASA open data portal, where I had my pick of what I wanted to focus on. I ended up choosing the 35,000 data item set about meteorites recorded in history. Extraterrestrial objects hurtling through space, the Earth's gravity pulling them into our atmosphere, and what remains crashing into the planet. Their name, their mass, their geographical location, and more, recorded on a CSV file. This little menu program encompasses my love and enthusiasm for astronomy, while also show casing the progress I have made in my Data Structures and Algorithms class. 

## Table of Contents

- [Installation](#installation)
- [Usage](#usage)
- [Menu Options](#menu-options)
- [Challenges and Accomplishments](#challenges-and-accomplishments)
- [The Future](#the-future)
- [Credits](#credits)
- [License](#license)

## Installation

1. Clone the repository:

    ```bash
    git clone /workspace/upper-division-cs/dsa-23au/java-dsa/harlee-app
    ```

2. Navigate to the project directory:

    ```bash
    cd meteorite-data-analyzer
    ```

3. Compile the Java program:

    ```bash
    javac com/harlee/app/*.java
    ```

4. Run the program:

    ```bash
    java com.harlee.app.App
    ```

## Usage

Upon running the program, you will be presented with a menu that allows you to choose different options. Follow the on-screen instructions to perform various operations on the meteorite dataset.

Below is a link to a short video showcasing the menu and what it can do.

## Menu Options

1. **Display a specific column from the meteorite dataset:**
   - Choose this option to visualize a specific column from the dataset. You will be presented with a small table of what options you can select.

2. **Analyze meteorite mass:**
   - Find the largest, smallest, and average meteorite masses.

3. **Explore meteorite occurrences:**
   - Find out how many meteorites fell in a specific year and their names.
   - Find the total number of recorded meteorites in the dataset.

4. **View Credits:**
   - See information about the program version, author, and special thanks.

5. **Quit:**
   - Exit the program.

## Challenges and Accomplishments

One of the biggest challenges I face as a student in computer science is the vocabulary and syntax of programming languages. My English vocabulary is a little weak and I have hard time describing scenarios or things specifically. With that in mind, learning a whole new language in computer science is difficult for me. Words like inheritance, iteration, and recursion can slow me down, as I don't exactly know what those words mean. I've seen them, I understand them in a sentence, but I don't know the proper definition. And that is the best way I can describe my struggle with programming. I see it, I can understand it, but I don't have the words to describe or define it. 

With a more practice, patience, and a little confidence in myself, I know I can become more familiar and comfortable in programming languages.

I think my biggest accomplishment in this code was learning how to build the menu. The do while loop had a switch case inside of it. the cases opened up a function to each option. This makes the code at the top cleaner. I had previously tried to cram all the code in for each option, in those individual cases. While it could have worked, it would make things a bit cluttered.

I am a little proud of the title art, I enjoy designing and planning these sort of things. 
## The Future

I would like to continue building onto this in over the next year of my time at Evergreen. The one thing I wanted to add, but did not have enough time for, was a Nearest Neighbor Algorithm. The CSV file comes with the longitudes and latitudes of meteorite locations. I wanted to give the use an option to find the closest neighbor of a given meteorite, or try to find the closest pair in the whole CSV file. I feel with more time and practice, I can do this and add it to the program.

## Credits

- **Author:** Harlee Hair
- **Version:** 1.0
- **Copyright:** © 2023 NCC-1701

Special thanks to:
- Paul and Richard
- The WORMS
- Fellow Evergreen CS Students

For more information, visit [NASA's Open Data Portal](https://data.nasa.gov).

## License

This project is licensed under the [MIT License](LICENSE).