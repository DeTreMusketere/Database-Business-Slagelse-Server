package model.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

/**
 *
 * @author Patrick
 */
public class IDHandler {

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

    private HashMap<Integer, String> deleteList;

    public void refresh() {
        deleteList = UpdateNumberHandler.getDeleteList();
        Collection<String> listOfValues = deleteList.values();

        ArrayList<Dealer> dealers = dealerRegister.getObjects();
        for (Dealer d : dealers) {
            int id = d.getId();
            if (currentDealerId < id) {
                currentDealerId = id;
            }
        }
        for (String s : listOfValues) {
            String[] value = s.split("§");
            if (value[0].equalsIgnoreCase("Dealer")) {
                if (currentDealerId < Integer.parseInt(value[1])) {
                    currentDealerId = Integer.parseInt(value[1]);
                }
            }
        }

        ArrayList<Store> stores = storeRegister.getObjects();
        for (Store d : stores) {
            int id = d.getId();
            if (currentStoreId < id) {
                currentStoreId = id;
            }
        }
        for (String s : listOfValues) {
            String[] value = s.split("§");
            if (value[0].equalsIgnoreCase("Store")) {
                if (currentStoreId < Integer.parseInt(value[1])) {
                    currentStoreId = Integer.parseInt(value[1]);
                }
            }
        }

        ArrayList<Product> products = productRegister.getObjects();
        for (Product d : products) {
            int id = d.getId();
            if (currentProductId < id) {
                currentProductId = id;
            }
        }
        for (String s : listOfValues) {
            String[] value = s.split("§");
            if (value[0].equalsIgnoreCase("Product")) {
                if (currentProductId < Integer.parseInt(value[1])) {
                    currentProductId = Integer.parseInt(value[1]);
                }
            }
        }

        ArrayList<Sale> sales = saleRegister.getObjects();
        for (Sale d : sales) {
            int id = d.getId();
            if (currentSaleId < id) {
                currentSaleId = id;
            }
        }
        for (String s : listOfValues) {
            String[] value = s.split("§");
            if (value[0].equalsIgnoreCase("Sale")) {
                if (currentSaleId < Integer.parseInt(value[1])) {
                    currentSaleId = Integer.parseInt(value[1]);
                }
            }
        }

        ArrayList<User> users = userRegister.getObjects();
        for (User d : users) {
            int id = d.getId();
            if (currentUserId < id) {
                currentUserId = id;
            }
        }
        for (String s : listOfValues) {
            String[] value = s.split("§");
            if (value[0].equalsIgnoreCase("User")) {
                if (currentUserId < Integer.parseInt(value[1])) {
                    currentUserId = Integer.parseInt(value[1]);
                }
            }
        }

        ArrayList<Tag> tags = tagRegister.getObjects();
        for (Tag d : tags) {
            int id = d.getId();
            if (currentTagId < id) {
                currentTagId = id;
            }
        }
        for (String s : listOfValues) {
            String[] value = s.split("§");
            if (value[0].equalsIgnoreCase("Tag")) {
                if (currentTagId < Integer.parseInt(value[1])) {
                    currentTagId = Integer.parseInt(value[1]);
                }
            }
        }

        ArrayList<Picture> pictures = pictureRegister.getObjects();
        for (Picture d : pictures) {
            int id = d.getId();
            if (currentPictureId < id) {
                currentPictureId = id;
            }
        }
        for (String s : listOfValues) {
            String[] value = s.split("§");
            if (value[0].equalsIgnoreCase("Picture")) {
                if (currentPictureId < Integer.parseInt(value[1])) {
                    currentPictureId = Integer.parseInt(value[1]);
                }
            }
        }

//        System.out.println("Dealer: " + currentDealerId);
//        System.out.println("Store: " + currentStoreId);
//        System.out.println("Product: " + currentProductId);
//        System.out.println("Sale: " + currentSaleId);
//        System.out.println("User: " + currentUserId);
//        System.out.println("Tag: " + currentTagId);
//        System.out.println("Picture: " + currentPictureId);
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
