package com.ZSR.app.Project;
import java.util.*;

public class Deck {
    private Stack<Card> deck;

    public Deck() {
        deck = new Stack<>();
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Value value : Card.Value.values()) {
                deck.push(new Card(suit, value));
            }
        }
    }
    
    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card deal() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("Cannot deal from an empty deck.");
        }
        return deck.pop();
    }
    
}
