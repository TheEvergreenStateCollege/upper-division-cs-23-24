package dev.codewithfriends;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Hello world!
 * https://www.mapcrow.info/united_states.html
 */
public class App 
{

    public static Graph<String,Long> readUSCitiesDistances() {
        String csvFilePath = "us-cities-distances.csv";
        InputStream is = App.class.getClassLoader().getResourceAsStream(csvFilePath);

        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            String line;

            // City index
            int i = 1; // Skip the * in the upper left corner

            String headerLine = br.readLine();
            String[] cities = headerLine.split("\\t");
            Graph<String,Long> graph = new GraphMatrix<>(cities.length - 1);

            assert(cities[0].equals("*")); // placeholder corner
            for (int k = 1; k < cities.length; k += 1) {
                graph.addNode(cities[k]);
            }
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split("\\t");
                if (tokens.length == 0 || line.length() == 0) {
                    continue;
                }

                // Assert the row name matches corresponding column name
                assert(cities[i].equals(tokens[0]));
                System.out.printf("  City %s\n", cities[i]);

                for (int j = 1; j < tokens.length; j += 1) {
                    String sourceCity = cities[i];
                    String destCity = cities[j];
                    long dist = Math.round(Double.parseDouble(tokens[j]));
                    System.out.printf(" %s %d", destCity, dist);
                    graph.addEdge(sourceCity, destCity, dist);
                }
                System.out.printf("\n");
                // Increment the city row index
                i += 1;

            }
            return graph;
        } catch(IOException ioe) {
            return null;
        }

    }

    public static void main( String[] args )
    {
        Graph<String,Long> graph = readUSCitiesDistances();
        System.out.println(graph.getEdge("Austin", "San Francisco").getWeight());
    }
}
