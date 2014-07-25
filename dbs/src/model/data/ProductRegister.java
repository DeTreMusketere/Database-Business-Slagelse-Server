
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class ProductRegister extends Register<Product> {

    public ProductRegister(DataDAO<Product> dao) {
        super(dao);
    }
    
    public void create(String name, String description, int picture, double price, Dealer parentDealer) {
        Product p = new Product(0, name, description, picture, price, parentDealer);
        insert(p);
    }
    
    public void create(String name, String description, int picture, double price, Store parentStore) {
        Product p = new Product(0, name, description, picture, price, parentStore);
        insert(p);
    }
    

}
