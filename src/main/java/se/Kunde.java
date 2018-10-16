/*
 * Projekt: TaxiSys
 * Datei  : Kunde.java (Entity Klasse)
 * Gruppe : <hier Namen der Mitglieder eintragen>
 * Letzte ??nderung: 26.08.2016
 *
 * ??nderungen
 * 26.08.16 : Initiale Erstellung der Klasse
 *
 */

package se;

import java.io.Serializable;

public class Kunde implements Serializable{
    
    final static boolean DEBUG = true;

    private String kundenNr;
    private String kundenName;
    private String kundenVorname;   // Setter und Getter
    private String kundenStrasse;          // Setter und Getter
    private String kundenOrt;             // Setter und Getter
    private String kundenHausnummer;         // Setter und Getter
    
    

    public Kunde() {

    }

    /**
     *
     * @param knr
     * @param name
     */
    public Kunde(String knr, String name,String vorname,String Strasse,String Ort,String Hausnummer) 
    {
        this.kundenNr = knr;
        this.kundenName = name;
        this.kundenVorname = vorname;
        this.kundenStrasse = Strasse;
        this.kundenOrt = Ort;
        this.kundenHausnummer = Hausnummer;
    }

    
    
    
    // Getter und Setter Methoden
    
    /**
     * @return the kundenName
     */
    public String getKundenName() {
        return kundenName;
    }

    /**
     * @param kundenName the kundenName to set
     */
    public void setKundenName(String kundenName) {
        this.kundenName = kundenName;
    }

    /**
     * @return the kundenStrasse
     */
    public String getKundenStrasse() {
        return kundenStrasse;
    }

    /**
     * @param kundenStrasse the kundenStra??e to set
     */
    public void setKundenStrasse(String kundenStrasse) {
        this.kundenStrasse = kundenStrasse;
    }

    /**
     * @return the kundenOrt
     */
    public String getKundenOrt() {
        return kundenOrt;
    }

    /**
     * @param kundenOrt the kundenOrt to set
     */
    public void setKundenOrt(String kundenOrt) {
        this.kundenOrt = kundenOrt;
    }

    /**
     * @return the kundenHausnummer
     */
    public String getKundenHausnummer() {
        return kundenHausnummer;
    }

    /**
     * @param kundenHausnummer the kundenHausnummer to set
     */
    public void setKundenHausnummer(String kundenHausnummer) {
        this.kundenHausnummer = kundenHausnummer;
    }

    /**
     * @return the kundenVorname
     */
    public String getKundenVorname() {
        return kundenVorname;
    }

    /**
     * @param kundenVorname the kundenVorname to set
     */
    public void setKundenVorname(String kundenVorname) {
        this.kundenVorname = kundenVorname;
    }

    /**
     * @return the kundenNr
     */
    public String getKundenNr() {
        return kundenNr;
    }

    /**
     * @param kundenNr the kundenNr to set
     */
    public void setKundenNr(String kundenNr) {
        this.kundenNr = kundenNr;
    }
 
    public void print(){
        System.out.println(kundenName + " - " + kundenVorname + " - " + kundenHausnummer + " - " + kundenOrt + " - " + kundenStrasse);
    }
    
    //Change Methoden
    
    public void changeKundenName(String s)
    {
        this.kundenName = this.kundenName.replace(kundenName, s);
    }
    public void changeKundenVorname(String s)
    {
        this.kundenVorname = this.kundenVorname.replace(kundenVorname, s);
    }
    public void changeKundenHausnummer(String s)
    {
        this.kundenHausnummer = this.kundenHausnummer.replace(kundenHausnummer, s);
    }
    public void changekundenOrt(String s)
    {
        this.kundenOrt = this.kundenOrt.replace(kundenOrt, s);
    }
    public void changekundenStrasse(String s)
    {
        this.kundenStrasse = this.kundenStrasse.replace(kundenStrasse, s);
    }
    
}
