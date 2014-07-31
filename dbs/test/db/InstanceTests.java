package db;

import db.data.DealerDAO;
import db.data.PictureDAO;
import db.data.ProductDAO;
import db.data.SaleDAO;
import db.data.StoreDAO;
import db.data.TagDAO;
import db.data.UserDAO;
import db.permission.AdminPermDAO;
import db.permission.Dealer_AdminPermDAO;
import db.permission.Dealer_CreatePermDAO;
import db.permission.Dealer_DeletePermDAO;
import db.permission.Dealer_ReadPermDAO;
import db.permission.Dealer_UpdatePermDAO;
import db.permission.Product_CreatePermDAO;
import db.permission.Product_DeletePermDAO;
import db.permission.Product_ReadPermDAO;
import db.permission.Product_UpdatePermDAO;
import db.permission.Sale_CreatePermDAO;
import db.permission.Sale_DeletePermDAO;
import db.permission.Sale_ReadPermDAO;
import db.permission.Sale_UpdatePermDAO;
import db.permission.Store_AdminPermDAO;
import db.permission.Store_CreatePermDAO;
import db.permission.Store_DeletePermDAO;
import db.permission.Store_ReadPermDAO;
import db.permission.Store_UpdatePermDAO;
import db.permission.User_CreatePermDAO;
import db.permission.User_DeletePermDAO;
import db.permission.User_ReadPermDAO;
import db.permission.User_UpdatePermDAO;
import model.data.DealerRegister;
import model.data.PictureRegister;
import model.data.ProductRegister;
import model.data.SaleRegister;
import model.data.StoreRegister;
import model.data.TagRegister;
import model.data.UserRegister;

/**
 *
 * @author Patrick
 */
public abstract class InstanceTests {

    protected DealerDAO dealerDAO;
    protected DealerRegister dealerRegister;

    protected StoreDAO storeDAO;
    protected StoreRegister storeRegister;

    protected ProductDAO productDAO;
    protected ProductRegister productRegister;

    protected SaleDAO saleDAO;
    protected SaleRegister saleRegister;

    protected UserDAO userDAO;
    protected UserRegister userRegister;

    protected TagDAO tagDAO;
    protected TagRegister tagRegister;

    protected PictureDAO pictureDAO;
    protected PictureRegister pictureRegister;

    protected AdminPermDAO adminPermDAO;

    protected Dealer_AdminPermDAO dealer_AdminPermDAO;
    protected Dealer_CreatePermDAO dealer_CreatePermDAO;
    protected Dealer_DeletePermDAO dealer_DeletePermDAO;
    protected Dealer_ReadPermDAO dealer_ReadPermDAO;
    protected Dealer_UpdatePermDAO dealer_UpdatePermDAO;

    protected Product_CreatePermDAO product_CreatePermDAO;
    protected Product_DeletePermDAO product_DeletePermDAO;
    protected Product_ReadPermDAO product_ReadPermDAO;
    protected Product_UpdatePermDAO product_UpdatePermDAO;

    protected Sale_CreatePermDAO sale_CreatePermDAO;
    protected Sale_DeletePermDAO sale_DeletePermDAO;
    protected Sale_ReadPermDAO sale_ReadPermDAO;
    protected Sale_UpdatePermDAO sale_UpdatePermDAO;

    protected Store_AdminPermDAO store_AdminPermDAO;
    protected Store_CreatePermDAO store_CreatePermDAO;
    protected Store_DeletePermDAO store_DeletePermDAO;
    protected Store_ReadPermDAO store_ReadPermDAO;
    protected Store_UpdatePermDAO store_UpdatePermDAO;

    protected User_CreatePermDAO user_CreatePermDAO;
    protected User_DeletePermDAO user_DeletePermDAO;
    protected User_ReadPermDAO user_ReadPermDAO;
    protected User_UpdatePermDAO user_UpdatePermDAO;

    public InstanceTests() {
        constructData();
        constructPermission();
        load();
    }
    
    

    protected void constructData() {
        pictureDAO = new PictureDAO();
        pictureRegister = new PictureRegister(pictureDAO);
        dealerDAO = new DealerDAO(pictureRegister);
        dealerRegister = new DealerRegister(dealerDAO);
        storeDAO = new StoreDAO(dealerRegister, pictureRegister);
        storeRegister = new StoreRegister(storeDAO);
        productDAO = new ProductDAO(dealerRegister, storeRegister, pictureRegister);
        productRegister = new ProductRegister(productDAO);
        saleDAO = new SaleDAO(dealerRegister, storeRegister, pictureRegister);
        saleRegister = new SaleRegister(saleDAO);
        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(userDAO);
        tagDAO = new TagDAO();
        tagRegister = new TagRegister(tagDAO);
    }

    protected void constructPermission() {
        adminPermDAO = new AdminPermDAO(userRegister, null);

        dealer_AdminPermDAO = new Dealer_AdminPermDAO(userRegister, dealerRegister);
        dealer_CreatePermDAO = new Dealer_CreatePermDAO(userRegister, null);
        dealer_DeletePermDAO = new Dealer_DeletePermDAO(userRegister, dealerRegister);
        dealer_ReadPermDAO = new Dealer_ReadPermDAO(userRegister, dealerRegister);
        dealer_UpdatePermDAO = new Dealer_UpdatePermDAO(userRegister, dealerRegister);

        product_CreatePermDAO = new Product_CreatePermDAO(userRegister, productRegister, dealerRegister, storeRegister);
        product_DeletePermDAO = new Product_DeletePermDAO(userRegister, productRegister);
        product_ReadPermDAO = new Product_ReadPermDAO(userRegister, productRegister);
        product_UpdatePermDAO = new Product_UpdatePermDAO(userRegister, productRegister);

        sale_CreatePermDAO = new Sale_CreatePermDAO(userRegister, saleRegister, dealerRegister, storeRegister);
        sale_DeletePermDAO = new Sale_DeletePermDAO(userRegister, saleRegister);
        sale_ReadPermDAO = new Sale_ReadPermDAO(userRegister, saleRegister);
        sale_UpdatePermDAO = new Sale_UpdatePermDAO(userRegister, saleRegister);

        store_AdminPermDAO = new Store_AdminPermDAO(userRegister, storeRegister);
        store_CreatePermDAO = new Store_CreatePermDAO(userRegister, storeRegister, dealerRegister);
        store_DeletePermDAO = new Store_DeletePermDAO(userRegister, storeRegister);
        store_ReadPermDAO = new Store_ReadPermDAO(userRegister, storeRegister);
        store_UpdatePermDAO = new Store_UpdatePermDAO(userRegister, storeRegister);
        store_UpdatePermDAO = new Store_UpdatePermDAO(userRegister, storeRegister);

        user_CreatePermDAO = new User_CreatePermDAO(userRegister, userRegister, dealerRegister, storeRegister);
        user_DeletePermDAO = new User_DeletePermDAO(userRegister, userRegister);
        user_ReadPermDAO = new User_ReadPermDAO(userRegister, userRegister);
        user_UpdatePermDAO = new User_UpdatePermDAO(userRegister, userRegister);
    }

    public void load() {
        dealerRegister.load();
        storeRegister.load();
        productRegister.load();
        saleRegister.load();
        userRegister.load();
        tagRegister.load();
    }

}
