package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class ProductRegister extends Register<Product> {

    public ProductRegister(IDHandler idHandler, DataDAO<Product> dao) {
        super(idHandler, dao);
    }

    public Product create(String name, String description, Picture picture, double price, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextProductId();
        Product p = new Product(id, name, description, picture, price, parentDealer, updateNumber);
        insert(p);
        return p;
    }

    public Product create(String name, String description, Picture picture, double price, Store parentStore) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextProductId();
        Product p = new Product(id, name, description, picture, price, parentStore, updateNumber);
        insert(p);
        p.setId(id);
        return p;
    }

    @Override
    public void delete(Product target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Product§" + id);
        super.delete(target);
    }

}
