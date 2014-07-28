package model.permission;

import java.util.ArrayList;

/**
 *
 * @author Patrick
 */
public class UserPermissionSet {

    //Admin
    private AdminPerm adminPerm;

    //Dealer
    private ArrayList<Dealer_AdminPerm> dealer_AdminPerms;
    private Dealer_CreatePerm dealer_CreatePerm;
    private ArrayList<Dealer_ReadPerm> dealer_ReadPerms;
    private ArrayList<Dealer_UpdatePerm> dealer_UpdatePerms;
    private ArrayList<Dealer_DeletePerm> dealer_DeletePerms;

    //Store
    private ArrayList<Store_AdminPerm> store_AdminPerms;
    private ArrayList<Store_CreatePerm> store_CreatePerms;
    private ArrayList<Store_ReadPerm> store_ReadPerms;
    private ArrayList<Store_UpdatePerm> store_UpdatePerms;
    private ArrayList<Store_DeletePerm> store_DeletePerms;

    //User
    private ArrayList<User_CreatePerm> user_CreatePerms;
    private ArrayList<User_ReadPerm> user_ReadPerms;
    private ArrayList<User_UpdatePerm> user_UpdatePerms;
    private ArrayList<User_DeletePerm> user_DeletePerms;

    //Product
    private ArrayList<Product_CreatePerm> product_CreatePerms;
    private ArrayList<Product_ReadPerm> product_ReadPerms;
    private ArrayList<Product_UpdatePerm> product_UpdatePerms;
    private ArrayList<Product_DeletePerm> product_DeletePerms;

    //Sale
    private ArrayList<Sale_CreatePerm> sale_CreatePerms;
    private ArrayList<Sale_ReadPerm> sale_ReadPerms;
    private ArrayList<Sale_UpdatePerm> Sale_UpdatePerms;
    private ArrayList<Sale_DeletePerm> sale_DeletePerms;

    public AdminPerm getAdminPerm() {
        return adminPerm;
    }

    public void setAdminPerm(AdminPerm adminPerm) {
        this.adminPerm = adminPerm;
    }

    public ArrayList<Dealer_AdminPerm> getDealer_AdminPerms() {
        return dealer_AdminPerms;
    }

    public void setDealer_AdminPerms(ArrayList<Dealer_AdminPerm> dealer_AdminPerms) {
        this.dealer_AdminPerms = dealer_AdminPerms;
    }

    public Dealer_CreatePerm getDealer_CreatePerm() {
        return dealer_CreatePerm;
    }

    public void setDealer_CreatePerm(Dealer_CreatePerm dealer_CreatePerm) {
        this.dealer_CreatePerm = dealer_CreatePerm;
    }

    public ArrayList<Dealer_ReadPerm> getDealer_ReadPerms() {
        return dealer_ReadPerms;
    }

    public void setDealer_ReadPerms(ArrayList<Dealer_ReadPerm> dealer_ReadPerms) {
        this.dealer_ReadPerms = dealer_ReadPerms;
    }

    public ArrayList<Dealer_UpdatePerm> getDealer_UpdatePerms() {
        return dealer_UpdatePerms;
    }

    public void setDealer_UpdatePerms(ArrayList<Dealer_UpdatePerm> dealer_UpdatePerms) {
        this.dealer_UpdatePerms = dealer_UpdatePerms;
    }

    public ArrayList<Dealer_DeletePerm> getDealer_DeletePerms() {
        return dealer_DeletePerms;
    }

    public void setDealer_DeletePerms(ArrayList<Dealer_DeletePerm> dealer_DeletePerms) {
        this.dealer_DeletePerms = dealer_DeletePerms;
    }

    public ArrayList<Store_AdminPerm> getStore_AdminPerms() {
        return store_AdminPerms;
    }

    public void setStore_AdminPerms(ArrayList<Store_AdminPerm> store_AdminPerms) {
        this.store_AdminPerms = store_AdminPerms;
    }

    public ArrayList<Store_CreatePerm> getStore_CreatePerms() {
        return store_CreatePerms;
    }

    public void setStore_CreatePerms(ArrayList<Store_CreatePerm> store_CreatePerms) {
        this.store_CreatePerms = store_CreatePerms;
    }

    public ArrayList<Store_ReadPerm> getStore_ReadPerms() {
        return store_ReadPerms;
    }

    public void setStore_ReadPerms(ArrayList<Store_ReadPerm> store_ReadPerms) {
        this.store_ReadPerms = store_ReadPerms;
    }

