package com.ndeanon25;
import java.util.*;

public class HangmanLogic {
    private Set<String> wordList;
    private SortedSet<Character> guesses;
    private int remainingGuesses;

    /**
     * 
     * @param dictionary = the list of possible words of all lengths
     * @param length = the legnth of the word
     * @param guessAmount = the guess amount the user chooses
     */
    public HangmanLogic(List<String> dictionary, int length, int guessAmount){
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

    //returns a String representation of possible target words with the account of the guessed chars 
    public String pattern(){
        if(wordList.isEmpty())
            throw new IllegalStateException();
        return pattern(wordList.iterator().next());
    }
    /**
     * 
     * @param guess
     * @return
     */
    public int record(char guess){
        if(wordList.isEmpty() || remainingGuesses == 0)
            throw new IllegalStateException();   // this throws an IllegalStateException since the game is over and no more guesses are allowed
        else if(!wordList.isEmpty() && guesses.contains(guess))
            throw new IllegalArgumentException(); //this is when a user chooses the same char 
            
        //Makes a HashMap and will group all the words with similar patterns
        Map<String,Set<String>> wordChoices = new TreeMap<String, Set<String>>();
        String initialWordChoice = this.pattern();
        guesses.add(guess); // the guessed char is added to the Set<String> of the users guess
        afterGuessList(wordChoices);

        if(this.pattern().equals(initialWordChoice))
            remainingGuesses--;
        return numOfwords(this.pattern(),guess);
    }

    /**
     * This method counts the occurrences of a specific character in a given String. It scans each character in the String and increments matches whenever
     * it finds a character that matches the provide char guess. It will return matches
     * @param pattern 
     * @param guess
     * @return
     */
    private int numOfwords(String pattern, char guess) {
        int matches = 0;
        for(int i = 0; i < pattern.length(); i++){
            if(pattern.charAt(i) == guesses())
                matches++;
        }
        return matches;
    }

    //This method is used to update the wordList and categorizes words based on their letter pattern. 
    private void afterGuessList(Map<String,Set<String>> wordChoices){

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
