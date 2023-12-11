package com.ZSR.app.Project;

import static org.junit.Assert.assertTrue;

import static org.junit.Assert.*;

import org.junit.Test;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class PokerTest {
    
    @Test
    public void testCreateStrategyKey() {
        ComputerPlayer player = new ComputerPlayer("TestAI", 100, new HashMap<>());
        List<Card> hand = new ArrayList<>();
        
        hand.add(new Card(Card.Suit.HEARTS, Card.Value.KING));
        hand.add(new Card(Card.Suit.CLUBS, Card.Value.QUEEN));
        hand.add(new Card(Card.Suit.HEARTS, Card.Value.TWO));
        hand.add(new Card(Card.Suit.CLUBS, Card.Value.THREE));
        hand.add(new Card(Card.Suit.SPADES, Card.Value.FOUR));
        player.setHand(hand);
        player.sortHand();
       

        String expectedKey = "KIQU"; 
        assertEquals(expectedKey, player.createStrategyKey(hand));
    }

    @Test
    public void testDetermineGameState() {
        ComputerPlayer player = new ComputerPlayer("TestAI", 100, new HashMap<>());

        // Example scenario: Unopened pot
        String gameState = player.determineGameState(0, 0, 0);
        assertEquals("UnopenedPot", gameState);

        // Example scenario: With limper
        gameState = player.determineGameState(50, 10, 5);
        assertEquals("WithLimper", gameState);

        // Example scenario: Raise in front
        gameState = player.determineGameState(50, 5, 10);
        assertEquals("RaiseInFront", gameState);
    }

    @Test
    public void testChooseActionBasedOnStrategy() {
        Map<String, PokerStrategy> strategies = new HashMap<>();
        strategies.put("TEST", new PokerStrategy("HIGH", "ANY", "Raise", "Call", "Fold", "Check", "Bet", "Call"));
        ComputerPlayer player = new ComputerPlayer("TestAI", 100, strategies);

        // Setting up a mock strategy
        PokerStrategy strategy = strategies.get("TEST");

        // Test for early position, unopened pot
        player.setEarlyPosition(true);
        String action = player.chooseActionBasedOnStrategy(strategy, "UnopenedPot");
        assertEquals("Raise", action);

        // Test for late position, with limper
        player.setEarlyPosition(false);
        action = player.chooseActionBasedOnStrategy(strategy, "WithLimper");
        assertEquals("Check", action);
    }

    @Test
    public void testReadStrategyCSV() {
        StrategyReader strategyReader = new StrategyReader();
        Map<String, PokerStrategy> strategies = strategyReader.readStrategyCSV("/workspace/upper-division-cs/dsa-23au/java-dsa/ZSR-project/src/main/resources/poker_strategy_subset.csv");
        assertNotNull(strategies);
        assertFalse(strategies.isEmpty()); 
    }

    @Test
    public void testIsFlush() {
        List<Card> hand = new ArrayList<>();
        hand.add(new Card(Card.Suit.CLUBS, Card.Value.TWO));
        hand.add(new Card(Card.Suit.CLUBS, Card.Value.THREE));
        hand.add(new Card(Card.Suit.CLUBS, Card.Value.FOUR));
        hand.add(new Card(Card.Suit.CLUBS, Card.Value.FIVE));
        hand.add(new Card(Card.Suit.CLUBS, Card.Value.SIX));

        assertTrue(Rank.isFlush(hand));
    }
}
