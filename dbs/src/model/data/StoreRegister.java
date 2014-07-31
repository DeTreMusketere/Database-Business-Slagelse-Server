
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class StoreRegister extends Register<Store> {

    public StoreRegister(DataDAO<Store> dao) {
        super(dao);
    }
    
    public void create(String name, String address, String phone, Picture picture, Dealer parentDealer) {
        Store s = new Store(0, name, address, phone, picture, parentDealer);
        int id = insert(s);
        s.setId(id);
    }

}
