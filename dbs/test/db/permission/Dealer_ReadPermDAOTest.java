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
import model.permission.Dealer_CreatePerm;
import model.permission.Dealer_ReadPerm;
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
public class Dealer_ReadPermDAOTest {
    
    private static DealerDAO dealerDAO;
    private static DealerRegister dealerRegister;
    private static StoreDAO storeDAO;
    private static StoreRegister storeRegister;
    private static UserDAO userDAO;
    private static UserRegister userRegister;
    private static Dealer_ReadPermDAO dealer_ReadPermDAO;
    private Dealer dealer;
    private User user;
    private Dealer_ReadPerm dealer_ReadPerm;
    
    
    public Dealer_ReadPermDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dealerDAO = new DealerDAO();
        dealerRegister = new DealerRegister(dealerDAO);
        storeDAO = new StoreDAO(dealerRegister);
        storeRegister = new StoreRegister(storeDAO);
        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(userDAO);
        dealer_ReadPermDAO = new Dealer_ReadPermDAO(userRegister, dealerRegister);
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
        
        dealer_ReadPerm = new Dealer_ReadPerm(dealer, user);
        dealer_ReadPermDAO.insert(dealer_ReadPerm);
        
        dealerRegister.load();
        storeRegister.load();
        userRegister.load();
    }
    
    @After
    public void tearDown() {
        dealer_ReadPermDAO.delete(dealer_ReadPerm);
        userDAO.delete(user);
        dealerDAO.delete(dealer);
    }

    /**
     * Test of select method, of class Dealer_ReadPermDAO.
     */
    @Test
    public void testSelect() {
        
        String expUser = user.toString();
        String expDealer = dealer.toString();
        
        ArrayList<Dealer_ReadPerm> result = dealer_ReadPermDAO.select(user);
        
        String resultUser = null;
        String resultDealer = null;
        
        for(Dealer_ReadPerm drp : result) {
            resultUser = drp.getExecutorUser().toString();
            resultDealer = drp.getTargetDealer().toString();
            if(resultUser.equals(expUser) && resultDealer.equals(expDealer)) {
                break;
            }
        }
        
        assertEquals(expUser, resultUser);
        assertEquals(expDealer, resultDealer);
    }
    
}
