package model.data;

import db.data.UpdateNumberDAO;

/**
 *
 * @author PK
 */
public class UpdateNumberHandler {

    private final UpdateNumberDAO updateNumberDAO;
    private int updateNumber = 0;

    public UpdateNumberHandler(UpdateNumberDAO updateNumberDAO) {
        this.updateNumberDAO = updateNumberDAO;
        updateNumber = updateNumberDAO.select();
        if (updateNumber == -1) {
            updateNumberDAO.insert(1);
            updateNumber = 1;
        }
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

    public int update() {
        updateNumber++;
        updateNumberDAO.update(updateNumber);
        return updateNumber;
    }

}
