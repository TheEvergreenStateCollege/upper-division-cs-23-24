package com.juniperGMVAD.app;
import java.util.List;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{


public static void main( String[] args )
    {
        System.out.println(args[0]);

        if (args[0].equals("top5mva")) {
            calculateTopMVA();
        } else if (args[0].equals("countrypercent")) {
            getCountryPercent();
        }
    }

    public static List<CountryData> calculateTopMVA ()
    {

    }

 }












