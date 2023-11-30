package com.devincompany.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Unit test for simple App.
 */
public class AppTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws FileNotFoundException, IOException
    {
        String[] args = {""};
        MessagesReader.main(args);
    }
}
