package com.ZSR.app.Project;
import java.util.*;

public class ComputerPlayer extends Player {
    private Map<String, PokerStrategy> strategies;

    public ComputerPlayer(String name, int startingChips, Map<String, PokerStrategy> strategies) {
        super(name, startingChips);
        this.strategies = strategies;
    }

    private String createStrategyKey(List<Card> sortedHand) {
        Card highestCard = sortedHand.get(sortedHand.size() - 1);
        Card secondHighestCard = sortedHand.get(sortedHand.size() - 2);
    
        String key = highestCard.getValue().toString() + secondHighestCard.getValue().toString();
    
        // Check if the cards are of the same suit for suited combinations (e.g., "T9s")
        if (highestCard.getSuit() == secondHighestCard.getSuit()) {
            key += "s";
        }
    
        return key;
    }

    
    public int placeBet(int chips) {
        List<Card> sortedHand = sortHand();
        String strategyKey = createStrategyKey(sortedHand);
        
        int pot = Game.getPot();
        int lastBet = Game.getLastBet();
        int bet = Game.getBet();

        String gameState = determineGameState(pot, lastBet, bet);
        PokerStrategy strategy = strategies.get(strategyKey + gameState); // Strategy key now includes game state

        if (strategy != null) {
            String action = chooseActionBasedOnStrategy(strategy);
            return determineBetAmount(action, chips);
        } else {
            // Stay in the game if no strategy found
            return 0;
        }
    }

    private int determineBetAmount(String action, int chips) {
        switch (action) {
            case "Fold":
                return 0; // Stay in the game
            case "Call":
                return Game.getLastBet();
            case "Raise":
                return Game.getLastBet() + 20; 
            default:
                return 0; // Stay if action is unrecognized
        }
    }

    private String determineGameState(int pot, int lastBet, int bet) {
        if (pot == 0) {
            return "UnopenedPot";
        } else if (lastBet > bet) {
            return "WithLimper";
        } else if (bet > lastBet) {
            return "RaiseInFront";
        } else {
            return ""; // Default case if none of the conditions are met
        }
    }


}

