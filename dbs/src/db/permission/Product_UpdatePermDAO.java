
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.Product;
import model.data.User;
import model.data.UserRegister;
import model.permission.Product_UpdatePerm;

/**
 *
 * @author Patrick
 */
public class Product_UpdatePermDAO extends PermissionDAO<Product, Product_UpdatePerm> {

    public Product_UpdatePermDAO(UserRegister userRegister, Register<Product> register) {
        super(userRegister, register);
        table = "update_product_perm";
    }

    @Override
    public void insert(Product_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetProductId = source.getTargetProduct().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "INSERT INTO "+table+" ('target_product_id', 'executor_user_id') VALUES("+targetProductId+", "+executorUserId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Product_UpdatePerm source) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetProductId = source.getTargetProduct().getId();
            int executorUserId = source.getExecutorUser().getId();
            
            String sql = "DELETE FROM "+table+" WHERE target_product_id="+targetProductId+" AND executor_user_id="+executorUserId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Product_UpdatePerm select(User executor, Product target) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetProductId = target.getId();
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE target_product_id="+targetProductId+" AND executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    Product_UpdatePerm product_UpdatePerm = new Product_UpdatePerm(target, executor);
                    return product_UpdatePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product_UpdatePerm> select(User executor) {
        ArrayList<Product_UpdatePerm> product_UpdatePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetProductId = rs.getInt("target_product_id");
                    Product target = register.get(targetProductId);
                    Product_UpdatePerm product_UpdatePerm = new Product_UpdatePerm(target, executor);
                    product_UpdatePerms.add(product_UpdatePerm);
                }
            }
            return product_UpdatePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
