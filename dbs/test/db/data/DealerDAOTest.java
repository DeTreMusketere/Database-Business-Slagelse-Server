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

    private Dealer testDealer;
    private Picture picture1;
    private Picture picture2;

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
        picture1 = pictureRegister.create("test", "data/pictures/test.png");

        testDealer = dealerRegister.create(name, description, phone, picture1);
    }

    @After
    public void tearDown() {
        dealerRegister.delete(testDealer);
        pictureRegister.delete(picture1);
        pictureRegister.delete(picture2);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");

        Dealer target = testDealer;

        int expectedResultId = testDealer.getId();
        String expectedResultName = "Netto V3";
        String expectedResultDescription = "Endnu bedre Netto";
        String expectedResultPhone = "25252525";
        picture2 = pictureRegister.create("test2", "data/pictures/test2.png");
        String expectedResultPictureString = picture2.getName();

        Dealer source = new Dealer(testDealer.getId(), expectedResultName, expectedResultDescription, expectedResultPhone, picture2, testDealer.getUpdateNumber());

        dealerRegister.update(source, target);

        Dealer resultDealer = dealerRegister.get(source.getId());
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

    }

}
