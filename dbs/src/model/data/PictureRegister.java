
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class PictureRegister extends Register<Picture> {

    public PictureRegister(IDHandler idHandler, DataDAO<Picture> dao) {
        super(idHandler, dao);
    }

    public Picture create(String name, byte[] byteArray) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextPictureId();
        Picture p = new Picture(id, name, byteArray, updateNumber);
        insert(p);
        return p;
    }

}
