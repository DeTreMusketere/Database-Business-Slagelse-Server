package db.permission;




import model.data.Dealer;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class TestCore {
    
    public static User getTestUser(Dealer d) {
        User u = new User(0, "Thomas Nielsen", "tn", "testPass", "tn@tn.dk", "456789456", d);
        return u;
    }
    
    public static Dealer getTestDealer() {
        Dealer d = new Dealer(0, "Netto", "Test", "123456789", 0);
        return d;
    }

}
