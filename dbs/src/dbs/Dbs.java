package dbs;

import control.FileHandler;
import control.PermissionHandler;
import db.DBTool;
import db.data.*;
import db.permission.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import json.JSONBuilder;
import model.data.*;
import networking.NetServer;
import std.TerminalController;

/**
 *
 * @author Patrick
 */
public class Dbs {

    public static final String TITLE = "DBS";
    public static final String VERSION = "0.0.0.0";

    private TerminalController stdController;
    private NetServer netServer;
    private JSONBuilder jsonBuilder;

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

    private UpdateNumberDAO updateNumberDAO;
    private UpdateNumberHandler updateNumberHandler;

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
    private FileHandler fileHandler;

    private IDHandler idHandler;

    public Dbs() throws IOException {
        fileHandler = new FileHandler();
        FileHandler.makeFolders();

        idHandler = new IDHandler();
        constructData();
        constructPermission();
        idHandler.init(dealerRegister, storeRegister, productRegister, saleRegister, userRegister, tagRegister, pictureRegister);
        permissionHandler = new PermissionHandler(adminPermDAO, dealer_AdminPermDAO, dealer_CreatePermDAO, dealer_DeletePermDAO, dealer_ReadPermDAO, dealer_UpdatePermDAO, product_CreatePermDAO, product_DeletePermDAO, product_ReadPermDAO, product_UpdatePermDAO, sale_CreatePermDAO, sale_DeletePermDAO, sale_ReadPermDAO, sale_UpdatePermDAO, store_AdminPermDAO, store_CreatePermDAO, store_DeletePermDAO, store_ReadPermDAO, store_UpdatePermDAO, user_CreatePermDAO, user_DeletePermDAO, user_ReadPermDAO, user_UpdatePermDAO);
        idHandler.refresh();

        jsonBuilder = new JSONBuilder(saleRegister, pictureRegister, tagRegister);
        netServer = new NetServer(6666, jsonBuilder);

        stdController = new TerminalController(this, netServer);
        stdController.start();

    }

    public DealerRegister getDealerRegister() {
        return dealerRegister;
    }

    public StoreRegister getStoreRegister() {
        return storeRegister;
    }

    public ProductRegister getProductRegister() {
        return productRegister;
    }

    public SaleRegister getSaleRegister() {
        return saleRegister;
    }

    public UserRegister getUserRegister() {
        return userRegister;
    }

    public TagRegister getTagRegister() {
        return tagRegister;
    }

    public PictureRegister getPictureRegister() {
        return pictureRegister;
    }

    public UpdateNumberHandler getUpdateNumberHandler() {
        return updateNumberHandler;
    }

    /**
     * Prints out the status of the program. Prints the amount of objects
     * currently held in the different data registers as well as the current
     * update number. Also prints out a status message stating whether the
     * netserver is running or not
     */
    public void status() {
        System.out.println("");
        System.out.println("### - REGISTER STATUS - ###");
        System.out.println("Pictures: " + pictureRegister.getObjects().size());
        System.out.println("Dealers: " + dealerRegister.getObjects().size());
        System.out.println("Stores: " + storeRegister.getObjects().size());
        System.out.println("Products: " + productRegister.getObjects().size());
        System.out.println("Sales: " + saleRegister.getObjects().size());
        System.out.println("Users: " + userRegister.getObjects().size());
        System.out.println("Tags: " + tagRegister.getObjects().size());
        System.out.println("");
        System.out.println("### - SERVER STATUS - ###");
        if (netServer.getRunning()) {
            System.out.println("NetServer is running");
        } else {
            System.out.println("NetServer is not running");
        }
        System.out.println("UpdateNumber: " + UpdateNumberHandler.getUpdateNumber());
        System.out.println("");
    }

    /**
     * Creates instances of all the data model and DAO classes.
     */
    private void constructData() {
        pictureDAO = new PictureDAO(fileHandler);
        pictureRegister = new PictureRegister(idHandler, pictureDAO);
        pictureRegister.load();

        dealerDAO = new DealerDAO(pictureRegister);
        dealerRegister = new DealerRegister(idHandler, dealerDAO);
        dealerRegister.load();

        storeDAO = new StoreDAO(dealerRegister, pictureRegister);
        storeRegister = new StoreRegister(idHandler, storeDAO);
        storeRegister.load();
        
        tagDAO = new TagDAO();
        tagRegister = new TagRegister(idHandler, tagDAO);
        tagRegister.load();

        productDAO = new ProductDAO(dealerRegister, storeRegister, pictureRegister, tagRegister);
        productRegister = new ProductRegister(idHandler, productDAO);
        productRegister.load();

        saleDAO = new SaleDAO(productRegister);
        saleRegister = new SaleRegister(idHandler, saleDAO);
        saleRegister.load();

        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(idHandler, userDAO);
        userRegister.load();

        

        updateNumberDAO = new UpdateNumberDAO();
        updateNumberHandler = new UpdateNumberHandler(updateNumberDAO, fileHandler);

        DBTool.close();
    }

