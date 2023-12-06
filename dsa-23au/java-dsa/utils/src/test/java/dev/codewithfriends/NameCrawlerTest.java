package dev.codewithfriends;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Map;

/**
 * Unit test for scraping from Names database
 */
public class NameCrawlerTest
{

    @Test
    public void testGetNamesByLetterPage()
    {
        NameCrawler.loadHeadersMap();
        Map<String,String> meaningsMap = NameCrawler.getNamesByLetterPage('q', 1);
        assertEquals(100, meaningsMap.size());
        assertEquals("Night Blooming Flower", meaningsMap.get("Quynh"));
    }
}
