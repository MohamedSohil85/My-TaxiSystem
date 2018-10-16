/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * 
 */
public class TaxilisteTest {
    
      private static Taxi taxi;
    private static int nr;

    public TaxilisteTest() {
       taxi = new Taxi("18","DA SA 1234","SUV");
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
     * Test of fahrerHinzufuegen method, of class Taxiliste.
     */
    @Test
    public void testTaxiHinzufuegen() {
        System.out.println("Taxi hinzufuegen");

        Taxiliste instance = new Taxiliste();

        Boolean expResult = true;
        Boolean result = instance.TaxiHinzufuegen(taxi);
        assertEquals(expResult, result);
        
        
    }
    
    /**
     * Test of setTaxiliste and getTaxiliste method, of class Taxiliste.
     */
    @Test
    public void testgetTaxiliste() {
        System.out.println("getTaxiliste");
        Taxiliste instance = new Taxiliste();
        ArrayList<Taxi> expResult = new ArrayList();
        instance.setTaxiliste(expResult);
        ArrayList<Taxi> result = instance.getTaxiliste();
        assertEquals(expResult, result);
    }
    
     /**
    * Test of  and listeAusgeben method, of class Taxiliste.
     */
    @Test
    public void testTaxilisteAusgeben(){
        System.out.println("Taxiliste listeAusgeben");
       Taxiliste instance = new Taxiliste();
        instance.listeAusgeben();
    }
    
   /**
    * Test of  setnr and getnr method, of class Taxiliste.
     */
    @Test
    public void testTaxisetNr(){
        System.out.println("Taxiliste setNr");
        nr = 2;
         Taxiliste instance = new Taxiliste();
        instance.setNr(nr);
        
        System.out.println("getNr");
        System.out.println(instance.getNr());
    }
    
    public void testTaxigetNr(){
        System.out.println("Taxiliste Getnr");
        nr = 15;
        Taxiliste instance = new Taxiliste();
        instance.setNr(nr);
        int expResult = instance.getNr();
        
        assertEquals(expResult, nr);
    }
    
}
