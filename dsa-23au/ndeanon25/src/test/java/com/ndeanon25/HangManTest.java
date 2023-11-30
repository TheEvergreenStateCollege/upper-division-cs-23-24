package com.ndeanon25;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.ndeanon25.FinalProjectDSA.HangmanLogic;


public class HangManTest {
    private HangmanLogic game;
    List<String> dict = new ArrayList<>();
    
    @Before
    public void setUp(){
        //We create a new Hangman game instance before each test
        List<String> dictionary = new ArrayList<>();
        dictionary.add("apple");
        dictionary.add("banana");
        dictionary.add("cherry");
        dictionary.add("grape");
        game = new HangmanLogic(dictionary, 5, 6);
    }
    @Test
    public void testRemainingGuessesAndGuesses(){
        int numOfwords = game.words().size();
        game.record('a');

        //Tests
        assertEquals(6,game.remainingGuesses());
        game.record('b');
        assertEquals(5, game.remainingGuesses());
        assertTrue(game.guesses().contains('a'));
        assertTrue(numOfwords > game.words().size());
    }

    @Test
    public void testRecordIncorrectGuess() {
        int remainingGuesses = game.remainingGuesses();
        int wordListSize = game.words().size();

        int numOfOccurrences = game.record('x');

        assertEquals(remainingGuesses - 1, game.remainingGuesses());
        assertTrue(game.guesses().contains('x'));
        assertEquals(wordListSize, game.words().size());
        assertEquals(0, numOfOccurrences);
    }

    @Test
    public void testNumOfwords() {
        String pattern = "a-pl-";
        int numOfOccurrences = game.numOfwords(pattern, 'a');
        assertEquals(1, numOfOccurrences);
    }

    @Test
    public void testAfterGuessList() {
        game.record('a');
        Set<String> wordSet = game.words();
        assertFalse(wordSet.contains("banana"));
        assertFalse(wordSet.contains("apple"));
        assertTrue(wordSet.contains("grape"));

    }

    @Test
    public void testPattern() {
        String pattern = game.pattern();
        assertTrue(pattern.matches("-{5}")); // Check if it's all dashes for a 5-letter word
    }
}
