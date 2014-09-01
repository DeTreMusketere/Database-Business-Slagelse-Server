/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.permission;

import db.InstanceTests;
import model.data.Dealer;
import model.data.Picture;
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

    private AdminPerm adminPerm;
    private User user;
    private Dealer dealer;
    private Picture picture;

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {

    }

    @Before
    public void setUp() {
        String name = "Netto";
        String description = "Meget cola";
        String phone = "25252525";
        picture = pictureRegister.create("test", "data/pictures/test.png");

        dealer = dealerRegister.create(name, description, phone, picture);

        String nameUser = "Bobby";
        String username = "XCunt_DestroyerX486";
        String password = "1234";
        String email = "bobby1996sejfyrnohomo@gmail.com";
        String phoneUser = "23446543";
        user = userRegister.create(nameUser, username, password, email, phoneUser, dealer);

        adminPerm = new AdminPerm(user);
        adminPermDAO.insert(adminPerm);
    }

    @After
    public void tearDown() {
        adminPermDAO.delete(adminPerm);
        userRegister.delete(user);
        dealerRegister.delete(dealer);
        pictureRegister.delete(picture);
    }

    @Test
    public void testSelect2() {
        System.out.println("select2");
        String expResult = user.toString();
        String result = adminPermDAO.select2(user).getExecutorUser().toString();

        assertEquals(expResult, result);
    }

}
