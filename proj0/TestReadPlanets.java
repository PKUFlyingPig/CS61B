/**
 *  Tests Nbody.readPlanets. Reads in ./data/planets.txt and checks output of
 *  readPlanets().
 */
public class TestReadPlanets {

    private static boolean doubleEquals(double actual, double expected, double eps) {
        return Math.abs(expected - actual) <= eps * Math.max(expected, actual);
    }

    /** Checks to make sure that readPlanets() works perfectly. */
    private static String checkReadPlanets() {
        System.out.println("Checking readPlanets...");
        String planetsTxtPath = "./data/planets.txt";
        /* If the following line fails to compile, you probably need to make
         * a certain method static! */
        Planet[] actualOutput = NBody.readPlanets(planetsTxtPath);

        /* Check the simple things: */
        if (actualOutput == null) {
            return "FAIL: readPlanets(); null output";
        }
        if (actualOutput.length != 5) {
            return "FAIL: readPlanets().length: Expected 5 and you gave " + actualOutput.length;
        }

        /* Check to make sure every planet exists, plus random spot checks */
        boolean foundEarth = false;
        boolean foundMars = false;
        boolean foundMercury = false;
        boolean foundSun = false;
        boolean foundVenus = false;
        boolean randomChecksOkay = true;
        for (Planet p : actualOutput) {
            if ("earth.gif".equals(p.imgFileName)) {
                foundEarth = true;
                if (!doubleEquals(p.xxPos, 1.4960e+11, 0.01)) {
                    System.out.println("Advice: Your Earth doesn't have the right xxPos!");
                    randomChecksOkay = false;
                }
            } else if ("mars.gif".equals(p.imgFileName)) {
                foundMars = true;
            } else if ("mercury.gif".equals(p.imgFileName)) {
                foundMercury = true;
                if (!doubleEquals(p.yyPos, 0, 0.01)) {
                    System.out.println("Advice: Your Mercury doesn't have the right yyPos!");
                    randomChecksOkay = false;
                }
            } else if ("sun.gif".equals(p.imgFileName)) {
                foundSun = true;
            } else if ("venus.gif".equals(p.imgFileName)) {
                foundVenus = true;
                if (!doubleEquals(p.mass, 4.8690e+24, 0.01)) {
                    System.out.println("Advice: Your Venus doesn't have the right mass!");
                    randomChecksOkay = false;
                }
            }
        }

        /* Build up a nice list of missing planets */
        String missingPlanets = "";
        if (!foundEarth) {
            missingPlanets += "Earth, ";
        }
        if (!foundMars) {
            missingPlanets += "Mars, ";
        }
        if (!foundMercury) {
            missingPlanets += "Mercury, ";
        }
        if (!foundSun) {
            missingPlanets += "Sun, ";
        }
        if (!foundVenus) {
            missingPlanets += "Venus, ";
        }
        if (missingPlanets.length() > 0) {
            String answer = "FAIL: readPlanets(); Missing these planets: ";
            answer += missingPlanets.substring(0, missingPlanets.length() - 2);
            return answer;
        }
        if (!randomChecksOkay) {
            return "FAIL: readPlanets(); Not all planets have correct info!";
        }
        return "PASS: readPlanets(); Congrats! This was the hardest test!";
    }

    public static void main(String[] args) {
        String testResult = checkReadPlanets();
        System.out.println(testResult);
    }
}
