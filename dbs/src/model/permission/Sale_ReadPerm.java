
package model.permission;

import interfaces.SalePermission;
import model.data.Sale;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class Sale_ReadPerm implements SalePermission {
    
    private Sale targetSale;
    private User executorUser;

    public Sale_ReadPerm(Sale targetSale, User executorUser) {
        this.targetSale = targetSale;
        this.executorUser = executorUser;
    }

    public Sale getTargetSale() {
        return targetSale;
    }

    public void setTargetSale(Sale targetSale) {
        this.targetSale = targetSale;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }

}
