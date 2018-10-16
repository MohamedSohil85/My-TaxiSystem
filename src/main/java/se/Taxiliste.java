/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import javax.swing.JOptionPane;

/**
 *
 * 
 */
public class Taxiliste implements Serializable{

    private ArrayList<Taxi> taxiliste;
    private int tNr;
    final static boolean DEBUG = false;

    public Taxiliste() {
        taxiliste = new ArrayList();
        tNr = 1;

        if (DEBUG) {
            
            String sTnr = Integer.toString(tNr);
            String sKz = "DA AB 0123";
            String sFtyp = "SUV";
            TaxiHinzufuegen(new Taxi(sTnr, sKz, sFtyp));

            sTnr = Integer.toString(tNr);
            sKz = "DA BC 0456";
            sFtyp = "Minivan";
            TaxiHinzufuegen(new Taxi(sTnr, sKz, sFtyp));
            
            sTnr = Integer.toString(tNr);
            sKz = "DA DE 0789";
            sFtyp = "Kleinwagen";
            TaxiHinzufuegen(new Taxi(sTnr, sKz, sFtyp));          
            
            
        }

    }

    public Boolean TaxiHinzufuegen(Taxi t) {
        
        // Taxiobjekt hinzuf??gen
        boolean taxiDoppelt = false;
        System.out.println("Taxihinzufuegen");
        
        
        for (int i = 0; i<taxiliste.size(); i++)
        {

            if(t.getKennzeichen().equals(taxiliste.get(i).getKennzeichen()) && t.getFahrzeugtyp().equals(taxiliste.get(i).getFahrzeugtyp()))
            {
                JOptionPane.showMessageDialog(null, "Taxi existiert bereits!");
                taxiDoppelt = true;
                break;
            }
            
        }
          if (taxiDoppelt  == false)
        {
            Date verfuegbarkeit [][] = new Date [100][2]; 
            
        for (int i=0 ; i<100 ; i++)
        {           
         verfuegbarkeit[i][0]=null;   
         verfuegbarkeit[i][1]=null;   
        }
        t.setVerfuegbarkeit(verfuegbarkeit);
        
            
            taxiliste.add(t);
            tNr++;
        }
        return true;
    }

    
    // Nur zu Debug-/Testzwecken
    public void listeAusgeben() {
        if (DEBUG) {
            System.out.println("Taxiliste");
            Iterator iter = getTaxiliste().iterator();

            while (iter.hasNext()) {
                Taxi tx = (Taxi) iter.next();
                System.out.println(tx.getTaxiNr() + " - " + tx.getKennzeichen() + " - " + tx.getFahrzeugtyp() + " - Fahrername : ");//+tx.getFahrer().getFahrerName());
                        System.out.println(tx.getFahrer().getFahrerName());
            }
        }
    }

    /**
     * @return the taxiliste
     */
    public ArrayList<Taxi> getTaxiliste() {
        return taxiliste;
    }

    /**
     * @param taxiliste the taxiliste to set
     */
    public void setTaxiliste(ArrayList<Taxi> taxiliste) {
        this.taxiliste = taxiliste;
    }

    /**
     * @return the tNr
     */
    public int getNr() {
        return tNr;
    }

    /**
     * @param tNr the tNr to set
     */
    public void setNr(int tNr) {
        this.tNr = tNr;
    }


}
