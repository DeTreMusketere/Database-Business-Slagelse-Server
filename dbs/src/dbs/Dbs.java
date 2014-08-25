package dbs;

import control.FileHandler;
import control.PermissionHandler;
import model.data.*;
import db.data.*;
import db.permission.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import json.JSONBuilder;
import networking.NetServer;
import std.CMDCTRL;
import std.STDOUTCTRL;
import std.STDController;

/**
 *
 * @author Patrick
 */
public class Dbs {

    public static final String TITLE = "DBS";
    public static final String VERSION = "0.0.0.0";
    
    private STDController stdController;
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

    //test
    private static BufferedImage test = null;

    public Dbs() throws IOException {
        fileHandler = new FileHandler();

        idHandler = new IDHandler();
        constructData();
        constructPermission();
        idHandler.init(dealerRegister, storeRegister, productRegister, saleRegister, userRegister, tagRegister, pictureRegister);
        permissionHandler = new PermissionHandler(adminPermDAO, dealer_AdminPermDAO, dealer_CreatePermDAO, dealer_DeletePermDAO, dealer_ReadPermDAO, dealer_UpdatePermDAO, product_CreatePermDAO, product_DeletePermDAO, product_ReadPermDAO, product_UpdatePermDAO, sale_CreatePermDAO, sale_DeletePermDAO, sale_ReadPermDAO, sale_UpdatePermDAO, store_AdminPermDAO, store_CreatePermDAO, store_DeletePermDAO, store_ReadPermDAO, store_UpdatePermDAO, user_CreatePermDAO, user_DeletePermDAO, user_ReadPermDAO, user_UpdatePermDAO);
        
        jsonBuilder = new JSONBuilder(saleRegister, pictureRegister);
        netServer = new NetServer(6666, jsonBuilder);
        
//        stdController = new STDController(this,netServer);
//        stdController.start();
        STDOUTCTRL.getInstance().start();
        CMDCTRL.getInstance().start();
        
        
    }
    
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
        if(netServer.getRunning()) {
            System.out.println("NetServer is running");
        } else {
            System.out.println("NetServer is not running");
        }
        System.out.println("");
    }

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
        
        productDAO = new ProductDAO(dealerRegister, storeRegister, pictureRegister);
        productRegister = new ProductRegister(idHandler, productDAO);
        productRegister.load();
        
        saleDAO = new SaleDAO(dealerRegister, storeRegister, pictureRegister);
        saleRegister = new SaleRegister(idHandler, saleDAO);
        saleRegister.load();
        
        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(idHandler, userDAO);
        userRegister.load();
        
        tagDAO = new TagDAO();
        tagRegister = new TagRegister(idHandler, tagDAO);
        tagRegister.load();
        
        updateNumberDAO = new UpdateNumberDAO();
        updateNumberHandler = new UpdateNumberHandler(updateNumberDAO, fileHandler);
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
    
    public void test() {
//        Testing permissionTest = new Testing(dealerRegister, storeRegister, productRegister, saleRegister, userRegister, tagRegister, pictureRegister, permissionHandler);
//        permissionTest.doTest();
        
        
        System.out.println(UpdateNumberHandler.getDeleteList().toString());        
        Dealer test = dealerRegister.create("TestDealer", "Hello I am test", "25252525", null);
        Dealer test2 = dealerRegister.create("TestDealer", "Hello I am test", "25252525", null);
        dealerRegister.delete(test);
        dealerRegister.delete(test2);
        System.out.println(UpdateNumberHandler.getDeleteList().toString());
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        Dbs dbs = new Dbs();

        //dbs.idHandler.refresh();
        //dbs.test();
        
        
//        User u = dbs.userRegister.getObjects().get(0);
//        UserPermissionSet ups = dbs.permissionHandler.constructUserPermissionSet(u);
//        System.out.println(ups.toString());
        
        

//        FileHandler fh = new FileHandler();
//        HashMap<Integer, String> map = fh.getDeleteList();
//        System.out.println(map.toString());
//        System.out.println(map.get(1));
//        
//        HashMap<Integer, String> map = new HashMap<>();
//        map.put(1, "one");
//        fh.saveDeleteList(map);
//        Saves a picture (Edit filepath for testing or riot)
//        byte[] imageInByte = null;              
//        try {
//            test = ImageIO.read(new File("C:\\Users\\PK\\Downloads\\DSC_0019.jpg"));
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            ImageIO.write(test, "jpg", baos);
//            baos.flush();
//            imageInByte = baos.toByteArray();
//            baos.close();
//            fh.saveByteArray(imageInByte, 2);
//        } catch (IOException ex) {
//            Logger.getLogger(Dbs.class.getName()).log(Level.SEVERE, null, ex);
//        }

//        Loads a picture and paints it in a JFrame
//        byte[] array = fh.getByteArray(2);
//        InputStream in = new ByteArrayInputStream(array);
//        try {
//            test = ImageIO.read(in);
//        } catch (IOException ex) {
//            Logger.getLogger(Dbs.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        JFrame frame = new JFrame();
//        JPanel panel = new JPanel() {
//            @Override
//            public void paint(Graphics g) {
//                  g.drawImage(test, 0, 0, null);
//            }
//        };
//
//        frame.setContentPane(panel);
//
//        frame.pack();
//        frame.setSize(test.getWidth(), test.getHeight());
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
    }

}
