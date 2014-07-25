
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.User;
import model.data.UserRegister;
import model.permission.Product_UpdatePerm;
import model.permission.User_UpdatePerm;

/**
 *
 * @author Patrick
 */
public class User_UpdatePermDAO extends PermissionDAO<User, User_UpdatePerm> {

    public User_UpdatePermDAO(UserRegister userRegister, Register<User> register) {
        super(userRegister, register);
        table = "update_user_perm";
    }

    @Override
    public void insert(User_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetUserId = source.getTargetUser().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO "+table+" ('target_user_id', 'executor_user_id') VALUES("+targetUserId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(User_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetUserId = source.getTargetUser().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM "+table+" WHERE target_user_id="+targetUserId+" AND executor_user_id="+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User_UpdatePerm select(User executor, User target) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetUserId = target.getId();
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE target_user_id="+targetUserId+" AND executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    User_UpdatePerm user_UpdatePerm = new User_UpdatePerm(target, executor);
                    return user_UpdatePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User_UpdatePerm> select(User executor) {
        ArrayList<User_UpdatePerm> user_UpdatePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetUserId = rs.getInt("target_user_id");
                    User target = register.get(targetUserId);
                    User_UpdatePerm user_UpdatePerm = new User_UpdatePerm(target, executor);
                    user_UpdatePerms.add(user_UpdatePerm);
                }
            }
            return user_UpdatePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
