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
import model.data.DealerRegister;
import model.data.Sale;
import model.data.Store;

/**
 *
 * @author Patrick
 */
public class StoreDAO extends DataDAO<Store> {

    private DealerRegister dealerRegister;

    public StoreDAO(DealerRegister dealerRegister) {
        this.dealerRegister = dealerRegister;
    }

    @Override
    public int insert(Store source) {
        try {
            Statement st = DBTool.getStatement();

            String name = source.getName();
            String address = source.getAddress();
            String phone = source.getPhone();
            int picture = source.getPicture();
            int parentDealerId = source.getParent().getId();

            String sql = "INSERT INTO store (name, address, phone, picture, parent_dealer_id) VALUES('" + name + "', '" + address + "', '" + phone + "', " + picture + ", " + parentDealerId + ");";
            st.execute(sql);
            ResultSet rs = st.executeQuery("SELECT LAST_INSERT_ID();");
            rs.next();
            int id = rs.getInt(1);

            return id;
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void update(Store source, Store target) {
        try {
            Statement st = DBTool.getStatement();

            String name = source.getName();
            String address = source.getAddress();
            String phone = source.getPhone();
            int picture = source.getPicture();
            int parentDealerId = source.getParent().getId();

            String sql = "UPDATE store SET name='" + name + "', address='" + address + "', phone='" + phone + "', picture=" + picture + ", parent_dealer_id=" + parentDealerId + " WHERE id_store=" + target.getId() + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Store target) {
        try {
            Statement statement = DBTool.getStatement();

            int targetid = target.getId();

            String sql = "DELETE FROM store WHERE id_store =" + targetid;
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Store select(int id) {
        try {
            Statement statement = DBTool.getStatement();

            String sql = "SELECT * FROM store WHERE id_store=" + id;
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int parentDealerId = rs.getInt(6);
            Dealer parentDealer = null;
            if (parentDealerId != 0) {
                parentDealer = dealerRegister.get(parentDealerId);
            }

            Store store = new Store(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), parentDealer);

            return store;
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Store> selectAll() {
        ArrayList<Store> stores = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM store;";
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int parentDealerId = rs.getInt(6);
                Dealer parentDealer = null;
                if (parentDealerId != 0) {
                    parentDealer = dealerRegister.get(parentDealerId);
                }

                Store store = new Store(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), parentDealer);
                stores.add(store);
            }
            return stores;
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
