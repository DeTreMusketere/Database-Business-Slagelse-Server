package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class StoreRegister extends Register<Store> {

    public StoreRegister(IDHandler idHandler, DataDAO<Store> dao) {
        super(idHandler, dao);
    }

    /**
     * Creates a store with the given parameters and an updated update number
     * and the newest id from the idHandler. Then inserts it into the database
     * and into the ArrayList.
     *
     * @param name The name of the store.
     * @param address The address of the store.
     * @param phone The phone number associated with the store.
     * @param picture A picture to represent the store.
     * @param parentDealer The parent dealer.
     * @return The created store.
     */
    public Store create(String name, String address, String phone, Picture picture, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextStoreId();
        Store s = new Store(id, name, address, phone, picture, parentDealer, updateNumber);
        insert(s);
        return s;
    }

    /**
     * Deletes a store and writes an entry in the deleteList for this object.
     *
     * @param target The store to be deleted.
     */
    @Override
    public void delete(Store target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Store§" + id);
        super.delete(target);
    }
}
