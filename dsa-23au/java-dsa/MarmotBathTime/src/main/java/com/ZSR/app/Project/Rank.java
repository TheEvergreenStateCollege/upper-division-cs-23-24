package com.ZSR.app.Project;
import java.util.*;

public class Rank {

    public enum HandType {
        HIGH_CARD, ONE_PAIR, TWO_PAIR, THREE_OF_A_KIND, STRAIGHT, FLUSH, FULL_HOUSE, FOUR_OF_A_KIND, STRAIGHT_FLUSH, ROYAL_FLUSH
    }

    HashMap<Integer, HandType> rank = new HashMap<Integer, HandType>();
    HashMap<Integer, String> tieBreak = new HashMap<Integer, String>();
    
    rank.put(1, HandType.HIGH_CARD);
    rank.put(2, HandType.ONE_PAIR); //HC break, HC break
    rank.put(3, HandType.TWO_PAIR); // HC break, HC break
    rank.put(4, HandType.THREE_OF_A_KIND); //HC break
    rank.put(5, HandType.STRAIGHT); //HC break, perfect tie split pot
    rank.put(6, HandType.FLUSH); //HC break, perfect tie split pot
    rank.put(7, HandType.FULL_HOUSE); //HC break
    rank.put(8, HandType.FOUR_OF_A_KIND); //HC break
    rank.put(9, HandType.STRAIGHT_FLUSH); //HC break,perfect tie split pot
    rank.put(10, HandType.ROYAL_FLUSH); //perfect tie split pot

    tieBreak.put(11, "HIGH_CARD 2");
    tieBreak.put(12, "HIGH_CARD 3");
    tieBreak.put(13, "HIGH_CARD 4");
    tieBreak.put(14, "HIGH_CARD 5");
    tieBreak.put(15, "HIGH_CARD 6");
    tieBreak.put(16, "HIGH_CARD 7");
    tieBreak.put(17, "HIGH_CARD 8");
    tieBreak.put(18, "HIGH_CARD 9");
    tieBreak.put(19, "HIGH_CARD T");
    tieBreak.put(20, "HIGH_CARD J");
    tieBreak.put(21, "HIGH_CARD Q");
    tieBreak.put(22, "HIGH_CARD K");
    tieBreak.put(23, "HIGH_CARD A");

    
     //* tie HashMap will be used in the event of a tie, finding the highest card in the relevant grouping of cards, 
     //* in the unlikely event of a that grouping of cards still tie (like if one or two pairs happen to do so), 
     //* then the next highest relevant grouping will determine the winner (and so on in hypothetical increasingly unlikely tie-breaking scenarios).
     //*  Flushes and straights that result in a perfect tie will make both players winners, pot will be split.
     
    //public isHighCard() 

    public static HandType evaluateHand(List<Card> hand) {
        Collections.sort(hand, Comparator.comparing(Card::getValue));

        HashMap<Card.Value, Integer> counter;
}

