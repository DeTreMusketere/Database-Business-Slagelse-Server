
package model.permission;

import interfaces.UserPermission;
import model.data.Dealer;
import model.data.Store;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class User_CreatePerm implements UserPermission {
    
    private User executorUser;
    private Dealer parentDealer;
    private Store parentStore;

    public User_CreatePerm(User executorUser, Dealer parentDealer) {
        this.executorUser = executorUser;
        this.parentDealer = parentDealer;
    }

    public User_CreatePerm(User executorUser, Store parentStore) {
        this.executorUser = executorUser;
        this.parentStore = parentStore;
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

    public Store getParentStore() {
        return parentStore;
    }

    public void setParentStore(Store parentStore) {
        this.parentStore = parentStore;
    }

}
