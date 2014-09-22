package model.data;

import abstracts.DataDAO;
import abstracts.Register;
import control.StringTool;
import java.util.Date;

/**
 *
 * @author Patrick
 */
public class SaleRegister extends Register<Sale> {

    public SaleRegister(IDHandler idHandler, DataDAO<Sale> dao) {
        super(idHandler, dao);
    }

    /**
     * Creates a sale with the given parameters and an updated update number and the newest id from the idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param product The product to create a sale of.
     * @param price The price of the sale.
     * @param start A date object of the time the sale is supposed to start.
     * @param end A date object of the time the sale is supposed to end.
     * @param publish A date object of the time the sale is going to be visible for the clients.
     * @return The created sale
     */
    public Sale create(Product product, double price, Date start, Date end, Date publish) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextSaleId();
        String formattedName = StringTool.capitalizeWords(product.getName());
        String formattedDescription = StringTool.capitalizeSentences(product.getDescription());
        Sale s = new Sale(id, product, price, start, end, publish, updateNumber);
        insert(s);
        return s;
    }

    /**
     * Deletes a sale and writes an entry in the deleteList for this object.
     *
     * @param target The sale to be deleted.
     */
    @Override
    public void delete(Sale target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Sale§" + id);
        super.delete(target);
    }

}
