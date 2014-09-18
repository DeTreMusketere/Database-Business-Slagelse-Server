package model.data;

import abstracts.Data;
import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public class Dealer extends Data {

    private String name;
    private String description;
    private String phone;
    private Picture picture;

    public Dealer(int id, String name, String description, String phone, Picture picture, int updateNumber) {
        super(id, updateNumber);
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

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }

    @Override
    public String toString() {
        String s = "name: " + name + " description: " + description + " phone: " + phone;
        if (picture != null) {
            s += " picture: " + picture.getName();
        }
        s += " updateNumber: " + updateNumber;
        return s;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        obj.put("name", name);
        obj.put("description", description);
        obj.put("phone", phone);
        try {
            obj.put("picture", picture.getId());
        } catch (NullPointerException ex) {
            obj.put("picture", -1);
        }
        obj.put("updatenumber", getUpdateNumber());
        return obj;
    }

}
