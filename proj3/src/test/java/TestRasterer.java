import org.junit.Before;
import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestRasterer {
    private static final double DOUBLE_THRESHOLD = 0.0000000000001;
    private static final String PARAMS_FILE = "raster_params.txt";
    private static final String RESULTS_FILE = "raster_results.txt";
    private static final int NUM_TESTS = 8;
    private static Rasterer rasterer;


    @Before
    public void setUp() throws Exception {
        rasterer = new Rasterer();
    }

    @Test
    public void testGetMapRaster() throws Exception {
        List<Map<String, Double>> testParams = paramsFromFile();
        List<Map<String, Object>> expectedResults = resultsFromFile();

        for (int i = 0; i < NUM_TESTS; i++) {
            System.out.println(String.format("Running test: %d", i));
            Map<String, Double> params = testParams.get(i);
            Map<String, Object> actual = rasterer.getMapRaster(params);
            Map<String, Object> expected = expectedResults.get(i);
            checkParamsMap("Your results did not match the expected results", expected, actual);
        }
    }

    private List<Map<String, Double>> paramsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(PARAMS_FILE), Charset.defaultCharset());
        List<Map<String, Double>> testParams = new ArrayList<>();
        int lineIdx = 2; // ignore comment lines
        for (int i = 0; i < NUM_TESTS; i++) {
            Map<String, Double> params = new HashMap<>();
            params.put("ullon", Double.parseDouble(lines.get(lineIdx)));
            params.put("ullat", Double.parseDouble(lines.get(lineIdx + 1)));
            params.put("lrlon", Double.parseDouble(lines.get(lineIdx + 2)));
            params.put("lrlat", Double.parseDouble(lines.get(lineIdx + 3)));
            params.put("w", Double.parseDouble(lines.get(lineIdx + 4)));
            params.put("h", Double.parseDouble(lines.get(lineIdx + 5)));
            testParams.add(params);
            lineIdx += 6;
        }
        return testParams;
    }

    private List<Map<String, Object>> resultsFromFile() throws Exception {
        List<String> lines = Files.readAllLines(Paths.get(RESULTS_FILE), Charset.defaultCharset());
        List<Map<String, Object>> expected = new ArrayList<>();
        int lineIdx = 4; // ignore comment lines
        for (int i = 0; i < NUM_TESTS; i++) {
            Map<String, Object> results = new HashMap<>();
            results.put("raster_ul_lon", Double.parseDouble(lines.get(lineIdx)));
            results.put("raster_ul_lat", Double.parseDouble(lines.get(lineIdx + 1)));
            results.put("raster_lr_lon", Double.parseDouble(lines.get(lineIdx + 2)));
            results.put("raster_lr_lat", Double.parseDouble(lines.get(lineIdx + 3)));
            results.put("depth", Integer.parseInt(lines.get(lineIdx + 4)));
            results.put("query_success", Boolean.parseBoolean(lines.get(lineIdx + 5)));
            lineIdx += 6;
            String[] dimensions = lines.get(lineIdx).split(" ");
            int rows = Integer.parseInt(dimensions[0]);
            int cols = Integer.parseInt(dimensions[1]);
            lineIdx += 1;
            String[][] grid = new String[rows][cols];
            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < cols; c++) {
                    grid[r][c] = lines.get(lineIdx);
                    lineIdx++;
                }
            }
            results.put("render_grid", grid);
            expected.add(results);
        }
        return expected;
    }

    private void checkParamsMap(String err, Map<String, Object> m1, Map<String, Object> m2) {
        for (String key : m1.keySet()) {
            assertTrue("Your results map is missing " + key, m2.containsKey(key));
            Object o1 = m1.get(key);
            Object o2 = m2.get(key);
            if (o1 instanceof Double) {
                assertTrue(err, Math.abs((Double) o1 - (Double) o2) < DOUBLE_THRESHOLD);
            } else if (o1 instanceof String[][]) {
                assertArrayEquals(err, (String[][]) o1, (String[][]) o2);
            } else {
                assertEquals(err, o1, o2);
            }
        }
    }
}
