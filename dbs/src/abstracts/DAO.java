
package abstracts;

import db.DBTool;

/**
 *
 * @author Patrick
 */
public abstract class DAO {

    /**
     * Closes database connection
     */
    public void close() {
        DBTool.close();
    }
}
