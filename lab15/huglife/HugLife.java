package huglife;
import creatures.*;

/** World facing class for HugLife simulator.
 *  @author Josh Hug
 */
public class HugLife {

    /** Size of the world. Probably best to keep this under 100 
      *  or so.
     */
    public static final int WORLD_SIZE = 15;

    /** Maximum number of cycles to simulate by default. */
    public static final int MAX_CYCLES = 1000;

    /** Time in milliseconds between simulation steps. 
     *  Reduce to make things run faster.
     */
    public static final int PAUSE_TIME_PER_SIMSTEP = 100;

    /** If true, the HugLifeAnimator class saves an image after every cycle.
     *  After all cycles have finished, or on exit, these images are combined into an animated GIF.
     *  Requires ImageMagick and only works in SIMULATE_BY_CYCLE mode.
     *  Consider reducing PAUSE_TIME_PER_SIMSTEP when this flag is on, since file writes will take some time.
     *  See the HugLifeAnimator class for details.
     */
    public static final boolean GENERATE_GIF = false;

    /** Name of output GIF (relative to working directory), assuming GENERATE_GIF is true.
     *  Should end in ".gif".
     */
    public static final String GIF_OUTPUT_FILENAME = System.currentTimeMillis() + ".gif";

    /** Creates a new world grid of size N for this HugLife simulation. */
    public HugLife(int N) {
        g = new Grid(N);
    }

    /** Adds a creature C to the HugLife universe at X, Y. */
    public void addCreature(int x, int y, Creature c) {
        g.createCreature(x, y, c);
    }

    /** Simulates the world for CYCLES cycles, simulation
     *  one entire cycle between 
     */
    public void simulate(int cycles) {
        if (GENERATE_GIF) {
            HugLifeAnimator.init(GIF_OUTPUT_FILENAME);
        }
        int cycleCount = 0;
        while (cycleCount < cycles) {
            boolean cycleCompleted = g.tic();
            if (cycleCompleted) {
                g.drawWorld();
                StdDraw.show(PAUSE_TIME_PER_SIMSTEP);
                if (GENERATE_GIF) {
                    HugLifeAnimator.saveGifFrame(cycleCount);
                }
                cycleCount += 1;
            }
        }
    }

    /** Simulates the world for TICS tics, simulating
     *   TICSBETWEENDRAW in between world drawing events.
     */
    public void simulate(int tics, int ticsBetweenDraw) {
        for (int i = 0; i < tics; i++) {
            g.tic();
            if ((i % ticsBetweenDraw) == 0) {
                g.drawWorld();
                StdDraw.show(PAUSE_TIME_PER_SIMSTEP);
            }
        }
    }

    /** A set of precanned hard-coded worlds. This is terrible
     *  style, but hey it's very easy to write this way.
     */
    public void initialize(String worldName) {
        if (worldName.equals("samplesolo")) {
            addCreature(11, 1, new SampleCreature());
        }

        else if (worldName.equals("sampleplip")) {
            addCreature(11, 1, new SampleCreature());
            addCreature(12, 12, new Plip());
            addCreature(4, 3, new Plip());
        }

        else if (worldName.equals("strugggz")) {
            System.out.println("You need to uncomment the strugggz test!");
            /*addCreature(11, 1, new SampleCreature());
            addCreature(12, 12, new Plip());
            addCreature(3, 3, new Plip());
            addCreature(4, 3, new Plip());

            addCreature(2, 2, new Clorus(1));*/
        } else {
            System.out.println("World name not recognized!");
        }
    }

    /**
     * Reads the world from file with worldName and intialized
     * a HugLife with the contents of the file
     * NOTE: DON'T USE THIS; KEPT FOR TESTING PURPOSES
     * @param  worldName name of the file to read from
     * @return a newly initialized HugLife
     */
    public static HugLife readWorld(String worldName) {
        In in = new In("huglife/" + worldName + ".world");
        HugLife h = new HugLife(WORLD_SIZE);
        while (!in.isEmpty()) {
            String creature = in.readString();
            int x = in.readInt();
            int y = in.readInt();
            switch (creature) {
                //Uncomment this when you're ready to test out your clorus class
                // case "clorus":
                //     h.addCreature(x, y, new Clorus(1));
                //     break;
                case "plip":
                    h.addCreature(x, y, new Plip());
                    break;
                case "samplecreature":
                    h.addCreature(x, y, new SampleCreature());
                    break;
            }
        }
        return h;
    }

    /** Runs world name specified by ARGS[0]. */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java huglife.HugLife [worldname]");
            return;
        }
        HugLife h = readWorld(args[0]);
        // HugLife h = new HugLife(WORLD_SIZE);
        // h.initialize(args[0]); DON'T USE ME

        if (SIMULATE_BY_CYCLE) {
            h.simulate(MAX_CYCLES);
        } else {
            h.simulate(MAX_TICS, TICS_BETWEEN_DRAW);
        }
    }

    /** Grid for holding all the creatures. */
    private Grid g;


    /** By default, the simulator simulates by cycle, i.e.
     *  allows every creature to move before drawing. 
     *  If you set this to false, then the world will be drawn
     *  between moves (much slower).
     */
    public static final boolean SIMULATE_BY_CYCLE = true;

    /** Maximum number of tics to simulate by default if using. */
    public static final int MAX_TICS = 100000;
    /** Number of tics to simulate between draw ops. */
    public static final int TICS_BETWEEN_DRAW = 10;

}
