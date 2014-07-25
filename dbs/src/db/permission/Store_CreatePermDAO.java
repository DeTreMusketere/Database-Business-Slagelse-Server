
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
import model.data.Store;
import model.data.User;
import model.data.UserRegister;
import model.permission.Store_CreatePerm;

/**
 *
 * @author Patrick
 */
public class Store_CreatePermDAO extends PermissionDAO<Store, Store_CreatePerm> {

    public Store_CreatePermDAO(UserRegister userRegister, Register<Store> register) {
        super(userRegister, register);
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
            
            String sql = "";
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
        throw new UnsupportedOperationException("Method not supported");
    }

}
