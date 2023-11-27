package com.devincompany.app;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.File;

/**
 * Hello world!
 *
 */
public class MessagesReader 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException
    {
        BufferedReader messageReader = new BufferedReader(new FileReader("/workspace/upper-division-cs/dsa-23au/java-dsa/devin-finalproject/src/main/java/com/devincompany/app/messages.csv"));
        FileWriter FileUpdater = new FileWriter("/workspace/upper-division-cs/dsa-23au/java-dsa/devin-finalproject/src/main/java/com/devincompany/app/messages.csv");
       
        String line = null;
        ArrayList<Integer> messagesPerDay = new ArrayList<>();
        ArrayList<String> dateIndex = new ArrayList<>();
        ArrayList<String> fullData = new ArrayList<>();

        while ((line = messageReader.readLine()) != null) {
            String[] values = line.split(",");
            fullData.add(values[0]);fullData.add(values[1]);
            messagesPerDay.add(Integer.parseInt(values[0]));
            dateIndex.add(values[1]);
        }
        DataOperations dataOperations = new DataOperations();
        System.out.print(dataOperations.HighestMessages(messagesPerDay));
        if (args[0].equals("getmessages")) {
            System.out.println(messagesPerDay.get(Integer.parseInt(args[1])));
        }
        if (args[0].equals("setmessages")) {
            if (args[1].equals("messagecount")){
                messagesPerDay.set(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
            }
            else if (args[1].equals("date")){
                dateIndex.set(Integer.parseInt(args[2]),args[3]);
            }
            FileUpdater.write("");
            FileUpdater = new FileWriter("/workspace/upper-division-cs/dsa-23au/java-dsa/devin-finalproject/src/main/java/com/devincompany/app/messages.csv",true);
            for (int i = 0; i < fullData.size(); i++){
                FileUpdater.write(fullData.get(i));
                if (Math.floorMod(i, 2) == 0){
                    FileUpdater.write(",");
                }
                else if (Math.floorMod(i, 2) == 1){
                    FileUpdater.write("\n");
                }
            }
            
        }
    }
}
