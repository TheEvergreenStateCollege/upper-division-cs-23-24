package dev.codewithfriends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Unit test for scraping from Names database
 */
public class AppTest 
{

    @Test
    public void testGetNamesByLetterPage()
    {
        App.loadHeadersMap();
        Map<String,String> meaningsMap = App.getNamesByLetterPage('q', 1);
        assertEquals(100, meaningsMap.size());
        assertEquals("Night Blooming Flower", meaningsMap.get("Quynh"));
    }
}
