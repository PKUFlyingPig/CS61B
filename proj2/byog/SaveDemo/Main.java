package byog.SaveDemo;

import edu.princeton.cs.introcs.StdDraw;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        /* If an editor has been saved before, we first load it */
        Editor e = loadEditor();
        e.initialize();

        /* Loop to run the Editor with the following commands:
              $: Saves the current screen
              @: Exits the editor
              !: Fred suggests the gang splits up (again...)
           Any other character will be added to a String which
           is displayed in the center of the screen */
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = StdDraw.nextKeyTyped();
                switch (c) {
                    case '$':
                        saveEditor(e);
                        break;
                    case '@':
                        System.exit(0);
                        break;
                    case '!':
                        e.tend();
                        break;
                    default: e.addChar(c);
                }
            }
            e.show();
        }
    }

    private static Editor loadEditor() {
        File f = new File("./save_data");
        if (f.exists()) {
            try {
                FileInputStream fs = new FileInputStream(f);
                ObjectInputStream os = new ObjectInputStream(fs);
                return (Editor) os.readObject();
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

        /* In the case no Editor has been saved yet, we return a new one. */
        return new Editor();
    }

    private static void saveEditor(Editor editor) {
        File f = new File("./save_data");
        try {
            if (!f.exists()) {
                f.createNewFile();
            }
            FileOutputStream fs = new FileOutputStream(f);
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(editor);
        }  catch (FileNotFoundException e) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException e) {
            System.out.println(e);
            System.exit(0);
        }
    }
}
