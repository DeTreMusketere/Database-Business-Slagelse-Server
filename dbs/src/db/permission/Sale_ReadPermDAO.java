
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
import model.permission.Sale_ReadPerm;

/**
 *
 * @author Patrick
 */
public class Sale_ReadPermDAO extends PermissionDAO<Sale, Sale_ReadPerm> {

    public Sale_ReadPermDAO(UserRegister userRegister, Register<Sale> register) {
        super(userRegister, register);
        table = "read_sale_perm";
    }

    @Override
    public void insert(Sale_ReadPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetSaleId = source.getTargetSale().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO `"+table+"` (`target_sale_id`, `executor_user_id`) VALUES("+targetSaleId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Sale_ReadPerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetSaleId = source.getTargetSale().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM `"+table+"` WHERE `target_sale_id` = "+targetSaleId+" AND `executor_user_id` = "+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public ArrayList<Sale_ReadPerm> select(User executor) {
        ArrayList<Sale_ReadPerm> sale_ReadPerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM `"+table+"` WHERE `executor_user_id` = "+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetSaleId = rs.getInt("target_sale_id");
                    Sale target = register.get(targetSaleId);
                    Sale_ReadPerm sale_ReadPerm = new Sale_ReadPerm(target, executor);
                    sale_ReadPerms.add(sale_ReadPerm);
                }
            }
            return sale_ReadPerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
