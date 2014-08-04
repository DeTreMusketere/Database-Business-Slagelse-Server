package control;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
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

    public byte[] getByteArray(int id) {
        try {
            Path path = Paths.get("pictures/" + id + ".data");
            byte[] byteArray = Files.readAllBytes(path);

//            FileInputStream fis = new FileInputStream("pictures/" + id + ".data");
//            BufferedInputStream bis = new BufferedInputStream(fis);
//            byte[] byteArray = null;
//            bis.read(byteArray);
            return byteArray;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
