package model.data;

import abstracts.DataDAO;
import abstracts.Register;
import control.StringTool;

/**
 *
 * @author Patrick
 */
public class DealerRegister extends Register<Dealer> {

    public DealerRegister(IDHandler idHandler, DataDAO<Dealer> dao) {
        super(idHandler, dao);
    }

    /**
     * Creates a dealer object with the given parameters and an updated update
     * number and the newest id from the idHandler. Then inserts it into the
     * database and into the ArrayList.
     *
     * @param name The dealer's name.
     * @param description Description of the dealer.
     * @param phone Phone number for the dealer.
     * @param picture Picture to represent the dealer.
     * @return The created dealer.
     */
    public Dealer create(String name, String description, String phone, Picture picture) {
        int updateNumber = UpdateNumberHandler.update();
        int id = idHandler.nextDealerId();
        String convertedName = StringTool.convertÆØÅ(name);
        String convertedDescription = StringTool.convertÆØÅ(description);
        Dealer d = new Dealer(id, convertedName, convertedDescription, phone, picture, updateNumber);
        insert(d);
        return d;
    }

    /**
     * Deletes a dealer and writes an entry in the deleteList for this object.
     *
     * @param target The dealer to be deleted.
     */
    @Override
    public void delete(Dealer target) {
        int id = target.getId();
        UpdateNumberHandler.delete("Dealer§" + id);
        super.delete(target);
    }

}
