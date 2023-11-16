package com.ndeanon25;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FunHangman {

    public static final String dictionaryFile = "dictionary.txt";

    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Welcome to Hangman!");
        System.out.println();
        Scanner input = new Scanner(new File("dictionary.txt"));
        List<String> theDictionary = new ArrayList<String>();
        while(input.hasNext()){
            theDictionary.add(input.next());
        }
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

    }
    
}
