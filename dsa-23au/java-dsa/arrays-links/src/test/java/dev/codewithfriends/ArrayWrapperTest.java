package dev.codewithfriends;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.util.List;
/**
 * Unit test for simple App.
 */
public class ArrayWrapperTest
{
    
      @Test
      public void testAdd()
{    
    String[] s1 ("eggs", "bread","penutbutter","butter");
    

        List<String> list = new ArrayWrapper<string>(10);
        list.add(s1[0]);
        list.add(s1[1]);
        list.add(s1[2]);
        list.add(s1[3]);

        int k = 2;

        assertEquals ("eggs", list.get(0));
        assertEquals ("bread", list.get(1));
        assertEquals ("penutbutter", list.get(k));
        assertEquals ("butter", list.get(3));

        list.add(2, "broccoli");
        assertEquals ("eggs", list.get(0));
        assertEquals ("bread", list.get(1));
        assertEquals ("broccoli", list.get(k));
        assertEquals ("penutbutter", list.get(k+1));
        assertEquals ("butter", list.get(k+2));
    }

    @Test
    public void add()
    {
        assertTrue( true );
    }
}
