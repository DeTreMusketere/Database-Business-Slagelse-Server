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
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, parentDealer, updateNumber);
        insert(u);
        return u;
    }

    public User create(String name, String username, String password, String email, String phone, Store parentStore) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, parentStore, updateNumber);
        insert(u);
        return u;
    }

    public User create(String name, String username, String password, String email, String phone) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, updateNumber);
        insert(u);
        return u;
    }
    
    @Override
    public void delete(User target) {
        int id = target.getId();
        UpdateNumberHandler.delete("User§" + id);
        super.delete(target);
    }

}
