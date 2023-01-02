/**
 *  Tests the Body constructor.
 */
public class TestBodyConstructor {

    /**
     *  Tests the Body constructor to make sure it's working correctly.
     */
    public static void main(String[] args) {
        checkBodyConstructor();
    }

    /**
     *  Checks whether or not two boubles are equal and prints the result.
     *
     *  @param  expected    Double expected
     *  @param  actual      Double received
     *  @param  label       Label for the 'test' case
     */
    private static void checkEquals(double expected, double actual, String label) {
        if (Double.isNaN(actual) || Double.isInfinite(actual)) {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else if (expected == actual) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     *  Checks whether or not two Strings are equal and prints the result.
     *  @param  expected    String expected
     *  @param  actual      String received
     *  @param  label       Label for the 'test' case
     */
    private static void checkStringEquals(String expected, String actual, String label) {
        if (expected.equals(actual)) {
            System.out.println("PASS: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        } else {
            System.out.println("FAIL: " + label
                    + ": Expected " + expected + " and you gave " + actual);
        }
    }

    /**
     *  Checks Body constructors to make sure they are setting instance
     *  variables correctly.
     */
    private static void checkBodyConstructor() {
        System.out.println("Checking first Body constructor...");

        double xxPos = 1.0,
               yyPos = 2.0,
               xxVel = 3.0,
               yyVel = 4.0,
               mass = 5.0;

        String imgFileName = "jupiter.gif";

        Body b = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);

        checkEquals(xxPos, b.xxPos, "xxPos");
        checkEquals(yyPos, b.yyPos, "yyPos");
        checkEquals(xxVel, b.xxVel, "xxVel");
        checkEquals(yyVel, b.yyVel, "yyVel");
        checkEquals(mass, b.mass, "mass");
        checkStringEquals(imgFileName, b.imgFileName, "path to image");

        System.out.println("Checking second Body constructor...");

        Body bCopy = new Body(b);
        checkEquals(b.xxPos, bCopy.xxPos, "xxPos");
        checkEquals(b.yyPos, bCopy.yyPos, "yyPos");
        checkEquals(b.xxVel, bCopy.xxVel, "xxVel");
        checkEquals(b.yyVel, bCopy.yyVel, "yyVel");
        checkEquals(b.mass, bCopy.mass, "mass");
        checkStringEquals(b.imgFileName, bCopy.imgFileName, "path to image");
    }
}
