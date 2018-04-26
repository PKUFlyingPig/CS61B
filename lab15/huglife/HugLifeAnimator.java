package huglife;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ConcurrentLinkedDeque;

/** GIF maker for HugLife.
 *  To set this up, first install ImageMagick (http://www.imagemagick.org/script/binary-releases.php).
 *  Then modify the IMAGEMAGICK constant below to point to the "convert" utility.
 *
 *  Note: If the simulation is terminated early (before MAX_CYCLES), frames close to the end may be left out of the GIF.
 *  If this is a problem, add a timeout before the finalizeGif() call in the shutdown hook in init().
 *  Alternatively, do away with the shutdown hook altogether and run ImageMagick manually from the command line.
 *
 *  @author Allen Guo
 */
public class HugLifeAnimator {

    /** Path to ImageMagick's "convert" utility.
     *  E.g., on Windows, this path will look like "C:\\Program Files\\ImageMagick-6.9.2-Q16\\convert".
     */
    public static final String IMAGEMAGICK = "C:\\Program Files\\ImageMagick-6.9.2-Q16\\convert";

    /** List of files to delete on exit.
     *  Concurrent because saveGifFrame() may still be writing frames on one thread while the shutdown hook
     *  has already started running on another thread.
     */
    public static ConcurrentLinkedDeque<File> toDelete = new ConcurrentLinkedDeque<>();

    /** Sets up the animator and adds a shutdown hook that generates the final GIF.
     *  @param  outputName name of the file to store the final GIF to
     */
    public static void init(String outputName) {
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                finalizeGif(outputName);
                for (File file : toDelete) {
                    file.delete();
                }
            }
        }));
    }

    /** Saves a single frame and schedules it for deletion on exit.
     *  To be called after each cycle, before incrementing the cycleCount.
     */
    public static void saveGifFrame(int cycleCount) {
        String filename = String.format("%05d.png", cycleCount);
        StdDraw.save(filename);
        toDelete.add(new File(filename));
    }

    /** Combines all PNG files in the current directory into a GIF. */
    private static void finalizeGif(String outputName) {
        ProcessBuilder pb = new ProcessBuilder(IMAGEMAGICK, "-delay", "10", "-loop", "1", "*.png", outputName);
        pb.redirectErrorStream(true);
        try {
            System.out.println("Writing GIF...");
            Process process = pb.start();
            BufferedReader inStreamReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while((s = inStreamReader.readLine()) != null){
                System.out.println(s);
            }
            System.out.println("Wrote GIF in " + System.getProperty("user.dir"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
