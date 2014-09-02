/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.permission;

import db.InstanceTests;
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
public class Dealer_DeletePermDAOTest extends InstanceTests {

    private static User user;
    private static Dealer dealer;
    private static Dealer_DeletePerm dealer_DeletePerm;

    public Dealer_DeletePermDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String name = "H&M";
        String description = "Alt det tøj du ikke kan lide";
        String phone = "25252525";
        dealer = dealerRegister.create(name, description, phone, null);

        String nameUser = "Gustav";
        String username = "mmMMMmmMmmm";
        String password = "1234";
        String email = "faaaaabuloouusssss@gmail.com";
        String phoneUser = "23446543";
        user = userRegister.create(nameUser, username, password, email, phoneUser, dealer);

        dealer_DeletePerm = new Dealer_DeletePerm(dealer, user);
        dealer_DeletePermDAO.insert(dealer_DeletePerm);

    }

    @After
    public void tearDown() {
        dealer_DeletePermDAO.delete(dealer_DeletePerm);
        userRegister.delete(user);
        dealerRegister.delete(dealer);
    }

    @Test
    public void testSelect() {
        System.out.println("select");
        User executor = user;
        String expResult = dealer_DeletePerm.toString();
        String result = null;
        ArrayList<Dealer_DeletePerm> dealer_DeletePerms = dealer_DeletePermDAO.select(executor);
        for (Dealer_DeletePerm deletePerm : dealer_DeletePerms) {
            result = deletePerm.toString();
            if (expResult.equals(result)) {
                break;
            }
        }

        assertEquals(expResult, result);
    }

}
