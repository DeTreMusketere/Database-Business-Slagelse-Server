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
import model.data.Picture;
import model.data.PictureRegister;
import model.data.Product;
import model.data.Store;
import model.data.StoreRegister;
import model.data.Tag;
import model.data.TagRegister;

/**
 *
 * @author Patrick
 */
public class ProductDAO extends DataDAO<Product> {

    private DealerRegister dealerRegister;
    private StoreRegister storeRegister;
    private PictureRegister pictureRegister;
    private TagRegister tagRegister;

    public ProductDAO(DealerRegister dealerRegister, StoreRegister storeRegister, PictureRegister pictureRegister, TagRegister tagRegister) {
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
        this.pictureRegister = pictureRegister;
        this.tagRegister = tagRegister;
    }

    @Override
    public void insert(Product source) {
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
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }
            int updateNumber = source.getUpdateNumber();

            String sql = "INSERT INTO product (id_product, name, description, picture, price, parent_store_id, parent_dealer_id, update_number) VALUES(" + id + ",'" + name + "', '" + description + "', " + picture + ", " + price + ", " + parentStoreId + ", " + parentDealerId + ", " + updateNumber + ");";
            statement.addBatch(sql);

            ArrayList<Tag> tags = source.getTags();
            for (Tag t : tags) {
                int tagId = t.getId();
                statement.addBatch("INSERT INTO product_tag (target_product_id, target_tag_id, update_number) VALUES(" + id + ", " + tagId + ", " + updateNumber + ");");
            }
            statement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO = Update this method so that it updates product_tag table
    @Override
    public void update(Product source, Product target) {
        try {
            Statement statement = DBTool.getStatement();

            String name = source.getName();
            String description = source.getDescription();
            int picture = 0;
            if (source.getPicture() != null) {
                picture = source.getPicture().getId();
            }
            double price = source.getPrice();
            int parentStoreId = 0;
            int parentDealerId = 0;
            if (source.getParentStore() != null) {
                parentStoreId = source.getParentStore().getId();
            }
            if (source.getParentDealer() != null) {
                parentDealerId = source.getParentDealer().getId();
            }
            int updateNumber = source.getUpdateNumber();

            String sql = "UPDATE product SET name='" + name + "', description='" + description + "', picture=" + picture + ", price=" + price + ", parent_store_id=" + parentStoreId + ", parent_dealer_id=" + parentDealerId + ", update_number=" + updateNumber + " WHERE id_product=" + target.getId() + ";";
            statement.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Product target) {
        try {
            Statement statement = DBTool.getStatement();

            int targetid = target.getId();

            String sql = "DELETE FROM product WHERE id_product =" + targetid;
            statement.addBatch(sql);
            
            String sql2 = "DELETE FROM product_tag WHERE target_product_id =" + targetid;
            statement.addBatch(sql2);
            
            statement.executeBatch();
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Product select(int id) {
        try {
            Statement statement = DBTool.getStatement();
            Product product = null;
            String sql = "SELECT * FROM product WHERE id_product=" + id;
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

                    int updateNumber = rs.getInt(8);
                    
                    Statement statement2 = DBTool.getInstance().createStatement();
                    ArrayList<Tag> tags = new ArrayList<>();
                    String sql2 = "SELECT * FROM product_tag WHERE target_product_id=" + id;
                    try (ResultSet rs2 = statement2.executeQuery(sql2)) {
                        while(rs2.next()) {
                            int tagId = rs2.getInt(3);
                            Tag tag = tagRegister.get(tagId);
                            if(tag != null) {
                                tags.add(tag);
                            } else {
                                System.out.println("Tag with id: " + tagId + " was not found!");
                            }
                        }
                    } catch(SQLException ex) {
                        Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (parentStore != null) {

                        product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), parentStore, tags, updateNumber);
                    } else {
                        product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), parentDealer, tags, updateNumber);
                    }
                }
            }
            return product;
        } catch (SQLException ex) {
            Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public ArrayList<Product> selectAll() {
        ArrayList<Product> products = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();

            String sql = "SELECT * FROM product;";
            try (ResultSet rs = st.executeQuery(sql)) {
                while (rs.next()) {
                    int productId = rs.getInt(1);
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

                    Product product;

                    int pictureId = rs.getInt(4);
                    Picture picture = null;
                    if (pictureId != 0) {
                        picture = pictureRegister.get(pictureId);
                    }

                    int updateNumber = rs.getInt(8);
                    
                    Statement st2 = DBTool.getInstance().createStatement();
                    ArrayList<Tag> tags = new ArrayList<>();
                    String sql2 = "SELECT * FROM product_tag WHERE target_product_id=" + productId;
                    try (ResultSet rs2 = st2.executeQuery(sql2)) {
                        while(rs2.next()) {
                            int tagId = rs2.getInt(3);
                            Tag tag = tagRegister.get(tagId);
                            if(tag != null) {
                                tags.add(tag);
                            } else {
                                System.out.println("Tag with id: " + tagId + " was not found!");
                            }
                        }
                    } catch(SQLException ex) {
                        Logger.getLogger(DealerDAO.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (parentStore != null) {
                        product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), parentStore, tags, updateNumber);
                    } else {
                        product = new Product(rs.getInt(1), rs.getString(2), rs.getString(3), picture, rs.getDouble(5), parentDealer, tags, updateNumber);
                    }

                    products.add(product);
                }
            }

            return products;
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
