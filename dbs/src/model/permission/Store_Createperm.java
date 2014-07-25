
package model.permission;

import interfaces.StorePermission;
import model.data.Dealer;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class Store_CreatePerm implements StorePermission {
    
    private User executorUser;
    private Dealer parentDealer;

    public Store_CreatePerm(User executorUser, Dealer parentDealer) {
        this.executorUser = executorUser;
        this.parentDealer = parentDealer;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }

    public Dealer getParentDealer() {
        return parentDealer;
    }

    public void setParentDealer(Dealer parentDealer) {
        this.parentDealer = parentDealer;
    }

}
