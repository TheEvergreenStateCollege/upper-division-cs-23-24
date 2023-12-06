package com.ndeanon25;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.ndeanon25.FinalProjectDSA.HangmanLogic;


public class HangManTest {
    private HangmanLogic game;
    
    @Before
    public void setUp(){
        //We create a new Hangman game instance before each test
        //This list will be used to create a dictionary of words for the Hangman game.
        List<String> dictionary = new ArrayList<>();
        dictionary.add("apple");
        dictionary.add("banana");
        dictionary.add("cherry");
        dictionary.add("grape");
        dictionary.add("hello");
        dictionary.add("bangs");
        dictionary.add("cabin");
        dictionary.add("faced");
        dictionary.add("pacer");
        dictionary.add("not");
        dictionary.add("talk");
        dictionary.add("hero");
        dictionary.add("quick");
        
        game = new HangmanLogic(dictionary, 5, 6);
        
    }
    @Test
    public void testStartofGame(){
        int numOfwords = game.words().size();
        //This is should size 8 because the game has started only taking in the words that is length 5. 
        assertEquals(8, numOfwords);
    }
        @Test
        public void guessAmount(){
        int numOfwords = game.words().size();   
        //This test is expected to test the remaining guesses 
        assertEquals(6,game.remainingGuesses());
        game.record('a');
        game.record('b');
        assertEquals(4,game.remainingGuesses());
        
        //Uses assertTrue to check if the set of guessed characters contains 'a' and 'b'.
        assertTrue(game.guesses().contains('a'));
        assertTrue(game.guesses().contains('b'));

        //Checks if the number of words in the game's word list has decreased after making guesses.
        assertTrue(numOfwords > game.words().size());
    }

    @Test
    public void testPattern(){
        assertEquals("-----", game.pattern());

    }

    @Test
    public void testRecordCorrectGuess(){
        assertEquals(6, game.remainingGuesses()); 
        int numOfOccurrences = game.record('a');
        assertEquals(5,game.remainingGuesses());
        assertEquals(1,numOfOccurrences);
        assertTrue(game.pattern().equals("-a---") || game.pattern().equals("a----"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRecordInvalidGuess() {
        game.record('x');
        game.record('z');
        assertEquals(4,game.remainingGuesses());
        // Should throw IllegalStateException
        assertEquals(0, game.record('x'));

    }

    @Test(expected = IllegalStateException.class)
    public void testRecordAfterGameOver() {
        game.record('s');
        game.record('w');
        game.record('v');
        game.record('x');
        game.record('z');
        game.record('q');
        // Should throw IllegalStateException
        game.record('a');
    }


    @Test
    public void testNumOfwords() {
        assertEquals(2, game.numOfwords("app--", 'p'));
        assertEquals(1, game.numOfwords("app--", 'a'));
    }

    @Test
    public void testAfterGuessList() {
        game.record('a');
        Set<String> wordSet = game.words();
        assertFalse(wordSet.contains("hello"));
        assertFalse(wordSet.contains("apple"));
        assertFalse(wordSet.contains("grape"));
        assertTrue(wordSet.contains("pacer"));

    }

    @Test
    public void testGetLargestKey() {
        // Create a Map containing patterns and sets of similar words
        Map<String, Set<String>> similarWords = new HashMap<>();

         // Create a Set containing words ("apple" and "banana") and associate it with the pattern "a---e"
        Set<String> set1 = new HashSet<>();
        set1.add("apple");
        set1.add("banana");

        // Create another Set containing the word "cherry" and associate it with the pattern "c----y"
        Set<String> set2 = new HashSet<>();
        set2.add("cherry");

        // Add both patterns and their associated sets to the similarWords map
        similarWords.put("a---e", set1);
        similarWords.put("c----y", set2);

        // Call the getLargestKey method and check the result
        assertEquals("a---e", game.getLargestKey(similarWords));
    }

        //The purpose of this test case is to ensure that the pattern method correctly generates and updates the word pattern,
        //and that it produces the expected patterns before and after guessing a letter.
    @Test
    public void testPatternMethod() {
        String pattern = game.pattern();
        
        // Check if it's all dashes for a 5-letter word
        assertTrue(pattern.matches("-{5}")); 
        
        game.record('a');
        String pattern2 = game.pattern("apple");
        assertEquals("a----", pattern2);
    }
}
