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
import model.data.Store;
import model.data.StoreRegister;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class UserDAO extends DataDAO<User> {

    private DealerRegister dealerRegister;
    private StoreRegister storeRegister;

    public UserDAO(DealerRegister dealerRegister, StoreRegister storeRegister) {
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
    }

    @Override
    public void insert(User source) {
        try {
            Statement st = DBTool.getStatement();

            int id = source.getId();
            String name = source.getName();
            String username = source.getUsername();
            String password = source.getPassword();
            String email = source.getEmail();
            String phone = source.getPhone();
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }
            String sql = "INSERT INTO user (id_user, name, username, password, email, phone, parent_store_id, parent_dealer_id) VALUES(" + id + ",'" + name + "', '" + username + "', '" + password + "', '" + email + "', '" + phone + "', " + parentStoreId + ", " + parentDealerId + ");";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(User source, User target) {
        try {
            Statement st = DBTool.getStatement();

            String name = source.getName();
            String username = source.getUsername();
            String password = source.getPassword();
            String email = source.getEmail();
            String phone = source.getPhone();
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }

            String sql = "UPDATE user SET name='" + name + "', username='" + username + "', password='" + password + "', email='" + email + "', phone='" + phone + "', parent_store_id=" + parentStoreId + ", parent_dealer_id=" + parentDealerId + " WHERE id_user=" + target.getId() + ";";
            st.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(User target) {
        try {
            Statement statement = DBTool.getStatement();

            int targetid = target.getId();

            String sql = "DELETE FROM user WHERE id_user =" + targetid;
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public User select(int id) {
        try {
            Statement st = DBTool.getStatement();
            User user = null;
            String sql = "SELECT * FROM user WHERE id_user=" + id + ";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int parentStoreId = rs.getInt(7);
                    int parentDealerId = rs.getInt(8);
                    Store parentStore = null;
                    Dealer parentDealer = null;
                    if (parentStoreId != 0) {
                        parentStore = storeRegister.get(parentStoreId);
                    }
                    if (parentDealerId != 0) {
                        parentDealer = dealerRegister.get(parentDealerId);
                    }

                    if (parentStore != null) {
                        user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), parentStore);
                    } else {
                        user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), parentDealer);
                    }
                }
            }
            return user;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<User> selectAll() {
        ArrayList<User> users = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM user;";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int parentStoreId = rs.getInt(7);
                    int parentDealerId = rs.getInt(8);
                    Store parentStore = null;
                    Dealer parentDealer = null;
                    if (parentStoreId != 0) {
                        parentStore = storeRegister.get(parentStoreId);
                    }
                    if (parentDealerId != 0) {
                        parentDealer = dealerRegister.get(parentDealerId);
                    }

                    User user;

                    if (parentStore != null) {
                        user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), parentStore);
                    } else {
                        user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), parentDealer);
                    }
                    users.add(user);
                }
            }
            return users;
        } catch (SQLException ex) {
            Logger.getLogger(UserDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
