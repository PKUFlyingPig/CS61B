package byog.SaveDemo;
import java.util.List;
import edu.princeton.cs.introcs.StdDraw;
import java.io.Serializable;
import java.awt.Color;

public class Square implements Serializable {
	private double x;
	private double y;
	private double size;
	private Color c;

    private static final long serialVersionUID = 45498234798734234L;


	public Square(double xp, double yp, double sizep, Color cp) {
		x = xp;
		y = yp;
		size = sizep;
		c = cp;
	}

	public void draw() {
		StdDraw.setPenColor(c);
		StdDraw.filledSquare(x, y, size);
	}
}