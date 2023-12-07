package com.juniperGMVAD.app;

import java.util.List;

import java.time.Instant;
import java.util.Comparator;

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
}












