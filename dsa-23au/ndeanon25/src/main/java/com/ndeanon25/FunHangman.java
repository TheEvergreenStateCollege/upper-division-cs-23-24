package com.ndeanon25;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FunHangman {

    //public static final String dictionaryFile = "dictionary.txt";

    public static List<String> readDictionaryFromFile(String filePath) throws IOException{
        List<String> theDictionary= new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
            String line;
            while((line = reader.readLine()) != null) {
                theDictionary.add(line.trim());
            }
        }
        return theDictionary;
    }

    public static void main(String[] args) {
        String filePath = "dictionary.txt";
        try{

            List<String> theDictionary = readDictionaryFromFile(filePath);    
            System.out.println("Welcome to Hangman!");
            System.out.println();
        
            Scanner console = new Scanner(System.in);
            System.out.println("What length word do you want to use?");
            int length = console.nextInt();
        
            if(length < 0 || length > 47){
                System.out.println("Please use a reasonable word length. Thank you.");
                length = console.nextInt();
            }

            System.out.println("How many wrong answers do you want?");
            int guessAmount = console.nextInt();
        
            if(guessAmount < 0 || guessAmount > 26){
                System.out.println("Please choose between 0 and 26");
                guessAmount = console.nextInt();
            } 
            System.out.println();
            List<String> dictionaryList2 = Collections.unmodifiableList(theDictionary);
            HangmanLogic hangman = new HangmanLogic(dictionaryList2,length,guessAmount);

            if(hangman.words().isEmpty()){
                System.out.println("There are no words of that length");
            }
            else
            startGame(console,hangman);
            endGame(hangman);
        
    } catch(IOException e) {
            e.printStackTrace();
        } 
    }

    public static void startGame(Scanner console, HangmanLogic hangman) {
        while (hangman.remainingGuesses() > 0 && hangman.pattern().contains("-")){
            System.out.println("Guesses: " + hangman.remainingGuesses());
            System.out.println("Guessed: " + hangman.guesses());
            System.out.println("Current: " + hangman.pattern());
            System.out.println("Your guess is? ");

            char letter = console.next().toLowerCase().charAt(0);
                if(hangman.guesses().contains(letter)){
                    System.out.println("You already guessed that. ");
                } 
                else{
                    int counter = hangman.record(letter);
                if (counter == 0){
                    System.out.println("Sorry, there are no" + letter + "'s in the word");
                }
                else if(counter == 1){
                    System.out.println("Nice! There is one " + letter);
                }
                else {
                    System.out.println("Great Job! There are " + counter + " " + letter + "'s in the word");
                }
                }
        System.out.println();
        
        }
    }
    public static void endGame(HangmanLogic hangman){
        String answer = hangman.words().iterator().next();
        System.out.println("Answer = " + answer);
        if(hangman.remainingGuesses() > 0) {
            System.out.println("gReaT jOb -_-");
        } else{
            System.out.println("Sorry, you lose ;( ");
        }
    }
    
}
