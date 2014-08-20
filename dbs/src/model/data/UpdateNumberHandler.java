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

    public int getUpdateNumber() {
        return updateNumber;
    }

    public static int update() {
        updateNumber++;
        updateNumberDAO.update(updateNumber);
        return updateNumber;
    }

    /**
     * Use this method whenever you delete something that the client devices
     * should delete as well.
     * The method increases the updateNumber, saves that in the database, updating the deleteList and saving that as a serializable.
     * @param value A string containing the object type followed by the id of
     * the deleted object The String should be written like this "TABLE§ID",
     * using the "§" sign to split the String
     */
    public static void delete(String value) {
        updateNumber++;
        updateNumberDAO.update(updateNumber);
        deleteList.put(updateNumber, value);
        fileHandler.saveDeleteList(deleteList);
    }

}
