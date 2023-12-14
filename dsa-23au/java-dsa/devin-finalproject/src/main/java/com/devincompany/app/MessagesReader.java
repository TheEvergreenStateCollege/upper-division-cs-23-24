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
    
    private static ArrayList<Integer> messagesPerDay = new ArrayList<>();
    private static ArrayList<String> dateIndex = new ArrayList<>();
    private static ArrayList<String> fullData = new ArrayList<>();
    public static void updateArrayList() throws ParseException,IOException
    {
        String csvPath = "src/main/resources/messages.csv";
        // InputStream is = ClassLoader.getSystemResourceAsStream("/AppleWatchData_myData.csv");
        // System.out.printf(" is null? %b \n", is == null);
        messagesPerDay.clear();
        dateIndex.clear();
        fullData.clear();
            try (BufferedReader br = new BufferedReader(new FileReader(csvPath)))
            {
            String line;
            int i = 0;
            while ((line = br.readLine()) != null)
            {
                String[] values = line.split(",");
                messagesPerDay.add(Integer.parseInt(values[0]));
                //System.out.println(values[0]);
                dateIndex.add(values[1]);
                if (values.length == 0 || line.length() == 0)
                {
                    continue;
                }
                // System.out.printf(" Line %d -%s\n", i, line);
                i += 1;
                try
                {
                    String dateString = String.format("11/13/23 %s", values[9]);
                    String dateFormat = "MM-dd-yy";
                    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
                    Date messagesDate = sdf.parse(values[1]);


                } catch(IndexOutOfBoundsException ioobe)
                {
                    // that line did not have an appointment time                    
                }
            }
         } catch(IOException ioe)
         {
             System.err.println(ioe.toString());
         }
       
        String line = null;
        
    }

    public static void main( String[] args ) throws FileNotFoundException, IOException, ParseException
    {
        String csvPath = "src/main/resources/messages.csv";
        // InputStream is = ClassLoader.getSystemResourceAsStream("/AppleWatchData_myData.csv");
        // System.out.printf(" is null? %b \n", is == null);
        updateArrayList();
        if (args[0].equals("getmessages"))
        {
            System.out.println(messagesPerDay.get(Integer.parseInt(args[1])));
        }
        if (args[0].equals("addmessages"))
        {
            FileWriter FileUpdater = new FileWriter(csvPath, true);
            FileUpdater.append("\n" + args[1] + "," + args[2]);
            FileUpdater.flush();
            FileUpdater.close();
            updateArrayList();
            System.out.println(messagesPerDay.get(messagesPerDay.size()-1));
        }
        if (args[0].equals("setmessages"))
        {
            if (args[1].equals("messagecount"))
            {
                messagesPerDay.set(Integer.parseInt(args[2]),Integer.parseInt(args[3]));
            }
            else if (args[1].equals("date"))
            {
                dateIndex.set(Integer.parseInt(args[2]),args[3]);
            }
            FileWriter FileUpdater = new FileWriter(csvPath);
            FileUpdater.write("");
            System.out.print(fullData.size());
            for (int i = 0; i < messagesPerDay.size(); i++)
            {
                System.out.println(i);
                FileUpdater.append(Integer.toString(messagesPerDay.get(i)) + ", " + dateIndex.get(i) + "\n");
            }
            FileUpdater.close();
            updateArrayList();
            if (args[1].equals("messagecount"))
            {
                System.out.println(messagesPerDay.get(Integer.parseInt(args[2])));
            }
            else if (args[1].equals("date"))
            {
                System.out.println(dateIndex.get(Integer.parseInt(args[2])));
            }
        }
        if (args[0].equals("dataoperations"))
        {   
            if (args[1].equals("gethighestmessage"))
            {
                System.out.println(DataOperations.highestMessages(messagesPerDay));
            }
            if (args[1].equals("samecountdays"))
            {
                System.out.println(DataOperations.highestMessages(messagesPerDay));
            }
            if (args[1].equals("notabledays"))
            {
                System.out.println(DataOperations.notableDays(messagesPerDay));
            }
            if (args[1].equals("coolmatrix"))
            {
                
                if (args[2].equals("one"))
                {
                    int[][] matrix  = DataOperations.coolIndexMatrix(messagesPerDay);
                    for (int i = 0; i < matrix.length; i++)
                    {
                        for (int j = 0; j < matrix[i].length; j++)
                        {
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println("");
                    }
                }
                else if (args[2].equals("two"))
                {
                    int[][] matrix  = DataOperations.coolIndexMatrixTwo(messagesPerDay);
                    for (int i = 0; i < matrix.length; i++)
                    {
                        for (int j = 0; j < matrix[i].length; j++)
                        {
                            System.out.print(matrix[i][j] + " ");
                        }
                        System.out.println("");
                    }
                }
                
            }
        }
    }
    /*public static int addMessages(String[] args) throws IOException, FileNotFoundException, ParseException {
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

        String line = null;

        DataOperations dataOperations = new DataOperations();
        System.out.print(dataOperations.HighestMessages(messagesPerDay));
        if (args[0].equals("addmessages")) {
            FileWriter FileUpdater = new FileWriter(csvPath, true);
            FileUpdater.append("\n" + args[1] + "," + args[2]);
            FileUpdater.close();
            return(messagesPerDay.get(messagesPerDay.size()));
        }
        return(0);
    }*/
}
