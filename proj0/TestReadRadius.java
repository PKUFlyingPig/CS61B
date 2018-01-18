/**
 *  Tests Nbody.readRadius. Reads in ./data/planets.txt and checks to see that
 *  result matches hard coded value.
 */
public class TestReadRadius {

    /**
     *  Checks whether or not two Doubles are equal and prints the result.
     *
     *  @param  expected    Expected double
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     *  @param  eps         Tolerance for the double comparison.
     */
    private static void checkEquals(double actual, double expected, String label, double eps) {
        if (Math.abs(expected - actual) <= eps * Math.max(expected, actual)) {
            System.out.println("PASS: " + label + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     *  Checks the NBody.readRadius() method.
     */
    private static void checkReadRadius() {
        System.out.println("Checking readRadius...");
        String planetsTxtPath = "./data/planets.txt";
        /* If the following line fails to compile, you probably need to make
         * a certain method static! */
        double actualOutput = NBody.readRadius(planetsTxtPath);
        checkEquals(actualOutput, 2.50E11, "readRadius()", 0.01);
    }

    public static void main(String[] args) {
        checkReadRadius();
    }
}
