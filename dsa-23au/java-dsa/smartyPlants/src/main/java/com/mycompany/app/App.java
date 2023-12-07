package com.mycompany.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList; 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.Arrays;


/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ArrayList<mrnaRead> readsz = new ArrayList<mrnaRead>();
        Arrays[] readzz = readsz.toArray();
        readFastQ(readzz, "../../testData/fastq_runid_9d742d72b6f5d334c2d0d388f2eb1da13decd9a6_34_0"); 
        for(int i = 0; i <= readzz.length(); i++){
            //replace [] with .get(i)
            System.out.print("hash: ");
            System.out.println(readzz[i].hash);
            System.out.print("run ID: ");
            System.out.println(readzz[i].runID);
            System.out.print("sample ID: ");
            System.out.println(readzz[i].sampleId);
            System.out.print("read: ");
            System.out.println(readzz[i].read);
            System.out.print("ch: ");
            System.out.println(readzz[i].ch);
            System.out.print("start time: ");
            System.out.println(readzz[i].startTime);
            System.out.print("model ID: ");
            System.out.println(readzz[i].modelID);
            System.out.print("data: ");
            System.out.println(readzz[i].data);
        }
    } 


public class mrnaRead
{
    String hash; 
    String runID;
    String sampleId;
    String read;
    String ch;
    String startTime;
    String modelID;
    String data;



}

public void readFastQ(ArrayList dataset, String file)
{
    //should add checking if file opened okay 
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String currentLine =  reader.readLine(); 
    while(currentLine != null){
        mrnaRead temp = new mrnaRead(); //double-check this is grammar
        String[] arr = currentLine.split(" "); 
        for(int i = 0; i <= arr.length(); i++){
            String[] innerArray = arr[i].split("=");
            switch(innerArray[0]){
                case "runid" :
                    temp.runID = innerArray[1];
                    break;
                case "sampleid" :
                    temp.sampleId = innerArray[1];
                    break;
                case "read" :
                    temp.read = innerArray[1];
                    break;
                case "ch" :
                    temp.ch = innerArray[1];
                    break;
                case "start_time" :
                    temp.startTime = innerArray[1];
                    break;
                case "model_version_id" :
                    temp.modelID = innerArray[1];
                    break;
                default :
                    temp.hash = innerArray[1];
            }
        }
        currentLine =  reader.readLine(); 
        //check if break/null
        temp.data = currentLine; 
        currentLine =  reader.readLine();        
    }
    reader.close();


}
}

//Iterate through lines of file while, if-else check for if metadata/data - meta starts with ">", edge case of missing half in the tuple
//Identified three tiers: line of metadata, data, and space-seperated categories within the line of metadata
//Long string of DNA letters is a String
//


