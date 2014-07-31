/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.permission;

import db.InstanceTests;
import model.data.Dealer;
import model.data.User;
import model.permission.AdminPerm;
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
public class AdminPermDAOTest extends InstanceTests {
    
    private static AdminPerm adminPerm;
    private static User user;
    private static Dealer dealer;
    
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
        
        adminPerm = new AdminPerm(user);
        adminPermDAO.insert(adminPerm);
        
        dealerRegister.load();
        storeRegister.load();
        userRegister.load();
    }
    
    @After
    public void tearDown() {
        adminPermDAO.delete(adminPerm);
        userDAO.delete(user);
        dealerDAO.delete(dealer);
    }
    
    @Test
    public void testSelect2() {
        System.out.println("select2");
        String expResult = user.toString();
        String result = adminPermDAO.select2(user).getExecutorUser().toString();
        
        assertEquals(expResult, result);
    }
    
}
