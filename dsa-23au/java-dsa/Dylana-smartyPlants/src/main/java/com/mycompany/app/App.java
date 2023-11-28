package com.mycompany.app;

/**
 * Hello world!
 *

public class App 
{

    public static void (String[] args);

    public static List<String> (String searchArgs) {
        throw new RuntimeException("Not yet implemented.");
    }

    public static void main( String[] args ) {
        //Two methods useful/relevent to project 
        //accuracy/confidence level and gene number count retrieval?
        //identify where in the pipeline we are

        System.out.println(args[0]);

        if (args[0].equals("load")) {
            loadAccuracyRange(args[1]);
        } else if (args[0].equals("retrieve")) {
            retrieveRange();
        }


    }


}
 */