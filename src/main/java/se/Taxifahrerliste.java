/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class Taxifahrerliste implements Serializable{

    final static boolean DEBUG = false;

    private ArrayList<Taxifahrer> taxifahrerListe;
    private int fNr;

    // Konstruktor
    public Taxifahrerliste() {
        taxifahrerListe = new ArrayList();

        // Starte Taxifahrernummer mit "1"
        fNr = 1;

        if (DEBUG) {
            String s_fNr = Integer.toString(fNr);
            String fahrerName = "Mustermann";
            String fahrerVorname = "Maximilian";
            String fahrerStrasse = "Musterstrasse";
            String fahrerOrt = "Musterort";
            String fahrerHausnummer = "1337";
            fahrerHinzufuegen(new Taxifahrer(s_fNr, fahrerName, fahrerVorname, fahrerStrasse, fahrerOrt, fahrerHausnummer));

            s_fNr = Integer.toString(fNr);
            fahrerName = "Ludyk";
            fahrerVorname = "Marius";
            fahrerStrasse = "Luisenstrasse";
            fahrerOrt = "Eberstadt";
            fahrerHausnummer = "17";
            fahrerHinzufuegen(new Taxifahrer(s_fNr, fahrerName, fahrerVorname, fahrerStrasse, fahrerOrt, fahrerHausnummer));

            s_fNr = Integer.toString(fNr);
            fahrerName = "Niedermeier";
            fahrerVorname = "Gisela";
            fahrerStrasse = "Darmstaedter Str";
            fahrerOrt = "Gross-Gerau";
            fahrerHausnummer = "42";
            fahrerHinzufuegen(new Taxifahrer(s_fNr, fahrerName, fahrerVorname, fahrerStrasse, fahrerOrt, fahrerHausnummer));
        }
    }

    public Boolean fahrerHinzufuegen(Taxifahrer tf) {
        // Taxiobjekt hinzuf??gen
        boolean taxifahrerDoppelt = false;
        
        for (int i = 0; i<taxifahrerListe.size(); i++)
        {

            if(tf.getFahrerName().equals(taxifahrerListe.get(i).getFahrerName()) && tf.getFahrerVorname().equals(taxifahrerListe.get(i).getFahrerVorname()) 
                    && tf.getFahrerStrasse().equals(taxifahrerListe.get(i).getFahrerStrasse()) && tf.getFahrerHausnummer().equals(taxifahrerListe.get(i).getFahrerHausnummer())
                    && tf.getFahrerOrt().equals(taxifahrerListe.get(i).getFahrerOrt()))
            {
                JOptionPane.showMessageDialog(null, "Taxifahrer existiert bereits!");
                taxifahrerDoppelt = true;
                break;
            }
            
        }
          if (taxifahrerDoppelt  == false)
        {
          // Taxifahrerobjekt hinzuf??gen
            taxifahrerListe.add(tf);
            fNr++;
        }
      
        return true;
    }

    // Nur zu Debug-/Testzwecken
    public void listeAusgeben() {
        if (DEBUG) {
            System.out.println("Fahrerliste");
            Iterator iter = getTaxifahrerListe().iterator();

            while (iter.hasNext()) {
                Taxifahrer tf = (Taxifahrer) iter.next();
                System.out.println(tf.getFahrerNr() + " - " + tf.getFahrerName() + " - " + tf.getFahrerVorname() + " - "
                        + tf.getFahrerOrt() + " - " + tf.getFahrerStrasse() + " - " + tf.getFahrerHausnummer());
            }
        }
    }

    /**
     * @return the taxifahrerListe
     */
    public ArrayList<Taxifahrer> getTaxifahrerListe() {
        return taxifahrerListe;
    }

    /**
     * @param taxifahrerListe the taxifahrerListe to set
     */
    public void setTaxifahrerListe(ArrayList<Taxifahrer> taxifahrerListe) {
        this.taxifahrerListe = taxifahrerListe;
    }

    /**
     * @return the fNr
     */
    public int getfNr() {
        return fNr;
    }

    /**
     * @param fNr the fNr to set
     */
    public void setfNr(int fNr) {
        this.fNr = fNr;
    }
}
