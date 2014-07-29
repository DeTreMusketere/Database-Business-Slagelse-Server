
package model.permission;

import interfaces.StorePermission;
import model.data.Store;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class Store_UpdatePerm implements StorePermission {
    
    private Store targetStore;
    private User executorUser;

    public Store_UpdatePerm(Store targetStore, User executorUser) {
        this.targetStore = targetStore;
        this.executorUser = executorUser;
    }

    public Store getTargetStore() {
        return targetStore;
    }

    public void setTargetStore(Store targetStore) {
        this.targetStore = targetStore;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }
    
    @Override
    public String toString(){
        return "target: " + targetStore.getName() + " executor: " + executorUser.getName();
    }

}
