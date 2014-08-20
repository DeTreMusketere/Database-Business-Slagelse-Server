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
import model.data.Picture;
import model.data.PictureRegister;
import model.data.Store;

/**
 *
 * @author Patrick
 */
public class StoreDAO extends DataDAO<Store> {

    private DealerRegister dealerRegister;
    private PictureRegister pictureRegister;

    public StoreDAO(DealerRegister dealerRegister, PictureRegister pictureRegister) {
        this.dealerRegister = dealerRegister;
        this.pictureRegister = pictureRegister;
    }

    @Override
    public void insert(Store source) {
        try {
            Statement st = DBTool.getStatement();

            int id = source.getId();
            String name = source.getName();
            String address = source.getAddress();
            String phone = source.getPhone();
            int picture = 0;
            if (source.getPicture() != null) {
                picture = source.getPicture().getId();
            }
            int parentDealerId = source.getParent().getId();
            int updateNumber = source.getUpdateNumber();
            
            String sql = "INSERT INTO store (id_store,name, address, phone, picture, parent_dealer_id, update_number) VALUES(" + id + ",'" + name + "', '" + address + "', '" + phone + "', " + picture + ", " + parentDealerId + ", " + updateNumber + ");";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Store source, Store target) {
        try {
            Statement st = DBTool.getStatement();

            String name = source.getName();
            String address = source.getAddress();
            String phone = source.getPhone();
            int picture = 0;
            if (source.getPicture() != null) {
                picture = source.getPicture().getId();
            }
            int parentDealerId = source.getParent().getId();
            int updateNumber = source.getUpdateNumber();

            String sql = "UPDATE store SET name='" + name + "', address='" + address + "', phone='" + phone + "', picture=" + picture + ", parent_dealer_id=" + parentDealerId + ", update_number=" + updateNumber + " WHERE id_store=" + target.getId() + ";";
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
            Store store = null;
            String sql = "SELECT * FROM store WHERE id_store=" + id;
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int parentDealerId = rs.getInt(6);
                    Dealer parentDealer = null;
                    if (parentDealerId != 0) {
                        parentDealer = dealerRegister.get(parentDealerId);
                    }
                    int pictureId = rs.getInt(5);
                    Picture picture = null;
                    if (pictureId != 0) {
                        picture = pictureRegister.get(pictureId);
                    }
                    int updateNumber = rs.getInt(7);
                    store = new Store(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), picture, parentDealer, updateNumber);
                }
            }
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
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int parentDealerId = rs.getInt(6);
                    Dealer parentDealer = null;
                    if (parentDealerId != 0) {
                        parentDealer = dealerRegister.get(parentDealerId);
                    }
                    int pictureId = rs.getInt(5);
                    Picture picture = null;
                    if (pictureId != 0) {
                        picture = pictureRegister.get(pictureId);
                    }
                    int updateNumber = rs.getInt(7);
                    Store store = new Store(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), picture, parentDealer, updateNumber);
                    stores.add(store);
                }
            }
            return stores;
        } catch (SQLException ex) {
            Logger.getLogger(StoreDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
