import java.io.ObjectInputStream;
import java.io.FileInputStream;

public class ObjectReader {
    private FileInputStream fis;
    private ObjectInputStream ois;

    public ObjectReader(String filename) {
        try {
            fis = new FileInputStream(filename);
            ois = new ObjectInputStream(fis);
        } catch (java.io.IOException e) {
            System.out.println("Error creating ObjectReader: ");
            e.printStackTrace();            
        }
    
    }

    public Object readObject() {
        try {
            return ois.readObject();
        } catch (java.io.IOException | ClassNotFoundException e) {
            System.out.println("Error reading object: ");
            e.printStackTrace();
            return null;
        }
    } 
} 