    /**
     * Creates instances of all the permission DAOs.
     */
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Dbs dbs = new Dbs();
//        
//        Tag tøj = dbs.getTagRegister().create("Tøj", "Tøj");
//        Tag fodtøj = dbs.getTagRegister().create("Fodtøj", "Fodtøj");
//        Tag fødevarer = dbs.getTagRegister().create("Fødevarer", "Fødevarer");
//        
//        String pics = "C:\\Users\\Patrick\\Desktop\\App billeder\\";
//        Picture pic1 = dbs.getPictureRegister().create("Flyverdragt", pics + "Flyverdragt.jpg");
//        Picture pic2 = dbs.getPictureRegister().create("Converse", pics + "Converse.jpg");
//        Picture pic3 = dbs.getPictureRegister().create("Pizza", pics + "Pizza.jpg");
//        Picture pic4 = dbs.getPictureRegister().create("Ost", pics + "Ost.jpg");
//        Picture pic5 = dbs.getPictureRegister().create("Mælk", pics + "Mælk.jpg");
//        Picture pic6 = dbs.getPictureRegister().create("Sandaler", pics + "Sandaler.jpg");
//        Picture pic7 = dbs.getPictureRegister().create("T-shirt", pics + "T-shirt.jpg");
//        Picture pic8 = dbs.getPictureRegister().create("Spinat", pics + "Spinat.jpg");
//        Picture pic9 = dbs.getPictureRegister().create("Bukser", pics + "Bukser.jpg");
//        Picture pic10 = dbs.getPictureRegister().create("Vanter", pics + "Vanter.jpg");
//        
//        Dealer dealer = dbs.getDealerRegister().create("Test dealer", "Test dealer", "123456789", null);
//        
//        ArrayList<Tag> flyverdragtTags = new ArrayList<>();
//        flyverdragtTags.add(tøj);
//        
//        ArrayList<Tag> converseTags = new ArrayList<>();
//        converseTags.add(tøj);
//        converseTags.add(fodtøj);
//        
//        ArrayList<Tag> pizzaTags = new ArrayList<>();
//        pizzaTags.add(fødevarer);
//        
//        ArrayList<Tag> ostTags = new ArrayList<>();
//        ostTags.add(fødevarer);
//        
//        ArrayList<Tag> mælkTags = new ArrayList<>();
//        mælkTags.add(fødevarer);
//        
//        ArrayList<Tag> sandalerTags = new ArrayList<>();
//        sandalerTags.add(tøj);
//        sandalerTags.add(fodtøj);
//        
//        ArrayList<Tag> t_shirtTags = new ArrayList<>();
//        t_shirtTags.add(tøj);
//        
//        ArrayList<Tag> spinatTags = new ArrayList<>();
//        spinatTags.add(fødevarer);
//        
//        ArrayList<Tag> bukserTags = new ArrayList<>();
//        bukserTags.add(tøj);
//        
//        ArrayList<Tag> vanterTags = new ArrayList<>();
//        vanterTags.add(tøj);
//        
//        Product flyverdragt = dbs.getProductRegister().create("Flyverdragt", "Test", pic1, 400, dealer, flyverdragtTags);
//        Product converse = dbs.getProductRegister().create("Converse sko", "Test", pic2, 600, dealer, converseTags);
//        Product pizza = dbs.getProductRegister().create("Pizza", "Test", pic3, 70, dealer, pizzaTags);
//        Product ost = dbs.getProductRegister().create("Ost", "Test", pic4, 25, dealer, ostTags);
//        Product mælk = dbs.getProductRegister().create("Mælk", "Test", pic5, 400, dealer, mælkTags);
//        Product sandaler = dbs.getProductRegister().create("Sandaler", "Test", pic6, 400, dealer, sandalerTags);
//        Product t_shirt = dbs.getProductRegister().create("T-Shirt", "Test", pic7, 400, dealer, t_shirtTags);
//        Product spinat = dbs.getProductRegister().create("Spinat", "Test", pic8, 400, dealer, spinatTags);
//        Product bukser = dbs.getProductRegister().create("Bukser", "Test", pic9, 400, dealer, bukserTags);
//        Product vanter = dbs.getProductRegister().create("Vanter", "Test", pic10, 400, dealer, vanterTags);
        
//        Product a = dbs.getProductRegister().get(1);
//        Product b = dbs.getProductRegister().get(2);
//        Product c = dbs.getProductRegister().get(3);
//        Product d = dbs.getProductRegister().get(4);
//        Product e = dbs.getProductRegister().get(5);
//        Product f = dbs.getProductRegister().get(6);
//        Product g = dbs.getProductRegister().get(7);
//        Product h = dbs.getProductRegister().get(8);
//        Product i = dbs.getProductRegister().get(9);
//        Product j = dbs.getProductRegister().get(10);
//        
//        Date date = new Date(2014, 01, 01, 12, 00);
//        
//        Sale aS = dbs.getSaleRegister().create(a, 400, date, date, date);
//        Sale bS = dbs.getSaleRegister().create(b, 300, date, date, date);
//        Sale cS = dbs.getSaleRegister().create(c, 65, date, date, date);
//        Sale dS = dbs.getSaleRegister().create(d, 29, date, date, date);
//        Sale eS = dbs.getSaleRegister().create(e, 2, date, date, date);
//        Sale fS = dbs.getSaleRegister().create(f, 250, date, date, date);
//        Sale gS = dbs.getSaleRegister().create(g, 50, date, date, date);
//        Sale hS = dbs.getSaleRegister().create(h, 60, date, date, date);
//        Sale iS = dbs.getSaleRegister().create(i, 150, date, date, date);
//        Sale jS = dbs.getSaleRegister().create(j, 250, date, date, date);
        
    }

}
