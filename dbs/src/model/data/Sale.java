
package model.data;

import abstracts.Data;

/**
 *
 * @author Patrick
 */
public class Sale extends Data {
    private String name;
    private String description;
    private int picture;
    private double price;
    private Store parentStore;
    private Dealer parentDealer;

    public Sale(int id, String name, String description, int picture, double price, Dealer parentDealer) {
        super(id);
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.parentDealer = parentDealer;
    }

    public Sale(int id, String name, String description, int picture, double price, Store parentStore) {
        super(id);
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.parentStore = parentStore;
        this.parentDealer = parentStore.getParent();
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

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
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
    
    @Override
    public String toString() {
        String s = "name: " + name + " description: " + description + " picture: " + picture + " price: " + price;
        if(parentDealer != null) {
            s += " parent dealer: " + parentDealer.getName();
        }
        if(parentStore != null) {
            s += " parent store: " + parentStore.getName();
        }
        
        return s;
    }
}
