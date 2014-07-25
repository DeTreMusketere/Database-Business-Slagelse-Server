
package model.permission;

import interfaces.DealerPermission;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class Dealer_CreatePerm implements DealerPermission {
    
    private User executorUser;

    public Dealer_CreatePerm(User executorUser) {
        this.executorUser = executorUser;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }

}
