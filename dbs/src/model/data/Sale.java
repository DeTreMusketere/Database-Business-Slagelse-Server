package model.data;

import abstracts.Data;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class Sale extends Data {

    private String name;
    private String description;
    private Picture picture;
    private double price;
    private Store parentStore;
    private Dealer parentDealer;
    private Date start;
    private Date end;
    private Date publish;

    public Sale(int id, String name, String description, Picture picture, double price, Date start, Date end, Date publish, Dealer parentDealer, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.start = start;
        this.end = end;
        this.publish = publish;
        this.parentDealer = parentDealer;
    }

    public Sale(int id, String name, String description, Picture picture, double price, Date start, Date end, Date publish, Store parentStore, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.description = description;
        this.picture = picture;
        this.price = price;
        this.start = start;
        this.end = end;
        this.publish = publish;
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

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public Date getPublish() {
        return publish;
    }

    public void setPublish(Date publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        String s = "name: " + name + " description: " + description + " picture: " + picture + " price: " + price + " start: " + start.toString() + " end: " + end.toString() + " publish: " + publish.toString();
        if (parentDealer != null) {
            s += " parent dealer: " + parentDealer.getName();
        }
        if (parentStore != null) {
            s += " parent store: " + parentStore.getName();
        }

        return s;
    }
}
