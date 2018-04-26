package huglife;
import java.awt.Color;

public class Impassible extends Occupant {
    public Impassible() {
        super("impassible");
    }

    /** Returns hardcoded black */
    public Color color() {
        return color(0, 0, 0);
    }    
}