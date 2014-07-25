
package model.permission;

import interfaces.ProductPermission;
import model.data.Product;
import model.data.User;

/**
 *
 * @author Patrick
 */
public class Product_UpdatePerm implements ProductPermission {
    
    private Product targetProduct;
    private User executorUser;

    public Product_UpdatePerm(Product targetProduct, User executorUser) {
        this.targetProduct = targetProduct;
        this.executorUser = executorUser;
    }

    public Product getTargetProduct() {
        return targetProduct;
    }

    public void setTargetProduct(Product targetProduct) {
        this.targetProduct = targetProduct;
    }

    public User getExecutorUser() {
        return executorUser;
    }

    public void setExecutorUser(User executorUser) {
        this.executorUser = executorUser;
    }

}
