package com.ZSR.app.Project;
import java.util.*;

public class Game {
    private static int pot = 0;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       
        Deck deck = new Deck();
        deck.shuffle();
        
        Queue<Player> Table;
        Player player = new Player("Player1", 100);
        List<Card> playersHand = player.getHand(); 

        //number of players, add to table
        //go around table and deal hands to players

        for (int i = 0; i < 5; i++) {
            player.drawCard(deck);
        }

        //player hand is printed
        player.showHand();

        //<?>betting and folding
        int bet = player.placeBet(player.getChips(), scanner);
        pot += bet;

        System.out.println("POT is at" + pot + "CHIPS.");
        //player can discard and redraw cards, <?> 1-3, 4 if high card
        player.redraw(deck, scanner);

        // <?> player is notified of other players discards and draws

        // <?> second round betting and folding
        player.sortHand();
        // hands are printed and compared, winner declared
        Rank.HandType handType = Rank.evaluateHand(player.getHand());

        // Display the evaluated hand type
        System.out.println("Your hand is a " + handType);;

        player.showHand();

        //<?> play again with chips from betting rolled over, win counter
        scanner.close();

    }       
}
