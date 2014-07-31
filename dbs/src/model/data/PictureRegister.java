
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

    public void create(String name, String url) {
        Picture p = new Picture(0, name, url);
        int id = insert(p);
        p.setId(id);
    }

}
