package com.juniperGMVAD.app;
import java.util.List;

import com.juniperGMVAD.app.BinaryTree.*;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args)
    {
        CompareYear comp = new CompareYear();
        BinaryTree<YearValue> bt = new BinaryTree<YearValue>(comp);

        YearValue yv1 = new YearValue(1960, 10.23);
        YearValue yv2 = new YearValue(1965, 134.3);
        YearValue yv3 = new YearValue(1970, 234.44444);
        YearValue yv4 = new YearValue(1975, 9.0);
        YearValue yv5 = new YearValue(1980, 45.65);
        YearValue yv6 = new YearValue(1987, 42.22222);

        bt.insert(yv1);
        bt.insert(yv2);
        bt.insert(yv3);
        bt.insert(yv4);
        bt.insert(yv5);

        if (bt.contains(yv3)) {
            System.out.println("Worked");
        }

        bt.printTreeInOrder();
        bt.delete(yv1);
        bt.printTreeInOrder();
        bt.delete(yv1);
        bt.printTreeInOrder();
        bt.delete(yv2);
        bt.printTreeInOrder();

        if (bt.contains(yv3)) {
            System.out.println("Worked");
        }

        /*Database database = new Database();
        ReadData readData = new ReadData(database);

        List<List<String>> tokens = readData.readAndTokenizeCSV("/workspace/upper-division-cs/dsa-23au/java-dsa/juniper-app/src/main/resources/GMVA.csv");

        for (List<String> line : tokens) {
            for (String s : line) {
                System.out.printf("%s,", s);
            }
            System.out.print("\n--------------------\n");
        }*/
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












