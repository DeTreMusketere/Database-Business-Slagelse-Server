package model.data;

import abstracts.Data;
import java.util.ArrayList;
import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public class Product extends Data {

    private String name;
    private String description;
    private Picture picture;
    private double price;
    private Store parentStore;
    private Dealer parentDealer;
    private ArrayList<Tag> tags;

    public Product(int id, String name, String description, Picture picture, double price, Dealer parentDealer, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.parentDealer = parentDealer;
        this.tags = new ArrayList<>();
    }

    public Product(int id, String name, String description, Picture picture, double price, Store parentStore, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.parentStore = parentStore;
        this.parentDealer = parentStore.getParent();
        this.tags = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Store getParentStore() {
        return parentStore;
    }

    public void setParentStore(Store parentStore) {
        this.parentStore = parentStore;
    }

    public Dealer getParentDealer() {
        return parentDealer;
    }

    public void setParentDealer(Dealer parentDealer) {
        this.parentDealer = parentDealer;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        String s = "name: " + name + " description: " + description + " picture: " + picture + " price: " + price;
        if (parentDealer != null) {
            s += " parent dealer: " + parentDealer.getName();
        }
        if (parentStore != null) {
            s += " parent store: " + parentStore.getName();
        }

        return s;
    }

    @Override
    public JSONObject getJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        obj.put("name", name);
        obj.put("description", description);
        obj.put("picture", picture.getId());
        obj.put("price", price);
        if (parentStore != null) {
            obj.put("parentstore", parentStore.getId());
        } else {
            obj.put("parentstore", -1);
        }
        if(parentDealer != null) {
            obj.put("parentdealer", parentDealer.getId());
        } else {
            obj.put("parentdealer", -1);
        }
        int count = 0;
        for(Tag t : tags) {
            count++;
            obj.put("tag" + count, t.getId());
        }
        return obj;
    }

}
