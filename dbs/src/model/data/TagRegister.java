
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

}
