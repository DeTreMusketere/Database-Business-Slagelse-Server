package db.data;

import abstracts.DataDAO;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.Product;
import model.data.ProductRegister;
import model.data.Sale;

/**
 *
 * @author Patrick
 */
public class SaleDAO extends DataDAO<Sale> {

    private ProductRegister productRegister;

    public SaleDAO(ProductRegister productRegister) {
        this.productRegister = productRegister;
    }

    @Override
    public void insert(Sale source) {
        try {
            Statement statement = DBTool.getStatement();

            int id = source.getId();
            int productId = source.getProduct().getId();
            String price = String.valueOf(source.getPrice());
            long start = source.getStart().getTime();
            long end = source.getEnd().getTime();
            long publish = source.getPublish().getTime();
            int updateNumber = source.getUpdateNumber();

            String sql = "INSERT INTO sale (id_sale, target_product_id, price, start, end, publish, update_number) VALUES(" + id + ", " + productId + ", '" + price + "', " + start + ", " + end + ", " + publish + ", " + updateNumber + ");";
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Sale source, Sale target) {
        try {
            Statement statement = DBTool.getStatement();

            int targetId = target.getId();
            int productId = source.getProduct().getId();
            String price = String.valueOf(source.getPrice());
            long start = source.getStart().getTime();
            long end = source.getEnd().getTime();
            long publish = source.getPublish().getTime();
            int updateNumber = source.getUpdateNumber();

            String sql = "UPDATE sale SET productId=" + productId + ", price='" + price + "', start=" + start + ", end=" + end + ", publish=" + publish + ", update_number=" + updateNumber + " WHERE id_sale=" + targetId + ";";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Sale target) {
        try {
            Statement statement = DBTool.getStatement();

            int targetid = target.getId();

            String sql = "DELETE FROM sale WHERE id_sale =" + targetid;
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Sale select(int id) {
        try {
            Statement statement = DBTool.getStatement();
            String sql = "SELECT * FROM sale WHERE id_sale=" + id + ";";

            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int productId = rs.getInt(2);
                    Product product = productRegister.get(productId);
                    if (product != null) {
                        double price = Double.valueOf(rs.getString(3));
                        Date start = new Date(rs.getLong(4));
                        Date end = new Date(rs.getLong(5));
                        Date publish = new Date(rs.getLong(6));
                        int updateNumber = rs.getInt(7);
                        Sale sale = new Sale(id, product, price, start, end, publish, updateNumber);
                        return sale;
                    }
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Sale> selectAll() {
        ArrayList<Sale> sales = new ArrayList<>();
        try {
            Statement statement = DBTool.getStatement();
            String sql = "SELECT * FROM sale;";

            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int id = rs.getInt(1);
                    int productId = rs.getInt(2);
                    Product product = productRegister.get(productId);
                    if (product != null) {
                        double price = Double.valueOf(rs.getString(3));
                        Date start = new Date(rs.getLong(4));
                        Date end = new Date(rs.getLong(5));
                        Date publish = new Date(rs.getLong(6));
                        int updateNumber = rs.getInt(7);
                        Sale sale = new Sale(id, product, price, start, end, publish, updateNumber);
                        sales.add(sale);
                    }
                }
            }
            return sales;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
