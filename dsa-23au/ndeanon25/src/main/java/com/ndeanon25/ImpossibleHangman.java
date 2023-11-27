package com.ndeanon25;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class ImpossibleHangman {

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

    public static void main(String[] args) {
        String filePath = "/workspace/upper-division-cs/dsa-23au/ndeanon25/src/main/java/com/ndeanon25/dictionary.txt";
        try{
            List<String> theDictionary = readDictionaryFromFile(filePath); 
            System.out.println();   
            System.out.println("Welcome to Impossible Hangman!");
            System.out.println();

    
            Scanner console = new Scanner(System.in);
            int length;
            
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
                    System.out.println("Invalid input. PLease put an integer.");
                    System.out.println();
                    console.next();
                }   
            }

            System.out.println("How many wrong answers do you want?");
            int guessAmount = console.nextInt();
        
            while(guessAmount < 1 || guessAmount > 26){
                System.out.println("Please choose between 1 and 26");
                guessAmount = console.nextInt();
            } 
            System.out.println();
            List<String> dictionaryList2 = Collections.unmodifiableList(theDictionary);
            HangmanLogic hangman = new HangmanLogic(dictionaryList2,length,guessAmount);

            if(hangman.words().isEmpty()){
                System.out.println("There are no words of that length");
                System.out.println();
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
