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

    /**
     * Use this for users with a dealer parent. Creates a user with the given
     * parameters and an updated update number and the newest id from the
     * idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param name The user's name.
     * @param username The username for the user.
     * @param password The user's password.
     * @param parentDealer The parent dealer.
     * @param email The email for the user.
     * @param phone The phone number belonging to the user.
     * @return The created user.
     */
    public User create(String name, String username, String password, String email, String phone, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, parentDealer, updateNumber);
        insert(u);
        return u;
    }

    /**
     * Use this for users with a store parent. Creates a user with the given
     * parameters and an updated update number and the newest id from the
     * idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param name The user's name.
     * @param username The username for the user.
     * @param password The user's password.
     * @param parentStore The parent store.
     * @param email The email for the user.
     * @param phone The phone number belonging to the user.
     * @return The created user.
     */
    public User create(String name, String username, String password, String email, String phone, Store parentStore) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, parentStore, updateNumber);
        insert(u);
        return u;
    }

    /**
     * Use this for users without a parent. Creates a user with the given
     * parameters and an updated update number and the newest id from the
     * idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param name The user's name.
     * @param username The username for the user.
     * @param password The user's password.
     * @param email The email for the user.
     * @param phone The phone number belonging to the user.
     * @return The created user.
     */
    public User create(String name, String username, String password, String email, String phone) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextUserId();
        User u = new User(id, name, username, password, email, phone, updateNumber);
        insert(u);
        return u;
    }

    /**
     * Deletes a user and writes an entry in the deleteList for this object.
     *
     * @param target The user to be deleted.
     */
    @Override
    public void delete(User target) {
        int id = target.getId();
        UpdateNumberHandler.delete("User§" + id);
        super.delete(target);
    }

}
