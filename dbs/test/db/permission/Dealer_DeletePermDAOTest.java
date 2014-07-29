/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.permission;

import db.data.DealerDAO;
import db.data.StoreDAO;
import db.data.UserDAO;
import java.util.ArrayList;
import model.data.Dealer;
import model.data.DealerRegister;
import model.data.StoreRegister;
import model.data.User;
import model.data.UserRegister;
import model.permission.Dealer_DeletePerm;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Patrick
 */
public class Dealer_DeletePermDAOTest {
    
    private static DealerDAO dealerDAO;
    private static DealerRegister dealerRegister;
    private static StoreDAO storeDAO;
    private static StoreRegister storeRegister;
    private static UserDAO userDAO;
    private static UserRegister userRegister;
    private static User user;
    private static Dealer dealer;
    private static Dealer_DeletePermDAO dealer_DeletePermDAO;
    private static Dealer_DeletePerm dealer_DeletePerm;
    
    public Dealer_DeletePermDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dealerDAO = new DealerDAO();
        dealerRegister = new DealerRegister(dealerDAO);
        storeDAO = new StoreDAO(dealerRegister);
        storeRegister = new StoreRegister(storeDAO);
        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(userDAO);
        dealer_DeletePermDAO = new Dealer_DeletePermDAO(userRegister, dealerRegister);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dealer = TestCore.getTestDealer();
        int idDealer = dealerDAO.insert(dealer);
        dealer.setId(idDealer);
        user = TestCore.getTestUser(dealer);
        int idUser = userDAO.insert(user);
        user.setId(idUser);
        
        dealer_DeletePerm = new Dealer_DeletePerm(dealer, user);
        dealer_DeletePermDAO.insert(dealer_DeletePerm);
        
        dealerRegister.load();
        storeRegister.load();
        userRegister.load();
    }
    
    @After
    public void tearDown() {
        dealer_DeletePermDAO.delete(dealer_DeletePerm);
        userDAO.delete(user);
        dealerDAO.delete(dealer);
    }

    

    @Test
    public void testSelect() {
        System.out.println("select");
        User executor = user;
        String expResult = dealer_DeletePerm.toString();
        String result = null;
        ArrayList<Dealer_DeletePerm> dealer_DeletePerms = dealer_DeletePermDAO.select(executor);
        for(Dealer_DeletePerm deletePerm : dealer_DeletePerms){
            result = deletePerm.toString();
            if(expResult.equals(result)){
                break;
            }
        }
        
        assertEquals(expResult, result);
    }
    
}
