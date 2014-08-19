
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
    
    public Product create(String name, String description, Picture picture, double price, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        Product p = new Product(0, name, description, picture, price, parentDealer, updateNumber);
        int id = insert(p);
        p.setId(id);
        return p;
    }
    
    public Product create(String name, String description, Picture picture, double price, Store parentStore) {
        int updateNumber = UpdateNumberHandler.update();
        Product p = new Product(0, name, description, picture, price, parentStore, updateNumber);
        int id  = insert(p);
        p.setId(id);
        return p;
    }
    

}
