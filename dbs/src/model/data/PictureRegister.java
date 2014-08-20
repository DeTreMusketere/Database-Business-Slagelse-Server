
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
    
    @Override
    public void update(Picture source, Picture target) {
        int updateNumber = UpdateNumberHandler.update();
        source.setUpdateNumber(updateNumber);
        super.update(source, target);
    }
    
    @Override
    public void delete(Picture target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Picture§" + id);
        super.delete(target);
    }

}
