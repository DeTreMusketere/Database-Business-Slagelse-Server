
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
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
import model.data.UserRegister;
import model.permission.User_CreatePerm;

/**
 *
 * @author Patrick
 */
public class User_CreatePermDAO extends PermissionDAO<User, User_CreatePerm> {
    
    private DealerRegister dealerRegister;
    private StoreRegister storeRegister;

    public User_CreatePermDAO(UserRegister userRegister, Register<User> register, DealerRegister dealerRegister, StoreRegister storeRegister) {
        super(userRegister, register);
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
        table = "create_user_perm";
    }

    @Override
    public void insert(User_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();

            User executorUser = source.getExecutorUser();
            Dealer parentDealer = source.getParentDealer();
            Store parentStore = source.getParentStore();
            
            int executorUserId = executorUser.getId();
            int parentDealerId = 0;
            if(parentDealer != null) {
                parentDealerId = parentDealer.getId();
            }
            int parentStoreId = 0;
            if(parentStore != null) {
                parentStoreId = parentStore.getId();
            }

            String sql = "INSERT INTO `"+table+"` (`executor_user_id`, `parent_store_id`, `parent_dealer_id`) VALUES("+executorUserId+", "+parentStoreId+", "+parentDealerId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(User_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();

            User executorUser = source.getExecutorUser();
            Dealer parentDealer = source.getParentDealer();
            Store parentStore = source.getParentStore();
            
            int executorUserId = executorUser.getId();
            int parentDealerId = 0;
            if(parentDealer != null) {
                parentDealerId = parentDealer.getId();
            }
            int parentStoreId = 0;
            if(parentStore != null) {
                parentStoreId = parentStore.getId();
            }

            String sql = "DELETE FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+" AND `parent_dealer_id` = "+parentDealerId+" AND `parent_store_id` = "+parentStoreId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<User_CreatePerm> select(User executor) {
        ArrayList<User_CreatePerm> user_CreatePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+";";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                int parentDealerId = rs.getInt("parent_dealer_id");
                    int parentStoreId = rs.getInt("parent_store_id");
                    
                    if(parentStoreId != 0) {
                        Store parentStore = storeRegister.get(parentStoreId);
                        User_CreatePerm sale_CreatePerm = new User_CreatePerm(executor, parentStore);
                        user_CreatePerms.add(sale_CreatePerm);
                    } else {
                        Dealer parentDealer = dealerRegister.get(parentDealerId);
                        User_CreatePerm sale_CreatePerm = new User_CreatePerm(executor, parentDealer);
                        user_CreatePerms.add(sale_CreatePerm);
                    }
            }
        } catch (SQLException ex) {
            Logger.getLogger(User_CreatePermDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
