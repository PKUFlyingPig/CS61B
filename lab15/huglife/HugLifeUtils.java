/** Utilities for lab 5
 *  @author Josh Hug
 */
package huglife;
import java.util.Random;
import java.util.List;


public class HugLifeUtils {
    private static Random r = null;

    /** Returns a random number uniformly between 0 and 1 */
    public static double random() {
        if (r == null)
            r = new Random();

        return r.nextDouble();
    }

    /** Returns a random number uniformly between min and max inclusive
        Stolen from: http://stackoverflow.com/questions/363681 */
    public static int randomInt(int min, int max) {
        if (r == null)
            r = new Random();

        return r.nextInt((max - min) + 1) + min;
    }

    /** Returns a random number uniformly between 0 and max */
    public static int randomInt(int max) {
        return randomInt(0, max);
    }

    /** Returns a random number uniformly between 0 and max */
    public static Direction randomEntry(List<Direction> L) {
        int dirIndex = randomInt(L.size() - 1);
        return L.get(dirIndex);
    }
}
