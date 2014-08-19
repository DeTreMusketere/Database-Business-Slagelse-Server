package control;

import db.DBTool;
import db.permission.*;
import java.util.ArrayList;
import java.util.Iterator;
import model.data.Dealer;
import model.data.Product;
import model.data.Sale;
import model.data.Store;
import model.data.User;
import model.permission.*;

/**
 *
 * @author Patrick
 */
public class PermissionHandler {

    private final AdminPermDAO adminPermDAO;

    private final Dealer_AdminPermDAO dealer_AdminPermDAO;
    private final Dealer_CreatePermDAO dealer_CreatePermDAO;
    private final Dealer_DeletePermDAO dealer_DeletePermDAO;
    private final Dealer_ReadPermDAO dealer_ReadPermDAO;
    private final Dealer_UpdatePermDAO dealer_UpdatePermDAO;

    private final Product_CreatePermDAO product_CreatePermDAO;
    private final Product_DeletePermDAO product_DeletePermDAO;
    private final Product_ReadPermDAO product_ReadPermDAO;
    private final Product_UpdatePermDAO product_UpdatePermDAO;

    private final Sale_CreatePermDAO sale_CreatePermDAO;
    private final Sale_DeletePermDAO sale_DeletePermDAO;
    private final Sale_ReadPermDAO sale_ReadPermDAO;
    private final Sale_UpdatePermDAO sale_UpdatePermDAO;

    private final Store_AdminPermDAO store_AdminPermDAO;
    private final Store_CreatePermDAO store_CreatePermDAO;
    private final Store_DeletePermDAO store_DeletePermDAO;
    private final Store_ReadPermDAO store_ReadPermDAO;
    private final Store_UpdatePermDAO store_UpdatePermDAO;

    private final User_CreatePermDAO user_CreatePermDAO;
    private final User_DeletePermDAO user_DeletePermDAO;
    private final User_ReadPermDAO user_ReadPermDAO;
    private final User_UpdatePermDAO user_UpdatePermDAO;

    public PermissionHandler(AdminPermDAO adminPermDAO, Dealer_AdminPermDAO dealer_AdminPermDAO, Dealer_CreatePermDAO dealer_CreatePermDAO, Dealer_DeletePermDAO dealer_DeletePermDAO, Dealer_ReadPermDAO dealer_ReadPermDAO, Dealer_UpdatePermDAO dealer_UpdatePermDAO, Product_CreatePermDAO product_CreatePermDAO, Product_DeletePermDAO product_DeletePermDAO, Product_ReadPermDAO product_ReadPermDAO, Product_UpdatePermDAO product_UpdatePermDAO, Sale_CreatePermDAO sale_CreatePermDAO, Sale_DeletePermDAO sale_DeletePermDAO, Sale_ReadPermDAO sale_ReadPermDAO, Sale_UpdatePermDAO sale_UpdatePermDAO, Store_AdminPermDAO store_AdminPermDAO, Store_CreatePermDAO store_CreatePermDAO, Store_DeletePermDAO store_DeletePermDAO, Store_ReadPermDAO store_ReadPermDAO, Store_UpdatePermDAO store_UpdatePermDAO, User_CreatePermDAO user_CreatePermDAO, User_DeletePermDAO user_DeletePermDAO, User_ReadPermDAO user_ReadPermDAO, User_UpdatePermDAO user_UpdatePermDAO) {
        this.adminPermDAO = adminPermDAO;
        this.dealer_AdminPermDAO = dealer_AdminPermDAO;
        this.dealer_CreatePermDAO = dealer_CreatePermDAO;
        this.dealer_DeletePermDAO = dealer_DeletePermDAO;
        this.dealer_ReadPermDAO = dealer_ReadPermDAO;
        this.dealer_UpdatePermDAO = dealer_UpdatePermDAO;
        this.product_CreatePermDAO = product_CreatePermDAO;
        this.product_DeletePermDAO = product_DeletePermDAO;
        this.product_ReadPermDAO = product_ReadPermDAO;
        this.product_UpdatePermDAO = product_UpdatePermDAO;
        this.sale_CreatePermDAO = sale_CreatePermDAO;
        this.sale_DeletePermDAO = sale_DeletePermDAO;
        this.sale_ReadPermDAO = sale_ReadPermDAO;
        this.sale_UpdatePermDAO = sale_UpdatePermDAO;
        this.store_AdminPermDAO = store_AdminPermDAO;
        this.store_CreatePermDAO = store_CreatePermDAO;
        this.store_DeletePermDAO = store_DeletePermDAO;
        this.store_ReadPermDAO = store_ReadPermDAO;
        this.store_UpdatePermDAO = store_UpdatePermDAO;
        this.user_CreatePermDAO = user_CreatePermDAO;
        this.user_DeletePermDAO = user_DeletePermDAO;
        this.user_ReadPermDAO = user_ReadPermDAO;
        this.user_UpdatePermDAO = user_UpdatePermDAO;
    }

