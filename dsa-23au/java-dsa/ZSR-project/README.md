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

### Properties of the Code

### Technology Stack

### Strengths

### Challenges

### What I'm proud of

### What I would like to add

### How to run
