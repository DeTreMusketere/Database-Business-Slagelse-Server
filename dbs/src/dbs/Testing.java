package dbs;

import control.PermissionHandler;
import model.data.*;

/**
 *
 * @author Patrick
 */
public class Testing {

    private DealerRegister dealerRegister;
    private StoreRegister storeRegister;
    private ProductRegister productRegister;
    private SaleRegister saleRegister;
    private UserRegister userRegister;
    private TagRegister tagRegister;
    private PictureRegister pictureRegister;

    private PermissionHandler permissionHandler;

    public Testing(DealerRegister dealerRegister, StoreRegister storeRegister, ProductRegister productRegister, SaleRegister saleRegister, UserRegister userRegister, TagRegister tagRegister, PictureRegister pictureRegister, PermissionHandler permissionHandler) {
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
        this.productRegister = productRegister;
        this.saleRegister = saleRegister;
        this.userRegister = userRegister;
        this.tagRegister = tagRegister;
        this.pictureRegister = pictureRegister;
        this.permissionHandler = permissionHandler;
    }

    public void doTest() {
        User user = userRegister.create("Test testsen", "testBruger", "testkode", "test@test.test", "123456789");
        Dealer dealer = dealerRegister.create("Test Dealer", "This is a test dealer", "123456789", null);
        Store store = storeRegister.create("Test store", "Test street", "This is a test store", null, dealer);
        Product productDealerLevel = productRegister.create("Test Milk", "This is a test product", null, 10, dealer);
        Product productStoreLevel = productRegister.create("Test Cookie", "This is a test product", null, 20, store);
        Tag tag = tagRegister.create("Videvarer", "Test tag");
        Tag tag2 = tagRegister.create("Sortevarer", "Test tag");
        
        tagRegister.delete(tag2);
        tagRegister.delete(tag);
        productRegister.delete(productStoreLevel);
        productRegister.delete(productDealerLevel);
        storeRegister.delete(store);
        dealerRegister.delete(dealer);
        userRegister.delete(user);
    }

}
