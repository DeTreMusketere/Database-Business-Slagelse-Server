package control;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author PK
 */
public class FileHandler {

    private File deleteListFile = new File("data/deleteList.data");
    private static final String dataFolder = "data";
    private static final String pictureFolder = dataFolder + "/pictures";
    
    public static void makeFolders() {
        File data = new File(dataFolder);
        data.mkdir();
        
        File picture = new File(pictureFolder);
        picture.mkdir();
    }
    
    /**
     * Converts an bufferedimage to a bytearray
     * @param image
     * @return ByteArray
     */
    public static byte[] convertBufferedImageToArray(BufferedImage image, String type) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write( image, type, baos );
            baos.flush();
            byte[] imageInByte = baos.toByteArray();
            baos.close();
            return imageInByte;
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Saves the byte array of a picture.
     *
     * @param byteArray. The byte array derived from the picture.
     * @param id id of the picture to be saved.
     */
    public void savePictureByteArray(byte[] byteArray, int id) {
        try {
            FileOutputStream fos = new FileOutputStream("data/pictures/" + id + ".data");
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
     * Returns the bytearray of a picture
     *
     * @param id id of the picture
     * @return the byte array of the picture
     */
    public byte[] getPictureByteArray(int id) {
        try {
            Path path = Paths.get("data/pictures/" + id + ".data");
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
     * Deletes the saved bye array of a picture.
     *
     * @param id id of the picture
     */
    public void deletePictureFile(int id) {
        File file = new File("data/pictures/" + id + ".data");
        file.delete();
    }

    /**
     * Saves the deleteList in the data folder.
     *
     * @param deleteList The list of deleted objects from UpdateNumberHandler.
     */
    public void saveDeleteList(HashMap<Integer, String> deleteList) {
        try {
            FileOutputStream fos = new FileOutputStream(deleteListFile);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(deleteList);
            oos.close();
            fos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public HashMap<Integer, String> getDeleteList() {
        try {
            FileInputStream fis = new FileInputStream(deleteListFile);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<Integer, String> map = (HashMap<Integer, String>) ois.readObject();
            return map;
        } catch (IOException | ClassNotFoundException ex) {
            return new HashMap<Integer, String>();
        }
    }
}
