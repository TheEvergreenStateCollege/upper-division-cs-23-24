package com.ZSR.app.Project;
import java.util.*;

public class Card {
    enum Suit {
        HEARTS, DIAMONDS, CLUBS, SPADES
    }

    enum Value {
        TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE
    }
    
    
    private final Suit suit;
    private final Value value;
    
    public Card(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
    }

    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value + " of " + suit;
    }
    
    public HashMap<Card.Suit, Integer> flushCheck;
    public HashMap<Card.Value, Integer> counter;

    


}
