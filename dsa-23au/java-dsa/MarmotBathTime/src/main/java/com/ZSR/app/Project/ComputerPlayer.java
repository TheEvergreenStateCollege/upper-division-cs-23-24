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

        String L1 = highestCard.getValue().toString();
        String L2 = secondHighestCard.getValue().toString();
    
        String key = L1.substring(L1.length() - 1) + L2.substring(L2.length() - 1);
    
        // Check if the cards are of the same suit for suited combinations (e.g., "T9s")
        if (highestCard.getSuit() == secondHighestCard.getSuit()) {
            key += "s";
        }
    
        return key;
    }

    private String chooseActionBasedOnStrategy(PokerStrategy strategy) {
        // Determine the current position of the computer player (Early Position or Late Position)
        // For simplicity, let's assume it's always Early Position (EP)
        String position = "EP"; // This should be dynamically determined based on the actual game state
    
        String action;
        if (position.equals("EP")) {
            // Example: Choose between unopenedPotEP, withLimperEP, and raiseInFrontEP based on the game state
            action = strategy.getUnopenedPotEP(); // Replace this with the correct field based on the game state
        } else {
            // Example for Late Position (LP)
            action = strategy.getUnopenedPotLP(); // Replace with appropriate LP strategy
        }
    
        return action;
    }

    
    public int compBet(int chips) {
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
                return Game.getLastBet(); // Stay in the game
            case "Call":
                return Game.getLastBet() + 10;
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

