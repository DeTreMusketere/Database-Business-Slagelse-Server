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
import model.data.Dealer;
import model.data.DealerRegister;
import model.data.Picture;
import model.data.PictureRegister;
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
    private PictureRegister pictureRegister;

    public SaleDAO(DealerRegister dealerRegister, StoreRegister storeRegister, PictureRegister pictureRegister) {
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
        this.pictureRegister = pictureRegister;
    }

    @Override
    public void insert(Sale source) {
        try {
            Statement statement = DBTool.getStatement();

            int id = source.getId();
            String name = source.getName();
            String description = source.getDescription();
            int picture = 0;
            if (source.getPicture() != null) {
                picture = source.getPicture().getId();
            }
            double price = source.getPrice();
            long start = source.getStart().getTime();
            long end = source.getEnd().getTime();
            long publish = source.getPublish().getTime();
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }
            int updateNumber = source.getUpdateNumber();

            String sql = "INSERT INTO sale (id_sale, name, description, picture, price, start, end, publish, parent_store_id, parent_dealer_id, update_number) VALUES(" + id + ",'" + name + "', '" + description + "', " + picture + ", " + price + ", " + start + ", " + end + ", " + publish + ", " + parentStoreId + ", " + parentDealerId + ", " + updateNumber + ");";
            statement.execute(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Sale source, Sale target) {
        try {
            Statement statement = DBTool.getStatement();

            String name = source.getName();
            String description = source.getDescription();
            int picture = 0;
            if (source.getPicture() != null) {
                picture = source.getPicture().getId();
            }
            double price = source.getPrice();
            long start = source.getStart().getTime();
            long end = source.getEnd().getTime();
            long publish = source.getPublish().getTime();
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }
            int updateNumber = source.getUpdateNumber();

            String sql = "UPDATE sale SET name='" + name + "', description='" + description + "', picture=" + picture + ", price=" + price + ", start=" + start + ", end=" + end + ", publish=" + publish + ", parent_store_id=" + parentStoreId + ", parent_dealer_id=" + parentDealerId + ", update_number=" + updateNumber + " WHERE id_sale=" + target.getId() + ";";
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

                    int pictureId = rs.getInt(4);
                    Picture picture = null;
                    if (pictureId != 0) {
                        picture = pictureRegister.get(pictureId);
                    }
                    Date start = new Date(rs.getLong(8));
                    Date end = new Date(rs.getLong(9));
                    Date publish = new Date(rs.getLong(10));
                    int updateNumber = rs.getInt(11);

                    if (parentStore != null) {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), start, end, publish, parentStore, updateNumber);
                    } else {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), start, end, publish, parentDealer, updateNumber);
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

                    int pictureId = rs.getInt(5);
                    Picture picture = null;
                    if (pictureId != 0) {
                        picture = pictureRegister.get(pictureId);
                    }

                    Date start = new Date(rs.getLong(8));
                    Date end = new Date(rs.getLong(9));
                    Date publish = new Date(rs.getLong(10));
                    
                    int updateNumber = rs.getInt(11);

                    if (parentStore != null) {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), start, end, publish, parentStore, updateNumber);
                    } else {
                        sale = new Sale(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), start, end, publish, parentDealer, updateNumber);
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
