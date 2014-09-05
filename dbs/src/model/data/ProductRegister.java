package model.data;

import abstracts.DataDAO;
import abstracts.Register;
import control.StringTool;

/**
 *
 * @author Patrick
 */
public class ProductRegister extends Register<Product> {

    public ProductRegister(IDHandler idHandler, DataDAO<Product> dao) {
        super(idHandler, dao);
    }

    /**
     * Use this for products with a dealer parent. Creates a product with the
     * given parameters and an updated update number and the newest id from the
     * idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param name The product's name.
     * @param description Description of the product.
     * @param picture A picture to represent the product.
     * @param price The price of the product.
     * @param parentDealer The parent dealer.
     * @return The created product.
     */
    public Product create(String name, String description, Picture picture, double price, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextProductId();
        String formattedName = StringTool.capitalizeWords(name);
        String formattedDescription = StringTool.capitalizeSentences(description);
        Product p = new Product(id, formattedName, formattedDescription, picture, price, parentDealer, updateNumber);
        insert(p);
        return p;
    }

    /**
     * Use this for products with a storre parent. Creates a product with the
     * given parameters and an updated update number and the newest id from the
     * idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param name The product's name.
     * @param description Description of the product.
     * @param picture A picture to represent the product.
     * @param price The price of the product.
     * @param parentStore The parent store.
     * @return The created product.
     */
    public Product create(String name, String description, Picture picture, double price, Store parentStore) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextProductId();
        Product p = new Product(id, name, description, picture, price, parentStore, updateNumber);
        insert(p);
        p.setId(id);
        return p;
    }

    /**
     * Deletes a product and writes an entry in the deleteList for this object.
     *
     * @param target The product to be deleted.
     */
    @Override
    public void delete(Product target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Product§" + id);
        super.delete(target);
    }

}
