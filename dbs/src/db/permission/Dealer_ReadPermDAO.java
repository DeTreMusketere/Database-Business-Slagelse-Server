
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
import model.permission.Dealer_ReadPerm;

/**
 *
 * @author Patrick
 */
public class Dealer_ReadPermDAO extends PermissionDAO<Dealer, Dealer_ReadPerm> {

    public Dealer_ReadPermDAO(UserRegister userRegister, Register<Dealer> register) {
        super(userRegister, register);
        table  = "read_dealer_perm";
    }

    @Override
    public void insert(Dealer_ReadPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetDealerId = source.getTargetDealer().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO "+table+" ('executor_user_id', 'target_dealer_id') VALUES("+executorUserId+", "+targetDealerId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Dealer_ReadPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = source.getExecutorUser().getId();
            int targetDealerId = source.getTargetDealer().getId();
            
            String sql = "DELETE FROM "+table+" WHERE executor_user_id = "+executorUserId+" AND target_dealer_id="+targetDealerId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Dealer_ReadPerm select(User executor, Dealer target) {
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            int targetDealerId = target.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+" AND target_dealer_id="+targetDealerId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    Dealer targetDealer = target;
                    
                    Dealer_ReadPerm dealer_ReadPerm = new Dealer_ReadPerm(targetDealer, executor);
                    return dealer_ReadPerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Dealer_ReadPerm> select(User executor) {
        ArrayList<Dealer_ReadPerm> dealer_ReadPerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetDealerId = rs.getInt("target_dealer_id");
                    Dealer targetDealer = register.get(targetDealerId);
                    
                    Dealer_ReadPerm dealer_ReadPerm = new Dealer_ReadPerm(targetDealer, executor);
                    dealer_ReadPerms.add(dealer_ReadPerm);
                }
            }
            return dealer_ReadPerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
