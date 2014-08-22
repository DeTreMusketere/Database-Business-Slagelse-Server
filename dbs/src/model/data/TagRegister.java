package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class TagRegister extends Register<Tag> {

    public TagRegister(IDHandler idHandler, DataDAO<Tag> dao) {
        super(idHandler, dao);
    }

    public Tag create(String name, String description) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextTagId();
        Tag t = new Tag(id, name, description, updateNumber);
        insert(t);
        return t;
    }

    @Override
    public void delete(Tag target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Tag§" + id);
        super.delete(target);
    }

}
