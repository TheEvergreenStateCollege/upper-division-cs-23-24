package com.ZSR.app.Project;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class StrategyReader {
    public Map<String, PokerStrategy> readStrategyCSV(String filePath) {
        Map<String, PokerStrategy> strategies = new HashMap<>();
        
        Path pathToFile = Paths.get(filePath);
        
        try (BufferedReader br = Files.newBufferedReader(pathToFile)) {
            String line = br.readLine(); // Read the header line
            
            while ((line = br.readLine()) != null) {
                String[] attributes = line.split(","); // Split the line into attributes
                
                PokerStrategy strategy = createStrategy(attributes);
                strategies.put(strategy.getHand(), strategy); // Assuming you want to key by 'hand'
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        return strategies;
    }

    private PokerStrategy createStrategy(String[] metadata) {
        String hand = metadata[0];
        String suit = metadata[1];
        String unopenedPotEP = metadata[2];
        String unopenedPotLP = metadata[3];
        String withLimperEP = metadata[4];
        String withLimperLP = metadata[5];
        String raiseInFrontEP = metadata[6];
        String raiseInFrontLP = metadata[7];
        

        return new PokerStrategy(hand, suit, unopenedPotEP, unopenedPotLP , withLimperEP, withLimperLP, raiseInFrontEP, raiseInFrontLP);
    }
}


