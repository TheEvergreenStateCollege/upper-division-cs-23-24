package com.ndeanon25;
import java.util.*;

public class HangmanManager {
    private Set<String> wordList;
    private SortedSet<Character> guesses;
    private int remainingGuesses;

    /**
     * 
     * @param dictionary = the list of possible words of all lengths
     * @param length = the legnth of the word
     * @param guessAmount = the guess amount the user chooses
     */
    public HangmanManager(List<String> dictionary, int length, int guessAmount){
        remainingGuesses = guessAmount;
        guesses = new TreeSet<Character>();
        wordList = new TreeSet<String>(); 

        //Iterates and adds all the words that will equals the length chosen from user
        for(String word: dictionary){
            if(word.length() == length){
                wordList.add(word);
            }
        }
    }

    // returns a set of the remaining possible words in the dictionary file
    public Set<String> words(){
        return wordList;
    }

    //returns the number of guesses remaining in the game
    public int remainingGuesses(){
        return remainingGuesses;
    }

    //returns a sorted set of the user's letter guesses
    public SortedSet<Character> guesses(){
        return guesses;
    }

    //returns a String representation of possible target words with the 
    //account of the guessed chars 
    public String pattern(){
        if(wordList.isEmpty())
            throw new IllegalStateException();
        return pattern(wordList.iterator().next());
    }
    
    //returns a string of a given word with the unknown char
    //The chars that have not been guessed are replaced with dashes
    // @param word = desired String to be changed
    // @param String = pattern created from given word
    private String pattern(String word){
        String builder = "";
        for(int i = 0; i < word.length();i++){
            if(guesses.contains(word.charAt(i)))
                builder += word.substring(i, i+1);
            else
                builder += "-";
        }
        return builder;
    }



    
}
