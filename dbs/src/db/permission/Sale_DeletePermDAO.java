
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import java.util.ArrayList;
import model.data.Sale;
import model.data.User;
import model.data.UserRegister;
import model.permission.Sale_DeletePerm;

/**
 *
 * @author Patrick
 */
public class Sale_DeletePermDAO extends PermissionDAO<Sale, Sale_DeletePerm> {

    public Sale_DeletePermDAO(UserRegister userRegister, Register<Sale> register) {
        super(userRegister, register);
    }

    @Override
    public void insert(Sale_DeletePerm source) {
    }

    @Override
    public void delete(Sale_DeletePerm source) {
    }

    @Override
    public Sale_DeletePerm select(User executor, Sale target) {
        return null;
    }

    @Override
    public ArrayList<Sale_DeletePerm> select(User executor) {
        return null;
    }

}
