package model.data;

import abstracts.DataDAO;
import abstracts.Register;
import control.FileHandler;
import control.StringTool;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Patrick
 */
public class PictureRegister extends Register<Picture> {

    public PictureRegister(IDHandler idHandler, DataDAO<Picture> dao) {
        super(idHandler, dao);
    }

    /**
     * Creates a picture object with the given parameters and an updated update
     * number and the newest id from the idHandler. Then inserts it into the
     * database and into the ArrayList.
     *
     * @param name Name of the picture.
     * @param imageArray A byteArray of a picture.
     * @return The created picture.
     */
    public Picture create(String name, byte[] imageArray) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextPictureId();
        String formattedName = StringTool.capitalizeWords(name);
        Picture p = new Picture(id, formattedName, imageArray, updateNumber);
        insert(p);
        return p;
    }

    /**
     * Creates a picture object with the given parameters and an updated update
     * number and the newest id from the idHandler. Then inserts it into the
     * database and into the ArrayList.
     *
     * @param name Name of the picture.
     * @param imagePath Filepath of the picture to create.
     * @return The created picture.
     */
    public Picture create(String name, String imagePath) {
        try {
            File file = new File(imagePath);
            BufferedImage image = ImageIO.read(file);
            String extension = null;
            int i = file.getName().lastIndexOf('.');
            if (i > 0) {
                extension = file.getName().substring(i + 1);
            }
            byte[] imageArray = FileHandler.convertBufferedImageToArray(image, extension);
            int updateNumber = UpdateNumberHandler.update();
            int id = idHandler.nextPictureId();
            Picture p = new Picture(id, name, imageArray, updateNumber);
            insert(p);
            return p;
        } catch (IOException ex) {
            Logger.getLogger(PictureRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * Deletes a picture and writes an entry in the deleteList for this object.
     *
     * @param target The picture to be deleted.
     */
    @Override
    public void delete(Picture target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Picture§" + id);
        super.delete(target);
    }

}
