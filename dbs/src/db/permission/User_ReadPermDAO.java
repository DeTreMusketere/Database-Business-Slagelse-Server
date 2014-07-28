
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
import model.permission.User_ReadPerm;

/**
 *
 * @author Patrick
 */
public class User_ReadPermDAO extends PermissionDAO<User, User_ReadPerm> {

    public User_ReadPermDAO(UserRegister userRegister, Register<User> register) {
        super(userRegister, register);
        table = "read_user_perm";
    }

    @Override
    public void insert(User_ReadPerm source) {
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
    public void delete(User_ReadPerm source) {
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
    public ArrayList<User_ReadPerm> select(User executor) {
        ArrayList<User_ReadPerm> user_ReadPerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetUserId = rs.getInt("target_user_id");
                    User target = register.get(targetUserId);
                    User_ReadPerm user_ReadPerm = new User_ReadPerm(target, executor);
                    user_ReadPerms.add(user_ReadPerm);
                }
            }
            return user_ReadPerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
