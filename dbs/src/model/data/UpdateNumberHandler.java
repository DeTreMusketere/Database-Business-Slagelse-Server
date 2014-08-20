package model.data;

import control.FileHandler;
import db.data.UpdateNumberDAO;
import java.util.HashMap;

/**
 *
 * @author PK
 */
public class UpdateNumberHandler {

    private static UpdateNumberDAO updateNumberDAO;
    private static int updateNumber = 0;
    private static HashMap<Integer, String> deleteList;
    private static FileHandler fileHandler;

    public UpdateNumberHandler(UpdateNumberDAO updateNumberDAO, FileHandler fileHandler) {
        UpdateNumberHandler.updateNumberDAO = updateNumberDAO;
        UpdateNumberHandler.fileHandler = fileHandler;
        updateNumber = updateNumberDAO.select();
        deleteList = fileHandler.getDeleteList();
        if (updateNumber == -1) {
            updateNumberDAO.insert(1);
            updateNumber = 1;
        }
    }

    /**
     * Gets the current updateNumber without increasing it.
     *
     * @return Current updateNumber.
     */
    public static int getUpdateNumber() {
        return updateNumber;
    }

    /**
     * Use this method whenever something that the client devices need is
     * updated. This will increase the updateNumber and return it, for use in
     * the updated object.
     *
     * @return Updated updateNumber.
     */
    public static int update() {
        updateNumber++;
        updateNumberDAO.update(updateNumber);
        return updateNumber;
    }

    /**
     * Use this method whenever you delete something that the client devices
     * should delete as well. The method increases the updateNumber, saves that
     * in the database, updating the deleteList and saving that as a
     * serializable.
     *
     * @param value A string containing the class of the object followed by the
     * id of the deleted object. The String should be written like this
     * "CLASS§ID", using the "§" sign to split the String
     */
    public static void delete(String value) {
        updateNumber++;
        updateNumberDAO.update(updateNumber);
        deleteList.put(updateNumber, value);
        fileHandler.saveDeleteList(deleteList);
    }
    
    public static HashMap<Integer, String> getDeleteList(){
        return deleteList;
    }

}
