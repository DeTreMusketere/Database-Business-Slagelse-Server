
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class SaleRegister extends Register<Sale> {

    public SaleRegister(DataDAO<Sale> dao) {
        super(dao);
    }
    
    public void create(String name, String description, Picture picture, double price, Dealer parentDealer) {
        Sale s = new Sale(0, name, description, picture, price, parentDealer);
        int id = insert(s);
        s.setId(id);
    }
    
    public void create(String name, String description, Picture picture, double price, Store parentStore) {
        Sale s = new Sale(0, name, description, picture, price, parentStore);
        int id = insert(s);
        s.setId(id);
    }

}
