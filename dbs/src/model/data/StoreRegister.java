
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class StoreRegister extends Register<Store> {

    public StoreRegister(IDHandler idHandler, DataDAO<Store> dao) {
        super(idHandler,dao);
    }
    
    public Store create(String name, String address, String phone, Picture picture, Dealer parentDealer) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextStoreId();
        Store s = new Store(id, name, address, phone, picture, parentDealer, updateNumber);
        insert(s);
        return s;
    }

}
