package com.ZSR.app.Project;
import java.util.*;

public class Rank {

    public enum HandType {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH
    }

    private HashMap<HandType, Integer> ranker;
    private HashMap<String, Integer> tiebreaker;

    public Rank() {
        ranker = new HashMap<>();
        tiebreaker = new HashMap<>();
    
        ranker.put(HandType.HIGH_CARD, 1);
        ranker.put(HandType.ONE_PAIR, 2);
        ranker.put(HandType.TWO_PAIR, 3);
        ranker.put(HandType.THREE_OF_A_KIND, 4);
        ranker.put(HandType.STRAIGHT, 5);
        ranker.put(HandType.FLUSH, 6);
        ranker.put(HandType.FULL_HOUSE, 7);
        ranker.put(HandType.FOUR_OF_A_KIND, 8);
        ranker.put(HandType.STRAIGHT_FLUSH, 9);
        ranker.put(HandType.ROYAL_FLUSH, 10);

        tiebreaker.put("HIGH_CARD 2", 11);
        tiebreaker.put("HIGH_CARD 3", 12);
        tiebreaker.put("HIGH_CARD 4", 13);
        tiebreaker.put("HIGH_CARD 5", 14);
        tiebreaker.put("HIGH_CARD 6", 15);
        tiebreaker.put("HIGH_CARD 7", 16);
        tiebreaker.put("HIGH_CARD 8", 17);
        tiebreaker.put("HIGH_CARD 9", 18);
        tiebreaker.put("HIGH_CARD T", 19);
        tiebreaker.put("HIGH_CARD J", 20);
        tiebreaker.put("HIGH_CARD Q", 21);
        tiebreaker.put("HIGH_CARD K", 22);
        tiebreaker.put("HIGH_CARD A", 23);
}

     
     public static HandType evaluateHand(List<Card> hand) {
         HashMap<Card.Value, Integer> Counter = new HashMap<>();
         for (Card card : hand) {
             Counter.put(card.getValue(), Counter.getOrDefault(card.getValue(), 0) + 1);
         }
         if (isRoyalFlush(hand)) {
            return HandType.ROYAL_FLUSH;
         }
         if (isStraightFlush(hand)) {
            return HandType.STRAIGHT_FLUSH;
         }
         if (hasFourOfAKind(Counter)) {
            return HandType.FOUR_OF_A_KIND;
         }
         if (isFullHouse(Counter)) {
            return HandType.FULL_HOUSE;
         }
         if (isFlush(hand)) {
            return HandType.FLUSH;
         }
         if (isStraight(hand)) {
            return HandType.STRAIGHT;
         }
         if (hasThreeOfAKind(Counter)) {
            return HandType.THREE_OF_A_KIND;
         }
         if (hasTwoPair(Counter)) {
            return HandType.TWO_PAIR;
         }
         if (hasPair(Counter)) {
            return HandType.ONE_PAIR;
         }
         return HandType.HIGH_CARD;
     }
      
     public static boolean isRoyalFlush(List<Card> hand) {
        if (!isFlush(hand)) {
            return false;
        }
        if (
        hand.get(0).getValue() == Card.Value.TEN &&
        hand.get(1).getValue() == Card.Value.JACK &&
        hand.get(2).getValue() == Card.Value.QUEEN &&
        hand.get(3).getValue() == Card.Value.KING &&
        hand.get(4).getValue() == Card.Value.ACE
        ) {
            return true;
        }
        return false;
    }
    
    public static boolean isStraightFlush(List<Card> hand) {
        if (isStraight(hand) && isFlush(hand)) {
            return true;
        }
        return false;
    }    

    public static boolean isFlush(List<Card> hand) {
        Card.Suit suit = hand.get(0).getSuit();
        for (Card card : hand) {
            if (card.getSuit() != suit) 
            return false;
        }
        return true;
    }

    public static boolean isStraight(List<Card> hand) {
        for (int i = 0; i < 4; i++) {
            if (hand.get(i + 1).getValue().ordinal() != hand.get(i).getValue().ordinal() + 1) {
                return false;
            }
        }
        return true;
    }


    private static boolean hasPair(HashMap<Card.Value, Integer> Counter) {
        for (Integer count : Counter.values()) {
            if (count == 2) {
                return true; 
            }
        }
        return false;
    }

    private static boolean hasTwoPair(HashMap<Card.Value, Integer> Counter) {
        int pairCount = 0;
        for (Integer count : Counter.values()) {
            if (count == 2) {
                pairCount++;
            }
        }
        if (pairCount == 2) {
            return true;
        }
        return false;
    }

    private static boolean hasThreeOfAKind(HashMap<Card.Value, Integer> Counter) {
        for (Integer count : Counter.values()) {
            if (count == 3) {
                return true; 
            }
        }
        return false;
    }

    private static boolean isFullHouse(HashMap<Card.Value, Integer> Counter) {
        boolean three = false, two = false;
        for (Integer count : Counter.values()) {
            if (count == 3) three = true;
            if (count == 2) two = true;
        }
        return three && two; 
    }
    
    private static boolean hasFourOfAKind(HashMap<Card.Value, Integer> Counter) {
        for (Integer count : Counter.values()) {
            if (count == 4) {
                return true; 
            }
        }
        return false;
    }

    
    
    
}

