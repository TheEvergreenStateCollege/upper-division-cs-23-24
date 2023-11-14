package com.devincompany.app;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class MessagesReader 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        BufferedReader messageReader = new BufferedReader(new FileReader("/workspace/upper-division-cs/dsa-23au/java-dsa/devin-finalproject/src/main/java/com/devincompany/app/messages.csv"));
        String line = null;
        ArrayList<Integer> messagesPerDay = new ArrayList<>();

        while ((line = messageReader.readLine()) != null) {
            String[] values = line.split(",");
            messagesPerDay.add(Integer.parseInt(values[0]));
        }
        DataOperations dataOperations = new DataOperations();
        System.out.print(dataOperations.HighestMessages(messagesPerDay));
        if (args[0].equals("getdata")) {

        }
    }
}
