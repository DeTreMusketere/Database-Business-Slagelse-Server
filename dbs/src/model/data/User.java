
package model.data;

import abstracts.Data;
import org.json.JSONObject;

/**
 *
 * @author Patrick
 */
public class User extends Data {
    
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Store parentStore;
    private Dealer parentDealer;

    public User(int id, String name, String username, String password, String email, String phone, Dealer parentDealer, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.parentDealer = parentDealer;
    }

    public User(int id, String name, String username, String password, String email, String phone, Store parentStore, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.parentStore = parentStore;
        this.parentDealer = parentStore.getParent();
    }
    
    public User(int id, String name, String username, String password, String email, String phone, int updateNumber) {
        super(id, updateNumber);
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.parentStore = null;
        this.parentDealer = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        String s = "name: " + name + " username: " + username + " password: " + password + " email: " + email + " phone: " + phone;
        if(parentDealer != null ) {
            s += " parent dealer: " + parentDealer.getName();
        }
        if(parentStore != null ) {
            s += " parent store: " + parentStore.getName();
        }
        
        return s;
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject obj = new JSONObject();
        obj.put("id", getId());
        obj.put("name", name);
        obj.put("username", username);
        obj.put("password", password);
        obj.put("email", email);
        obj.put("phone", phone);
        if(parentDealer != null) {
            obj.put("parentdealer", parentDealer.getId());
        } else {
            obj.put("parentdealer", -1);
        }
        obj.put("updatenumber", getUpdateNumber());
        return obj;
    }
    
}
