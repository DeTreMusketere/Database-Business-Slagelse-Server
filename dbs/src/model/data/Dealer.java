
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
    private int picture;

    public Dealer(int id, String name, String description, String phone, int picture) {
        super(id);
        this.name = name;
        this.description = description;
        this.phone = phone;
        this.picture = picture;
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
    
    
}
