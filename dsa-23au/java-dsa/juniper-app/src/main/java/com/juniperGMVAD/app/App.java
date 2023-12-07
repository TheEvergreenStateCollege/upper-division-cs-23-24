package com.juniperGMVAD.app;
import java.util.List;
import java.util.Comparator;
import java.util.*;

import com.juniperGMVAD.app.BinaryTree.*;
import com.juniperGMVAD.app.HashMap.*;

//Data structures: BST for database, Queue for BFS, Hashmap for command line interface, 2D Array for correlation
public class App 
{

 public static void main(String[] args) 
     {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while(!exit)
            {
                System.out.print("Enter a command (or 'exit' to quit): ");
                String input = scanner.nextLine();
                            
                if (input.equalsIgnoreCase("exit"))
                {
                    exit = true;
                }
                else
                {
                    processCommand(input);
                }
            }
     }
                
private static void processCommand(String input) 
    {

        String[] parts = input.split("\\s+");
        String command = parts[0];

        String[] parameters = new String[parts.length - 1];
        System.arraycopy(parts, 1, parameters, 0, parameters.length);
        switch(command)
            {
                case "help":
                System.out.println("tokenizeCSV [parameter], ...");
                break;

                //example
                case "tokenizeCSV":
                tokenizeCSV(parameters);
                break;

                default:
                System.out.println("Unknown command. Type 'help' for available commands.");
            }
                                           
    }

private static void tokenizeCSV(String[] parameters)
{
    ReadData readData = new ReadData();
    if (parameters.length == 1) {
        String param = parameters[0];

        System.out.println(readData.readAndTokenizeCSV(param));
    } else {
        System.out.println("Invalid number of parameters");
    }
}

}
    














