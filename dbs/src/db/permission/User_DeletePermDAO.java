
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
import model.permission.User_DeletePerm;

/**
 *
 * @author Patrick
 */
public class User_DeletePermDAO extends PermissionDAO<User, User_DeletePerm> {

    public User_DeletePermDAO(UserRegister userRegister, Register<User> register) {
        super(userRegister, register);
        table = "delete_user_perm";
    }

    @Override
    public void insert(User_DeletePerm source) {
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
    public void delete(User_DeletePerm source) {
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
    public User_DeletePerm select(User executor, User target) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetUserId = target.getId();
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE target_user_id="+targetUserId+" AND executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    User_DeletePerm user_DeletePerm = new User_DeletePerm(target, executor);
                    return user_DeletePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<User_DeletePerm> select(User executor) {
        ArrayList<User_DeletePerm> user_DeletePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetUserId = rs.getInt("target_user_id");
                    User target = register.get(targetUserId);
                    User_DeletePerm user_DeletePerm = new User_DeletePerm(target, executor);
                    user_DeletePerms.add(user_DeletePerm);
                }
            }
            return user_DeletePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