    public ArrayList<Store_UpdatePerm> getStore_UpdatePerms() {
        return store_UpdatePerms;
    }

    public void setStore_UpdatePerms(ArrayList<Store_UpdatePerm> store_UpdatePerms) {
        this.store_UpdatePerms = store_UpdatePerms;
    }

    public ArrayList<Store_DeletePerm> getStore_DeletePerms() {
        return store_DeletePerms;
    }

    public void setStore_DeletePerms(ArrayList<Store_DeletePerm> store_DeletePerms) {
        this.store_DeletePerms = store_DeletePerms;
    }

    public ArrayList<User_CreatePerm> getUser_CreatePerms() {
        return user_CreatePerms;
    }

    public void setUser_CreatePerms(ArrayList<User_CreatePerm> user_CreatePerms) {
        this.user_CreatePerms = user_CreatePerms;
    }

    public ArrayList<User_ReadPerm> getUser_ReadPerms() {
        return user_ReadPerms;
    }

    public void setUser_ReadPerms(ArrayList<User_ReadPerm> user_ReadPerms) {
        this.user_ReadPerms = user_ReadPerms;
    }

    public ArrayList<User_UpdatePerm> getUser_UpdatePerms() {
        return user_UpdatePerms;
    }

    public void setUser_UpdatePerms(ArrayList<User_UpdatePerm> user_UpdatePerms) {
        this.user_UpdatePerms = user_UpdatePerms;
    }

    public ArrayList<User_DeletePerm> getUser_DeletePerms() {
        return user_DeletePerms;
    }

    public void setUser_DeletePerms(ArrayList<User_DeletePerm> user_DeletePerms) {
        this.user_DeletePerms = user_DeletePerms;
    }

    public ArrayList<Product_CreatePerm> getProduct_CreatePerms() {
        return product_CreatePerms;
    }

    public void setProduct_CreatePerms(ArrayList<Product_CreatePerm> product_CreatePerms) {
        this.product_CreatePerms = product_CreatePerms;
    }

    public ArrayList<Product_ReadPerm> getProduct_ReadPerms() {
        return product_ReadPerms;
    }

    public void setProduct_ReadPerms(ArrayList<Product_ReadPerm> product_ReadPerms) {
        this.product_ReadPerms = product_ReadPerms;
    }

    public ArrayList<Product_UpdatePerm> getProduct_UpdatePerms() {
        return product_UpdatePerms;
    }

    public void setProduct_UpdatePerms(ArrayList<Product_UpdatePerm> product_UpdatePerms) {
        this.product_UpdatePerms = product_UpdatePerms;
    }

    public ArrayList<Product_DeletePerm> getProduct_DeletePerms() {
        return product_DeletePerms;
    }

    public void setProduct_DeletePerms(ArrayList<Product_DeletePerm> product_DeletePerms) {
        this.product_DeletePerms = product_DeletePerms;
    }

    public ArrayList<Sale_CreatePerm> getSale_CreatePerms() {
        return sale_CreatePerms;
    }

    public void setSale_CreatePerms(ArrayList<Sale_CreatePerm> sale_CreatePerms) {
        this.sale_CreatePerms = sale_CreatePerms;
    }

    public ArrayList<Sale_ReadPerm> getSale_ReadPerms() {
        return sale_ReadPerms;
    }

    public void setSale_ReadPerms(ArrayList<Sale_ReadPerm> sale_ReadPerms) {
        this.sale_ReadPerms = sale_ReadPerms;
    }

    public ArrayList<Sale_UpdatePerm> getSale_UpdatePerms() {
        return Sale_UpdatePerms;
    }

    public void setSale_UpdatePerms(ArrayList<Sale_UpdatePerm> Sale_UpdatePerms) {
        this.Sale_UpdatePerms = Sale_UpdatePerms;
    }

    public ArrayList<Sale_DeletePerm> getSale_DeletePerms() {
        return sale_DeletePerms;
    }

    public void setSale_DeletePerms(ArrayList<Sale_DeletePerm> sale_DeletePerms) {
        this.sale_DeletePerms = sale_DeletePerms;
    }

    @Override
    public String toString() {
        String s = "";

        s += "### - DEALER ADMIN PERMISSIONS - ###\n";
        if (dealer_AdminPerms != null) {
            for (Dealer_AdminPerm d : dealer_AdminPerms) {
                s += d.toString();
            }
        } else {
            s += "null\n";
        }

        return s;
    }

}
