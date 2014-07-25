
package control;

import db.DBTool;
import java.util.ArrayList;
import model.data.User;
import model.permission.UserPermissionSet;
import model.permission.AdminPerm;
import model.permission.Dealer_AdminPerm;
import model.permission.Dealer_CreatePerm;
import model.permission.Dealer_DeletePerm;
import model.permission.Dealer_ReadPerm;
import model.permission.Dealer_UpdatePerm;
import model.permission.Product_CreatePerm;
import model.permission.Product_DeletePerm;
import model.permission.Product_ReadPerm;
import model.permission.Product_UpdatePerm;
import model.permission.Sale_CreatePerm;
import model.permission.Sale_DeletePerm;
import model.permission.Sale_ReadPerm;
import model.permission.Sale_UpdatePerm;
import model.permission.Store_AdminPerm;
import model.permission.Store_CreatePerm;
import model.permission.Store_DeletePerm;
import model.permission.Store_ReadPerm;
import model.permission.Store_UpdatePerm;
import model.permission.User_CreatePerm;
import model.permission.User_DeletePerm;
import model.permission.User_ReadPerm;
import model.permission.User_UpdatePerm;

/**
 *
 * @author Patrick
 */
public class PermissionHandler {
    
    public PermissionHandler() {
        
    }
    
    public UserPermissionSet constructUserPermissionSet(User u) {
        UserPermissionSet ups = new UserPermissionSet();
        
        //Admin
        AdminPerm adminperm;
        
        //Dealer
        ArrayList<Dealer_AdminPerm> dealer_AdminPerm;
        Dealer_CreatePerm dealer_CreatePerm;
        ArrayList<Dealer_ReadPerm> dealer_ReadPerms;
        ArrayList<Dealer_UpdatePerm> dealer_UpdatePerms;
        ArrayList<Dealer_DeletePerm> dealer_DeletePerms;
        
        //Store
        ArrayList<Store_AdminPerm> store_AdminPerms;
        ArrayList<Store_CreatePerm> store_CreatePerms;
        ArrayList<Store_ReadPerm> store_ReadPerms;
        ArrayList<Store_UpdatePerm> store_UpdatePerms;
        ArrayList<Store_DeletePerm> store_DeletePerms;
        
        //User
        ArrayList<User_CreatePerm> user_CreatePerms;
        ArrayList<User_ReadPerm> user_ReadPerms;
        ArrayList<User_UpdatePerm> user_UpdatePerms;
        ArrayList<User_DeletePerm> user_DeletePerms;
        
        //product
        ArrayList<Product_CreatePerm> product_CreatePerms;
        ArrayList<Product_ReadPerm> product_ReadPerms;
        ArrayList<Product_UpdatePerm> product_UpdatePerms;
        ArrayList<Product_DeletePerm> product_DeletePerms;
        
        //Sale
        ArrayList<Sale_CreatePerm> sale_CreatePerms;
        ArrayList<Sale_ReadPerm> sale_ReadPerms;
        ArrayList<Sale_UpdatePerm> Sale_UpdatePerms;
        ArrayList<Sale_DeletePerm> sale_DeletePerms;
        
        
        DBTool.close();
        return ups;
    }

}
