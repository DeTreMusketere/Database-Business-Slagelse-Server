
package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class DealerRegister extends Register<Dealer> {

    public DealerRegister(IDHandler idHandler, DataDAO<Dealer> dao) {
        super(idHandler,dao);
    }
    
    public Dealer create(String name, String description, String phone, Picture picture) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextDealerId();
        Dealer d = new Dealer(id, name, description, phone, picture, updateNumber);
        insert(d);
        return d;
    }


}
