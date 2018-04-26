package huglife;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 *  @author Josh Hug
 *  A class that represents living creatures. You should
 *  extend this class to populate your world.
 */
public abstract class Creature extends Occupant {
    /** energy for this creature. */
    protected double energy;

    /** Creates a creature with the name N. The intention is that this
      * name should be shared between all creatures of the same type.
      */
    public Creature(String n) {
        super(n);
    }

    /** Called when this creature moves. */
    public abstract void move();

    /** Called when this creature attacks C. */
    public abstract void attack(Creature c);

    /** Called when this creature chooses replicate.
      * Must return a creature of the same type.
      */
    public abstract Creature replicate();

    /** Called when this creature chooses stay. */
    public abstract void stay();

    /** Returns an action. The creature is provided information about its
     *  immediate NEIGHBORS with which to make a decision.
     */
    public abstract Action chooseAction(Map<Direction, Occupant> neighbors);

    /** Returns the current energy. */
    public double energy() {
        return energy;
    }

    /** Utility method that converts a Map<Direction, String> N to a list of all
        neighbors of the specified TYPE. For example, if the map contains:
        UP -> "sample", DOWN -> "empty", LEFT -> "empty", RIGHT -> "impassible"
        and type is "empty", it will return a list containing Direction.DOWN and
        Direction.LEFT */
    public List<Direction> getNeighborsOfType(Map<Direction, Occupant> n,
                                              String type) {
        List<Direction> L = new ArrayList<Direction>();
        for (Map.Entry<Direction, Occupant> entry : n.entrySet()) {
            String occupantName = entry.getValue().name();
            if (occupantName.equals(type)) {
                L.add(entry.getKey());
            }
        }
        return L;
    }
}
