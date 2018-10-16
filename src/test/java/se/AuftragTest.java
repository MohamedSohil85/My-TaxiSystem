/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @
 */
public class AuftragTest {

//    private static Kunde testKunde;
//    private static Taxi testTaxi;
//    private static Taxifahrer testFahrer;
//    private static Date testDatum;
//    private static Auftrag testAuftrag;
    public AuftragTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        testKunde = new Kunde("101", "Sohil", "Mohamed", "Luisen Str", "Darmstadt", "1");
//        testTaxi = new Taxi("201", "DA DD 0123", "SUV");
//        testFahrer = new Taxifahrer("301", "Emre", "Can", "Muster Str", "Dieburg", "3");
//        testDatum = new Date(2016 - 1900, 11 - 1, 30);
//        testAuftrag = new Auftrag(testKunde, "401", "DA, Luisenplatz 3", "Di, Hochschulstr 20", testDatum, testTaxi, testFahrer, "50");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getKunde method, of class Auftrag.
     */
    @Test
    public void testGetKunde() {
        System.out.println("getKunde");

        Auftrag instance = new Auftrag();
        Kunde expResult = new Kunde("101", "Sohil", "Mohamed", "Luisen Str", "Darmstadt", "1");
        instance.setKunde(expResult);
        Kunde result = instance.getKunde();
        assertEquals(" passt nicht", expResult, result);

        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setKunde method, of class Auftrag.
     */
    @Test
    public void testSetKunde() {
        System.out.println("setKunde");
        Kunde expResult = new Kunde("102", "Sohil", "Mohamed", "Luisenstr", "Darmstadt", "15");
        Auftrag instance = new Auftrag();
        instance.setKunde(expResult);

        assertEquals(expResult, instance.getKunde());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getAuftragsNr method, of class Auftrag.
     */
    @Test
    public void testGetAuftragsNr() {
        System.out.println("getAuftragsNr");
        Auftrag instance = new Auftrag();
        String expResult = "55";
        instance.setAuftragsNr(expResult);

        assertEquals(expResult, instance.getAuftragsNr());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setAuftragsNr method, of class Auftrag.
     */
    @Test
    public void testSetAuftragsNr() {
        System.out.println("setAuftragsNr");
        String expResult = "77";
        Auftrag instance = new Auftrag();
        instance.setAuftragsNr(expResult);

        assertEquals(expResult, instance.getAuftragsNr());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getStartOrt method, of class Auftrag.
     */
    @Test
    public void testGetStartOrt() {
        System.out.println("getStartOrt");
        Auftrag instance = new Auftrag();
        //String expResult = "Darmstadt HBf 11";
        Strasse expResult = new Strasse("Muster Str., 52");
        instance.setStartOrt(expResult);
        Strasse result = instance.getStartOrt();

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setStartOrt method, of class Auftrag.
     */
    @Test
    public void testSetStartOrt() {
        System.out.println("setStartOrt");
        Strasse startOrt = new Strasse("Dieburg, Hauptstrasse, 5");
        Auftrag instance = new Auftrag();
        instance.setStartOrt(startOrt);

        assertEquals(startOrt, instance.getStartOrt());

// TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getZielOrt method, of class Auftrag.
     */
    @Test
    public void testGetZielOrt() {
        System.out.println("getZielOrt");
        Auftrag instance = new Auftrag();
        Strasse expResult = new Strasse("Mainz, HBf 2") ;
        instance.setZielOrt(expResult);
        Strasse result = instance.getZielOrt();

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setZielOrt method, of class Auftrag.
     */
    @Test
    public void testSetZielOrt() {
        System.out.println("setZielOrt");
        Strasse zielOrt =  new Strasse("Frankfurt am Main, Hauptwache 2");
        Auftrag instance = new Auftrag();
        instance.setZielOrt(zielOrt);

        assertEquals(zielOrt, instance.getZielOrt());

// TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getDatum method, of class Auftrag.
     */
    @Test
    public void testGetDatum() {
        System.out.println("getDatum");
        Auftrag instance = new Auftrag();
        Date expResult = new Date(2016 - 1900, 12 - 1, 31);
        instance.setDatum(expResult);
        Date result = instance.getDatum();

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of setDatum method, of class Auftrag.
     */
    @Test
    public void testSetDatum() {
        System.out.println("setDatum");
        Date d = new Date(2016 - 1900, 12 - 1, 31);
        Auftrag instance = new Auftrag();
        instance.setDatum(d);

        assertEquals(d, instance.getDatum());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxi method, of class Auftrag.
     */
    @Test
    public void testGetTaxi() {
        System.out.println("getTaxi");
        Auftrag instance = new Auftrag();
        Taxi expResult = new Taxi("201", "DA DD 0123", "SUV");
        instance.setTaxi(expResult);
        Taxi result = instance.getTaxi();

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setTaxi method, of class Auftrag.
     */
    @Test
    public void testSetTaxi() {
        System.out.println("setTaxi");
        Taxi taxi = new Taxi("201", "DA DD 0123", "SUV");
        Auftrag instance = new Auftrag();
        instance.setTaxi(taxi);

        assertEquals(taxi, instance.getTaxi());
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    /**
     * Test of getTaxifahrer method, of class Auftrag.
     */
    @Test
    public void testGetTaxifahrer() {
        System.out.println("getTaxifahrer");
        Auftrag instance = new Auftrag();
        Taxifahrer expResult = new Taxifahrer("321", "Emre", "Can", "Muster Str", "Dieburg", "33");
        instance.setTaxifahrer(expResult);
        Taxifahrer result = instance.getTaxifahrer();

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setTaxifahrer method, of class Auftrag.
     */
    @Test
    public void testSetTaxifahrer() {
        System.out.println("setTaxifahrer");
        Taxifahrer taxifahrer = new Taxifahrer("321", "Emre", "Can", "MusterStr", "Dieburg", "43");
        Auftrag instance = new Auftrag();
        instance.setTaxifahrer(taxifahrer);

        assertEquals(taxifahrer, instance.getTaxifahrer());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of getPreis method, of class Auftrag.
     */
    @Test
    public void testGetPreis() {
        System.out.println("getPreis");
        Auftrag instance = new Auftrag();
        String expResult = "52";
        instance.setPreis(expResult);
        String result = instance.getPreis();

        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of setPreis method, of class Auftrag.
     */
    @Test
    public void testSetPreis() {
        System.out.println("setPreis");
        String preis = "49";
        Auftrag instance = new Auftrag();
        instance.setPreis(preis);

        assertEquals(preis, instance.getPreis());
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        String status = "Erstellt";
        Auftrag instance = new Auftrag();
        instance.setStatus(status);
        String result = instance.getStatus();
        assertEquals(status, result);
    }

    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        String status = "Abgeschlossen";
        Auftrag instance = new Auftrag();
        instance.setStatus(status);
        assertEquals(status, instance.getStatus());
    }

}
