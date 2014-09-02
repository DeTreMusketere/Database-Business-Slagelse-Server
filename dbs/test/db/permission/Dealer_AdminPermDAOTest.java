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
public class Dealer_AdminPermDAOTest extends InstanceTests {

    private User user;
    private Dealer dealer;
    private Dealer_AdminPerm dealer_AdminPerm;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String name = "Kiwi";
        String description = "Meget ost";
        String phone = "25252525";
        dealer = dealerRegister.create(name, description, phone, null);

        String nameUser = "Gert";
        String username = "HyggeligFyr45";
        String password = "1234";
        String email = "watisemail@gmail.com";
        String phoneUser = "23446543";
        user = userRegister.create(nameUser, username, password, email, phoneUser, dealer);

        dealer_AdminPerm = new Dealer_AdminPerm(dealer, user);
        dealer_AdminPermDAO.insert(dealer_AdminPerm);

    }

    @After
    public void tearDown() {
        dealer_AdminPermDAO.delete(dealer_AdminPerm);
        userRegister.delete(user);
        dealerRegister.delete(dealer);
    }

    @Test
    public void testSelect() {
        System.out.println("select");

        String expUser = user.toString();
        String expDealer = dealer.toString();

        ArrayList<Dealer_AdminPerm> result = dealer_AdminPermDAO.select(user);

        String resultUser = null;
        String resultDealer = null;

        for (Dealer_AdminPerm dap : result) {
            resultUser = dap.getExecutorUser().toString();
            resultDealer = dap.getTargetDealer().toString();
            if (resultUser.equals(expUser) && resultDealer.equals(expDealer)) {
                break;
            }
        }

        assertEquals(expUser, resultUser);
        assertEquals(expDealer, resultDealer);
    }

}
