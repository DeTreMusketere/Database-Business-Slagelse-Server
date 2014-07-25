
package model.data;

import abstracts.Data;

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

    public User(int id, String name, String username, String password, String email, String phone, Dealer parentDealer) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.parentDealer = parentDealer;
    }

    public User(int id, String name, String username, String password, String email, String phone, Store parentStore) {
        super(id);
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.parentStore = parentStore;
        this.parentDealer = parentStore.getParent();
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
    
}
