
package model.data;

import abstracts.Data;

/**
 *
 * @author Patrick
 */
public class Store extends Data {

    private String name;
    private String address;
    private String phone;
    private int picture;
    private Dealer parentDealer;

    public Store(int id, String name, String address, String phone, int picture, Dealer parentDealer) {
        super(id);
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.picture = picture;
        this.parentDealer = parentDealer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public Dealer getParent() {
        return parentDealer;
    }

    public void setParent(Dealer parentDealer) {
        this.parentDealer = parentDealer;
    }
    
    
}
