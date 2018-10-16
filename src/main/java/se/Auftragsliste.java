/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

//import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import static se.Auftragsliste.DEBUG;

/**
 *
 * 
 */
public final class Auftragsliste implements Serializable{

    final static boolean DEBUG = true;

    private ArrayList<Auftrag> auftragsliste;
    private int anr;

    // Konstruktor
    public Auftragsliste(Strassenkarte sK, Kundenliste kListe, Taxiliste tListe, Taxifahrerliste tfListe) {
        auftragsliste = new ArrayList();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        // Starte Auftragsnummer mit "1"
        anr = 1;

        if (DEBUG) {
            // Auftrag 1   
            String sAnr = Integer.toString(anr);

            String sZeit = "27.12.2017 15:00";
            Date dZeit = null;
            try {
                dZeit = formatter.parse(sZeit);
            } catch (ParseException e) {

            }
            /*
            Kunde k = new Kunde("1000", "Kathmann", "Margit", "Musterstrasse", "Musterort", "1337");
            Taxi taxi = new Taxi("1000", "DA DB 1010", "SUV");
            Taxifahrer fahrer = new Taxifahrer("2000", "Leven", "Jochen", "Musterstrasse", "Musterort", "17");
            */
            String sPreis = "30.00";
            String status = "Erstellt";
            auftragHinzufuegen(new Auftrag(kListe.getKundeliste().get(0), 
                    sAnr, 
                    sK.getStrassenliste().get(1), 
                    sK.getStrassenliste().get(4), 
                    dZeit, 
                    tListe.getTaxiliste().get(1), 
                    tfListe.getTaxifahrerListe().get(0), 
                    sPreis, 
                    status));

            // Auftrag 2
            sAnr = Integer.toString(anr);

            sZeit = "30.02.2017 18:00";
            try {
                dZeit = formatter.parse(sZeit);
            } catch (ParseException e) {

            }
            /*k = new Kunde("1010", "Amelie", "Kliehm", "Musterstrasse", "Musterort", "77");
            taxi = new Taxi("1010", "DI GG 1010", "VAN");
            fahrer = new Taxifahrer("2010", "Thea", "Friz", "Musterstrasse", "Musterort", "51");*/
            sPreis = "50.00";
            status = "Erstellt";
            auftragHinzufuegen(new Auftrag(kListe.getKundeliste().get(0), 
                    sAnr, 
                    sK.getStrassenliste().get(2), 
                    sK.getStrassenliste().get(7), 
                    dZeit, 
                    tListe.getTaxiliste().get(0), 
                    tfListe.getTaxifahrerListe().get(1), 
                    sPreis, 
                    status));
            // Auftrag 3
            sAnr = Integer.toString(anr);

            sZeit = "10.06.2017 10:00";
            try {
                dZeit = formatter.parse(sZeit);
            } catch (ParseException e) {

            }
            /*k = new Kunde("1020", "Lucas", "Conrad", "Musterstrasse", "Musterort", "25");
            taxi = new Taxi("1020", "DA AA 1020", "SUV");
            fahrer = new Taxifahrer("2020", "Weitzel", "Gerd", "Musterstrasse", "Musterort", "11");*/
            sPreis = "20.00";
            status = "Erstellt";
            auftragHinzufuegen(new Auftrag(kListe.getKundeliste().get(1), 
                    sAnr, 
                    sK.getStrassenliste().get(0), 
                    sK.getStrassenliste().get(5), 
                    dZeit, 
                    tListe.getTaxiliste().get(0), 
                    tfListe.getTaxifahrerListe().get(1), 
                    sPreis, 
                    status));
            // Auftrag 4
            sAnr = Integer.toString(anr);

            sZeit = "10.12.2016 10:00";
            try {
                dZeit = formatter.parse(sZeit);
            } catch (ParseException e) {

            }
            /*k = new Kunde("1020", "Lucas", "Conrad", "Musterstrasse", "Musterort", "25");
            taxi = new Taxi("1020", "DA AA 1020", "SUV");
            fahrer = new Taxifahrer("2020", "Weitzel", "Gerd", "Musterstrasse", "Musterort", "11");*/
            sPreis = "20.00";
            status = "Erstellt";
            auftragHinzufuegen(new Auftrag(kListe.getKundeliste().get(2), 
                    sAnr, 
                    sK.getStrassenliste().get(3), 
                    sK.getStrassenliste().get(1), 
                    dZeit, 
                    tListe.getTaxiliste().get(2), 
                    tfListe.getTaxifahrerListe().get(2), 
                    sPreis, 
                    status));
            // Auftrag 5
            sAnr = Integer.toString(anr);

            sZeit = "10.12.2016 10:00";
            try {
                dZeit = formatter.parse(sZeit);
            } catch (ParseException e) {

            }
            /*k = new Kunde("1020", "Lucas", "Conrad", "Musterstrasse", "Musterort", "25");
            taxi = new Taxi("1020", "DA AA 1020", "SUV");
            fahrer = new Taxifahrer("2020", "Weitzel", "Gerd", "Musterstrasse", "Musterort", "11");*/
            sPreis = "20.00";
            status = "Erstellt";
            auftragHinzufuegen(new Auftrag(kListe.getKundeliste().get(1), 
                    sAnr, 
                    sK.getStrassenliste().get(8), 
                    sK.getStrassenliste().get(10), 
                    dZeit, 
                    tListe.getTaxiliste().get(1), 
                    tfListe.getTaxifahrerListe().get(1), 
                    sPreis, 
                    status));
        }

    }

