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

    public void insert(int updateNumber) {
        try {
            Statement st = DBTool.getStatement();
            String sql = "INSERT INTO update_number (update_number) VALUES(" + updateNumber + ");";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateNumberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void update(int updateNumber) {
        try {
            Statement st = DBTool.getStatement();
            String sql = "UPDATE update_number SET update_number=" + updateNumber + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UpdateNumberDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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
