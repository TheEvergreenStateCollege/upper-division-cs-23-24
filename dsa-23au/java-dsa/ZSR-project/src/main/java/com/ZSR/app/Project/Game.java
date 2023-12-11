package com.ZSR.app.Project;
import java.util.*;

public class Game {
    private static int pot = 0;
    private static int bet = 0;
    private static int lastBet = 0;
    private static boolean isEarlyPosition;

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
        ComputerPlayer robotPlayer = new ComputerPlayer("Robot", 100, strategies);

        Deque<Player> table = new ArrayDeque<>();
        table.add(player);
        table.add(computerPlayer);
        table.add(robotPlayer);
        

        
        isEarlyPosition = true;
        if (player instanceof ComputerPlayer) {
            ((ComputerPlayer) computerPlayer).setEarlyPosition(isEarlyPosition);
        }if (player instanceof ComputerPlayer) {
            ((ComputerPlayer) robotPlayer).setEarlyPosition(isEarlyPosition);
        }

        for (int i = 0; i < 5; i++) {
            for (Player p : table) {
                p.drawCard(deck);
            }
        }

        player.sortHand();
        player.showHand();
    

        for (Player p : table) {
            int bet;
            if (p instanceof ComputerPlayer) {
                bet = ((ComputerPlayer) p).compBet(p.getChips());
            } else {
                bet = p.placeBet(p.getChips(), scanner);
                //bet = 0;
            }
        
            pot += bet;
            lastBet = bet;
            System.out.println(p.getName() + " bets " + bet + " CHIPS. POT is at " + pot + " CHIPS.");
        }

        for (Player p : table) {
            if (p instanceof ComputerPlayer) {
                ((ComputerPlayer) p).redraw(deck);
            } else {
                player.redraw(deck, scanner);
                // Logic for human players to redraw
            }
        }

        //player.redraw(deck, scanner);
        player.sortHand();
        player.showHand();
        Rank.HandType handType = Rank.evaluateHand(player.getHand());
        System.out.println("Your hand is a " + handType);

        isEarlyPosition = false;
        if (player instanceof ComputerPlayer) {
            ((ComputerPlayer) computerPlayer).setEarlyPosition(isEarlyPosition);
        }if (player instanceof ComputerPlayer) {
            ((ComputerPlayer) robotPlayer).setEarlyPosition(isEarlyPosition);
        }

        for (Player p : table) {
            int bet;
            if (p instanceof ComputerPlayer) {
                bet = ((ComputerPlayer) p).compBet(p.getChips());
            } else {
                bet = p.placeBet(p.getChips(), scanner);
               // bet = 0;
            }
        
            pot += bet;
            lastBet = bet;
            System.out.println(p.getName() + " bets " + bet + " CHIPS. POT is at " + pot + " CHIPS.");
        }
        player.sortHand();
        // hands are printed and compared, winner declared
        handType = Rank.evaluateHand(player.getHand());
        Rank.HandType CompHand = Rank.evaluateHand(computerPlayer.getHand());
        Rank.HandType RoboHand = Rank.evaluateHand(robotPlayer.getHand());
        // Display the evaluated hand type
        System.out.println("Your hand is a " + handType);
        System.out.println(computerPlayer + "hand is a " + CompHand);
        System.out.println(robotPlayer + "hand is a " + RoboHand);

        //player.showHand();

        //<?> play again with chips from betting rolled over, win counter
        scanner.close();

    }       
}
