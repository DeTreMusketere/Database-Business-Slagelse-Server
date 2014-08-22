package db.permission;

import abstracts.Data;
import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.User;
import model.data.UserRegister;
import model.permission.AdminPerm;

/**
 *
 * @author Patrick
 */
public class AdminPermDAO extends PermissionDAO<Data, AdminPerm> {

    /**
     * Constructs a AdminPermDAO object. 
     * Register is not used in this class
     * @param userRegister
     * @param register (Not used)
     */
    public AdminPermDAO(UserRegister userRegister, Register<Data> register) {
        super(userRegister, register);
        table = "admin_perm";
    }

    @Override
    public void insert(AdminPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO `"+table+"` (`executor_user_id`) VALUES("+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(AdminPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * Selects a PERMISSIONTYPE object based on executorUser
     * @param executor
     * @return PERMISSIONTYPE
     */
    public AdminPerm select2(User executor) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    AdminPerm adminPerm = new AdminPerm(executor);
                    return adminPerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<AdminPerm> select(User executor) {
        throw new UnsupportedOperationException("Method not supported");
    }

}
