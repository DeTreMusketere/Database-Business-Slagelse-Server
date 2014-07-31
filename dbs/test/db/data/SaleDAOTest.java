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

    private static Dealer parentDealer;
    private static Sale testSale;
    private static int idDealer;
    private static Picture picture1;
    private static Picture picture2;

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
        
        picture1 = new Picture(0, "Fugl", "");
        int pictureId = pictureDAO.insert(picture1);
        picture1.setId(pictureId);
        
        double price = 10.50;
        parentDealer = new Dealer(0, "Fakta", "Det tager kun 5 minutter", "2342523525", picture1);
        idDealer = dealerRegister.insert(parentDealer);
        parentDealer.setId(idDealer);
        Date start = new Date(2000, 03, 21, 12, 00);
        Date end = new Date(2000, 03, 21, 15, 00);
        Date publish = new Date(2000, 03, 21, 12, 00);
        testSale = new Sale(0, name, description, picture1, price, start, end, publish, parentDealer);
        int idSale = saleDAO.insert(testSale);
        testSale.setId(idSale);
        
        pictureRegister.load();
        
    }

    @After
    public void tearDown() {
        saleDAO.delete(testSale);
        pictureDAO.delete(picture1);
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
        
        
        picture2 = new Picture(0, "Fisk", "");
        int pictureId = pictureDAO.insert(picture2);
        picture2.setId(pictureId);
        
        Date start = new Date(2001, 03, 21, 12, 00);
        Date end = new Date(2001, 03, 21, 15, 00);
        Date publish = new Date(2001, 03, 21, 12, 00);
        long expectedResultStart = start.getTime();
        long expectedResultEnd = end.getTime();
        long expectedResultPublish = publish.getTime();
        
        String expectedResultPictureString = picture2.getName();
        double expectedResultPrice = 8.50;
        Dealer expectedResultParentDealer = parentDealer;
        Sale source = new Sale(expectedResultId, expectedResultName, expectedResultDescription, picture2, expectedResultPrice, start, end, publish, expectedResultParentDealer);

        pictureRegister.load();
        
        saleDAO.update(source, target);
        
        Sale resultSale = saleDAO.select(testSale.getId());
        

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
        
        pictureDAO.delete(picture2);
    }

}
