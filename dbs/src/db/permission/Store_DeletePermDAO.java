
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
import model.permission.Store_DeletePerm;

/**
 *
 * @author Patrick
 */
public class Store_DeletePermDAO extends PermissionDAO<Store, Store_DeletePerm> {

    public Store_DeletePermDAO(UserRegister userRegister, Register<Store> register) {
        super(userRegister, register);
        table = "delete_store_perm";
    }

    @Override
    public void insert(Store_DeletePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = source.getTargetStore().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO "+table+" ('target_store_id', 'executor_user_id') VALUES("+targetStoreId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Store_DeletePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = source.getTargetStore().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM "+table+" WHERE target_store_id="+targetStoreId+" AND executor_user_id="+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Store_DeletePerm select(User executor, Store target) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = target.getId();
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE target_store_id="+targetStoreId+" AND executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    Store_DeletePerm store_DeletePerm = new Store_DeletePerm(target, executor);
                    return store_DeletePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Store_DeletePerm> select(User executor) {
        ArrayList<Store_DeletePerm> store_DeletePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetStoreId = rs.getInt("target_store_id");
                    Store target = register.get(targetStoreId);
                    Store_DeletePerm store_DeletePerm = new Store_DeletePerm(target, executor);
                    store_DeletePerms.add(store_DeletePerm);
                }
            }
            return store_DeletePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
