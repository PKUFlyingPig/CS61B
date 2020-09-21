package lab9tester;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.In;
import java.util.HashSet;
import java.util.Set;
import org.junit.Test;
import lab9.BSTMap;

/**
 * Tests by Brendan Hu, Spring 2015, revised for 2018 by Josh Hug
 */
public class TestBSTMap {

    @Test
    public void sanityGenericsTest() {
        try {
            BSTMap<String, String> a = new BSTMap<String, String>();
            BSTMap<String, Integer> b = new BSTMap<String, Integer>();
            BSTMap<Integer, String> c = new BSTMap<Integer, String>();
            BSTMap<Boolean, Integer> e = new BSTMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i));
            assertTrue(b.get("hi" + i).equals(1 + i));
            assertTrue(b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi"));
        assertTrue(b.get("hi") != null);
    }

    @Test
    public void sanitySetTest() {
        Set<Integer> keys = new HashSet<>();
        BSTMap<Integer, Integer> map = new BSTMap<>();
        for (int i = 0; i < 4; i++) {
            keys.add(i);
            map.put(i, i);
        }
        Set<Integer> mapKeys = map.keySet();
        assertEquals(mapKeys, keys);
    }

    @Test
    public void sanityIterator() {
        BSTMap<String, Integer> map = new BSTMap<>();
        for (int i = 0; i < 4; i++) {
            map.put("haha" + i, i);
        }
        Set<String> keys = new HashSet<>();
        for (String k : map) {
            keys.add(k);
        }
        assertEquals(keys, map.keySet());
    }

    @Test
    public void sanityRemove() {
        BSTMap<Integer, Integer> map = new BSTMap<>();
        map.put(5, 1);
        map.put(3, 2);
        map.put(8, 3);
        map.put(1, 4);
        map.put(4, 5);
        map.put(6, 6);
        assertEquals(new Integer(1), map.remove(5));
        assertEquals(5, map.size());
        assertEquals(new Integer(2), map.remove(3));
        assertEquals(4, map.size());
        assertEquals(new Integer(3), map.remove(8));
        assertEquals(3, map.size());
        assertNull(map.remove(12));
    }

    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestBSTMap.class);
    }
}
