package huglife;
import java.awt.Color;

public class Empty extends Occupant {
    public Empty() {
        super("empty");
    }

    /** Returns hardcoded black */
    public Color color() {
        return color(255, 255, 255);
    }    
}