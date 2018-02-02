import org.junit.Test;
import static org.junit.Assert.*;

/** Demos the version of assertEquals with a String message. */
public class AssertEqualsStringDemo {
    @Test
    public void test1() {
        int expected = 5;
        int actual = StdRandom.uniform(9);
        assertEquals("Oh noooo!\nThis is bad:\n   Random number " + actual 
                     + " not equal to " + expected + "!", 
                     expected, actual);
    }

    /** This main method is optional. */
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(AssertEqualsStringDemo.class);
    }
} 
