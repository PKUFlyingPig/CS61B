package hw3.hash;
import java.util.List;
import java.util.ArrayList;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.Color;
import edu.princeton.cs.algs4.StdRandom;

public class ComplexOomage implements Oomage {
    protected List<Integer> params;
    private static final double WIDTH = 0.05;

    @Override
    public int hashCode() {
        int total = 0;
        for (int x : params) {
            total = total * 256;
            total = total + x;
        }
        return total;
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() != this.getClass()) {
            return false;
        }
        ComplexOomage otherComplexOomage = (ComplexOomage) o;
        return params.equals(otherComplexOomage.params);
    }

    public ComplexOomage(List<Integer> params) {
        if (params == null) {
            throw new IllegalArgumentException("params must not be null!");            
        }

        for (Integer x : params) {
            validate(x);
        }
        this.params = new ArrayList<>(params);
    }

    private void validate(Integer param) {
        if (param == null || param < 0 || param > 255) {
            throw new IllegalArgumentException(param + " must be between 0 and 255.");
        }
    }

    /* Draws this ComplexOomage. */
    @Override
    public void draw(double x, double y, double scalingFactor) {
        int offset = 0;
        int r = params.get(0);
        int g = 0;
        int b = 0;
        double maxX = x + WIDTH * scalingFactor;
        double maxY = y + WIDTH * scalingFactor;
        double incX = WIDTH / 5 * scalingFactor;
        double incY = WIDTH / 5 * scalingFactor;
        double subsquareWidth = WIDTH / 10 * scalingFactor;

        for (double xPos = x; xPos < maxX; xPos += incX) {
            for (double yPos = y; yPos < maxY; yPos += incY) {
                Color c = new Color(r, g, b);
                StdDraw.setPenColor(c);
                StdDraw.filledSquare(xPos, yPos, subsquareWidth);
                b = g;
                g = r;
                offset = (offset + 1) % params.size();
                r = params.get(offset);
            }
        }
    }

    public static ComplexOomage randomComplexOomage() {
        int N = StdRandom.uniform(1, 10);
        ArrayList<Integer> params = new ArrayList<>(N);
        for (int i = 0; i < N; i += 1) {
            params.add(StdRandom.uniform(0, 255));
        }
        return new ComplexOomage(params);
    }

    public static void main(String[] args) {
        System.out.println("Drawing 4 random complex Oomages.");
        randomComplexOomage().draw(0.25, 0.25, 1.5);
        randomComplexOomage().draw(0.75, 0.75, 1.5);
        randomComplexOomage().draw(0.25, 0.75, 1.5);
        randomComplexOomage().draw(0.75, 0.25, 1.5);
    }
} 
