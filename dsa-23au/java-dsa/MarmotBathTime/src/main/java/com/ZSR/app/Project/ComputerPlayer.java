package com.ZSR.app.Project;
import java.util.*;

public class ComputerPlayer extends Player {
    private Map<String, PokerStrategy> strategies;
    private boolean isEarlyPosition;


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
    
        if (highestCard.getSuit() == secondHighestCard.getSuit()) {
            key += "s";
        }
    
        return key;
    }

    public int compBet(int chips) {
        List<Card> sortedHand = sortHand();
        String strategyKey = createStrategyKey(sortedHand);
        
        int pot = Game.getPot();
        int lastBet = Game.getLastBet();
        int bet = Game.getBet();
    
        String gameState = determineGameState(pot, lastBet, bet);
        PokerStrategy strategy = strategies.get(strategyKey);
    
        if (strategy != null) {
            String action = chooseActionBasedOnStrategy(strategy, gameState);
            return determineBetAmount(action, chips);
        } else {
            return 0;
        }
    }

    public void setEarlyPosition(boolean isEarlyPosition) {
        this.isEarlyPosition = isEarlyPosition;
    }
    

    private String chooseActionBasedOnStrategy(PokerStrategy strategy, String gameState) {
        String action;
    
        if (isEarlyPosition) {
            switch (gameState) {
                case "UnopenedPot":
                    action = strategy.getUnopenedPotEP();
                    break;
                case "WithLimper":
                    action = strategy.getWithLimperEP();
                    break;
                case "RaiseInFront":
                    action = strategy.getRaiseInFrontEP();
                    break;
                default:
                    action = "Fold"; 
                    break;
            }
        } else {
            switch (gameState) {
                case "UnopenedPot":
                    action = strategy.getUnopenedPotLP();
                    break;
                case "WithLimper":
                    action = strategy.getWithLimperLP();
                    break;
                case "RaiseInFront":
                    action = strategy.getRaiseInFrontLP();
                    break;
                default:
                    action = "Fold"; 
                    break;
            }
        }
    
        return action;
    }
    
    

    private int determineBetAmount(String action, int chips) {
        switch (action) {
            case "Fold":
                return 10; 
            case "Call":
                return Game.getLastBet();
            case "Raise":
                return Game.getLastBet() + 10; 
            default:
                return 20; 
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
            return ""; 
        }
    }


}

