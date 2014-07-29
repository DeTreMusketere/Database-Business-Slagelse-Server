
package model.permission;

import interfaces.DealerPermission;
import model.data.Dealer;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class Dealer_AdminPerm implements DealerPermission {
    
    private Dealer targetDealer;
    private User executorUser;

    public Dealer_AdminPerm(Dealer targetDealer, User executorUser) {
        this.targetDealer = targetDealer;
        this.executorUser = executorUser;
    }

    public Dealer getTargetDealer() {
        return targetDealer;
    }

    public void setTargetDealer(Dealer targetDealer) {
        this.targetDealer = targetDealer;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }

    @Override
    public String toString() {
        return "target: " + targetDealer.getName() + " executor: " + executorUser.getName();
    }

}