    public Boolean auftragHinzufuegen(Auftrag a) {
        // Kundenobjekt hinzufügen
        auftragsliste.add(a);

        
        // Neue Auftragsnummer setzen
        anr++;

        return true;
    }

    public int getAnr() {
        return anr;
    }

    public void setAnr(int anr) {
        this.anr = anr;
    }

    public void listeAusgeben() {
        // int x = 0;
        if (DEBUG) {
            System.out.println("Auftragsliste");
            Iterator iter = auftragsliste.iterator();

            while (iter.hasNext()) {
                Auftrag a = (Auftrag) iter.next();
                System.out.println(a.getAuftragsNr() + " - " + a.getStartOrt() + " - " + a.getZielOrt() + " - "
                        + a.getDatum() + " - " + a.getTaxi().getFahrzeugtyp() + " - " + a.getTaxifahrer().getFahrerVorname() + " "
                        + a.getTaxifahrer().getFahrerName() + " - " + a.getPreis());
            }
        }
    }

    public ArrayList<Auftrag> getAuftragsliste() {
        return auftragsliste;
    }

    public void setAuftragsliste(ArrayList<Auftrag> auftragsliste) {
        this.auftragsliste = auftragsliste;
    }

    public void statusaktualisieren() {
        Date Endzeit;
        double dauer;    // Temporär bis die Dauer für eine Fahrt berechnet werden kann

        for (int i = 0; i < auftragsliste.size(); i++) {
            dauer = 1 + (Double.parseDouble(auftragsliste.get(i).getPreis()) -2) * 2;
            
            Calendar cal = Calendar.getInstance();
            cal.setTime(auftragsliste.get(i).getDatum());
            cal.add(Calendar.MINUTE, (int)dauer);
            Endzeit = cal.getTime();
            if (new Date().before(auftragsliste.get(i).getDatum())) // Wenn aktuelles Datum vor dem Datum des Status liegt, dann wurde der Auftrag erstellt
            {
                auftragsliste.get(i).setmoeglicherstati(0);
            } else if (new Date().after(auftragsliste.get(i).getDatum()) && new Date().before(Endzeit)) //Wenn aktuelles Datum nach dem Datum des Auftrages kommt und vor der Endzeit ist dann in Bearbeitung 
            {
                auftragsliste.get(i).setmoeglicherstati(1);
            } else if (new Date().after(Endzeit)) //Wenn aktuelles Datum nach dem Datum des Auftrages kommt dann ist er abgeschlossen
            {
                auftragsliste.get(i).setmoeglicherstati(2);
            } else {
                System.out.println("Ungültiges Datum");
            }
        }
    }

    public void initAuftragFile(Kundenliste kListe, Taxiliste tListe, Taxifahrerliste fListe) {

    }

    public void writeToFile(Auftragsliste aListe) {

    }

    public void readFromFile() {

    }

    public void updateFile() {

    }
}
