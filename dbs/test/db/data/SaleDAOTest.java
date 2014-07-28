/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.data;

import abstracts.DataDAO;
import java.util.ArrayList;
import model.data.Dealer;
import model.data.DealerRegister;
import model.data.Sale;
import model.data.StoreRegister;
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
public class SaleDAOTest {

    private static Dealer parentDealer;
    private static Sale testSale;
    private static DealerDAO dealerDAO;
    private static SaleDAO instance;
    private static DealerRegister dealerRegister;

    public SaleDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        dealerRegister = new DealerRegister(new DealerDAO());
        instance = new SaleDAO((dealerRegister), new StoreRegister(new StoreDAO(dealerRegister)));
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String name = "Billige bananer";
        String description = "Super billige Chickadicka bananer fra Sverige";
        int picture = 5;
        double price = 10.50;
        parentDealer = new Dealer(0, "Fakta", "Det ta'r kun 5 minutter", "2342523525", 6);
        int idDealer = dealerDAO.insert(parentDealer);
        parentDealer.setId(idDealer);
        testSale = new Sale(0, name, description, picture, price, parentDealer);
        int idSale = instance.insert(testSale);
        testSale.setId(idSale);
    }

    @After
    public void tearDown() {
        instance.delete(testSale);
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
        int expectedResultPicture = 10;
        double expectedResultPrice = 8.50;
        Dealer expectedResultParentDealer = parentDealer;
        Sale source = new Sale(expectedResultId, expectedResultName, expectedResultDescription, expectedResultPicture, expectedResultPrice, expectedResultParentDealer);

        instance.update(source, target);

        Sale resultSale = instance.select(testSale.getId());

        int resultId = resultSale.getId();
        String resultName = resultSale.getName();
        String resultDescription = resultSale.getDescription();
        int resultPicture = resultSale.getPicture();
        double resultPrice = resultSale.getPrice();
        Dealer resultParentDealer = resultSale.getParentDealer();
        
        

        assertEquals(expectedResultId, resultId);
        assertEquals(expectedResultName, resultName);
        assertEquals(expectedResultDescription, resultDescription);
        assertEquals(expectedResultPicture, resultPicture);
        assertEquals(expectedResultPrice, resultPrice, 0.05);
        assertEquals(expectedResultParentDealer, resultParentDealer);
        
    }

}
