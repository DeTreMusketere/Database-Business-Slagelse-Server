package model.data;

import abstracts.DataDAO;
import abstracts.Register;
import control.StringTool;

/**
 *
 * @author Patrick
 */
public class TagRegister extends Register<Tag> {

    public TagRegister(IDHandler idHandler, DataDAO<Tag> dao) {
        super(idHandler, dao);
    }

    /**
     * Creates a tag with the given parameters and an updated update number and
     * the newest id from the idHandler. Then inserts it into the database and
     * into the ArrayList.
     *
     * @param name Name of the tag.
     * @param description Description of the tag.
     * @return The created tag.
     */
    public Tag create(String name, String description) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextTagId();
        String formattedName = StringTool.capitalizeWords(name);
        String formattedDescription = StringTool.capitalizeSentences(description);
        Tag t = new Tag(id, formattedName, formattedDescription, updateNumber);
        insert(t);
        return t;
    }

    /**
     * Deletes a tag and writes an entry in the deleteList for this object.
     *
     * @param target The tag to be deleted.
     */
    @Override
    public void delete(Tag target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Tag§" + id);
        super.delete(target);
    }

}
