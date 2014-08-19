
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class TagRegister extends Register<Tag> {

    public TagRegister(DataDAO<Tag> dao) {
        super(dao);
    }
    
    public Tag create(String name, String description) {
        Tag t = new Tag(0, name, description);
        int id = insert(t);
        t.setId(id);
        return t;
    }

}
