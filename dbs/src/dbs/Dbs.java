package dbs;

import model.data.*;
import model.permission.*;
import db.data.*;
import db.permission.*;

/**
 *
 * @author Patrick
 */
public class Dbs {
    
    private DealerDAO dealerDAO;
    private DealerRegister dealerRegister;
    
    private StoreDAO storeDAO;
    private StoreRegister storeRegister;
    
    private ProductDAO productDAO;
    private ProductRegister produdtRegister;
    
    private SaleDAO saleDAO;
    private SaleRegister saleRegister;
    
    private UserDAO userDAO;
    private UserRegister userRegister;
    
    private TagDAO tagDAO;
    private TagRegister tagRegister;

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
    
    private  User_CreatePermDAO user_CreatePermDAO;
    private  User_DeletePermDAO user_DeletePermDAO;
    private  User_ReadPermDAO user_ReadPermDAO;
    private  User_UpdatePermDAO user_UpdatePermDAO;

    public Dbs() {
        
    }

    public void load() {
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Dbs dbs = new Dbs();
        dbs.load();
    }

}
