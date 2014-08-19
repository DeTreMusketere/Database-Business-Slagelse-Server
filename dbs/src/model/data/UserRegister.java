
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class UserRegister extends Register<User> {

    public UserRegister(IDHandler idHandler, DataDAO<User> dao) {
        super(idHandler, dao);
    }
    
    public User create(String name, String username, String password, String email, String phone, Dealer parentDealer) {
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, parentDealer);
        insert(u);
        return u;
    }
    
    public User create(String name, String username, String password, String email, String phone, Store parentStore) {
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, parentStore);
        insert(u);
        return u;
    }
    
    public User create(String name, String username, String password, String email, String phone) {
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone);
        insert(u);
        return u;
    }

}
