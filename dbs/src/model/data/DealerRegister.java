package model.data;

import abstracts.DataDAO;
import abstracts.Register;

/**
 *
 * @author Patrick
 */
public class DealerRegister extends Register<Dealer> {

    public DealerRegister(IDHandler idHandler, DataDAO<Dealer> dao) {
        super(idHandler, dao);
    }

    public Dealer create(String name, String description, String phone, Picture picture) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextDealerId();
        Dealer d = new Dealer(id, name, description, phone, picture, updateNumber);
        insert(d);
        return d;
    }

    @Override
    public void update(Dealer source, Dealer target) {
        int updateNumber = UpdateNumberHandler.update();
        source.setUpdateNumber(updateNumber);
        super.update(source, target);
    }

    @Override
    public void delete(Dealer target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Dealer§" + id);
        super.delete(target);
    }

}
