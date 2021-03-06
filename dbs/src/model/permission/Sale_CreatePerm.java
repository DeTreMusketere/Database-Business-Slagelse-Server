
package model.permission;

import interfaces.SalePermission;
import model.data.Dealer;
import model.data.Store;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class Sale_CreatePerm implements SalePermission {
    
    private User executorUser;
    private Dealer parentDealer;
    private Store parentStore;

    public Sale_CreatePerm(User executorUser, Dealer parentDealer) {
        this.executorUser = executorUser;
        this.parentDealer = parentDealer;
    }

    public Sale_CreatePerm(User executorUser, Store parentStore) {
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
    
    @Override
    public String toString() {
        String s =  "executor: " + executorUser.getName();
        if(parentDealer != null) {
            s += " parent dealer: " + parentDealer.getName();
        }
        if(parentStore != null) {
            s += " parent store: " + parentStore.getName();
        }
        
        return s;
    }

}
