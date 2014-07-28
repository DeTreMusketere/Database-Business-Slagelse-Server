
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Dealer;
import model.data.User;
import model.data.UserRegister;
import model.permission.Dealer_AdminPerm;

/**
 *
 * @author Patrick
 */
public class Dealer_AdminPermDAO extends PermissionDAO<Dealer, Dealer_AdminPerm> {

    public Dealer_AdminPermDAO(UserRegister userRegister, Register<Dealer> register) {
        super(userRegister, register);
        table = "admin_dealer_perm";
    }

    @Override
    public void insert(Dealer_AdminPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = source.getExecutorUser().getId();
            int targetDealerId = source.getTargetDealer().getId();
            
            String sql = "INSERT INTO `"+table+"` (`target_dealer_id`, `executor_user_id`) VALUES("+targetDealerId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Dealer_AdminPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = source.getExecutorUser().getId();
            int targetDealerId = source.getTargetDealer().getId();
            
            String sql = "DELETE FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+" AND `target_dealer_id` = "+targetDealerId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Dealer_AdminPerm> select(User executor) {
        ArrayList<Dealer_AdminPerm> dealer_AdminsPerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetDealerId = rs.getInt("target_dealer_id");
                    Dealer targetDealer = register.get(targetDealerId);
                    
                    Dealer_AdminPerm dealer_AdminPerm = new Dealer_AdminPerm(targetDealer, executor);
                    dealer_AdminsPerms.add(dealer_AdminPerm);
                }
            }
            return dealer_AdminsPerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
