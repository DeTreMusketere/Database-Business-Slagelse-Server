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
     * Use this for sales with a dealer parent. Creates a sale with the given
     * parameters and an updated update number and the newest id from the
     * idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param name The sale's name.
     * @param description Description of the sale.
     * @param picture A picture to represent the sale.
     * @param price The price of the sale.
     * @param start A date object of the time the sale is supposed to start.
     * @param end A date object of the time the sale is supposed to end.
     * @param publish A date object of the time the sale is going to be visible
     * for the clients.
     * @param parentDealer The parent dealer.
     * @return The created sale.
     */
    public Sale create(String name, String description, Picture picture, double price, Date start, Date end, Date publish, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextSaleId();
        String formattedName = StringTool.capitalizeWords(name);
        String formattedDescription = StringTool.capitalizeSentences(description);
        Sale s = new Sale(id, formattedName, formattedDescription, picture, price, start, end, publish, parentDealer, updateNumber);
        insert(s);
        return s;
    }

    /**
     * Use this for sales with a store parent. Creates a sale with the given
     * parameters and an updated update number and the newest id from the
     * idHandler. Then inserts it into the database and into the ArrayList.
     *
     * @param name The sale's name.
     * @param description Description of the sale.
     * @param picture A picture to represent the sale.
     * @param price The price of the sale.
     * @param start A date object of the time the sale is supposed to start.
     * @param end A date object of the time the sale is supposed to end.
     * @param publish A date object of the time the sale is going to be visible
     * for the clients.
     * @param parentStore The parent store.
     * @return The created sale.
     */
    public Sale create(String name, String description, Picture picture, double price, Date start, Date end, Date publish, Store parentStore) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextSaleId();
        Sale s = new Sale(id, name, description, picture, price, start, end, publish, parentStore, updateNumber);
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
