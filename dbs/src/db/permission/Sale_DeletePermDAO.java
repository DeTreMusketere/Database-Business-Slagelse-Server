
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
import model.permission.Sale_DeletePerm;

/**
 *
 * @author Patrick
 */
public class Sale_DeletePermDAO extends PermissionDAO<Sale, Sale_DeletePerm> {

    public Sale_DeletePermDAO(UserRegister userRegister, Register<Sale> register) {
        super(userRegister, register);
        table = "delete_sale_perm";
    }

    @Override
    public void insert(Sale_DeletePerm source) {
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
    public void delete(Sale_DeletePerm source) {
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
    public ArrayList<Sale_DeletePerm> select(User executor) {
        ArrayList<Sale_DeletePerm> sale_DeletePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetSaleId = rs.getInt("target_sale_id");
                    Sale target = register.get(targetSaleId);
                    Sale_DeletePerm sale_DeletePerm = new Sale_DeletePerm(target, executor);
                    sale_DeletePerms.add(sale_DeletePerm);
                }
            }
            return sale_DeletePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
