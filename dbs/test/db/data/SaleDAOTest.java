/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.data;

import db.InstanceTests;
import java.util.Date;
import model.data.Dealer;
import model.data.Picture;
import model.data.Sale;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PK
 */
public class SaleDAOTest extends InstanceTests {

    private Dealer parentDealer;
    private Dealer expectedResultParentDealer;
    private Sale testSale;
    private Picture picture1;
    private Picture picture2;

    public SaleDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String name = "Billige bananer";
        String description = "Super billige Chickadicka bananer fra Sverige";
        picture1 = pictureRegister.create("test", "data/pictures/test.png");
        double price = 10.50;
        parentDealer = dealerRegister.create("Fakta", "Det tager kun 5 minutter, hvis køen ikke er for lang", "12346587", null);
        parentDealer.setId(parentDealer.getId());
        Date start = new Date(2000, 03, 21, 12, 00);
        Date end = new Date(2000, 03, 21, 15, 00);
        Date publish = new Date(2000, 03, 21, 12, 00);
        testSale = saleRegister.create(name, description, picture1, price, start, end, publish, parentDealer);
    }

    @After
    public void tearDown() {
        saleRegister.delete(testSale);
        dealerRegister.delete(parentDealer);
        dealerRegister.delete(expectedResultParentDealer);
        pictureRegister.delete(picture1);
        pictureRegister.delete(picture2);
    }

    /**
     * Test of update method, of class SaleDAO.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Sale target = testSale;

        int expectedResultId = testSale.getId();
        String expectedResultName = "Chickadicka bananer";
        String expectedResultDescription = "Super billige Chickadicka bananer fra Sverige";

        picture2 = pictureRegister.create("test2", "data/pictures/test2.png");

        Date start = new Date(2001, 03, 21, 12, 00);
        Date end = new Date(2001, 03, 21, 15, 00);
        Date publish = new Date(2001, 03, 21, 12, 00);
        long expectedResultStart = start.getTime();
        long expectedResultEnd = end.getTime();
        long expectedResultPublish = publish.getTime();

        String expectedResultPictureString = picture2.getName();
        double expectedResultPrice = 8.50;
        expectedResultParentDealer = dealerRegister.create("Føtex", "Vores butik er større end din", "87546132", null);
        Sale source = new Sale(testSale.getId(), expectedResultName, expectedResultDescription, picture2, expectedResultPrice, start, end, publish, expectedResultParentDealer, testSale.getUpdateNumber());

        saleRegister.update(source, target);

        Sale resultSale = saleRegister.get(source.getId());

        int resultId = resultSale.getId();
        String resultName = resultSale.getName();
        String resultDescription = resultSale.getDescription();
        String resultPicture = resultSale.getPicture().getName();
        double resultPrice = resultSale.getPrice();
        Dealer resultParentDealer = resultSale.getParentDealer();
        long resultStart = resultSale.getStart().getTime();
        long resultEnd = resultSale.getEnd().getTime();
        long resultPublish = resultSale.getPublish().getTime();

        assertEquals(expectedResultId, resultId);
        assertEquals(expectedResultName, resultName);
        assertEquals(expectedResultDescription, resultDescription);
        assertEquals(expectedResultPictureString, resultPicture);
        assertEquals(expectedResultPrice, resultPrice, 0.05);
        assertEquals(expectedResultParentDealer, resultParentDealer);
        assertEquals(expectedResultStart, resultStart);
        assertEquals(expectedResultEnd, resultEnd);
        assertEquals(expectedResultPublish, resultPublish);

    }

}
