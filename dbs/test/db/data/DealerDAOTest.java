/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.data;

import db.InstanceTests;
import model.data.Dealer;
import model.data.Picture;
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
public class DealerDAOTest extends InstanceTests {

    private static Dealer testDealer;
    private static Picture picture1;
    private static Picture picture2;

    @BeforeClass
    public static void setUpClass() {
        
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String name = "Netto V2";
        String description = "Meget meget meget flot forretning, ofte tilbud på cola";
        String phone = "25252525";
        
        picture1 = new Picture(0, "Fugl", null);
        int pictureId = pictureDAO.insert(picture1);
        picture1.setId(pictureId);
        
        testDealer = new Dealer(0, name, description, phone, picture1);
        int id = dealerDAO.insert(testDealer);
        testDealer.setId(id);
        
        pictureRegister.load();
    }

    @After
    public void tearDown() {
        dealerDAO.delete(testDealer);
        pictureDAO.delete(picture1);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Dealer target = testDealer;

        int expectedResultId = testDealer.getId();
        String expectedResultName = "Netto V3";
        String expectedResultDescription = "Endnu bedre Netto";
        String expectedResultPhone = "25252525";
        picture2 = new Picture(0, "Fisk", null);
        int pictureId = pictureDAO.insert(picture2);
        picture2.setId(pictureId);
        String expectedResultPictureString = picture2.getName();

        Dealer source = new Dealer(expectedResultId, expectedResultName, expectedResultDescription, expectedResultPhone, picture2);

        dealerDAO.update(source, target);
        
        pictureRegister.load();

        Dealer resultDealer = dealerDAO.select(testDealer.getId());

        int resultId = resultDealer.getId();
        String resultName = resultDealer.getName();
        String resultDescription = resultDealer.getDescription();
        String resultPhone = resultDealer.getPhone();
        String resultPicture = resultDealer.getPicture().getName();
        
        

        assertEquals(expectedResultId, resultId);
        assertEquals(expectedResultName, resultName);
        assertEquals(expectedResultDescription, resultDescription);
        assertEquals(expectedResultPhone, resultPhone);
        assertEquals(expectedResultPictureString, resultPicture);
        
        pictureDAO.delete(picture2);
    }

}
