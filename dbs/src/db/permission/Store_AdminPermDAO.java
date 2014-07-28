
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Store;
import model.data.User;
import model.data.UserRegister;
import model.permission.Store_AdminPerm;

/**
 *
 * @author Patrick
 */
public class Store_AdminPermDAO extends PermissionDAO<Store, Store_AdminPerm> {

    public Store_AdminPermDAO(UserRegister userRegister, Register<Store> register) {
        super(userRegister, register);
        table = "admin_store_perm";
    }

    @Override
    public void insert(Store_AdminPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = source.getExecutorUser().getId();
            int targetStoreId = source.getTargetStore().getId();
            
            String sql = "INSERT INTO "+table+" ('target_store_id', 'executor_user_id') VALUES("+targetStoreId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Store_AdminPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = source.getExecutorUser().getId();
            int targetStoreId = source.getTargetStore().getId();
            
            String sql = "DELETE FROM "+table+" WHERE executor_user_id = "+executorUserId+" AND target_store_id="+targetStoreId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Store_AdminPerm> select(User executor) {
        ArrayList<Store_AdminPerm> store_AdminsPerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetStoreId = rs.getInt("target_store_id");
                    Store targetStore = register.get(targetStoreId);
                    
                    Store_AdminPerm store_AdminPerm = new Store_AdminPerm(targetStore, executor);
                    store_AdminsPerms.add(store_AdminPerm);
                }
            }
            return store_AdminsPerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
