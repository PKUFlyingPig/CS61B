package huglife;

/** Actions are created by Creatures to signify intent, and take effect via
 *  the HugLife simulator.
 *
 *  Note there are three Action constructors, each with their own semantics!
 *  They are:
 *  1. Action(ActionType at): Used for creating actions that don't involve
 *     movement.
 *  2. Action(ActionType at, Direction d): Used for creating actions that
 *     involve relative movement.
 *  3. Action(ActionType at, int x, int y): Used for creation actions that
 *     involve absolute movement. NOT NECESSARY FOR THIS LAB, but included in
 *     case you want to do anything fancy.
 *
 *  There are five ActionTypes: MOVE, REPLICATE, ATTACK, STAY, and DIE. If you
 *    specify MOVE, REPLICATE, ATTACK, you must use one of the movement
 *    constructors.
 *
 *    If you specify STAY or DIE, you must use one the non-movement
 *    constructor.
 *
 *  @author Josh Hug
 */

public class Action {
    /** There are exactly five possible actions. MOVE, REPLICATE, and ATTACK
     *  are movement based. STAY and DIE are non-movement actions.
     */
    public enum ActionType {
        MOVE,
        REPLICATE,
        ATTACK,
        STAY,
        DIE
    }

    /** Actions without absolute position should use UNDEFINED position. */
    private static final int UNDEFINED = -126;

    /** Create an action involving no movement. A little strange to throw
      *  a runtime error here if the ActionType is a movement based action,
      *  since in principle we can catch this at compile time. However, doing
      *  so would require nested enums and there is enough new syntax in this
      *  lab as it is. Result is of type AT.
      */
    public Action(ActionType at) {
        if (isMoveAction(at)) {
            throw new IllegalArgumentException("Attempted to create action "
                             + "of type " + at + " with no direction.");
        }
        type = at;
        dir = null;
        x = UNDEFINED;
        y = UNDEFINED;
    }

    /** Creates action of type AT and direction D. */
    public Action(ActionType at, Direction d) {
        if (!isMoveAction(at)) {
            throw new IllegalArgumentException("Attempted to create action "
                             + "of type " + at + " with a direction.");
        }
        this.type = at;
        this.dir = d;
        this.x = UNDEFINED;
        this.y = UNDEFINED;
    }

    /** Creates action of type AT and target location X and Y. */
    public Action(ActionType at, int x, int y) {
        if (!isMoveAction(at)) {
            throw new IllegalArgumentException("Attempted to create action "
                             + "of type " + at + " with a location.");
        }
        type = at;
        dir = null;
        this.x = x;
        this.y = y;
    }

    /** The type of the action. */
    public final ActionType type;

    /** The direction of the action (if applicable). */
    public final Direction dir;

    /** The x target of the action (if applicable). */
    public final int x;

    /** The y target of the action (if applicable). */
    public final int y;

    /** Returns whether the action AT is a move action. */
    public boolean isMoveAction(ActionType at) {
        return ((at == ActionType.MOVE) || (at == ActionType.REPLICATE)
                 || (at == ActionType.ATTACK));
    }


    /** Returns whether this Action is equal to OTHER. */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        Action that = (Action) other;
        return this.x == that.x && this.y == that.y && this.dir == that.dir
               && this.type == that.type;
    }

    /** Returns string representation of this action. */
    public String toString() {
        if ((dir == null) && (x != UNDEFINED)) {
            return String.format("Action: " + type + " at " + x + ", "
                                 + y + ".");
        } else if ((dir != null)) {
            return String.format("Action: " + type + " in direction "
                                 + dir + ".");
        } else {
            return String.format("Action: " + type + ".");
        }

    }
}
