package com.juniperGMVAD.app;

import java.util.List;

import java.time.Instant;
import java.util.Comparator;
import java.util.*;

import com.juniperGMVAD.app.BinaryHeap.BinaryHeap;
import com.juniperGMVAD.app.BinaryTree.*;
import com.juniperGMVAD.app.Enum.Country;
import com.juniperGMVAD.app.Enum.Indicator;
import com.juniperGMVAD.app.HashMap.*;

/**
 * <h1> Economic Indicator Calculator </h1>
 * Calculates averages of economic indicators over times and compares these for individual countries.
 * 
 * @author Juniper
 * @author Jonah Eadie
 * @version 1.0
 * @since 2022-12-06
 */

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
    } 
    else 
    {
        System.out.println("Invalid number of parameters");
    }
}


  /*  public static void main(String[] args)
    {
        
        Database db = new Database();
        db.setYearValue(Country.WLD, Indicator.MVA, 2000, 5d);
        db.setYearValue(Country.WLD, Indicator.MVA, 2001, 5d);
        db.setYearValue(Country.WLD, Indicator.MVA, 2002, 5d);
        db.setYearValue(Country.WLD, Indicator.MVA, 2003, 5d);

        db.setYearValue(Country.USA, Indicator.MVA, 2000, 1d);
        db.setYearValue(Country.USA, Indicator.MVA, 2001, 1d);
        db.setYearValue(Country.USA, Indicator.MVA, 2002, 1d);
        db.setYearValue(Country.USA, Indicator.MVA, 2003, 1d);

        System.out.println(db.getYearValue(Country.USA, Indicator.PGMVA, 2000));

        db.removeYearValue(Country.USA, Indicator.MVA, 2001);

        //db.removeYearValue(Country.USA, Indicator.MVA, 2000);
        List<YearValue> list = db.yearValuesAsList(Country.USA, Indicator.PGMVA);

        for (YearValue yv : list) {
            System.out.println(yv);
        }
        */
        /* 
        //countryData.printLastUpdatedDebug();

        System.out.println(countryData.getValue(Indicator.MVA, 2000));
        System.out.println(countryData.getValue(Indicator.MVA, 2002));

        List<YearValue> list = countryData.valuesAsList(Indicator.MVA);

        for (YearValue yv : list) {
            System.out.println(yv);
        }

        Pair<Indicator, Integer> pair1 = new Pair<Indicator, Integer>(Indicator.MVA, 2000);
        Pair<Indicator, Integer> pair2 = new Pair<Indicator, Integer>(Indicator.MVA, 2000);

        if (pair1.equals(pair2)) {
            System.out.println("Equals");
        }

        System.out.println(countryData.lastUpdated(Indicator.MVA, 2000));
        countryData.setValue(Indicator.MVA, 2000, 1d);
        System.out.println(countryData.lastUpdated(Indicator.MVA, 2000));
        countryData.printLastUpdatedDebug();
        */

        /*
        BinaryHeap<YearValue> bh = new BinaryHeap<YearValue>(new SortByYear());

        bh.insert(new YearValue(2000, 2.33));
        bh.insert(new YearValue(2002, 4553.33));
        bh.insert(new YearValue(2005, 66.33));
        bh.insert(new YearValue(2007, 4.33));

        for (int i = 2010; i < 2110; i++) {
            bh.insert(new YearValue(i, 2.33));
        }

        Database database = new Database();
        ReadData readData = new ReadData(database);
        //"/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv"
        readData.loadAndProcess("GMVA.csv");

         switch (args[0]) {
            case "top5":
                List<String> top5 = database.getTop(5, 2016, 2022);
                System.out.println("Top 5 national GMVA change from 2016 to 2022");

                int rank = 1;
                for (String s : top5) {
                    System.out.printf("%n. %s\n", rank, s);
                    rank += 1;
                }

                break;
            case "gmva":
                break;
            case "default": 
                System.out.println("Usage: [application] [top5 / gmva] [country name] [year]"); //TODO: make this legible and accurate
            
        }
        */  
    }
}












