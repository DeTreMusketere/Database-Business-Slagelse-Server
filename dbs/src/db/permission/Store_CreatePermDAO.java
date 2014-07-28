
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
import model.data.User;
import model.data.UserRegister;
import model.permission.Store_CreatePerm;

/**
 *
 * @author Patrick
 */
public class Store_CreatePermDAO extends PermissionDAO<Store, Store_CreatePerm> {
    
    private DealerRegister dealerRegister;

    public Store_CreatePermDAO(UserRegister userRegister, Register<Store> register, DealerRegister dealerRegister) {
        super(userRegister, register);
        this.dealerRegister = dealerRegister;
        table = "create_store_perm";
    }

    @Override
    public void insert(Store_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int parentDealerId = source.getParentDealer().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO "+table+" ('executor_user_id', 'parent_dealer_id') VALUES("+executorUserId+", "+parentDealerId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Store_CreatePermDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Store_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int parentDealerId = source.getParentDealer().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM "+table+" WHERE executor_user_id="+executorUserId+" AND parent_dealer_id="+parentDealerId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(Store_CreatePermDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Store_CreatePerm select(User executor, Store target) {
        throw new UnsupportedOperationException("Method not supported");
    }
    
    public Store_CreatePerm select2(User executor, Dealer parent) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            int parentDealerId = parent.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+" AND parent_dealer_id="+parentDealerId+";";
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()) {
                Store_CreatePerm store_CreatePerm  = new Store_CreatePerm(executor, parent);
                return store_CreatePerm;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Store_CreatePermDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Store_CreatePerm> select(User executor) {
        ArrayList<Store_CreatePerm> store_CreatePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int parentDealerId = rs.getInt("parent_dealer_id");
                    Dealer parentDealer = dealerRegister.get(parentDealerId);
                    Store_CreatePerm store_CreatePerm  = new Store_CreatePerm(executor, parentDealer);
                    store_CreatePerms.add(store_CreatePerm);
                }
            }
            return store_CreatePerms;
        } catch (SQLException ex) {
            Logger.getLogger(Store_CreatePermDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
