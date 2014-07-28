
package db.permission;

import abstracts.PermissionDAO;
import abstracts.Register;
import db.DBTool;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.data.Dealer;
import model.data.DealerRegister;
import model.data.Sale;
import model.data.Store;
import model.data.StoreRegister;
import model.data.User;
import model.data.UserRegister;
import model.permission.Sale_CreatePerm;

/**
 *
 * @author Patrick
 */
public class Sale_CreatePermDAO extends PermissionDAO<Sale, Sale_CreatePerm> {
    
    private DealerRegister dealerRegister;
    private StoreRegister storeRegister;

    public Sale_CreatePermDAO(UserRegister userRegister, Register<Sale> register, DealerRegister dealerRegister, StoreRegister storeRegister) {
        super(userRegister, register);
        this.dealerRegister = dealerRegister;
        this.storeRegister = storeRegister;
        table = "create_sale_perm";
    }

    @Override
    public void insert(Sale_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();

            User executorUser = source.getExecutorUser();
            Dealer parentDealer = source.getParentDealer();
            Store parentStore = source.getParentStore();
            
            int executorUserId = executorUser.getId();
            int parentDealerId = 0;
            if(parentDealer != null) {
                parentDealerId = parentDealer.getId();
            }
            int parentStoreId = 0;
            if(parentStore != null) {
                parentStoreId = parentStore.getId();
            }

            String sql = "INSERT INTO "+table+" ('executor_user_id', 'parent_store_id', 'parent_dealer_id') VALUES("+executorUserId+", "+parentStoreId+", "+parentDealerId+");";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Sale_CreatePerm source) {
        try {
            Statement st = DBTool.getStatement();

            User executorUser = source.getExecutorUser();
            Dealer parentDealer = source.getParentDealer();
            Store parentStore = source.getParentStore();
            
            int executorUserId = executorUser.getId();
            int parentDealerId = 0;
            if(parentDealer != null) {
                parentDealerId = parentDealer.getId();
            }
            int parentStoreId = 0;
            if(parentStore != null) {
                parentStoreId = parentStore.getId();
            }

            String sql = "DELETE FROM "+table+" WHERE executor_user_id="+executorUserId+" AND parent_dealer_id="+parentDealerId+" AND parent_store_id="+parentStoreId+";";
            st.execute(sql);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public ArrayList<Sale_CreatePerm> select(User executor) {
        ArrayList<Sale_CreatePerm> sale_CreatePerms = new ArrayList<>();
        try {
            Statement st = DBTool.getStatement();
            
            int executorUserId = executor.getId();
            
            String sql = "SELECT * FROM "+table+" WHERE executor_user_id="+executorUserId+";";
            try (ResultSet rs = st.executeQuery(sql)) {
                while(rs.next()) {
                    int parentDealerId = rs.getInt("parent_dealer_id");
                    int parentStoreId = rs.getInt("parent_store_id");
                    
                    if(parentStoreId != 0) {
                        Store parentStore = storeRegister.get(parentStoreId);
                        Sale_CreatePerm sale_CreatePerm = new Sale_CreatePerm(executor, parentStore);
                        sale_CreatePerms.add(sale_CreatePerm);
                    } else {
                        Dealer parentDealer = dealerRegister.get(parentDealerId);
                        Sale_CreatePerm sale_CreatePerm = new Sale_CreatePerm(executor, parentDealer);
                        sale_CreatePerms.add(sale_CreatePerm);
                    }
                    
                }
            }
            return sale_CreatePerms;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
