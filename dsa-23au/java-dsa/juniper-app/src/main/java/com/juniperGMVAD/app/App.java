package com.juniperGMVAD.app;
import java.util.List;
/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args )
    {

        ReadData readData = new ReadData();
        readData.processData();

        System.out.println(args[0]);
        Database newDatabase = new Database();
        if (args[0].equals("top5")) {
            newDatabase.getTop5();
        }
    }

}












