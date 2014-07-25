
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class UserRegister extends Register<User> {

    public UserRegister(DataDAO<User> dao) {
        super(dao);
    }
    
    public void create(String name, String username, String password, String email, String phone, Dealer parentDealer) {
        User u = new User(0, name, username, password, email, phone, parentDealer);
        insert(u);
    }
    
    public void create(String name, String username, String password, String email, String phone, Store parentStore) {
        User u = new User(0, name, username, password, email, phone, parentStore);
        insert(u);
    }

}
