package dev.codewithfriends;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class DMVMainTest 
{
    @Test
    public void testWaitlist()
    {
        DMVMain dmv = new DMVMain();
        dmv.waitlist(null, null, null);
    }
}
