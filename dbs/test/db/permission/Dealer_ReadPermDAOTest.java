/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.permission;

import db.InstanceTests;
import java.util.ArrayList;
import model.data.Dealer;
import model.data.User;
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
public class Dealer_ReadPermDAOTest extends InstanceTests {

    private Dealer dealer;
    private User user;
    private Dealer_ReadPerm dealer_ReadPerm;
    
    
    public Dealer_ReadPermDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {

    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        dealer = TestCore.getTestDealer(null);
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
