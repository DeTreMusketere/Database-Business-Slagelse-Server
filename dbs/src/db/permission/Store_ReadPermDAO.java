
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
import model.permission.Store_ReadPerm;

/**
 *
 * @author Patrick
 */
public class Store_ReadPermDAO extends PermissionDAO<Store, Store_ReadPerm> {

    public Store_ReadPermDAO(UserRegister userRegister, Register<Store> register) {
        super(userRegister, register);
        table = "read_store_perm";
    }

    @Override
    public void insert(Store_ReadPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = source.getTargetStore().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO `"+table+"` (`target_store_id`, `executor_user_id`) VALUES("+targetStoreId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Store_ReadPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetStoreId = source.getTargetStore().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM `"+table+"` WHERE `target_store_id` = "+targetStoreId+" AND `executor_user_id` = "+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Store_ReadPerm> select(User executor) {
        ArrayList<Store_ReadPerm> store_ReadPerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetStoreId = rs.getInt("target_store_id");
                    Store target = register.get(targetStoreId);
                    Store_ReadPerm store_ReadPerm = new Store_ReadPerm(target, executor);
                    store_ReadPerms.add(store_ReadPerm);
                }
            }
            return store_ReadPerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
