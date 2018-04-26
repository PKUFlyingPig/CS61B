import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;

/** Utility class for writing Java objects to files. The Object must
  * implement the Serializable interface. */

public class ObjectWriter {
    private FileOutputStream fos;
    private ObjectOutputStream os;

    /** Creates a new ObjectWriter. */
    public ObjectWriter(String filename) {
        try {
            fos = new FileOutputStream(filename, false);
            os = new ObjectOutputStream(fos);
        } catch (java.io.IOException e) {
            System.out.println("Error creating ObjectWriter: ");
            e.printStackTrace();            
        }
    }

    /** Writes an object to a file. */
    public void writeObject(Serializable s) {
        try {
            os.writeObject(s);
        } catch (java.io.IOException e) {
            System.out.println("Error writing object: ");
            e.printStackTrace();
        }
    } 
} 
