/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.permission;

import java.util.ArrayList;
import model.data.User;
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
    
    public Dealer_CreatePermDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
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
     * Test of insert method, of class Dealer_CreatePermDAO.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Dealer_CreatePerm source = null;
        Dealer_CreatePermDAO instance = null;
        instance.insert(source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class Dealer_CreatePermDAO.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Dealer_CreatePerm source = null;
        Dealer_CreatePermDAO instance = null;
        instance.delete(source);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select2 method, of class Dealer_CreatePermDAO.
     */
    @Test
    public void testSelect2() {
        System.out.println("select2");
        User executor = null;
        Dealer_CreatePermDAO instance = null;
        Dealer_CreatePerm expResult = null;
        Dealer_CreatePerm result = instance.select2(executor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of select method, of class Dealer_CreatePermDAO.
     */
    @Test
    public void testSelect() {
        System.out.println("select");
        User executor = null;
        Dealer_CreatePermDAO instance = null;
        ArrayList<Dealer_CreatePerm> expResult = null;
        ArrayList<Dealer_CreatePerm> result = instance.select(executor);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
