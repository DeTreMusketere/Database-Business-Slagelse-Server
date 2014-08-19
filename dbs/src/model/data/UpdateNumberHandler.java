package model.data;

import db.data.UpdateNumberDAO;

/**
 *
 * @author PK
 */
public class UpdateNumberHandler {

    private static UpdateNumberDAO updateNumberDAO;
    private static int updateNumber = 0;

    public UpdateNumberHandler(UpdateNumberDAO updateNumberDAO) {
        UpdateNumberHandler.updateNumberDAO = updateNumberDAO;
        updateNumber = updateNumberDAO.select();
        if (updateNumber == -1) {
            updateNumberDAO.insert(1);
            updateNumber = 1;
        }
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

    public static int update() {
        updateNumber++;
        updateNumberDAO.update(updateNumber);
        return updateNumber;
    }

}
