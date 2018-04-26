package huglife;

import java.util.Queue;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/** Primary class for simulation of a HugLife world.
 *
 *  Only intended for use by the HugLife class (or something similar).
 *  @author Josh Hug
 */
public class Grid {
    /** Size of the grid */
    private int N;
    /** Total living population of the world */
    private int population;
    /** 2D grid of all the occupants */
    private Occupant[][] occupants;
    /** Not the most efficient data structure! We'll end up spending
     * a linear amount of time scanning through this queue, when
     * we could get this done essentially for free. Maybe I'll
     * fix this later, but I don't think it's a performance bottleneck.
     */
    private Queue<Position> moveQueue;

    /** dummy position used to track when an entire cycle has been completed */
    private static final Position sentinel = new Position(-23, -23);


    /** Creates a grid of size N */
    public Grid(int N) {
        this.N = N;
        population = 0;
        occupants = new Occupant[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                occupants[y][x] = new Empty();
            }
        }
        moveQueue = new ArrayDeque<Position>();
        moveQueue.add(sentinel);

    }

    /** Returns true if X and Y are in bounds */
    private boolean inBounds(int x, int y) {
        if (x < 0 || y < 0 || x >= N || y >= N) {
            return false;
        }
        return true;
    }

    /** Returns true if X, Y is empty */
    private boolean isEmpty(int x, int y) {
        return getOccupant(x, y).name.equals("empty");
    }

    /** Returns true if X and Y contains a living thing,
        i.e. if the contents are anything other than empty
        or impassible. Wowza, actually useful instanceof!  */
    private boolean isCreature(int x, int y) {
        Occupant o = getOccupant(x, y);
        return o instanceof Creature;
    }

    /** Returns occupant of X and Y */
    private Occupant getOccupant(int x, int y) {

        if (inBounds(x, y)) {
            return occupants[y][x];
        }

        return new Impassible();
    }

    /** Returns creature in position X and Y. If there is
        no creature at position x, y, an exception is thrown.  */
    private Creature getCreature(int x, int y) {
        creatureCheck(x, y);
        return (Creature) getOccupant(x, y);
    }

    /** creates a new member of the world and puts at end of queue.
        creation means:
        1. increase population
        2. add to grid
        3. add to deque */
    void createCreature(int x, int y, Creature c) {
        if (!isEmpty(x, y)) {
            Occupant oldOccupant = getOccupant(x, y);
            throw new IllegalArgumentException(
                      String.format("Tried to place a %s at (%d, %d), but "
                       + " space is already occupied by a %s.", c.name,
                       x, y, oldOccupant));
        }

        population += 1;
        placeOccupant(x, y, c);

        getInLine(x, y);
    }

    /** destroys creature in position x, y
        destruction means:
        1. reduce population
        2. remove from grid
        3. remove from deque */
    void destroyCreature(int x, int y) {
        if (!isCreature(x, y)) {
            throw new IllegalArgumentException(
                      String.format("Tried to destroy a creature at (%d, %d), but " +
                             "no creature at this position.", x, y));
        }

        population -= 1;
        removeOccupant(x, y);
        removeFromQueue(x, y);
    }

    /** Places occupant O in position X and Y, throwing an
        exception if the space is not empty.  */

    void placeOccupant(int x, int y, Occupant o) {
        if (!isEmpty(x, y)) {
            Occupant oldOccupant = getOccupant(x, y);
            throw new IllegalArgumentException(
                      String.format("Tried to place a %s at (%d, %d), but "
                       + " space is already occupied by a %s.", o.name,
                       x, y, oldOccupant));
        }

        occupants[y][x] = o;
    }


    /** removes occupant, does not affect population or dequeue */
    private void removeOccupant(int x, int y) {
        if (isEmpty(x, y) || !inBounds(x, y)) {
            throw new IllegalArgumentException(
                      String.format("Tried to remove (%d, %d), but "
                       + " space is empty or out of bounds.", x, y));
        }

        occupants[y][x] = new Empty();
    }

    /** True if any life exists. */
    private boolean lifeExists() {
        return population > 0;
    }


    /** Returns the TOP, BOTTOM, LEFT, and RGIHT neighbors
      * of position X and Y */

    public Map<Direction, Occupant> neighbors(int x, int y) {
        HashMap<Direction, Occupant> neighbors =
                new HashMap<Direction, Occupant>();
        Occupant top = getOccupant(x, y + 1);
        Occupant bottom = getOccupant(x, y - 1);
        Occupant left = getOccupant(x - 1, y);
        Occupant right = getOccupant(x + 1, y);

        neighbors.put(Direction.TOP, top);
        neighbors.put(Direction.BOTTOM, bottom);
        neighbors.put(Direction.LEFT, left);
        neighbors.put(Direction.RIGHT, right);

        return neighbors;
    }


    /** Redraw entire world (slow!).
     *
     *  In a better simulator, we'd only redraw the things
     *  we really needed to redraw.
     */
    public void drawWorld() {
        StdDraw.clear();
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setXscale(0, N);
        StdDraw.setYscale(0, N);
        StdDraw.filledSquare(N / 2.0, N / 2.0, N / 2.0);

        for (int x = 0; x < N; x += 1) {
            for (int y = 0; y < N; y += 1) {
                Occupant o = getOccupant(x, y);
                StdDraw.setPenColor(o.color());
                StdDraw.filledSquare(x + 0.5, y + 0.5, 0.45);
            }
        }
    }

    /**
     * Writes the world to a .world file
     * @param worldName name fo the world that is being written
     */
    public void writeWorld(String worldName) {
        Out out = new Out("huglife/" + worldName + ".world");
        for (int j = N; j >= 0; j--) {
             for (int i = 0; i < N; i++) {
                Occupant o = getOccupant(i, j);
                out.println(o.name + " " + i + " " + j);
            }
            out.println();
        }
    }

    /** Gives the x, y coordinates if we go in direction of action A from
     *  position X, Y
     */

    private static Position targetPosition(int x, int y, Action a) {
        if (a.dir == Direction.TOP) {
            return new Position(x, y + 1);
        }
        if (a.dir == Direction.BOTTOM) {
            return new Position(x, y - 1);
        }
        if (a.dir == Direction.LEFT) {
            return new Position(x - 1, y);
        }
        if (a.dir == Direction.RIGHT) {
            return new Position(x + 1, y);
        }
        return new Position(a.x, a.y);

    }


    /**  Remove position X, Y from the move queue.
      *
      *  Permit removal even if something is not in the queue.
      *  This can happen because a creature just chose the die action */
    private void removeFromQueue(int x, int y) {
        Position p = new Position(x, y);
        if (moveQueue.contains(p)) {
            moveQueue.remove(p);
        }
    }

    /** Puts position X, Y into the move queue. */
    private void getInLine(int x, int y) {
        Position p = new Position(x, y);
        if (!isCreature(x, y)) {

            String msg = String.format("Tried to add creature at (%d, %d) to " +
                         "the move queue, but no creature exists at that spot.",
                         x, y);

            throw new IllegalArgumentException(msg);
        }

        if (moveQueue.contains(p)) {
            String msg = String.format("Tried to add creature at (%d, %d) to " +
                         "the move queue, but creature is already in line.",
                         x, y);

            throw new IllegalArgumentException(msg);

        }

        moveQueue.add(p);
    }

    /** Peforms a move action from X, Y to TX, TY. */
    void doMove(int x, int y, int tx, int ty) {
        Creature from = getCreature(x, y);
        Occupant to = getOccupant(tx, ty);

        collisionCheck(x, y, tx, ty, "move");

        placeOccupant(tx, ty, from);
        removeOccupant(x, y);

        from.move();

        getInLine(tx, ty);
    }

    /** Perform the replicate action from X, Y to TX, TY */
    void doReplicate(int x, int y, int tx, int ty) {
        Creature from = getCreature(x, y);
        Occupant to = getOccupant(tx, ty);

        creatureCheck(x, y, "replicate");
        collisionCheck(x, y, tx, ty, "replicate");

        Creature newCreature = from.replicate();
        createCreature(tx, ty, newCreature);

        getInLine(x, y);
    }

    /** Kills off creature in position X, Y, replacing it with
        an empty square. */
    void doDie(int x, int y) {
        destroyCreature(x, y);
    }

    /** Creature in X, Y takes TX, TY's place. Tx, ty
        is removed from the board. Attacks always succeed.
        Attacks on empty squares are forbidden. */
    void doAttack(int x, int y, int tx, int ty) {
        creatureCheck(x, y, "attack");
        creatureCheck(tx, ty, "attack");

        Creature from = getCreature(x, y);
        Creature to = getCreature(tx, ty);

        destroyCreature(tx, ty);

        removeOccupant(x, y);
        placeOccupant(tx, ty, from);
        from.attack(to);
        getInLine(tx, ty);
    }

    /** Performs the stay action in position X, Y */
    void doStay(int x, int y) {
        Creature c = getCreature(x, y);

        c.stay();
        getInLine(x, y);
    }

    /** Handles action A in position X, Y.
     */
    void handleAction(int x, int y, Action a) {
        Position p = targetPosition(x, y, a);
        int tx = p.x();
        int ty = p.y();

        if (a.type == Action.ActionType.MOVE) {
            doMove(x, y, tx, ty);
        }

        if (a.type == Action.ActionType.REPLICATE) {
            doReplicate(x, y, tx, ty);
        }

        if (a.type == Action.ActionType.DIE) {
            doDie(x, y);
        }

        if (a.type == Action.ActionType.ATTACK) {
            doAttack(x, y, tx, ty);
        }

        if (a.type == Action.ActionType.STAY) {
            doStay(x, y);
        }

    }


    /** Requests an action from the creature in position x, y.
     *  The weird cast is so we can throw a more useful message to
     *  the user if something goes wrong, e.g. we ask an empty
     *  square for an action.
     */
    Action requestAction(int x, int y) {
        creatureCheck(x, y, "requestAction");
        Creature c = (Creature) getOccupant(x, y);
        if (c.energy() < 0) {
            return new Action(Action.ActionType.DIE);
        }

        Map<Direction, Occupant> nbot = neighbors(x, y);
        return c.chooseAction(nbot);
    }

    /** Perform one tic of the simulation. Returns true if
     *  this tic was the completion of a cycle;
     */
    public boolean tic() {
        if (lifeExists()) {
            if (false) {
                assertQueueCorrect();
            }
            Position p = moveQueue.remove();
            if (p.equals(sentinel)) {
                moveQueue.add(sentinel);
                return true;
            }

            Action action = requestAction(p.x, p.y);
            handleAction(p.x, p.y, action);
            return false;
        }
        return true;
    }


    /** Checks that a move from X, Y to TX, TY is valid, where
     *  MOVESTR is the type of move, printed for debugging reasons.
     */

    private void collisionCheck(int x, int y, int tx, int ty,
                                String moveStr) {
        Occupant from = getOccupant(x, y);
        Occupant to = getOccupant(tx, ty);

        if (!isEmpty(tx, ty)) {

            String msg = String.format("%s tried to %s from " +
                         "(%d, %d) to (%d, %d) already occupied by %s.",
                         from.name(), moveStr, x, y, tx, ty, to.name());

            throw new IllegalArgumentException(msg);
        }
    }


    /** Checks that a creature exists at position X, Y, where
     *  ACTIONSTR is the type of action, printed for debugging reasons.
     */
    private void creatureCheck(int x, int y, String actionStr) {
        if (!isCreature(x, y)) {

            String msg = String.format("Something tried to %s at " +
                         "(%d, %d), but no creature exists at that spot.",
                         actionStr, x, y);

            throw new IllegalArgumentException(msg);
        }
    }

    /** Checks that a creature exists at position X, Y.
     */

    private void creatureCheck(int x, int y) {
        if (!isCreature(x, y)) {

            String msg = String.format("Tried to get creature from " +
                         "(%d, %d), but no creature exists at that spot.",
                         x, y);

            throw new IllegalArgumentException(msg);
        }
    }

    /** Method Used only for debugging.
     *  Asserts that the queue matches the world state.
     */
    private void assertQueueCorrect() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                Position p = new Position(x, y);
                Occupant o = getOccupant(x, y);
                if ((o instanceof Creature) && (!moveQueue.contains(p))) {
                    throw new Error(String.format(
                          "(%d, %d) is missing from moveQueue", x, y));
                }
            }
        }
    }
}

