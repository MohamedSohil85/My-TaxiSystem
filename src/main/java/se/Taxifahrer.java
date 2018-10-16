
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.io.Serializable;

/**
 *
 * 
 */
public class Taxifahrer implements Serializable{

    private String fahrerNr;
    private String fahrerName;
    private String fahrerVorname;   
    private String fahrerStrasse;      
    private String fahrerOrt;      
    private String fahrerHausnummer; 
    private boolean verfuegbar;
    
    public Taxifahrer(){};
    
    public Taxifahrer(String fNr, String fahrerName, String fahrerVorname, String fahrerStrasse, String fahrerOrt, String fahrerHausnummer){
        this.fahrerNr = fNr;
        this.fahrerName = fahrerName;
        this.fahrerVorname = fahrerVorname;
        this.fahrerStrasse = fahrerStrasse;
        this.fahrerOrt = fahrerOrt;
        this.fahrerHausnummer = fahrerHausnummer;
        verfuegbar = true;
    }
    
    /**
     * @return the fahrerNr
     */
    public String getFahrerNr() {
        return fahrerNr;
    }

    /**
     * @param fahrerNr the fahrerNr to set
     */
    public void setFahrerNr(String fahrerNr) {
        this.fahrerNr = fahrerNr;
    }

    /**
     * @return the fahrerName
     */
    public String getFahrerName() {
        return fahrerName;
    }

    /**
     * @param fahrerName the fahrerName to set
     */
    public void setFahrerName(String fahrerName) {
        this.fahrerName = fahrerName;
    }

    /**
     * @return the fahrerVorname
     */
    public String getFahrerVorname() {
        return fahrerVorname;
    }

    /**
     * @param fahrerVorname the fahrerVorname to set
     */
    public void setFahrerVorname(String fahrerVorname) {
        this.fahrerVorname = fahrerVorname;
    }

    /**
     * @return the fahrerStrasse
     */
    public String getFahrerStrasse() {
        return fahrerStrasse;
    }

    /**
     * @param fahrerStrasse the fahrerStrasse to set
     */
    public void setFahrerStrasse(String fahrerStrasse) {
        this.fahrerStrasse = fahrerStrasse;
    }

    /**
     * @return the fahrerOrt
     */
    public String getFahrerOrt() {
        return fahrerOrt;
    }

    /**
     * @param fahrerOrt the fahrerOrt to set
     */
    public void setFahrerOrt(String fahrerOrt) {
        this.fahrerOrt = fahrerOrt;
    }

    /**
     * @return the fahrerHausnummer
     */
    public String getFahrerHausnummer() {
        return fahrerHausnummer;
    }

    /**
     * @param fahrerHausnummer the fahrerHausnummer to set
     */
    public void setFahrerHausnummer(String fahrerHausnummer) {
        this.fahrerHausnummer = fahrerHausnummer;
    }

    /**
     * @return the verfuegbar
     */
    public boolean getVerfuegbar() {
        return verfuegbar;
    }

    /**
     * @param verfuegbar the verfuegbar to set
     */
    public void setVerfuegbar(boolean verfuegbar) {
        this.verfuegbar = verfuegbar;
    }
    
 
}
