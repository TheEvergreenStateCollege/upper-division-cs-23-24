# Five-Card Draw Command Line Poker

```mermaid

flowchart TB
    A[Start Game] --> B[Initialize Game Components]
    B --> C{Is it Player's Turn?}
    C -->|Yes| D[Player Makes Move]
    C -->|No| E[Computer Move]
    D --> F[Process Player's Move]
    E --> G[Process Computer Player's Move]
    F --> H{Is Round Over?}
    G --> H
    H -->|Yes| I[Calculate and Award Pot]
    H -->|No| C
    I --> J{Is Game Over?}
    J -->|Yes| K[End Game]
    J -->|No| B
    K --> L[Display Results and Exit]

```
This project is a one-player poker simulator, played from the terminal of an IDE. The player using the number pad on their keyboard to place bets and decide which card, if any, they wish to discard and redraw.
It features two computer players that make decisions based on the two highest cards in their hands, the current phase of gameplay, and the betting behaviors of the other players. 

### What I Learned
This is so far the longest program I have written, so I learned a lot about managing a codebase with many different classes and methods that all have an effect on each other, and how to solve problems when certain features I had designed didn't mesh well with other ones in the way that I had planned. I also learned, in the process of writing this program, how to use several concepts that I have never tried using before, including enums, switch cases, and game states. Overall, the biggest takeaway I have from this project is the importance of managing expectations and setting realistic goals.

### Challenges
I initially wanted to create a game that could be much more modular, with multiple sets of rules so that the player could play a variety of card games with one "deck" so to speak. Once I actually started writing the program, I realized that such a goal was beyond my ability and knowledge to produce, so I scaled back to a single choice of game, five-card draw. Even then, I found myself unable to implement every feature that I would have liked to put into the game. As the game is right now, The player has to determine themselves who won the round and there is no continuity of betting or awarding out the pot. There is also no easy way to determine the outcome of a tie at the moment. The player would have to use the debugger and look at the computer player's hands in order to do so. 

Besides management of scope, I found it rather difficult to properly program the computer players. With the human player, every decision was one that I could manage and keep track of every step of the way, as it was part of an interactive process, but with the computer players it was not nearly as easy to manage. Each decision I would take forgranted when playing myself had to be accounted for in the code, and without the breakpoints and feedback that the scanner entry and print statements would provide on the command line, debugging needed to be a more involved process. This plays back into my previous challenge of setting achievable goals, as any action or reaction that the computer players could take needed to be anticipated in the code. Because of this,he compter players are a little more simplistic than I had originally wanted them to be. 

### Strengths and What I am Proud of
Having said that, the computer players do, in fact, place bets based on the bets of the other players and current stage of the game, as well as exchange their cards if they have a junk hand. I'm proud that I was able to put together a program that is functional and multi-faceted, even if it is not as fleshed out as I would have initially liked to have made it.

### Invariant and Properties of the Code
I used enums for the values and suits of the cards, as well as possible hand rankings. Enums are defined sets of constant values, and in my project they are applied to every card object as a core part of the hand sorting, ranking, discard process, and gameplay overall. These Card objects always have valid value and suit enums, so I think it's fair to call those the main invariants of the project. These enums define the only permissible values that a Card object's suit and value can take, and once a Card is created, the value and suit it is created with will always represent that card.

The deck in the game is made using a stack, as that seemed fitting. During runtime, 52 cards are created, 13 of each of the 4 suits with one for each value per suit, just like with a regular deck of cards, sans joker. These are all pushed onto the stack and shuffled, and then used to fill the hands of the three players, which are ArrayLists. The players are put into a deque called "table" that is used for turn order, which, if I am being honest, is only a deque because I realized that I didn't have a proper fourth data structure or algorithm to satisfy the requirements of the project (since ArrayLists do not count). A boolean flag (isEarlyPosition) is set to true for the computer players bfore the first round of betting, then set to false after the redraw phase and before the second round of betting, and this plays into their decision-making logic. All player hands are sorted using insertion sort before they are evaluated using a hash map to count their cards and determine the hand type.

### Technology Stack
This project was written in Java, using git for version control, junit for testing, and VSCode for the actual writing of the code.

### What I would like to add
The first thing I would do would be to finish the ranking system, so that a proper winner could be determined and ties could be broken. Second, I would make the game replayable, with chips being rolled over into the next iteration of gameplay, and probably a win counter as a nicety. After that, I would like update the player logic to make them a little more advanced, as well as make it possible to add more computer players. Those would be the main smaller changes.

As for bigger changes, I would like to implement a GUI to make the game more enjoyable to play using a library such as Swing, JavaFX, or something similar. 

### How to run
1. Clone the repository:

    ```bash
    git clone /workspace/upper-division-cs/dsa-23au/java-dsa/ZSR-project
    ```
2. Navigate to the project directory:

    ```bash
    cd dsa-23au/java-dsa/ZSR-project/src/main/java/com/ZSR/app/Project
    ```
3. Compile the Java program:

    ```bash
    javac *.java
    ```
4. Run the program:  (This is not working for some reason)

    ```bash
    java com.ZSR.app.Project.Game
    ```
5. Follow the prompts in the terminal to play.

