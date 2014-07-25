package db.data;

import abstracts.DataDAO;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.Dealer;
import model.data.DealerRegister;
import model.data.Sale;
import model.data.Store;
import model.data.StoreRegister;

/**
 *
 * @author Patrick
 */
public class SaleDAO extends DataDAO<Sale> {

    private DealerRegister dealerRegister;
    private StoreRegister storeRegister;

    public SaleDAO(DealerRegister dealerRegister, StoreRegister storeRegister) {
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
    }

    @Override
    public int insert(Sale source) {
        try {
            Statement statement = DBTool.getStatement();

            String name = source.getName();
            String description = source.getDescription();
            int picture = source.getPicture();
            double price = source.getPrice();
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }

            int id = -1;
            String sql = "INSERT INTO sale (name, description, picture, price, parent_store_id, parent_dealer_id) VALUES('" + name + "', '" + description + "', " + picture + ", " + price + ", " + parentStoreId + ", " + parentDealerId + ");";
            statement.execute(sql);
            try (ResultSet rs = statement.executeQuery("SELECT LAST_INSERT_ID();")) {
                while (rs.next()) {
                    id = rs.getInt(1);
                }
            }
            return id;
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    @Override
    public void update(Sale source, Sale target) {
        try {
            Statement statement = DBTool.getStatement();

            String name = source.getName();
            String description = source.getDescription();
            int picture = source.getPicture();
            double price = source.getPrice();
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }

            String sql = "UPDATE sale SET name='" + name + "', description='" + description + "', picture=" + picture + ", price=" + price + ", parent_store_id=" + parentStoreId + ", parent_dealer_id=" + parentDealerId + " WHERE id_sale=" + target.getId() + ";";
            statement.execute(sql);
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
            Sale sale = null;
            String sql = "SELECT * FROM sale WHERE id_sale=" + id;
            try (ResultSet rs = statement.executeQuery(sql)) {
                while (rs.next()) {
                    int parentStoreId = rs.getInt(6);
                    int parentDealerId = rs.getInt(7);
                    Store parentStore = null;
                    Dealer parentDealer = null;
                    if (parentStoreId != 0) {
                        parentStore = storeRegister.get(parentStoreId);
                    }
                    if (parentDealerId != 0) {
                        parentDealer = dealerRegister.get(parentDealerId);
                    }

                    if (parentStore != null) {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), parentStore);
                    } else {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), parentDealer);
                    }
                }
            }
            return sale;
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Sale> selectAll() {
        ArrayList<Sale> sales = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM sale;";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int parentStoreId = rs.getInt(6);
                    int parentDealerId = rs.getInt(7);
                    Store parentStore = null;
                    Dealer parentDealer = null;
                    if (parentStoreId != 0) {
                        parentStore = storeRegister.get(parentStoreId);
                    }
                    if (parentDealerId != 0) {
                        parentDealer = dealerRegister.get(parentDealerId);
                    }
                    
                    Sale sale;
                    
                    if (parentStore != null) {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), parentStore);
                    } else {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getDouble(5), parentDealer);
                    }
                    
                    sales.add(sale);
                }
            }

            return sales;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
