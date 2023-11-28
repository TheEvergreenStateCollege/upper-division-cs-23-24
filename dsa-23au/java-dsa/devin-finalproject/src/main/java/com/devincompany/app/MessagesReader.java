package com.devincompany.app;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Hello world!
 *
 */
public class MessagesReader 
{
    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException
    {
        String csvPath = "messages.csv";

        // InputStream is = ClassLoader.getSystemResourceAsStream("/AppleWatchData_myData.csv");
        InputStream is = MessagesReader.class.getClassLoader().getResourceAsStream(csvPath);
       // System.out.printf(" is null? %b \n", is == null);
        ArrayList<Integer> messagesPerDay = new ArrayList<>();
        ArrayList<String> dateIndex = new ArrayList<>();
        ArrayList<String> fullData = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                fullData.add(values[0]);fullData.add(values[1]);
                messagesPerDay.add(Integer.parseInt(values[0]));
                dateIndex.add(values[1]);
                if (values.length == 0 || line.length() == 0) {
                    continue;
                }
                System.out.printf(" Line %d -%s\n", i, line);
                i += 1;    
                try {
                    String dateString = String.format("11/13/23 %s", values[9]);
                    String dateFormat = "MM-dd-yy";
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                    Date messagesDate = sdf.parse(values[1]);


                } catch(IndexOutOfBoundsException ioobe) {
                    // that line did not have an appointment time                    
                }
            }
         } catch(IOException ioe) {
             System.err.println(ioe.toString());
         }
        FileWriter FileUpdater = new FileWriter(csvPath);
       
        String line = null;

        DataOperations dataOperations = new DataOperations();
        System.out.print(dataOperations.HighestMessages(messagesPerDay));
        if (args[0].equals("getmessages")) {
            System.out.println(messagesPerDay.get(Integer.parseInt(args[1])));
        }
        if (args[0].equals("addmessages")) {
            FileUpdater.close();
            FileUpdater = new FileWriter(csvPath,true);
            FileUpdater.write(args[1] + "," + args[2]);
        }
        if (args[0].equals("setmessages")) {
            if (args[1].equals("messagecount")){
                messagesPerDay.set(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
            }
            else if (args[1].equals("date")){
                dateIndex.set(Integer.parseInt(args[2]),args[3]);
            }
            FileUpdater.write("");
            FileUpdater.close();
            FileUpdater = new FileWriter(csvPath,true);
            for (int i = 0; i < fullData.size(); i++){
                FileUpdater.write(fullData.get(i));
                if (Math.floorMod(i, 2) == 0){
                    FileUpdater.write(",");
                }
                else if (Math.floorMod(i, 2) == 1){
                    FileUpdater.write("\n");
                }
            }
            FileUpdater.close();
        }
    }
    public static int getMessages(String[] args) throws IOException, FileNotFoundException, ParseException {
        String csvPath = "messages.csv";

        // InputStream is = ClassLoader.getSystemResourceAsStream("/AppleWatchData_myData.csv");
        InputStream is = MessagesReader.class.getClassLoader().getResourceAsStream(csvPath);
       // System.out.printf(" is null? %b \n", is == null);
        ArrayList<Integer> messagesPerDay = new ArrayList<>();
        ArrayList<String> dateIndex = new ArrayList<>();
        ArrayList<String> fullData = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                fullData.add(values[0]);fullData.add(values[1]);
                messagesPerDay.add(Integer.parseInt(values[0]));
                dateIndex.add(values[1]);
                if (values.length == 0 || line.length() == 0) {
                    continue;
                }
                System.out.printf(" Line %d -%s\n", i, line);
                i += 1;    
                try {
                    String dateString = String.format("11/13/23 %s", values[9]);
                    String dateFormat = "MM-dd-yy";
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                    Date messagesDate = sdf.parse(values[1]);


                } catch(IndexOutOfBoundsException ioobe) {
                    // that line did not have an appointment time                    
                }
            }
         } catch(IOException ioe) {
             System.err.println(ioe.toString());
         }
        FileWriter FileUpdater = new FileWriter(csvPath);
       
        String line = null;

        DataOperations dataOperations = new DataOperations();
        System.out.print(dataOperations.HighestMessages(messagesPerDay));
        if (args[0].equals("getmessages")) {
            return(messagesPerDay.get(Integer.parseInt(args[1])));
        }
        return(0);
    }
}
