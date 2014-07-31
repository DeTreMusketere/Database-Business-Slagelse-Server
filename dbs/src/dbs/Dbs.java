package dbs;

import control.PermissionHandler;
import model.data.*;
import model.permission.*;
import db.data.*;
import db.permission.*;

/**
 *
 * @author Patrick
 */
public class Dbs {
    
    public static final String TITLE = "DBS";
    public static final String VERSION = "0.0.0.0";
    
    private DealerDAO dealerDAO;
    private DealerRegister dealerRegister;
    
    private StoreDAO storeDAO;
    private StoreRegister storeRegister;
    
    private ProductDAO productDAO;
    private ProductRegister productRegister;
    
    private SaleDAO saleDAO;
    private SaleRegister saleRegister;
    
    private UserDAO userDAO;
    private UserRegister userRegister;
    
    private TagDAO tagDAO;
    private TagRegister tagRegister;
    
    private PictureDAO pictureDAO;
    private PictureRegister pictureRegister;

    private AdminPermDAO adminPermDAO;
    
    private Dealer_AdminPermDAO dealer_AdminPermDAO;
    private Dealer_CreatePermDAO dealer_CreatePermDAO;
    private Dealer_DeletePermDAO dealer_DeletePermDAO;
    private Dealer_ReadPermDAO dealer_ReadPermDAO;
    private Dealer_UpdatePermDAO dealer_UpdatePermDAO;
    
    private Product_CreatePermDAO product_CreatePermDAO;
    private Product_DeletePermDAO product_DeletePermDAO;
    private Product_ReadPermDAO product_ReadPermDAO;
    private Product_UpdatePermDAO product_UpdatePermDAO;
    
    private Sale_CreatePermDAO sale_CreatePermDAO;
    private Sale_DeletePermDAO sale_DeletePermDAO;
    private Sale_ReadPermDAO sale_ReadPermDAO;
    private Sale_UpdatePermDAO sale_UpdatePermDAO;
    
    private Store_AdminPermDAO store_AdminPermDAO;
    private Store_CreatePermDAO store_CreatePermDAO;
    private Store_DeletePermDAO store_DeletePermDAO;
    private Store_ReadPermDAO store_ReadPermDAO;
    private Store_UpdatePermDAO store_UpdatePermDAO;
    
    private User_CreatePermDAO user_CreatePermDAO;
    private User_DeletePermDAO user_DeletePermDAO;
    private User_ReadPermDAO user_ReadPermDAO;
    private User_UpdatePermDAO user_UpdatePermDAO;
    
    private PermissionHandler permissionHandler;

    public Dbs() {
        constructData();
        constructPermission();
        
        permissionHandler = new PermissionHandler(adminPermDAO, dealer_AdminPermDAO, dealer_CreatePermDAO, dealer_DeletePermDAO, dealer_ReadPermDAO, dealer_UpdatePermDAO, product_CreatePermDAO, product_DeletePermDAO, product_ReadPermDAO, product_UpdatePermDAO, sale_CreatePermDAO, sale_DeletePermDAO, sale_ReadPermDAO, sale_UpdatePermDAO, store_AdminPermDAO, store_CreatePermDAO, store_DeletePermDAO, store_ReadPermDAO, store_UpdatePermDAO, user_CreatePermDAO, user_DeletePermDAO, user_ReadPermDAO, user_UpdatePermDAO);
    }
    
    private void constructData() {
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
    
    private void constructPermission() {
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Dbs dbs = new Dbs();
        dbs.load();
        User u = dbs.userRegister.getObjects().get(0);
        UserPermissionSet ups = dbs.permissionHandler.constructUserPermissionSet(u);
        System.out.println(ups.toString());
    }

}
