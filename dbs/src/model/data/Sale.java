package model.data;

import abstracts.Data;
import java.util.ArrayList;
import java.util.Date;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public class Sale extends Data {

    private Product product;
    private double price;
    private Date start;
    private Date end;
    private Date publish;

    public Sale(int id, Product product, double price, Date start, Date end, Date publish, int updateNumber) {
        super(id, updateNumber);
        this.product = product;
        this.price = price;
        this.start = start;
        this.end = end;
        this.publish = publish;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
        String s = "name: " + product.getName() + " description: " + product.getDescription() + " picture: " + product.getPicture().getId() + " price: " + price + " start: " + start.getTime() + " end: " + end.getTime() + " publish: " + publish.getTime();
        if (product.getParentDealer() != null) {
            s += " parent dealer: " + product.getParentDealer().getName();
        }
        if (product.getParentStore() != null) {
            s += " parent store: " + product.getParentStore().getName();
        }

        return s;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        obj.put("name", product.getName());
        obj.put("description", product.getDescription());
        try {
            obj.put("picture", product.getPicture().getId());
        } catch (NullPointerException ex) {
            obj.put("picture", -1);
        }
        obj.put("price", price);
        if (product.getParentStore() != null) {
            obj.put("parentstore", product.getParentStore().getId());
        } else {
            obj.put("parentstore", -1);
        }
        if (product.getParentDealer() != null) {
            obj.put("parentdealer", product.getParentDealer().getId());
        } else {
            obj.put("parentdealer", -1);
        }
        obj.put("start", start.getTime());
        obj.put("end", end.getTime());
        obj.put("publish", publish.getTime());
        JSONArray tArray = new JSONArray();
        for(Tag t : product.getTags()) {
            tArray.put(t.getId());
        }
        obj.put("tags", tArray);
        obj.put("updatenumber", getUpdateNumber());

        return obj;
    }
}
