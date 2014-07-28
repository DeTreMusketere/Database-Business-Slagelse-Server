package db.data;

import abstracts.DataDAO;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.Dealer;

/**
 *
 * @author PK
 */
public class DealerDAO extends DataDAO<Dealer> {

    @Override
    public int insert(Dealer source) {
        try {
            Statement statement = DBTool.getStatement();

            String name = source.getName();
            String description = source.getDescription();
            String phone = source.getPhone();
            int picture = source.getPicture();
            int id = -1;
            String sql = "INSERT INTO dealer (name, description, phone, picture) VALUES('" + name + "', '" + description + "', '" + phone + "', '" + picture + "');";
            statement.execute(sql);
            try (ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID();")) {
                while (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void update(Dealer source, Dealer target) {
        try {
            Statement statement = DBTool.getStatement();

            String name = source.getName();
            String description = source.getDescription();
            String phone = source.getPhone();
            int picture = source.getPicture();
            int targetid = target.getId();

            String sql = "UPDATE dealer SET name='" + name + "', description='" + description + "', phone='" + phone + "', picture='" + picture + "' WHERE id_dealer=" + targetid;
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void delete(Dealer target) {
        try {
            Statement statement = DBTool.getStatement();

            int targetid = target.getId();

            String sql = "DELETE FROM dealer WHERE id_dealer =" + targetid;
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Dealer select(int id) {
        try {
            Statement statement = DBTool.getStatement();
            Dealer dealer = null;
            String sql = "SELECT * FROM dealer WHERE id_dealer=" + id;
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    dealer = new Dealer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                }
            }
            return dealer;
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Dealer> selectAll() {
        try {
            Statement statement = DBTool.getStatement();

            ArrayList<Dealer> dealerList = new ArrayList<>();

            String sql = "SELECT * FROM dealer";
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    Dealer dealer = new Dealer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5));
                    dealerList.add(dealer);
                }
            }
            return dealerList;
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
