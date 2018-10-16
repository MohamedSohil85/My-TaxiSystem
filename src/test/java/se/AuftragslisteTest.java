/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.util.ArrayList;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * 
 */
public class AuftragslisteTest {

    private static Kunde testKunde;
    private static Taxi testTaxi;
    private static Taxifahrer testFahrer;
    private static Date testDatum;
    private static Auftrag testAuftrag;
    private static Auftragsliste testAListe;

    public AuftragslisteTest() {
        testKunde = new Kunde("101", "Winterberg", "Pauline", "Luisen Str", "Darmstadt", "1");
        testTaxi = new Taxi("201", "DA DD 0123", "SUV");
        testFahrer = new Taxifahrer("301", "Kreutzer", "Heinz", "Muster Str", "Dieburg", "3");
        testDatum = new Date(2016 - 1900, 11 - 1, 30);
        testAuftrag = new Auftrag(testKunde, "401", new Strasse("DA, Luisenplatz 3"),new Strasse ("Di, Hochschulstr 20"),
                testDatum, testTaxi, testFahrer, "50", "Erstellt");
//        testAListe.auftragHinzufuegen(testAuftrag);

    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of auftragHinzufuegen method, of class Auftragsliste.
     */
    /*
    @Test
    public void testAuftragHinzufuegen() {
        System.out.println("auftragHinzufuegen");

//        Kunde k = new Kunde("55", "Mustermann", "John", "Musterstr", "Musterort", "22");
//        String n = "51";
//        String so = "Darmstadt";
//        String zo = "Dieburg";
//        Date d = new Date();
//        Taxi t = new Taxi("11", "DA DD 0123", "SUV");
//        Taxifahrer f = new Taxifahrer("33", "Weber", "Max", "Muster Str 2", "Musterort", "3");
//        String p = "50";
//        Auftrag a = new Auftrag(k, so, so, zo, d, t, f, p);
//        Auftragsliste instance = new Auftragsliste();

        Boolean expResult = true;
        //Boolean result = instance.auftragHinzufuegen(testAuftrag);
        assertEquals(expResult, result);
    }

    /**
     * Test of getAuftragsliste method, of class Auftragsliste.
     
    @Test
    public void testGetAuftragsliste() {
        System.out.println("getAuftragsliste");
        Auftragsliste instance = new Auftragsliste();
        ArrayList<Auftrag> expResult = new ArrayList();
        instance.setAuftragsliste(expResult);
        ArrayList<Auftrag> result = instance.getAuftragsliste();
        assertEquals(expResult, result);
    }
*/
}
