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
        Database database = new Database();
        ReadData readData = new ReadData(database);
        readData.processData();

      //  System.out.println(args[0]);
     
       // if (args[0].equals("top5")) {
           database.getTop5();
       // }
        

    }

}












