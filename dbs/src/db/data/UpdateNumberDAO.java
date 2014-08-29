package db.data;

import abstracts.DAO;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PK
 */
public class UpdateNumberDAO extends DAO {

    public UpdateNumberDAO() {
    }

    /**
     * Inserts an update number to the database.
     *
     * @param updateNumber The update number to put into the database.
     */
    public void insert(int updateNumber) {
        try {
            Statement st = DBTool.getStatement();
            String sql = "INSERT INTO update_number (update_number) VALUES(" + updateNumber + ");";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateNumberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Updates the update number in the database.
     *
     * @param updateNumber The new update number to replace the old one.
     */
    public void update(int updateNumber) {
        try {
            Statement st = DBTool.getStatement();
            String sql = "UPDATE update_number SET update_number=" + updateNumber + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateNumberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Gets the update number from the database.
     *
     * @return Update number
     */
    public int select() {
        Statement st = DBTool.getStatement();
        String sql = "SELECT * FROM update_number;";
        int updateNumber = -1;
        try (ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                updateNumber = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UpdateNumberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateNumber;
    }

}
