import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Basic sanity check for your GraphDB construction. This test simply tests a small number of
 * hard coded queries, but if you get them right, your GraphDB class is probably correct.
 */
public class TestGraphBuilding {
    private static GraphDB graph;
    private static GraphDB graphSmall;
    private static final String OSM_DB_PATH = "../library-sp18/data/berkeley-2018.osm.xml";
    private static final String OSM_DB_PATH_SMALL = "../library-sp18/data/berkeley-2018-small.osm.xml";
    private static boolean initialized = false;

    /**
     * Initializes the student graphs.
     * You should not need to modify this code. If you do, then the Autograder
     * may not work with your code.
     *
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {
        if (initialized) {
            return;
        }
        graph = new GraphDB(OSM_DB_PATH);
        graphSmall = new GraphDB(OSM_DB_PATH_SMALL);
        initialized = true;
    }

    /**
     * Here are a couple things to consider if you don't have the right number:
     *
     * 1. Be sure to only add ways that are valid. (Especially if you have 374535 nodes after clean)
     * 2. Depending on the graph implementation, edges may be directed or undirected. Remember that
     * all roads are two-way for this project!
     */
    @Test
    public void testNodeCount() {
        Iterable<Long> ids = graph.vertices();
        int numberOfNodes = countIterableItems(ids);
        assertEquals("Your graph should have 25176 nodes after cleaning. Consider removing the call"
                + " to clean and seeing if you get 399287 nodes as expected as a sanity check on"
                + "  your results before calling clean.", 25176, numberOfNodes);
    }

    @Test
    public void testNodeCountSmall() {
        Iterable<Long> ids = graphSmall.vertices();
        int numberOfNodes = countIterableItems(ids);
        assertEquals("Your graph should have 21 nodes after cleaning. Consider removing the call"
                + " to clean and seeing if you get 250 nodes as expected as a sanity check on"
                + "  your results before calling clean.", 21, numberOfNodes);

    }

    @Test
    public void testAdjacent() {
        long v = 3347105714L;
        HashSet<Long> expected = new HashSet<>();
        HashSet<Long> actual = new HashSet<>();
        expected.add(1026001234L);
        expected.add(2291835223L);

        for (long neighbor : graph.adjacent(v)) {
            actual.add(neighbor);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testAdjacentSmall() {
        long v = 4333613088L;
        HashSet<Long> expected = new HashSet<>();
        HashSet<Long> actual = new HashSet<>();
        expected.add(267632001L);
        expected.add(2252623356L);

        for (long neighbor : graphSmall.adjacent(v)) {
            actual.add(neighbor);
        }
        assertEquals(expected, actual);
    }

    @Test
    public void testLonAndLat() {
        long v = 1790732915L;
        assertEquals(-122.2891056, graph.lon(v), 0.00001);
        assertEquals(37.8885798, graph.lat(v), 0.00001);
    }

    @Test
    public void testLonAndLatSmall() {
        long v = 4333613088L;
        assertEquals(-122.2522578, graphSmall.lon(v), 0.00001);
        assertEquals(37.8686094, graphSmall.lat(v), 0.00001);
    }

    @Test
    public void testDistance() {
        long v = 1790732915L;
        long w = 2385920503L;
        assertEquals(4.553799921200937, graph.distance(v, w), 0.00001);
    }

    @Test
    public void testDistanceSmall() {
        long v = 267632001L;
        long w = 2252623344L;
        assertEquals(0.04455059524452295, graphSmall.distance(v, w), 0.00001);
    }

    @Test
    public void testClosest() {
        double lon = -122.2892;
        double lat = 37.8885;
        assertEquals(53042711L, graph.closest(lon, lat));
    }

    @Test
    public void testClosestSmall() {
        double lon = -122.25207;
        double lat = 37.8680554;
        assertEquals(2252623344L, graphSmall.closest(lon, lat));
    }

    private <Item> int countIterableItems(Iterable<Item> it) {
        int N = 0;
        for (Item x : it) {
            N += 1;
        }
        return N;
    }
}
