package abstracts;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Patrick
 * @param <DATATYPE>
 */
public abstract class Register<DATATYPE> {

    private ArrayList<DATATYPE> objects;
    private final DataDAO<DATATYPE> dao;

    public Register(DataDAO<DATATYPE> dao) {
        this.dao = dao;
        objects = new ArrayList<>();
    }

    /**
     * Inserts a source object
     *
     * @param source
     */
    public int insert(DATATYPE source) {
        objects.add(source);
        int id = dao.insert(source);
        Data d = (Data) source;
        return id;
    }

    /**
     * Updates a target with a source
     *
     * @param source
     * @param target
     */
    public void update(DATATYPE source, DATATYPE target) {
        objects.set(objects.indexOf(target), source);
        dao.update(source, target);
    }

    /**
     * Deletes a target
     *
     * @param target
     */
    public void delete(DATATYPE target) {
        objects.remove(target);
        dao.delete(target);
    }

    /**
     * Gets a target based on ID
     *
     * @param id
     * @return DATATYPE
     */
    public DATATYPE get(int id) {
        Iterator<DATATYPE> i = objects.iterator();

        while (i.hasNext()) {
            DATATYPE object = i.next();
            Data d = (Data) object;
            if (d.getId() == id) {
                return object;
            }
        }
        return null;
    }

    /**
     * Loads all objects to memory
     */
    public void load() {
        objects = dao.selectAll();
    }

    /**
     * Gets all objects
     *
     * @return ArrayList<DATATYPE>
     */
    public ArrayList<DATATYPE> getObjects() {
        return objects;
    }

    /**
     * Sets all objects
     *
     * @param objects
     */
    public void setObjects(ArrayList<DATATYPE> objects) {
        this.objects = objects;
    }

}
