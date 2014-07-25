package dbs;

import db.data.DealerDAO;
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
import model.data.DealerRegister;
import model.data.ProductRegister;
import model.data.SaleRegister;
import model.data.StoreRegister;
import model.data.TagRegister;
import model.data.UserRegister;

/**
 *
 * @author Patrick
 */
public class Dbs {

    private final DealerDAO dealerDAO;
    private final DealerRegister dealerRegister;
    private final StoreDAO storeDAO;
    private final StoreRegister storeRegister;
    private final UserDAO userDAO;
    private final UserRegister userRegister;
    private final ProductDAO productDAO;
    private final ProductRegister productRegister;
    private final SaleDAO saleDAO;
    private final SaleRegister saleRegister;
    private final TagDAO tagDAO;
    private final TagRegister tagRegister;

    private final AdminPermDAO adminPermDAO;
    private final Dealer_AdminPermDAO dealer_AdminPermDAO;
    private final Dealer_CreatePermDAO dealer_CreatePermDAO;
    private final Dealer_ReadPermDAO dealer_ReadPermDAO;
    private final Dealer_UpdatePermDAO dealer_UpdatePermDAO;
    private final Dealer_DeletePermDAO dealer_DeletePermDAO;

    public Dbs() {
        this.dealerDAO = new DealerDAO();
        this.dealerRegister = new DealerRegister(dealerDAO);
        this.storeDAO = new StoreDAO(dealerRegister);
        this.storeRegister = new StoreRegister(storeDAO);
        this.userDAO = new UserDAO(dealerRegister, storeRegister);
        this.userRegister = new UserRegister(userDAO);
        this.productDAO = new ProductDAO(dealerRegister, storeRegister);
        this.productRegister = new ProductRegister(productDAO);
        this.saleDAO = new SaleDAO(dealerRegister, storeRegister);
        this.saleRegister = new SaleRegister(saleDAO);
        this.tagDAO = new TagDAO();
        this.tagRegister = new TagRegister(tagDAO);
        
        this.adminPermDAO = new AdminPermDAO(userRegister, null);
        this.dealer_AdminPermDAO = new Dealer_AdminPermDAO(userRegister, dealerRegister);
        this.dealer_CreatePermDAO = new Dealer_CreatePermDAO(userRegister, null);
        this.dealer_ReadPermDAO = new Dealer_ReadPermDAO(userRegister, dealerRegister);
        this.dealer_UpdatePermDAO = new Dealer_UpdatePermDAO(userRegister, dealerRegister);
        this.dealer_DeletePermDAO = new Dealer_DeletePermDAO(userRegister, dealerRegister);
    }

    public void load() {
        dealerRegister.load();
        storeRegister.load();
        userRegister.load();
        productRegister.load();
        saleRegister.load();
        tagRegister.load();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Dbs dbs = new Dbs();
        dbs.load();
    }

}
