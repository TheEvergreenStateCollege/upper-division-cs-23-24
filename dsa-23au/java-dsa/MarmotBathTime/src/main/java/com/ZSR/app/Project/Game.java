package com.ZSR.app.Project;
import java.util.*;

public class Game {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Deck deck = new Deck();
        deck.shuffle();
        Queue<Player> Table;
        Player player = new Player("Player1");
        List<Card> playersHand = player.getHand(); 

        //number of players, add to table
        //go around table and deal hands to players

        for (int i = 0; i < 5; i++) {
            player.drawCard(deck);
        }

        //player hand is printed
        player.showHand();

        //<?>betting and folding

        //player can discard and redraw cards, <?> 1-3, 4 if high card
        player.redraw(deck, scanner);

        // <?> player is notified of other players discards and draws

        // <?> second round betting and folding

        // hands are printed and compared, winner declared

        player.showHand();

        //<?> play again with chips from betting rolled over, win counter
        scanner.close();

    }       
}
