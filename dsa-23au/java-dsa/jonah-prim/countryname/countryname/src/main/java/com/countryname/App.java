package com.countryname;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        BufferedReader reader;
        List<List<String>> tokenized = new ArrayList<List<String>>();
        String pattern = "\"([^\"]*)\",";
        Pattern reg = Pattern.compile(pattern);
        
        try {
		    reader = new BufferedReader(new FileReader("com/countryname/res.txt"));
			String line = reader.readLine();

			while (line != null) {
                List<String> split = new ArrayList<String>();

                Matcher ma = reg.matcher(line);
                ma.groupCount();

                while (ma.find()) {
                    split.add(ma.group(1));
                }
                
                /*for (int i = 1; i <= ma.groupCount(); i++) {
                    split.add(ma.group(i));
                }*/

                tokenized.add(split);
				line = reader.readLine();
			}

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

        for (List<String> s : tokenized) {
            System.out.printf("%s(\"%s\",\"%s\"),\n", s.get(1), s.get(0), s.get(1));
        }
    }
}
