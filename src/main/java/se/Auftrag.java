/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

//import java.util.Date;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author 
 */
public class Auftrag implements Serializable{

    private Kunde kunde;
    private String auftragsNr;
    private Strasse startOrt;
    private Strasse zielOrt;
    private Date datum;
    private Taxi taxi;
    private Taxifahrer taxifahrer;
    private final double grundPreis = 2;
    private String preis;
    private String status;
    private static final String[] moeglichestati = {
        "Erstellt", "In_Bearbeitung", "Abgeschlossen"};
   
    public Auftrag() {
    }

    public Auftrag(Kunde kunde, String auftragsNr, Strasse startOrt, Strasse zielOrt,
            Date zeit, Taxi taxi, Taxifahrer taxifahrer, String preis, String status) {
        this.kunde = kunde;
        this.auftragsNr = auftragsNr;
        this.startOrt = startOrt;
        this.zielOrt = zielOrt;
        this.datum = zeit;
        this.taxi = taxi;
        this.taxifahrer = taxifahrer;
        this.preis = preis;
        this.status = status;
    }

    //Setter für moeglicheStati
    public void setmoeglicherstati(int nr){
        if (nr < 0 && nr > 2)
        {
            System.out.println("Neuer Status ungueltiger Index");
        }
        else
        {
        this.status = moeglichestati[nr];
        }
    }
    
  
    
    /**
     * @return the kunde
     */
    
    
    
    
    public Kunde getKunde() {
        return kunde;
    }

    /**
     * @param kunde the kunde to set
     */
    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    /**
     * @return the auftragsNr
     */
    public String getAuftragsNr() {
        return auftragsNr;
    }

    /**
     * @param auftragsNr the auftragsNr to set
     */
    public void setAuftragsNr(String auftragsNr) {
        this.auftragsNr = auftragsNr;
    }

    /**
     * @return the startOrt
     */
    public Strasse getStartOrt() {
        return startOrt;
    }

    /**
     * @param startOrt the startOrt to set
     */
    public void setStartOrt(Strasse startOrt) {
        this.startOrt = startOrt;
    }

    /**
     * @return the zielOrt
     */
    public Strasse getZielOrt() {
        return zielOrt;
    }

    /**
     * @param zielOrt the zielOrt to set
     */
    public void setZielOrt(Strasse zielOrt) {
        this.zielOrt = zielOrt;
    }

    /**
     * @return the zeit
     */
    public Date getDatum() {
        return datum;
    }

    /**
     * @param d the zeit to set
     */
    public void setDatum(Date d) {
        this.datum = d;
    }

    /**
     * @return the taxi
     */
    public Taxi getTaxi() {
        return taxi;
    }

    /**
     * @param taxi the taxi to set
     */
    public void setTaxi(Taxi taxi) {
        this.taxi = taxi;
    }

    /**
     * @return the taxifahrer
     */
    public Taxifahrer getTaxifahrer() {
        return taxifahrer;
    }

    /**
     * @param taxifahrer the taxifahrer to set
     */
    public void setTaxifahrer(Taxifahrer taxifahrer) {
        this.taxifahrer = taxifahrer;
    }

    /**
     * @return the preis
     */
    public String getPreis() {
        return preis;
    }

    /**
     * @param preis the preis to set
     */
    public void setPreis(String preis) {
        this.preis = preis;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the grundPreis
     */
    public double getGrundPreis() {
        return grundPreis;
    }

}
