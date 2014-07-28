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
import model.permission.Dealer_AdminPerm;
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
public class Dealer_AdminPermDAOTest {
    
    private static DealerDAO dealerDAO;
    private static DealerRegister dealerRegister;
    private static StoreDAO storeDAO;
    private static StoreRegister storeRegister;
    private static UserDAO userDAO;
    private static UserRegister userRegister;
    
    
    public Dealer_AdminPermDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
        dealerDAO = new DealerDAO();
        dealerRegister = new DealerRegister(dealerDAO);
        storeDAO = new StoreDAO(dealerRegister);
        storeRegister = new StoreRegister(storeDAO);
        userDAO = new UserDAO(dealerRegister, storeRegister);
        userRegister = new UserRegister(userDAO);
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of insert method, of class Dealer_AdminPermDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Dealer_AdminPerm source = null;
        Dealer_AdminPermDAO instance = null;
        instance.insert(source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class Dealer_AdminPermDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Dealer_AdminPerm source = null;
        Dealer_AdminPermDAO instance = null;
        instance.delete(source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class Dealer_AdminPermDAO.
     */
    @Test
    public void testSelect_User_Dealer() {
        System.out.println("select");
        User executor = null;
        Dealer target = null;
        Dealer_AdminPermDAO instance = null;
        Dealer_AdminPerm expResult = null;
        Dealer_AdminPerm result = instance.select(executor, target);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class Dealer_AdminPermDAO.
     */
    @Test
    public void testSelect_User() {
        System.out.println("select");
        User executor = null;
        Dealer_AdminPermDAO instance = null;
        ArrayList<Dealer_AdminPerm> expResult = null;
        ArrayList<Dealer_AdminPerm> result = instance.select(executor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
