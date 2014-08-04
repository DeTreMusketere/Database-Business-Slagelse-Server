
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class PictureRegister extends Register<Picture> {

    public PictureRegister(DataDAO<Picture> dao) {
        super(dao);
    }

    public void create(String name, byte[] byteArray) {
        Picture p = new Picture(0, name, byteArray);
        int id = insert(p);
        p.setId(id);
    }

}
