/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.data;

import db.DBTool;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.data.Dealer;
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
public class DealerDAOTest {

    private static Dealer testDealer;
    private static DealerDAO instance;

    public DealerDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        instance = new DealerDAO();
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        String name = "Netto V2";
        String description = "Meget meget meget flot forretning, ofte tilbud på cola";
        String phone = "25252525";
        int picture = 1;
        testDealer = new Dealer(0, name, description, phone, picture);
        int id = instance.insert(testDealer);
        testDealer.setId(id);
    }

    @After
    public void tearDown() {
        instance.delete(testDealer);
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        Dealer target = testDealer;

        int expectedResultId = testDealer.getId();
        String expectedResultName = "Netto V3";
        String expectedResultDescription = "Endnu bedre Netto";
        String expectedResultPhone = "25252525";
        int expectedResultPicture = 2;

        Dealer source = new Dealer(expectedResultId, expectedResultName, expectedResultDescription, expectedResultPhone, expectedResultPicture);

        DealerDAO instance = new DealerDAO();
        instance.update(source, target);

        Dealer resultDealer = instance.select(testDealer.getId());

        int resultId = resultDealer.getId();
        String resultName = resultDealer.getName();
        String resultDescription = resultDealer.getDescription();
        String resultPhone = resultDealer.getPhone();
        int resultPicture = resultDealer.getPicture();

        assertEquals(expectedResultId, resultId);
        assertEquals(expectedResultName, resultName);
        assertEquals(expectedResultDescription, resultDescription);
        assertEquals(expectedResultPhone, resultPhone);
        assertEquals(expectedResultPicture, resultPicture);
    }

}
