package com.ndeanon25.FinalProjectDSA;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ImpossibleHangman {
    private static boolean wordCountSwitch = false;
    private static int showWordCount; 
    private static boolean showWordsSwitch = false;

    /**
     * I got this off of your DMV project. This basically reads the dictionary text file and puts it in a List<String>
     * @param filePath The path of the Dictionary
     * @return A list of strings representing the 
     * @throws IOException
     */
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
    /**
     * This is my main method that runs the game. This method initializes and manages the game, allowing the player to choose the word length
     * and the number of wrong answers before starting the game. It has try and catch to catch when the user does not put in a valid input for the questions. 
     * Then it creates a game with the chosen parameters. 
     * The main entry point for the Impossible Hangman game.
     * It reads a dictionary of words from a file, initializes the HangmanLogic object, and handles the game flow.
     */
    public static void main(String[] args) {

        //File Path to the dictionary 
        String filePath = "/workspace/upper-division-cs/dsa-23au/java-dsa/ndeanon25/src/main/java/com/ndeanon25/FinalProjectDSA/dictionary.txt";
        try{

            //Read the dictionary from the file and put it in a list of Strings
            List<String> theDictionary = readDictionaryFromFile(filePath); 
            System.out.println();   
            System.out.println("Welcome to Impossible Hangman!");
            System.out.println();

    
            Scanner console = new Scanner(System.in);
            int length;
            
            // Ask the player for the desired word length within a valid range
            while(true){
                try{
                    System.out.println("What word length do you want to use?");
                    length = console.nextInt(); 
                    
                    if(length >= 2 && length <= 23){
                         break;
                    }else{
                        System.out.println();
                         System.out.println("It has to be between 2 and 23");
                    }
                } catch(InputMismatchException e){
                    System.out.println("Invalid input. Please put an integer between 2 and 23.");
                    System.out.println();
                    console.next();
                }   
            }
    
            int guessAmount;

            // Ask the player for the desired guess amount within a valid range
            while(true){
                try{
                    System.out.println("How many wrong answers do you want?");
                    guessAmount = console.nextInt();
                    
                    if(guessAmount >= 2 && guessAmount <= 26){
                         break;
                    }else{
                        System.out.println();
                        System.out.println("Please choose between 2 and 26");
                    }
                } catch(InputMismatchException e){
                    System.out.println("Invalid input. Please put an integer between 2 and 26.");
                    System.out.println();
                    console.next();
                }   
            }

            System.out.println();

            // Create an unmodifiable list from the dictionary
            List<String> dictionaryList2 = Collections.unmodifiableList(theDictionary);

            //Makes the hangman game with the given parameters 
            HangmanLogic hangman = new HangmanLogic(dictionaryList2,length,guessAmount);

            //Extra precaution incase there are no words with the chosen length in the hangman game.
            if(hangman.words().isEmpty()){
                System.out.println("There are no words of that length");
                System.out.println();
            }
            else{
            //Start the game
            startGame(console,hangman);
        }
            //Ends the game
            endGame(hangman);
        
    } catch(IOException e) {
            e.printStackTrace();
        } 
    }
    /**
     * Starts the Impossible Hangman game, allowing the player to make guesses and interact with the game.
     * The method runs in a loop until the game ends either by running out of guesses or by correctly guessing the word.
     * @param console The scanner used to read player input.
     * @param hangman The HangmanLogic object representing the game's logic and state.
     */
    public static void startGame(Scanner console, HangmanLogic hangman) {
        while (hangman.remainingGuesses() > 0 && hangman.pattern().contains("-")){

            if(wordCountSwitch){
                    showWordCount = hangman.words().size();
                    System.out.println("There are " + showWordCount + " words left.");
                    
                }
                if(showWordsSwitch){
                    System.out.println(hangman.words());
                    System.out.println();
                }
            System.out.println("Guesses: " + hangman.remainingGuesses());
            System.out.println("Guessed: " + hangman.guesses());
            System.out.println("Current: " + hangman.pattern());
            System.out.println("What letter do you want to guess? ");

            char letter = console.next().toLowerCase().charAt(0);

                if(hangman.guesses().contains(letter)){
                    System.out.println("You already guessed that. ");
                } else if(!(letter >='a' && letter <= 'z')){
                    System.out.println("Invalid input. Please enter a-z.");
                } 
                else{
                    int counter = hangman.record(letter);
                if (counter == 0){
                    System.out.println("Sorry, there are no " + letter + "'s in the word");
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
    /**
     * This method ends the Impossible Hangman game, displaying the game outcome 
     * @param hangman The game's state. 
     */
    public static void endGame(HangmanLogic hangman){

        //Get the answer word from the game
        String answer = hangman.words().iterator().next();
        System.out.println("Answer = " + answer);
        
        //Check the game outcome and display a winning or losing message accordingly
        if(hangman.remainingGuesses() > 0) {
            System.out.println("GREAT JOB! =]");
        } else{
            System.out.println("Sorry, you lose ;( ");
        }
    }
    
}
