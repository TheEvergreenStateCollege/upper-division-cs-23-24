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
/* 
        public static void main(String[] args)
        {
            if (args.length <= 0) {
                System.err.println("Usage: java <program> [command]");
                System.err.println("Enter \"help\" for a list of commands");
                return;
            }
    
            Database database = new Database();
            ReadData readData = new ReadData(database);
            //"/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv"
            readData.loadAndProcess("GMVA.csv");
    
            switch (args[0]) {
                case "help":    System.out.println("Commands: pgmva, mva");
                                break;
                case "top5":    
                                break;
                case "mva":     if (args.length < 3) {
                                    System.err.println("Usage: java <program> mva [country-code] [year]");
                                    return;
                                }
                                String countryCode = args[1];
                                int year = Integer.parseInt(args[2]);
                                Country country = Country.valueOf(countryCode);
                                System.out.println(database.getYearValue(country, Indicator.MVA, year));
                                break;
                
                case "pgmva":   if (args.length < 3) {
                                    System.err.println("Usage: java <program> pgmva [country-code] [year]");
                                    return;
                                }
                                String pcountryCode = args[1];
                                int pyear = Integer.parseInt(args[2]);
                                Country pcountry = Country.valueOf(pcountryCode);
                                System.out.println(database.getYearValue(pcountry, Indicator.PGMVA, pyear));
                                break;
            }
        }
        */
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
                   System.out.println("mva [year] [countrycode], pgmva [year] [countrycode]");
                   System.out.println("pgmvaChange [startYear] [endYear], top5 [year]");
                   System.out.println("corrtop40 [startYear] [endYear]");
                   break;

                   case "mva":
                    getMVA(parameters);
                    break;
                
                   case "pgmva":
                   getPGMVA(parameters);
                   break;

                   case "pgmvaChange":
                   getPGMVAchange(parameters);
                   break;
    
                   case "top5":
                   top5PerYear(parameters);
                   break;

                   case "corrtop5":
                   Top5ContCorrNNIMVA(parameters);
                   break;

                   default:
                   System.out.println("Unknown command. Type 'help' for available commands.");
               }
                                              
       }
   
   
   private static void getPGMVA(String[] parameters)
   {
    Database database = new Database();
    ReadData readData = new ReadData(database);
    //"/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv"
    readData.loadAndProcess("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv");

    if (parameters.length == 2){
        int pyear = Integer.parseInt(parameters[0]);
        Country pcountry = Country.valueOf(parameters[1]);
        System.out.println((database.getYearValue(pcountry, Indicator.PGMVA, pyear) * 100) + "%");
    }
    else 
    {
        System.out.println("Invalid number of parameters");
    }

   }

   private static void getMVA(String[] parameters)
   {
    Database database = new Database();
    ReadData readData = new ReadData(database);
    //"/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv"
    readData.loadAndProcess("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv");
    if (parameters.length == 2){
        int pyear = Integer.parseInt(parameters[0]);
        Country pcountry = Country.valueOf(parameters[1]);
        System.out.println(database.getYearValue(pcountry, Indicator.MVA, pyear));
    }
    else 
    {
        System.out.println("Invalid number of parameters");
    }

   }

   private static void getPGMVAchange(String[] parameters)
   {
    Database database = new Database();
    ReadData readData = new ReadData(database);
    //"/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv"
    readData.loadAndProcess("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv");

    if (parameters.length == 3){
        int pyear = Integer.parseInt(parameters[0]);
        int pyear2 = Integer.parseInt(parameters[1]);
        Country pcountry = Country.valueOf(parameters[2]);

        double value1 = database.getYearValue(pcountry, Indicator.PGMVA, pyear);
        double value2 = database.getYearValue(pcountry, Indicator.PGMVA, pyear2);

        double pchange = (value2 - value1) * 100;
        System.out.println(pchange + "%");
    }
    else 
    {
        System.out.println("Invalid number of parameters");
    }

   }


public static void top5PerYear(String[] parameters)
{

    Database database = new Database();

    ReadData readData = new ReadData(database);
    //"/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv"
    readData.loadAndProcess("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv");
    List<String> topPerYear = new ArrayList<String>();

    if (parameters.length == 1)
    {
        int param = Integer.parseInt(parameters[0]);
    for (Map.Entry<Country, CountryData> Entry : database.countryData.entrySet())
    {
        if (Entry.getKey() != Country.WLD)
        {
        Country pcountry = Entry.getKey();
        double percCount = (database.getYearValue(pcountry, Indicator.PGMVA, param) * 100);

        if(!Double.isInfinite(percCount))
        {
            topPerYear.add(pcountry.toString() + ": " + percCount + "%");

        }
    }
    }

        Collections.sort(topPerYear, (s1, s2) ->
        {
            double perc1 = Double.parseDouble(s1.split(": ")[1].replace('%', ' '));
            double perc2 = Double.parseDouble(s2.split(": ")[1].replace('%', ' '));

            return Double.compare(perc2, perc1);
        });
        topPerYear = topPerYear.subList(0, Math.min(topPerYear.size(), 10));

        for (int i = 0; i < topPerYear.size(); i++)
        {
            System.out.println(topPerYear.get(i));

        }
    }
    else
    {
        System.out.println("Invalid number of parameters");
    }
}

public static void Top5ContCorrNNIMVA(String[] parameters)
{
    Database database = new Database();
    ReadData readData = new ReadData(database);
    readData.loadAndProcess("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv");
    readData.loadAndProcess2("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/NNIpercapita.csv");

    if (parameters.length == 2)
    {
        int param1 = Integer.parseInt(parameters[0]);
        int param2 = Integer.parseInt(parameters[1]);
        System.out.println(database.getTop5ContCorrNNIMVA(param1, param2));
    }
}
}
/*private static void tokenizeCSV(String[] parameters)
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
*/

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
    













