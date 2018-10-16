/*
 * Projekt: TaxiSys
 * Datei  : Kundeliste.java (Control-Klasse)
 * Gruppe : <hier Namen der Mitglieder eintragen>
 * Letzte ??nderung: 26.08.2016
 *
 * ??nderungen
 * 26.08.16 : Initiale Erstellung der Klasse
 *
 */

package se;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JOptionPane;

public class Kundenliste implements Serializable{

    final static boolean DEBUG = false;

    private ArrayList<Kunde> kundenliste;
    private int knr;

    // Konstruktor
    public Kundenliste() {
        kundenliste = new ArrayList();

        // Starte Kundennummer mit "1"
        knr = 1;

        if (DEBUG) {
            String s_knr = Integer.toString(knr);
            String kname = "Mustermann";
            String kvorname ="Max"   ;
            String kStrasse  = "Musterstrasse";          
            String kOrt = "Musterort";             
            String kHausnummer = "1337";         
            kundeHinzufuegen(new Kunde(s_knr, kname, kvorname, kStrasse, kOrt, kHausnummer));
            
            s_knr = Integer.toString(knr);
            kname = "Kathmann";
            kvorname ="Margit"   ;
            kStrasse  = "Musterstrasse";          
            kOrt = "Musterort";             
            kHausnummer = "1337";         
            kundeHinzufuegen(new Kunde(s_knr, kname, kvorname, kStrasse, kOrt, kHausnummer));
            
            s_knr = Integer.toString(knr);
            kname = "Weingarten";
            kvorname ="Josef"   ;
            kStrasse  = "Musterstrasse";          
            kOrt = "Musterort";             
            kHausnummer = "1337";         
            kundeHinzufuegen(new Kunde(s_knr, kname, kvorname, kStrasse, kOrt, kHausnummer));
        }
    }

    public Boolean kundeHinzufuegen(Kunde k) {
        // Kundenobjekt hinzuf??gen
        boolean kundedoppelt = false;
      
   
        for (int i = 0; i<kundenliste.size(); i++)
        {
            System.out.println(k.getKundenName() + " " +kundenliste.get(i).getKundenName());
            if(k.getKundenName().equals(kundenliste.get(i).getKundenName()) && k.getKundenVorname().equals(kundenliste.get(i).getKundenVorname()) 
                    && k.getKundenStrasse().equals(kundenliste.get(i).getKundenStrasse()) && k.getKundenHausnummer().equals(kundenliste.get(i).getKundenHausnummer())
                    && k.getKundenOrt().equals(kundenliste.get(i).getKundenOrt()))
            {
                System.out.println("Kunde existiert bereits!");
                kundedoppelt = true;
                JOptionPane.showMessageDialog(null, "Kunde existiert bereits!");
            }
            
        }
          if (kundedoppelt  == false)
        {
            kundenliste.add(k);
            knr++;
        }
        
        
        
        // Neue Kundennummer setzen
        
        return true;
    }

    public ArrayList<Kunde> getKundeliste() {
        return this.kundenliste;
    }

    public void setKundeliste(ArrayList<Kunde> kundenliste) {
        this.kundenliste = kundenliste;
    }

    public int getKnr() {
        return knr;
    }

    public void setKnr(int knr) {
        this.knr = knr;
    }
    
    // ? Evtl auch hier alle Getter und Setter Methoden ausschreiben ?
    

    // Nur zu Debug-/Testzwecken
    public void listeAusgeben() {
       // int x = 0;
        if (DEBUG) {
            System.out.println("Kundenliste");
            Iterator iter = kundenliste.iterator();

            while (iter.hasNext()) {
                Kunde k = (Kunde) iter.next();
                System.out.println(k.getKundenNr() + " - " + k.getKundenName() + " - " + k.getKundenVorname() + " - " +
                        k.getKundenOrt() + " - " + k.getKundenStrasse() + " - " + k.getKundenHausnummer());
            }
        }
    }

}
