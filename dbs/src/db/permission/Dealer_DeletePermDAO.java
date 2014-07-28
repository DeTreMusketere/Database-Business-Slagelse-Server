
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
import model.permission.Dealer_DeletePerm;

/**
 *
 * @author Patrick
 */
public class Dealer_DeletePermDAO extends PermissionDAO<Dealer,Dealer_DeletePerm> {

    public Dealer_DeletePermDAO(UserRegister userRegister, Register<Dealer> register) {
        super(userRegister, register);
        table = "delete_dealer_perm";
    }

    @Override
    public void insert(Dealer_DeletePerm source) {
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
    public void delete(Dealer_DeletePerm source) {
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
    public ArrayList<Dealer_DeletePerm> select(User executor) {
        ArrayList<Dealer_DeletePerm> dealer_DeletePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetDealerId = rs.getInt("target_dealer_id");
                    Dealer targetDealer = register.get(targetDealerId);
                    
                    Dealer_DeletePerm dealer_DeletePerm = new Dealer_DeletePerm(targetDealer, executor);
                    dealer_DeletePerms.add(dealer_DeletePerm);
                }
            }
            return dealer_DeletePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
