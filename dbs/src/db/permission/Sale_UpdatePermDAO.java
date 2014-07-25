
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Sale;
import model.data.User;
import model.data.UserRegister;
import model.permission.Sale_UpdatePerm;

/**
 *
 * @author Patrick
 */
public class Sale_UpdatePermDAO extends PermissionDAO<Sale, Sale_UpdatePerm> {

    public Sale_UpdatePermDAO(UserRegister userRegister, Register<Sale> register) {
        super(userRegister, register);
        table = "update_sale_perm";
    }

    @Override
    public void insert(Sale_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetSaleId = source.getTargetSale().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO "+table+" ('target_sale_id', 'executor_user_id') VALUES("+targetSaleId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Sale_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetSaleId = source.getTargetSale().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM "+table+" WHERE target_sale_id="+targetSaleId+" AND executor_user_id="+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Sale_UpdatePerm select(User executor, Sale target) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetSaleId = target.getId();
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE target_sale_id="+targetSaleId+" AND executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    Sale_UpdatePerm sale_UpdatePerm = new Sale_UpdatePerm(target, executor);
                    return sale_UpdatePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Sale_UpdatePerm> select(User executor) {
        ArrayList<Sale_UpdatePerm> sale_UpdatePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetSaleId = rs.getInt("target_sale_id");
                    Sale target = register.get(targetSaleId);
                    Sale_UpdatePerm sale_UpdatePerm = new Sale_UpdatePerm(target, executor);
                    sale_UpdatePerms.add(sale_UpdatePerm);
                }
            }
            return sale_UpdatePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
