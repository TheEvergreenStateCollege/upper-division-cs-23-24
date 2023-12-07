package com.juniperGMVAD.app;

import java.util.List;

import java.time.Instant;
import java.util.Comparator;

import com.juniperGMVAD.app.BinaryHeap.BinaryHeap;
import com.juniperGMVAD.app.BinaryTree.*;
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
        /*
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

        bh.printHeapDebug();
        */

        /*Database database = new Database();
        ReadData readData = new ReadData(database);
        readData.processData();

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












