/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package db.permission;

import db.InstanceTests;
import model.data.Dealer;
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
public class Dealer_CreatePermDAOTest extends InstanceTests {

    private Dealer dealer;
    private User user;
    private Dealer_CreatePerm dealer_CreatePerm;
    
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
        String name = "InterSport";
        String description = "Vi har Nikadidas";
        String phone = "25252525";
        dealer = dealerRegister.create(name, description, phone, null);

        String nameUser = "Torben";
        String username = "BigPecs007";
        String password = "1234";
        String email = "CristianoRonaldofan48327643@gmail.com";
        String phoneUser = "23446543";
        user = userRegister.create(nameUser, username, password, email, phoneUser, dealer);
        
        dealer_CreatePerm = new Dealer_CreatePerm(user);
        dealer_CreatePermDAO.insert(dealer_CreatePerm);
        
    }
    
    @After
    public void tearDown() {
        dealer_CreatePermDAO.delete(dealer_CreatePerm);
        userRegister.delete(user);
        dealerRegister.delete(dealer);
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
