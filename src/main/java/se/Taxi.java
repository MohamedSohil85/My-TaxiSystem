/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 *
 */
public class Taxi implements Serializable{
    
    private String taxiNr;
    private String kennzeichen;
    private String fahrzeugtyp;
    private boolean verfuegbarTmp;
    private Date verfuegbarkeit [][];
    private Taxifahrer fahrer;
    static private Taxifahrerliste taxifahrerliste  = new Taxifahrerliste();
    private ArrayList<Taxifahrer> taxifahrerListe = taxifahrerliste.getTaxifahrerListe();
   
    public Taxi(){};
    
    public Taxi(String tNr, String knz, String fTyp){
        this.taxiNr = tNr;
        this.kennzeichen = knz;
        this.fahrzeugtyp = fTyp;
        verfuegbarTmp = true;
        Date verfuegbarkeit [][] = new Date [100][2]; 
        
        Taxifahrerliste taxifahrerliste = new Taxifahrerliste();
        
        int hit = getFreierFahrer();
        if ( hit != -1 ){            
        taxifahrerListe = taxifahrerliste.getTaxifahrerListe();
        fahrer = taxifahrerListe.get(hit);        
        }else {
            Taxifahrer dummi = new Taxifahrer("Kein FahrzeugNr","KeinFahrerName","KeinFahrerVorname","KeinFahrerStrasse","KeinFahrerOrt","KeinFahrerHausnummer");
            // String fNr, String fahrerName, String fahrerVorname, String fahrerStrasse, String fahrerOrt, String fahrerHausnummer){
            fahrer = dummi;            
            System.out.println("Es konnte kein Taxifahrer zugeordnet werden");
        }
    }
    
    public void print(){
        System.out.println(taxiNr + " - " + kennzeichen + " - " + fahrzeugtyp + " - " + verfuegbarTmp);
    }

    /**
     * @return the taxiNr
     */
    public String getTaxiNr() {
        return taxiNr;
    }

    /**
     * @param taxiNr the taxiNr to set
     */
    public void setTaxiNr(String taxiNr) {
        this.taxiNr = taxiNr;
    }

    /**
     * @return the kennzeichen
     */
    public String getKennzeichen() {
        return kennzeichen;
    }

    /**
     * @param kennzeichen the kennzeichen to set
     */
    public void setKennzeichen(String kennzeichen) {
        this.kennzeichen = kennzeichen;
    }

    /**
     * @return the fahrzeugtyp
     */
    public String getFahrzeugtyp() {
        return fahrzeugtyp;
    }

    /**
     * @param fahrzeugtyp the fahrzeugtyp to set
     */
    public void setFahrzeugtyp(String fahrzeugtyp) {
        this.fahrzeugtyp = fahrzeugtyp;
    }

    /**
     * @return the verfuegbar
     */
    public boolean getVerfuegbar() {
        return verfuegbarTmp;
    }

    /**
     * @param verfuegbar the verfuegbar to set
     */
    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbarTmp = verfuegbar;
    }

    /**
     * @return the verfuegbarkeit
     */
    public Date[][] getVerfuegbarkeit() {
        return verfuegbarkeit;
    }
    
    public void setVerfuegbarkeitListe(Date datum) {
        
        for (int j=0; j<100; j++)       // setzen des Anfangsdatum und Endzeitpunkt in die Liste
                                        // Umbauen : Anfangsdatum && Enddatum vor Erstelldatum ODER Anfangsdatum && Enddatum nach Erstelldatum
        {
            if ( verfuegbarkeit[j][0] == null) { verfuegbarTmp=true; continue; }
            if( verfuegbarkeit[j][0].before(datum) && verfuegbarkeit[j][1].before(datum))   // Wenn Anfangsdatum && Enddatum  vor Erstelldatum
            {
                verfuegbarTmp=true;
            }
            else if( verfuegbarkeit[j][0].after(datum) && verfuegbarkeit[j][1].after(datum))  // Wenn Anfangsdatum && Enddatum  nach Erstelldatum
            {
                verfuegbarTmp=true;
            }
            else                        // Wenn Anfangsdatum && Enddatum  zwischen Erstelldatum
            {
                verfuegbarTmp=false;
            }
        }
    }

    /**
     * @param verfuegbarkeit the verfuegbarkeit to set
     */

    public void setVerfuegbarkeit(Date[][] verfuegbarkeit) {
        this.verfuegbarkeit = verfuegbarkeit;
    }
    
    public int getFreierFahrer()
    {   
        int hit = -1;
        
        for ( int i=0; i<taxifahrerListe.size();i++)
        {
            if ( taxifahrerListe.get(i).getVerfuegbar() == true )
            {
                hit = i;
                taxifahrerListe.get(i).setVerfuegbar(false);
                System.out.println("freier Fahrer gefunden i: " + i + " "+taxifahrerListe.get(i).getFahrerName());
                break;
            }
            System.out.println("Keinen freien Fahrer gefunden i: " + i );
        }
        return hit;
    }

    /**
     * @return the fahrer
     */
    public Taxifahrer getFahrer() {
        return fahrer;
    }

    /**
     * @param fahrer the fahrer to set
     */
    public void setFahrer(Taxifahrer fahrer) {
        this.fahrer = fahrer;
    }
    
}
