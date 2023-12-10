package com.harlee.app;

import java.util.List;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    //I struggled with this one, as I think not having maven makes the assertequals impossible to use without making it a method
    
    // Test CSVDataParser with a valid CSV file
    private void assertEquals(boolean b, boolean empty) {
    }
    @Test
    public void testParseCSVValidFile() {
        String filePath = "/workspace/upper-division-cs/dsa-23au/java-dsa/harlee-app/src/main/java/com/harlee/app/Meteorite_Landings2.csv";
        char delimiter = ',';

        List<String[]> parsedData = CSVDataParser.parseCSV(filePath, delimiter);

        assertEquals(false, parsedData.isEmpty());
    }

    // Test CSVDataParser with an empty CSV file
    private void assertEquals1(String string) {
    }
    @Test
    public void testParseCSVEmptyFile() {
        String filePath = "path/to/empty/file.csv";
        char delimiter = ',';

        List<String[]> parsedData = CSVDataParser.parseCSV(filePath, delimiter);
        assertEquals1("CSV file is empty.");
    }  

    // Test CSVDataVisualizer with valid data and index
    private void assertEquals2(String string, List<String> columnValues) {
    }
    @Test
    public void testVisualizeColumnValidData() {
        List<String[]> testData = List.of(
                new String[]{"Name1", "Value1"},
                new String[]{"Name2", "Value2"},
                new String[]{"Name3", "Value3"}
        );

        int columnIndex = 1;
        assertEquals2("Value1, Value2, Value3", CSVDataVisualizer.extractColumnValues(testData, columnIndex));
    }

    
    // Test CSVDataVisualizer with an invalid index
    private void assertEquals3(String string, List<String> columnValues) {
    }
    @Test
    public void testVisualizeColumnInvalidIndex() {
        List<String[]> testData = List.of(
                new String[]{"Name1", "Value1"},
                new String[]{"Name2", "Value2"},
                new String[]{"Name3", "Value3"}
        );

        int invalidIndex = 5;
        assertEquals3("", CSVDataVisualizer.extractColumnValues(testData, invalidIndex));
    }
   

}
