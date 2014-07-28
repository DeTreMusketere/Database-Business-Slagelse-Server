package abstracts;

import java.util.ArrayList;
import model.data.User;
import model.data.UserRegister;

/**
 *
 * @author Patrick
 * @param <DATATYPE>
 * @param <PERMISSIONTYPE>
 */
public abstract class PermissionDAO<DATATYPE, PERMISSIONTYPE> {

    protected final UserRegister userRegister;
    protected final Register<DATATYPE> register;
    protected String table;

    public PermissionDAO(UserRegister userRegister, Register<DATATYPE> register) {
        this.userRegister = userRegister;
        this.register = register;
    }

    /**
     * Inserts a source object
     *
     * @param source
     */
    public abstract void insert(PERMISSIONTYPE source);

    /**
     * Deletes a source object
     *
     * @param source
     */
    public abstract void delete(PERMISSIONTYPE source);

    /**
     * Selects all PERMISSIONTYPE objects based on executorUser
     *
     * @param executor
     * @return ArrayList<PERMISSIONTYPE>
     */
    public abstract ArrayList<PERMISSIONTYPE> select(User executor);

}
