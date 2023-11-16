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

 
    }

}












