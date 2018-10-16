/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.util.ArrayList;
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
public class KundenlisteTest {

    private static Kunde testKunde;
    private static int knr;

    public KundenlisteTest() {
        testKunde = new Kunde("80", "Schäfer", "Simon", "Teststrasse", "Teststadt", "5");
//

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
     * Test of kundeHinzufuegen method, of class Kundenliste.
     */
    @Test
    public void testKundehinzufuegen() {
        System.out.println("Kunden hinzufuegen");

        Kundenliste instance = new Kundenliste();

        Boolean expResult = true;
        Boolean result = instance.kundeHinzufuegen(testKunde);
        assertEquals(expResult, result);
    }

    /**
     * Test of setKundenliste and getKundenliste method, of class Kundenliste.
     */
    @Test
    public void testGetKundenliste() {
        System.out.println("getKundenliste");
        Kundenliste instance = new Kundenliste();
        ArrayList<Kunde> expResult = new ArrayList();
        instance.setKundeliste(expResult);
        ArrayList<Kunde> result = instance.getKundeliste();
        assertEquals(expResult, result);
    }
    
    
    /**
    * Test of  and listeAusgeben method, of class Kundenliste.
     */
    @Test
    public void testKundenListeAusgeben(){
        System.out.println("Kundenliste listeAusgeben");
        Kundenliste instance = new Kundenliste();
        instance.listeAusgeben();
    }
    
    /**
    * Test of  setKnr and getKnr method, of class Kundenliste.
     */
    @Test
    public void testKundenlisteSetKnr(){
        System.out.println("Kundenliste SetKnr");
        knr = 2;
        Kundenliste instance = new Kundenliste();
        instance.setKnr(knr);
        
        System.out.println("getKnr");
        System.out.println(instance.getKnr());
    }
    
    public void testKundenlisteGetKnr(){
        System.out.println("Kundenliste GetKnr");
        knr = 15;
        Kundenliste instance = new Kundenliste();
        instance.setKnr(knr);
        int expResult = instance.getKnr();
        
        assertEquals(expResult, knr);
    }
    
}
