
package model.data;

import abstracts.DataDAO;
import abstracts.Register;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class SaleRegister extends Register<Sale> {

    public SaleRegister(DataDAO<Sale> dao) {
        super(dao);
    }
    
    public void create(String name, String description, Picture picture, double price, Date start, Date end, Date publish, Dealer parentDealer) {
        Sale s = new Sale(0, name, description, picture, price, start, end, publish, parentDealer);
        int id = insert(s);
        s.setId(id);
    }
    
    public void create(String name, String description, Picture picture, double price, Date start, Date end, Date publish, Store parentStore) {
        Sale s = new Sale(0, name, description, picture, price, start, end, publish, parentStore);
        int id = insert(s);
        s.setId(id);
    }

}
