package control;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PK
 */
public class FileHandler {

    /**
     *Saves the byte array of a picture.
     * @param byteArray. The byte array derived from the picture.
     * @param id id of the picture to be saved.
     */
    public void saveByteArray(byte[] byteArray, int id) {
        try {
            FileOutputStream fos = new FileOutputStream("pictures/" + id + ".data");
            try (BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                bos.write(byteArray);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *Returns the bytearray of a picture
     * @param id id of the picture
     * @return the byte array of the picture
     */
    public byte[] getByteArray(int id) {
        try {
            Path path = Paths.get("pictures/" + id + ".data");
            byte[] byteArray = Files.readAllBytes(path);
            return byteArray;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     *Deletes the saved bye array of a picture.
     * @param id id of the picture
     */
    public void deleteFile(int id){
        File file = new File("pictures/" + id + ".data");
        file.delete();
    }
}
