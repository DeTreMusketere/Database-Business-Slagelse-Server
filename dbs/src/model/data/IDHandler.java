package model.data;

import abstracts.DAO;
import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public class IDHandler extends DAO {

    private int currentDealerId;
    private int currentStoreId;
    private int currentUserId;
    private int currentProductId;
    private int currentSaleId;
    private int currentTagId;
    private int currentPictureId;

    private DealerRegister dealerRegister;
    private StoreRegister storeRegister;
    private ProductRegister productRegister;
    private SaleRegister saleRegister;
    private UserRegister userRegister;
    private TagRegister tagRegister;
    private PictureRegister pictureRegister;

    public void refresh() {
        ArrayList<Dealer> dealers = dealerRegister.getObjects();
        for (Dealer d : dealers) {
            int id = d.getId();
            if (currentDealerId < id) {
                currentDealerId = id;
            }
        }

        ArrayList<Store> stores = storeRegister.getObjects();
        for (Store d : stores) {
            int id = d.getId();
            if (currentStoreId < id) {
                currentStoreId = id;
            }
        }

        ArrayList<Product> products = productRegister.getObjects();
        for (Product d : products) {
            int id = d.getId();
            if (currentProductId < id) {
                currentProductId = id;
            }
        }

        ArrayList<Sale> sales = saleRegister.getObjects();
        for (Sale d : sales) {
            int id = d.getId();
            if (currentSaleId < id) {
                currentSaleId = id;
            }
        }

        ArrayList<User> users = userRegister.getObjects();
        for (User d : users) {
            int id = d.getId();
            if (currentUserId < id) {
                currentUserId = id;
            }
        }

        ArrayList<Tag> tags = tagRegister.getObjects();
        for (Tag d : tags) {
            int id = d.getId();
            if (currentTagId < id) {
                currentTagId = id;
            }
        }

        ArrayList<Picture> pictures = pictureRegister.getObjects();
        for (Picture d : pictures) {
            int id = d.getId();
            if (currentPictureId < id) {
                currentPictureId = id;
            }
        }

        System.out.println("Dealer: " + currentDealerId);
        System.out.println("Store: " + currentStoreId);
        System.out.println("Product: " + currentProductId);
        System.out.println("Sale: " + currentSaleId);
        System.out.println("User: " + currentUserId);
        System.out.println("Tag: " + currentTagId);
        System.out.println("Picture: " + currentPictureId);
    }

    public void init(DealerRegister dealerRegister, StoreRegister storeRegister, ProductRegister productRegister, SaleRegister saleRegister, UserRegister userRegister, TagRegister tagRegister, PictureRegister pictureRegister) {
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
        this.productRegister = productRegister;
        this.saleRegister = saleRegister;
        this.userRegister = userRegister;
        this.tagRegister = tagRegister;
        this.pictureRegister = pictureRegister;
    }

    public int currentDealerId() {
        return currentDealerId;
    }

    public int nextDealerId() {
        this.currentDealerId++;
        return this.currentDealerId;
    }

    public int currentStoreId() {
        return currentStoreId;
    }

    public int nextStoreId() {
        this.currentStoreId++;
        return this.currentStoreId;
    }

    public int currentUserId() {
        return currentUserId;
    }

    public int nextUserId() {
        this.currentUserId++;
        return this.currentUserId;
    }

    public int currentProductId() {
        return currentProductId;
    }

    public int nextProductId() {
        this.currentProductId++;
        return this.currentProductId;
    }

    public int currentSaleId() {
        return currentSaleId;
    }

    public int nextSaleId() {
        this.currentSaleId++;
        return this.currentSaleId;
    }

    public int currentTagId() {
        return currentTagId;
    }

    public int nextTagId() {
        this.currentTagId++;
        return this.currentTagId;
    }

    public int currentPictureId() {
        return currentPictureId;
    }

    public int nextPictureId() {
        this.currentPictureId++;
        return this.currentPictureId;
    }

}
