package abstracts;

import java.util.ArrayList;

/**
 *
 * @author Patrick
 * @param <DATATYPE>
 */
public abstract class DataDAO<DATATYPE> extends DAO {

    /**
     * Inserts a source
     *
     * @param source
     */
    public abstract void insert(DATATYPE source);

    /**
     * Updates a target with a source
     *
     * @param source
     * @param target
     */
    public abstract void update(DATATYPE source, DATATYPE target);

    /**
     * Deletes a target
     *
     * @param target
     */
    public abstract void delete(DATATYPE target);

    /**
     * Selects a target based on ID
     *
     * @param id
     * @return DATATYPE
     */
    public abstract DATATYPE select(int id);

    /**
     * Selects all targets
     *
     * @return ArrayList<DATATYPE>
     */
    public abstract ArrayList<DATATYPE> selectAll();
}
