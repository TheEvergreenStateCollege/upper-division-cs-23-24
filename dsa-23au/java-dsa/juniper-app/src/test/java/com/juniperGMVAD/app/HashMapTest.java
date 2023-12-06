package com.juniperGMVAD.app;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.juniperGMVAD.app.HashMap.HashMap;

public class HashMapTest {
    // Adding
    // Removing
    // Getting existent
    // Getting non-existent

    @Test
    public void put() {
        HashMap<String, Integer> hm = new HashMap<String, Integer>();

        int num = 0;
        for (char a = 'a'; a <= 'z'; a++) {
            for (char b = 'a'; b <= 'z'; b++, num++) {
                String str = new StringBuilder().append(a).append(b).toString();
                hm.put(str, num);
            }
        }

        assertTrue(num == hm.dynamicSizeDebug());
    }

    @Test
    public void get() {

    }

    @Test
    public void remove() {

    }
}