    /**
     * Sets admin permission for a user
     *
     * @param executorUser
     * @param value
     */
    public void setAdminPerm(User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        AdminPerm perm = adminPermDAO.select2(executorUser);
        if (perm != null) {
            exist = true;
        } else {
            perm = new AdminPerm(executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                adminPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                adminPermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets admin permission for a user relative to a dealer
     *
     * @param targetDealer
     * @param executorUser
     * @param value
     */
    public void setDealer_AdminPerm(Dealer targetDealer, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Dealer_AdminPerm perm = null;
        ArrayList<Dealer_AdminPerm> perms = dealer_AdminPermDAO.select(executorUser);
        for (Dealer_AdminPerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetDealer() == targetDealer) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Dealer_AdminPerm(targetDealer, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                dealer_AdminPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                dealer_AdminPermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets dealer create permission for a user
     *
     * @param executorUser
     * @param value
     */
    public void setDealer_CreatePerm(User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Dealer_CreatePerm perm = dealer_CreatePermDAO.select2(executorUser);
        if (perm != null) {
            exist = true;
        } else {
            perm = new Dealer_CreatePerm(executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                dealer_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                dealer_CreatePermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets read permission for a user relative to a dealer
     *
     * @param targetDealer
     * @param executorUser
     * @param value
     */
    public void setDealer_ReadPerm(Dealer targetDealer, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Dealer_ReadPerm perm = null;
        ArrayList<Dealer_ReadPerm> perms = dealer_ReadPermDAO.select(executorUser);
        for (Dealer_ReadPerm p : perms) {
            if (p.getTargetDealer() == targetDealer && p.getExecutorUser() == executorUser) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Dealer_ReadPerm(targetDealer, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                dealer_ReadPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                dealer_ReadPermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets update permission for a user relative to a dealer
     *
     * @param targetDealer
     * @param executorUser
     * @param value
     */
    public void setDealer_UpdatePerm(Dealer targetDealer, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Dealer_UpdatePerm perm = null;
        ArrayList<Dealer_UpdatePerm> perms = dealer_UpdatePermDAO.select(executorUser);
        for (Dealer_UpdatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetDealer() == targetDealer) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Dealer_UpdatePerm(targetDealer, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                dealer_UpdatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                dealer_UpdatePermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets delete permission for a user relative to a dealer
     *
     * @param targetDealer
     * @param executorUser
     * @param value
     */
    public void setDealer_DeletePerm(Dealer targetDealer, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Dealer_DeletePerm perm = null;
        ArrayList<Dealer_DeletePerm> perms = dealer_DeletePermDAO.select(executorUser);
        for (Dealer_DeletePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetDealer() == targetDealer) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Dealer_DeletePerm(targetDealer, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                dealer_DeletePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                dealer_DeletePermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets admin permission for a user relative to a store
     *
     * @param targetStore
     * @param executorUser
     * @param value
     */
    public void setStore_AdminPerm(Store targetStore, User executorUser, boolean value) {
        boolean exist = false;
        Store_AdminPerm perm = null;
        ArrayList<Store_AdminPerm> perms = store_AdminPermDAO.select(executorUser);
        for (Store_AdminPerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetStore() == targetStore) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Store_AdminPerm(targetStore, executorUser);
        }

        if (value) {
            if (!exist) {
                store_AdminPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                store_AdminPermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets create permission for a user relative to a store
     *
     * @param executorUser
     * @param parentDealer
     * @param value
     */
    public void setStore_CreatePerm(User executorUser, Dealer parentDealer, boolean value) {
        boolean exist = false;
        Store_CreatePerm perm = null;
        ArrayList<Store_CreatePerm> perms = store_CreatePermDAO.select(executorUser);
        for (Store_CreatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getParentDealer() == parentDealer) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Store_CreatePerm(executorUser, parentDealer);
        }

        if (value) {
            if (!exist) {
                store_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                store_CreatePermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets read permission for a user relative to a store
     *
     * @param targetStore
     * @param executorUser
     * @param value
     */
    public void setStore_ReadPerm(Store targetStore, User executorUser, boolean value) {
        boolean exist = false;
        Store_ReadPerm perm = null;
        ArrayList<Store_ReadPerm> perms = store_ReadPermDAO.select(executorUser);
        for (Store_ReadPerm p : perms) {
            if (p.getTargetStore() == targetStore && p.getExecutorUser() == executorUser) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Store_ReadPerm(targetStore, executorUser);
        }

        if (value) {
            if (!exist) {
                store_ReadPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                store_ReadPermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets update permission for a user relative to a store
     *
     * @param targetStore
     * @param executorUser
     * @param value
     */
    public void setStore_UpdatePerm(Store targetStore, User executorUser, boolean value) {
        boolean exist = false;
        Store_UpdatePerm perm = null;
        ArrayList<Store_UpdatePerm> perms = store_UpdatePermDAO.select(executorUser);
        for (Store_UpdatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetStore() == targetStore) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Store_UpdatePerm(targetStore, executorUser);
        }

        if (value) {
            if (!exist) {
                store_UpdatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                store_UpdatePermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets delete permission for a user relative to a store
     *
     * @param targetStore
     * @param executorUser
     * @param value
     */
    public void setStore_DeletePerm(Store targetStore, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Store_DeletePerm perm = null;
        ArrayList<Store_DeletePerm> perms = store_DeletePermDAO.select(executorUser);
        for (Store_DeletePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetStore() == targetStore) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Store_DeletePerm(targetStore, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                store_DeletePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                store_DeletePermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets product create permission for a user relative to a dealer
     *
     * @param executorUser
     * @param parentDealer
     * @param value
     */
    public void setProduct_CreatePerm(User executorUser, Dealer parentDealer, boolean value) {
        boolean exist = false;
        Product_CreatePerm perm = null;
        ArrayList<Product_CreatePerm> perms = product_CreatePermDAO.select(executorUser);
        for (Product_CreatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getParentDealer() == parentDealer) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Product_CreatePerm(executorUser, parentDealer);
        }

        if (value) {
            if (!exist) {
                product_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                product_CreatePermDAO.delete(perm);
            }
        }
    }

    /**
     * Sets product create permission for a user relative to a store
     *
     * @param executorUser
     * @param parentStore
     * @param value
     */
    public void setProduct_CreatePerm(User executorUser, Store parentStore, boolean value) {
        boolean exist = false;
        Product_CreatePerm perm = null;
        ArrayList<Product_CreatePerm> perms = product_CreatePermDAO.select(executorUser);
        for (Product_CreatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getParentStore() == parentStore) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Product_CreatePerm(executorUser, parentStore);
        }

        if (value) {
            if (!exist) {
                product_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                product_CreatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets read permission for a user relative to a product
     * @param targetProduct
     * @param executorUser
     * @param value 
     */
    public void setProduct_ReadPerm(Product targetProduct, User executorUser, boolean value) {
        boolean exist = false;
        Product_ReadPerm perm = null;
        ArrayList<Product_ReadPerm> perms = product_ReadPermDAO.select(executorUser);
        for (Product_ReadPerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetProduct() == targetProduct) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Product_ReadPerm(targetProduct, executorUser);
        }

        if (value) {
            if (!exist) {
                product_ReadPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                product_ReadPermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets update permission for a user relative to a product
     * @param targetProduct
     * @param executorUser
     * @param value 
     */
    public void setProduct_UpdatePerm(Product targetProduct, User executorUser, boolean value) {
        boolean exist = false;
        Product_UpdatePerm perm = null;
        ArrayList<Product_UpdatePerm> perms = product_UpdatePermDAO.select(executorUser);
        for (Product_UpdatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetProduct() == targetProduct) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Product_UpdatePerm(targetProduct, executorUser);
        }

        if (value) {
            if (!exist) {
                product_UpdatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                product_UpdatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets delete permission for a user relative to a product
     * @param targetProduct
     * @param executorUser
     * @param value 
     */
    public void setProduct_DeletePerm(Product targetProduct, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Product_DeletePerm perm = null;
        ArrayList<Product_DeletePerm> perms = product_DeletePermDAO.select(executorUser);
        for (Product_DeletePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetProduct() == targetProduct) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Product_DeletePerm(targetProduct, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                product_DeletePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                product_DeletePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets sale create permission for a user relative to a dealer
     * @param executorUser
     * @param parentDealer
     * @param value 
     */
    public void setSale_CreatePerm(User executorUser, Dealer parentDealer, boolean value) {
        boolean exist = false;
        Sale_CreatePerm perm = null;
        ArrayList<Sale_CreatePerm> perms = sale_CreatePermDAO.select(executorUser);
        for (Sale_CreatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getParentDealer() == parentDealer) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Sale_CreatePerm(executorUser, parentDealer);
        }

        if (value) {
            if (!exist) {
                sale_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                sale_CreatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets sale create permission for a user relative to a store
     * @param executorUser
     * @param parentStore
     * @param value 
     */
    public void setSale_CreatePerm(User executorUser, Store parentStore, boolean value) {
        boolean exist = false;
        Sale_CreatePerm perm = null;
        ArrayList<Sale_CreatePerm> perms = sale_CreatePermDAO.select(executorUser);
        for (Sale_CreatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getParentStore() == parentStore) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Sale_CreatePerm(executorUser, parentStore);
        }

        if (value) {
            if (!exist) {
                sale_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                sale_CreatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets read permission for a user relative to a sale
     * @param targetSale
     * @param executorUser
     * @param value 
     */
    public void setSale_ReadPerm(Sale targetSale, User executorUser, boolean value) {
        boolean exist = false;
        Sale_ReadPerm perm = null;
        ArrayList<Sale_ReadPerm> perms = sale_ReadPermDAO.select(executorUser);
        for (Sale_ReadPerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetSale() == targetSale) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Sale_ReadPerm(targetSale, executorUser);
        }

        if (value) {
            if (!exist) {
                sale_ReadPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                sale_ReadPermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets update permission for a user relative to a sale
     * @param targetSale
     * @param executorUser
     * @param value 
     */
    public void setSale_UpdatePerm(Sale targetSale, User executorUser, boolean value) {
        boolean exist = false;
        Sale_UpdatePerm perm = null;
        ArrayList<Sale_UpdatePerm> perms = sale_UpdatePermDAO.select(executorUser);
        for (Sale_UpdatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetSale() == targetSale) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Sale_UpdatePerm(targetSale, executorUser);
        }

        if (value) {
            if (!exist) {
                sale_UpdatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                sale_UpdatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets delete permission for a user relative to a sale
     * @param targetSale
     * @param executorUser
     * @param value 
     */
    public void setSale_DeletePerm(Sale targetSale, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        Sale_DeletePerm perm = null;
        ArrayList<Sale_DeletePerm> perms = sale_DeletePermDAO.select(executorUser);
        for (Sale_DeletePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetSale() == targetSale) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new Sale_DeletePerm(targetSale, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                sale_DeletePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                sale_DeletePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets user create permission for a user relative to a dealer
     * @param executorUser
     * @param parentDealer
     * @param value 
     */
    public void setUser_CreatePerm(User executorUser, Dealer parentDealer, boolean value) {
        boolean exist = false;
        User_CreatePerm perm = null;
        ArrayList<User_CreatePerm> perms = user_CreatePermDAO.select(executorUser);
        for (User_CreatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getParentDealer() == parentDealer) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new User_CreatePerm(executorUser, parentDealer);
        }

        if (value) {
            if (!exist) {
                user_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                user_CreatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets user create permission for a user relative to a store
     * @param executorUser
     * @param parentStore
     * @param value 
     */
    public void setUser_CreatePerm(User executorUser, Store parentStore, boolean value) {
        boolean exist = false;
        User_CreatePerm perm = null;
        ArrayList<User_CreatePerm> perms = user_CreatePermDAO.select(executorUser);
        for (User_CreatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getParentStore() == parentStore) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new User_CreatePerm(executorUser, parentStore);
        }

        if (value) {
            if (!exist) {
                user_CreatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                user_CreatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets read permission for a user relative to a user
     * @param targetUser
     * @param executorUser
     * @param value 
     */
    public void setSale_ReadPerm(User targetUser, User executorUser, boolean value) {
        boolean exist = false;
        User_ReadPerm perm = null;
        ArrayList<User_ReadPerm> perms = user_ReadPermDAO.select(executorUser);
        for (User_ReadPerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetUser() == targetUser) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new User_ReadPerm(targetUser, executorUser);
        }

        if (value) {
            if (!exist) {
                user_ReadPermDAO.insert(perm);
            }
        } else {
            if (exist) {
                user_ReadPermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets update permission for a user relative to a user
     * @param targetUser
     * @param executorUser
     * @param value 
     */
    public void setUser_UpdatePerm(User targetUser, User executorUser, boolean value) {
        boolean exist = false;
        User_UpdatePerm perm = null;
        ArrayList<User_UpdatePerm> perms = user_UpdatePermDAO.select(executorUser);
        for (User_UpdatePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetUser() == targetUser) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new User_UpdatePerm(targetUser, executorUser);
        }

        if (value) {
            if (!exist) {
                user_UpdatePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                user_UpdatePermDAO.delete(perm);
            }
        }
    }
    
    /**
     * Sets delete permission for a user relative to a sale
     * @param targetUser
     * @param executorUser
     * @param value 
     */
    public void setUser_DeletePerm(User targetUser, User executorUser, boolean value) {
        // Does it exist?
        boolean exist = false;
        User_DeletePerm perm = null;
        ArrayList<User_DeletePerm> perms = user_DeletePermDAO.select(executorUser);
        for (User_DeletePerm p : perms) {
            if (p.getExecutorUser() == executorUser && p.getTargetUser() == targetUser) {
                exist = true;
                perm = p;
                break;
            }
        }
        if (!exist) {
            perm = new User_DeletePerm(targetUser, executorUser);
        }

        // Do the set
        if (value) {
            if (!exist) {
                user_DeletePermDAO.insert(perm);
            }
        } else {
            if (exist) {
                user_DeletePermDAO.delete(perm);
            }
        }
    }

    /**
     * Constructs a UserPermissionSet with all permissions for a user
     *
     * @param u
     * @return UserPermissionSet
     */
    public UserPermissionSet constructUserPermissionSet(User u) {
        UserPermissionSet ups = new UserPermissionSet();

        //Admin
        AdminPerm adminperm = adminPermDAO.select2(u);

        //Dealer
        ArrayList<Dealer_AdminPerm> dealer_AdminPerm = dealer_AdminPermDAO.select(u);
        Dealer_CreatePerm dealer_CreatePerm = dealer_CreatePermDAO.select2(u);
        ArrayList<Dealer_ReadPerm> dealer_ReadPerms = dealer_ReadPermDAO.select(u);
        ArrayList<Dealer_UpdatePerm> dealer_UpdatePerms = dealer_UpdatePermDAO.select(u);
        ArrayList<Dealer_DeletePerm> dealer_DeletePerms = dealer_DeletePermDAO.select(u);

        //Store
        ArrayList<Store_AdminPerm> store_AdminPerms = store_AdminPermDAO.select(u);
        ArrayList<Store_CreatePerm> store_CreatePerms = store_CreatePermDAO.select(u);
        ArrayList<Store_ReadPerm> store_ReadPerms = store_ReadPermDAO.select(u);
        ArrayList<Store_UpdatePerm> store_UpdatePerms = store_UpdatePermDAO.select(u);
        ArrayList<Store_DeletePerm> store_DeletePerms = store_DeletePermDAO.select(u);

        //User
        ArrayList<User_CreatePerm> user_CreatePerms = user_CreatePermDAO.select(u);
        ArrayList<User_ReadPerm> user_ReadPerms = user_ReadPermDAO.select(u);
        ArrayList<User_UpdatePerm> user_UpdatePerms = user_UpdatePermDAO.select(u);
        ArrayList<User_DeletePerm> user_DeletePerms = user_DeletePermDAO.select(u);

        //product
        ArrayList<Product_CreatePerm> product_CreatePerms = product_CreatePermDAO.select(u);
        ArrayList<Product_ReadPerm> product_ReadPerms = product_ReadPermDAO.select(u);
        ArrayList<Product_UpdatePerm> product_UpdatePerms = product_UpdatePermDAO.select(u);
        ArrayList<Product_DeletePerm> product_DeletePerms = product_DeletePermDAO.select(u);

        //Sale
        ArrayList<Sale_CreatePerm> sale_CreatePerms = sale_CreatePermDAO.select(u);
        ArrayList<Sale_ReadPerm> sale_ReadPerms = sale_ReadPermDAO.select(u);
        ArrayList<Sale_UpdatePerm> sale_UpdatePerms = sale_UpdatePermDAO.select(u);
        ArrayList<Sale_DeletePerm> sale_DeletePerms = sale_DeletePermDAO.select(u);

        DBTool.close();
        return ups;
    }

}
