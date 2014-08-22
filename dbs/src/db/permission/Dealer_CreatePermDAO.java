
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
import model.permission.Dealer_CreatePerm;

/**
 *
 * @author Patrick
 */
public class Dealer_CreatePermDAO extends PermissionDAO<Data, Dealer_CreatePerm> {

    /**
     * Constructs a Dealer_CreatePermDAO object. 
     * Register is not used for this class
     * @param userRegister
     * @param register (Not used)
     */
    public Dealer_CreatePermDAO(UserRegister userRegister, Register<Data> register) {
        super(userRegister, register);
        table = "create_dealer_perm";
    }

    @Override
    public void insert(Dealer_CreatePerm source) {
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
    public void delete(Dealer_CreatePerm source) {
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
    public Dealer_CreatePerm select2(User executor) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    
                    Dealer_CreatePerm dealer_CreatePerm = new Dealer_CreatePerm(executor);
                    return dealer_CreatePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Dealer_CreatePerm> select(User executor) {
        throw new UnsupportedOperationException("Method not supported");
    }

}
