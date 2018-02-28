package byog.SaveDemo;

import edu.princeton.cs.introcs.StdDraw;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Main {
    public static void main(String[] args) {
        StdDraw.enableDoubleBuffering();

        /* If a World has been saved before, we first load it */
        World w = loadWorld();

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                switch (c) {
                    case 'a':
                        w.addRandomSquare();
                        break;
                    case 'q':
                        saveWorld(w);
                        System.exit(0);
                        break;
                    default:
                }
            }
            drawEverything(w);
        }
    }

    private static void drawEverything(World w) {
        StdDraw.clear(StdDraw.BLACK);
        w.draw();
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(0.5, 0.8, "Press a to add square.");
        StdDraw.text(0.5, 0.85, "Press q to quit and save.");
        StdDraw.text(0.5, 0.9, "Delete world.ser to go back to a blank canvas.");
        StdDraw.show();
        StdDraw.pause(100);
    }

    private static World loadWorld() {
        File f = new File("./world.ser");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                World loadWorld = (World) os.readObject();
                os.close();
                return loadWorld;
            } catch (FileNotFoundException e) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException e) {
                System.out.println(e);
                System.exit(0);
            } catch (ClassNotFoundException e) {
                System.out.println("class not found");
                System.exit(0);
            }
        }

        /* In the case no World has been saved yet, we return a new one. */
        return new World();
    }

    private static void saveWorld(World w) {
        File f = new File("./world.ser");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(w);
            os.close();
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
