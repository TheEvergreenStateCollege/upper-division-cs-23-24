package com.ZSR.app.Project;
import java.util.*;

public class Game {
    private static int pot = 0;
    private static int bet = 0;
    private static int lastBet = 0;

    public static int getPot() {
        return pot;
    }

    public static int getBet() {
        return bet;
    }

    public static int getLastBet() {
        return lastBet;
    }
    public static void main(String[] args) {
        String filePath = "dsa-23au/java-dsa/MarmotBathTime/src/main/resources/poker_strategy_subset.csv"; 
        StrategyReader strategyReader = new StrategyReader();
        Map<String, PokerStrategy> strategies = strategyReader.readStrategyCSV(filePath);
        Scanner scanner = new Scanner(System.in);
       
        Deck deck = new Deck();
        deck.shuffle();
        
        
        Player player = new Player("Player1", 100);
        ComputerPlayer computerPlayer = new ComputerPlayer("AI", 100, strategies);
        List<Card> playersHand = player.getHand(); 
        
        Queue<Player> table = new LinkedList<>(); 
        table.add(player);
        table.add(computerPlayer);

        

        //number of players, add to table
        //go around table and deal hands to players

        for (int i = 0; i < 5; i++) {
            player.drawCard(deck);
            computerPlayer.drawCard(deck);
        }

        //player hand is printed
        player.showHand();

        //<?>betting and folding
        bet = player.placeBet(player.getChips(), scanner);
        pot += bet;
        lastBet = bet;
        System.out.println("POT is at " + pot + " CHIPS.");
        //player can discard and redraw cards, <?> 1-3, 4 if high card
        player.redraw(deck, scanner);
        player.sortHand();
        player.showHand();
        Rank.HandType handType = Rank.evaluateHand(player.getHand());
        System.out.println("Your hand is a " + handType);
        // <?> player is notified of other players discards and draws
        bet = player.placeBet(player.getChips(), scanner);
        pot += bet;
        lastBet = bet;
        System.out.println("POT is at " + pot + " CHIPS.");
        // <?> second round betting and folding
        player.sortHand();
        // hands are printed and compared, winner declared
        handType = Rank.evaluateHand(player.getHand());

        // Display the evaluated hand type
        System.out.println("Your hand is a " + handType);

        player.showHand();

        //<?> play again with chips from betting rolled over, win counter
        scanner.close();

    }       
}
