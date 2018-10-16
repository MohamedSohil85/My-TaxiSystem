/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

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
public class KundenTest {
    
    private static Kunde testKunde;
    
    private String kundenNr;
    private String kundenName;
    private String kundenVorname;   // Setter und Getter
    private String kundenStrasse;          // Setter und Getter
    private String kundenOrt;             // Setter und Getter
    private String kundenHausnummer;         // Setter und Getter
    
    public KundenTest() {
        testKunde = new Kunde("80", "Schäfer", "Simon", "Teststrasse", "Teststadt", "5");
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
//        testKunde = new Kunde("101", "Winterberg", "Pauline", "Luisen Str", "Darmstadt", "1");

    }

    @After
    public void tearDown() {
    }

    /**
     * Test of setKundenNr method, of class Kunde.
     */
    @Test
    public void testSetKundenNr() {
        System.out.println("setKundenNr");
        String expResult = "188";
        Kunde instance = new Kunde();
        instance.setKundenNr(expResult);
        assertEquals(expResult, instance.getKundenNr());
    }
    
    /**
     * Test of getKundenNr method, of class Kunde.
     */
     @Test
    public void testGetKundenNr() {
        System.out.println("getKundenNr");
        Kunde instance = new Kunde();
        String expResult = "50";
        instance.setKundenNr(expResult);
        String result = instance.getKundenNr();

        assertEquals(expResult, result);
    }
    /**
     * Test of setKundenName und getKundenName method, of class Kunde.
     */
    @Test
    public void testSetKundenName()
    {
        System.out.println("setKundenName");
        kundenName = "Flachmann";
        Kunde instance = new Kunde();
        instance.setKundenName(kundenName);

        assertEquals(kundenName, instance.getKundenName());
    }
    @Test
    public void testGetKundenname()
    {
        System.out.println("getKundenName");
        Kunde instance = new Kunde();
        String expResult = "Schäfer";
        instance.setKundenName(expResult);
        String result = instance.getKundenName();
        
        assertEquals(expResult, result);
    }
    /**
     * Test of setKundenVorname und getKundenVorname method, of class Kunde.
     */
    @Test
    public void testSetKundenVorname()
    {
          System.out.println("setKundenVorname");
        kundenVorname = "Dirk";
        Kunde instance = new Kunde();
        instance.setKundenVorname(kundenName);

        assertEquals(kundenName, instance.getKundenVorname());
    }
    @Test
     public void testGetKundenVorname()
    {
        System.out.println("getKundenVorname");
        Kunde instance = new Kunde();
        String expResult = "Simon";
        instance.setKundenVorname(expResult);
        String result = instance.getKundenVorname();
        
        assertEquals(expResult, result);
    }
    
      /**
     * Test of testSetKundenStrasse und testGetKundenStrasse method, of class Kunde.
     */
    @Test
    public void testSetKundenStrasse()
    {
        System.out.println("setKundenStrasse");
        kundenStrasse = "TestWeg";
        Kunde instance = new Kunde();
        instance.setKundenStrasse(kundenStrasse);

        assertEquals(kundenStrasse, instance.getKundenStrasse());
    }
    
    @Test
     public void testGetKundenStrasse()
    {
        System.out.println("getKundenStrasse");
        Kunde instance = new Kunde();
        String expResult = "Testweg";
        instance.setKundenStrasse(expResult);
        String result = instance.getKundenStrasse();
        
        assertEquals(expResult, result);
    }
     
      /**
     * Test of testSetKundenOrt und testGetKundenOrt method, of class Kunde.
     */
    @Test
         public void testSetKundenOrt()
    {
        System.out.println("setKundenOrt");
        kundenOrt = "Alsbach";
        Kunde instance = new Kunde();
        instance.setKundenOrt(kundenOrt);

        assertEquals(kundenOrt, instance.getKundenOrt());
    }
         
     @Test
     public void testGetKundenOrt()
    {
        System.out.println("getKundenOrt");
        Kunde instance = new Kunde();
        String expResult = "Alsbach";
        instance.setKundenOrt(expResult);
        String result = instance.getKundenOrt();
        
        assertEquals(expResult, result);
    }
     
      /**
     * Test of testSetKundenHausnummer und testGetKundenHausnummer method, of class Kunde.
     */
    @Test
     public void testSetKundenHausnummer()
     {
         System.out.println("setKundenHausnummer");
        kundenHausnummer = "5";
        Kunde instance = new Kunde();
        instance.setKundenHausnummer(kundenHausnummer);

        assertEquals(kundenHausnummer, instance.getKundenHausnummer());
     }
     
     @Test
       public void testGetKundenHausnummer()
    {
        System.out.println("getKundenHausnummer");
        Kunde instance = new Kunde();
        String expResult = "5";
        instance.setKundenHausnummer(expResult);
        String result = instance.getKundenHausnummer();
        
        assertEquals(expResult, result);
    }
       
      /**
     * Test of changeKundenName method, of class Kunde
     */
       @Test
       public void testChangeKundenName()
       {
           System.out.println("changeKundenName");
           Kunde instance = new Kunde();
           instance.setKundenName("Meier");
           String expResult = "Maier";
           instance.changeKundenName(expResult);
           String result = instance.getKundenName();
           
             assertEquals(expResult, result);
       }
       
       /**
     * Test of changeKundenVorname method, of class Kunde
     */
       @Test
       public void testchangeKundenVorname()
       {
           System.out.println("changeKundenVorname");
           Kunde instance = new Kunde();
           instance.setKundenVorname("Peter");
           String expResult = "Alex";
           instance.changeKundenVorname(expResult);
           String result = instance.getKundenVorname();
           
             assertEquals(expResult, result);
       }
       
           /**
     * Test of changeKundenHausnummer method, of class Kunde
     */
       @Test
       public void testchangeKundenHausnummer()
       {
           System.out.println("changeKundenHausnummer");
           Kunde instance = new Kunde();
           instance.setKundenHausnummer("8");
           String expResult = "20";
           instance.changeKundenHausnummer(expResult);
           String result = instance.getKundenHausnummer();
           
             assertEquals(expResult, result);
       }
       
           /**
     * Test of changekundenOrt method, of class Kunde
     */
       @Test
       public void testchangekundenOrt()
       {
           System.out.println("changekundenOrt");
           Kunde instance = new Kunde();
           instance.setKundenOrt("Mannheim");
           String expResult = "Bensheim";
           instance.changekundenOrt(expResult);
           String result = instance.getKundenOrt();
           
             assertEquals(expResult, result);
       }
       
           /**
     * Test of changekundenStrasse method, of class Kunde
     */
       @Test
       public void testchangekundenStrasse()
       {
           System.out.println("changekundenStrasse");
           Kunde instance = new Kunde();
           instance.setKundenStrasse("Peterweg");
           String expResult = "Alexweg";
           instance.changekundenStrasse(expResult);
           String result = instance.getKundenStrasse();
           
             assertEquals(expResult, result);
       }
}
