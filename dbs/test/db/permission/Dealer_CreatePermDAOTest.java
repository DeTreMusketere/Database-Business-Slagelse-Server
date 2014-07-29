/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.permission;

import db.data.DealerDAO;
import db.data.StoreDAO;
import db.data.UserDAO;
import model.data.Dealer;
import model.data.DealerRegister;
import model.data.StoreRegister;
import model.data.User;
import model.data.UserRegister;
import model.permission.Dealer_CreatePerm;
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
public class Dealer_CreatePermDAOTest {
    
    private static DealerDAO dealerDAO;
    private static DealerRegister dealerRegister;
    private static StoreDAO storeDAO;
    private static StoreRegister storeRegister;
    private static UserDAO userDAO;
    private static UserRegister userRegister;
    private static Dealer_CreatePermDAO dealer_CreatePermDAO;
    private Dealer dealer;
    private User user;
    private Dealer_CreatePerm dealer_CreatePerm;
    
    public Dealer_CreatePermDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dealerDAO = new DealerDAO();
        dealerRegister = new DealerRegister(dealerDAO);
        storeDAO = new StoreDAO(dealerRegister);
        storeRegister = new StoreRegister(storeDAO);
        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(userDAO);
        dealer_CreatePermDAO = new Dealer_CreatePermDAO(userRegister, null);
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
        
        dealer_CreatePerm = new Dealer_CreatePerm(user);
        dealer_CreatePermDAO.insert(dealer_CreatePerm);
        
        dealerRegister.load();
        storeRegister.load();
        userRegister.load();
    }
    
    @After
    public void tearDown() {
        dealer_CreatePermDAO.delete(dealer_CreatePerm);
        userDAO.delete(user);
        dealerDAO.delete(dealer);
    }

    /**
     * Test of select2 method, of class Dealer_CreatePermDAO.
     */
    @Test
    public void testSelect2() {
        System.out.println("select2");
        
        String expUser = user.toString();
        
        String result = dealer_CreatePermDAO.select2(user).getExecutorUser().toString();
        
        assertEquals(expUser, result);
    }
    
}
