
package model.permission;

import interfaces.AdminPermission;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class AdminPerm implements AdminPermission {
    
    private User executorUser;

    public AdminPerm(User executorUser) {
        this.executorUser = executorUser;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }
    
    

}
