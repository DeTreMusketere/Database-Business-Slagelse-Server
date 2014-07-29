
package model.permission;

import interfaces.UserPermission;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class User_ReadPerm implements UserPermission {
    
    private User targetUser;
    private User executorUser;

    public User_ReadPerm(User targetUser, User executorUser) {
        this.targetUser = targetUser;
        this.executorUser = executorUser;
    }

    public User getTargetUser() {
        return targetUser;
    }

    public void setTargetUser(User targetUser) {
        this.targetUser = targetUser;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }
    
    @Override
    public String toString(){
        return "target: " + targetUser.getName() + " executor: " + executorUser.getName();
    }
    

}
