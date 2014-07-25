
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Product;
import model.data.User;
import model.data.UserRegister;
import model.permission.Product_DeletePerm;

/**
 *
 * @author Patrick
 */
public class Product_DeletePermDAO extends PermissionDAO<Product, Product_DeletePerm> {

    public Product_DeletePermDAO(UserRegister userRegister, Register<Product> register) {
        super(userRegister, register);
        table = "delete_product_perm";
    }

    @Override
    public void insert(Product_DeletePerm source) {
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
    public void delete(Product_DeletePerm source) {
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
    public Product_DeletePerm select(User executor, Product target) {
        try {
            Statement st = DBTool.getStatement();
            
            int targetProductId = target.getId();
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE target_product_id="+targetProductId+" AND executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    Product_DeletePerm product_DeletePerm = new Product_DeletePerm(target, executor);
                    return product_DeletePerm;
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ArrayList<Product_DeletePerm> select(User executor) {
        ArrayList<Product_DeletePerm> product_DeletePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int targetProductId = rs.getInt("target_product_id");
                    Product target = register.get(targetProductId);
                    Product_DeletePerm product_DeletePerm = new Product_DeletePerm(target, executor);
                    product_DeletePerms.add(product_DeletePerm);
                }
            }
            return product_DeletePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
