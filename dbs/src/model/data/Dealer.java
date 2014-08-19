
package model.data;

import abstracts.Data;

/**
 *
 * @author Patrick
 */
public class Dealer extends Data {
    private String name;
    private String description;
    private String phone;
    private Picture picture;
    private int updateNumber;

    public Dealer(int id, String name, String description, String phone, Picture picture, int updateNumber) {
        super(id);
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.picture = picture;
        this.updateNumber = updateNumber;
    }

    public String getName() {
        return name;
    }

    public int getUpdateNumber() {
        return updateNumber;
    }

    public void setUpdateNumber(int updateNumber) {
        this.updateNumber = updateNumber;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
    
    @Override
    public String toString() {
        String s = "name: " + name + " description: " + description + " phone: " + phone;
        if(picture != null) {
            s += " picture: " + picture.getName();
        }
        s += " updateNumber: " + updateNumber;
        return s;
    }
    
    
}
