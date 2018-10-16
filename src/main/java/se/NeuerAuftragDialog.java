/*
 * Projekt: TaxiSys
 * Datei  : NeuerKundeDialog.java (modaler Dialog)
 * Gruppe : <hier Namen der Mitglieder eintragen>
 * Letzte Änderung: 26.08.2016
 *
 * Änderungen
 * 26.08.16 : Initiale Erstellung der Klasse
 *
 *
 */
package se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class NeuerAuftragDialog implements Serializable{

    final static boolean DEBUG = false;

    // Kundenobjekt für Eingabe
    static private Auftrag auftrag;
    static private Auftragsliste auftragsliste;

    static private Taxi taxi;
    static private Taxiliste taxiliste;

    static private Taxifahrer taxifahrer;
    static private Taxifahrerliste taxifahrerliste;

    static private Kunde kunde;
    static private Kundenliste kundenliste;

    static private Strasse startStrasse;
    static private Strasse zielStrasse;
    static private Strassenkarte strassenKarte;
    
    static private Date datum;
    static private JSpinner JS_spinner;

    // Anzeige eines Dialogs zur Eingabe der Kundendaten
    public static Auftrag showInputDialog(Window owner, String message, String title,
            Auftragsliste aliste, Kundenliste kListe, Taxiliste tListe, Taxifahrerliste fListe, Strassenkarte sKarte) {

        auftragsliste = aliste;  // Referenz speichern
        kundenliste = kListe;
        taxiliste = tListe;
        taxifahrerliste = fListe;
        strassenKarte = sKarte;
        
        auftrag = new Auftrag();
        datum = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title/*, ModalityType.APPLICATION_MODAL*/);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        //final JDialog dialog = new JDialog(owner, title );
        //dialog.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");

        JLabel jL_auftragsNr = new JLabel("Auftragsnummer");
        JLabel jTF_auftragsNr = new JLabel();
        int anr = aliste.getAnr();
        jTF_auftragsNr.setText(String.valueOf(anr));

                
        //Strassen
        List<String> strasseStringList = new ArrayList<String>();
        strasseStringList.add("Bitte auswaehlen");
        for (int i = 0; i < strassenKarte.getStrassenliste().size(); i++) {
            String sName;
            sName = strassenKarte.getStrassenliste().get(i).getStrassenName();
            strasseStringList.add(sName);
        }
        String[] strassenStringArr = new String[strasseStringList.size()];
        strassenStringArr = strasseStringList.toArray(strassenStringArr);  
        
        
        //Start
        JLabel jL_startStrasse = new JLabel("Start");
        JComboBox jCB_startstrassenlist = new JComboBox(strassenStringArr);  
        jCB_startstrassenlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // da Index in ComboBox gleich Index in Kundenliste ist, speichere es zwichen
                int tmpStrassenIndex = jCB_startstrassenlist.getSelectedIndex();
                // falls keine Auswahl getroffen, dann liefert die getSelectedIndex() Methode -1 zurueck
                // if(getSelectedIndex() !=-1 && getSelectedIndex() !=0)
                if (tmpStrassenIndex > 0) {
                    tmpStrassenIndex -= 1;
                    startStrasse = strassenKarte.getStrassenliste().get(tmpStrassenIndex);
                } else {
                    startStrasse = null;
                }

            }
        });
        //Ziel
        JLabel jL_zielStrasse = new JLabel("Ziel");
        JComboBox jCB_zielstrassenlist = new JComboBox(strassenStringArr);
        
        jCB_zielstrassenlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // da Index in ComboBox gleich Index in Kundenliste ist, speichere es zwichen
                int tmpStrassenIndex = jCB_zielstrassenlist.getSelectedIndex();
                // falls keine Auswahl getroffen, dann liefert die getSelectedIndex() Methode -1 zurueck
                // if(getSelectedIndex() !=-1 && getSelectedIndex() !=0)
                if (tmpStrassenIndex > 0) {
                    tmpStrassenIndex -= 1;
                    zielStrasse = strassenKarte.getStrassenliste().get(tmpStrassenIndex);
                } else {
                    zielStrasse = null;
                }

            }
        });  
        

        //Kunde
        List<String> kundeStringList = new ArrayList<String>();
        kundeStringList.add("Bitte auswaehlen");
        for (int i = 0; i < kundenliste.getKundeliste().size(); i++) {
            String kNr, kVn, kNm;
            kNr = kundenliste.getKundeliste().get(i).getKundenNr();
            kVn = kundenliste.getKundeliste().get(i).getKundenVorname();
            kNm = kundenliste.getKundeliste().get(i).getKundenName();
            // TaxiListString auffuellen
            kundeStringList.add(kNr + ": " + kVn + " " + kNm);
        }

        JLabel jL_kunde = new JLabel("Kunde");
        
        String[] kundeStringArr = new String[kundeStringList.size()];
        kundeStringArr = kundeStringList.toArray(kundeStringArr);
        JComboBox jCB_kundenlist = new JComboBox(kundeStringArr);
        jCB_kundenlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // da Index in ComboBox gleich Index in Kundenliste ist, speichere es zwichen
                int tmpKundenIndex = jCB_kundenlist.getSelectedIndex();
                // falls keine Auswahl getroffen, dann liefert die getSelectedIndex() Methode -1 zurueck
                // if(getSelectedIndex() !=-1 && getSelectedIndex() !=0)
                if (tmpKundenIndex > 0) {
                    tmpKundenIndex -= 1;
                    kunde = kundenliste.getKundeliste().get(tmpKundenIndex);
                } else {
                    kunde = null;
                }

            }
        });


        // Datum und zeit
        JLabel jL_datum = new JLabel("Datum (dd.MM.yyyy HH:mm)");
        SpinnerDateModel SDM_dateModel = new SpinnerDateModel();
        JS_spinner = new JSpinner(SDM_dateModel);
        //Date datenow = Calendar.getInstance().getTime();
        SpinnerDateModel sdm = new SpinnerDateModel(datum, null, null, Calendar.HOUR_OF_DAY);
        JS_spinner.setModel(sdm);
        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(JS_spinner, "dd-MMM-yyyy HH:mm");
        JS_spinner.setEditor(dateEditor);
        ((JSpinner.DefaultEditor) JS_spinner.getEditor()).getTextField().setEditable(false);

        // Taxi 
        JLabel jL_taxi = new JLabel("Taxi");
        final JTextField jTF_taxi = new JTextField();
        jTF_taxi.setFocusable(true);

        Date verfuegbarkeit[][] = new Date[100][2];   // 100 Zeiten, 2 Aufträge

        for (int i = 0; i < taxiliste.getTaxiliste().size(); i++) // Alle Taxis die Verfügbarkeit aktualisieren
        {
            taxiliste.getTaxiliste().get(i).setVerfuegbarkeitListe(datum);
        }

        List<String> taxiStringList = new ArrayList<String>();
        taxiStringList.add("Bitte auswaehlen");
        for (int i = 0; i < taxiliste.getTaxiliste().size(); i++) {
            String tNr, tKz, tFt;
            if (taxiliste.getTaxiliste().get(i).getVerfuegbar() == true) {
                tNr = taxiliste.getTaxiliste().get(i).getTaxiNr();
                tKz = taxiliste.getTaxiliste().get(i).getKennzeichen();
                taxiStringList.add(tNr + ": " + tKz);
            }
        }

        String[] taxiStringArr = new String[taxiStringList.size()];
        taxiStringArr = taxiStringList.toArray(taxiStringArr);
        // Combobox initialisieren
        JComboBox jCB_taxilist = new JComboBox(taxiStringArr);
        jCB_taxilist.addActionListener(new ActionListener() {
            @Override

            public void actionPerformed(ActionEvent e) {

                String tmpTaxi = "0";

                if (jCB_taxilist.getSelectedIndex() != -1) {
                    tmpTaxi = jCB_taxilist.getSelectedItem().toString(); // 2:DA BC 0456

                    if (tmpTaxi != "Bitte auswaehlen") {
                        tmpTaxi = tmpTaxi.substring(0, tmpTaxi.length() - 12);     // 2
                        for (int i = 0; i < taxiliste.getTaxiliste().size(); i++) {
                            if (Integer.parseInt(taxiliste.getTaxiliste().get(i).getTaxiNr()) == Integer.parseInt(tmpTaxi)) {
                                taxi = taxiliste.getTaxiliste().get(i);
                            }
                        }
                    }
                } else {
                    taxi = null;
                }
                //taxi = taxiliste.getTaxiliste().get(1);
            }
        });
        //jCB_taxilist.setSelectedIndex(1);

        // Taxifahrer
        JLabel jL_taxiFahrer = new JLabel("Taxi Fahrer");
        List<String> fahrerStringList = new ArrayList<String>();
        fahrerStringList.add("Bitte auswaehlen");
        for (int i = 0; i < taxifahrerliste.getTaxifahrerListe().size(); i++) {
            String Nr, vN, nN;
            Nr = taxifahrerliste.getTaxifahrerListe().get(i).getFahrerNr();
            vN = taxifahrerliste.getTaxifahrerListe().get(i).getFahrerVorname();
            nN = taxifahrerliste.getTaxifahrerListe().get(i).getFahrerName();
            // TaxiListString auffuellen
            fahrerStringList.add(Nr + ": " + vN + " " + nN);
        }
        //Taxifahrerliste
        String[] fahrerStringArr = new String[fahrerStringList.size()];
        fahrerStringArr = fahrerStringList.toArray(fahrerStringArr);
        JComboBox jCB_taxifahrerlist = new JComboBox(fahrerStringArr);
        jCB_taxifahrerlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int tmpFahrerIndex = jCB_taxifahrerlist.getSelectedIndex();
                if (tmpFahrerIndex != 0) {
                    tmpFahrerIndex -= 1;
                    taxifahrer = taxifahrerliste.getTaxifahrerListe().get(tmpFahrerIndex);
                } else {
                    taxifahrer = null;
                }
            }
        });

        JLabel jL_Preis = new JLabel("Preis (Euro)");
        final JTextField jTF_preis = new JTextField();
        jTF_preis.setFocusable(true);
        JButton jB_preisBerechnen = new JButton("Berechnen");

        // Preis Berechnen
        jB_preisBerechnen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
               if(startStrasse != null && zielStrasse !=null){
                   System.out.println("Strassen eingetragen: Berechnen");
                   for (int i = 0; i <=sKarte.getStrassenliste().size()-1; i++) {
                       double distance = strassenKarte.DistanzBerechnen(startStrasse, zielStrasse);
                       double preis = distance + auftrag.getGrundPreis();
                       DecimalFormat dFormatter = new DecimalFormat("######.00");
                       String sPreis = dFormatter.format(preis);
                       jTF_preis.setText(sPreis);
                       //jTF_preis.setText(String.valueOf(preis));
                   }
               }else{
                   JOptionPane.showMessageDialog(null, "Bitte Start und Ziel eintragen.\n Preis konnte nicht berechnet werden");
               }
                
            }
        });

        JButton jB_ok = new JButton("Speichern");
        JButton jB_cancel = new JButton("Abbrechen");
        JButton jB_aktualisieren = new JButton("Aktualisieren");

        // Aktualisieren
        jB_aktualisieren.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                taxiStringList.clear();
                datum = (Date)JS_spinner.getValue();
//                try {
//                    datum = formatter.parse(strDatum);
//                } catch (ParseException pe) {
//                };

                taxiStringList.add("Bitte auswaehlen");

                for (int i = 0; i < taxiliste.getTaxiliste().size(); i++) {
                    taxiliste.getTaxiliste().get(i).setVerfuegbarkeitListe(datum);

                    String tNr, tKz;
                    if (taxiliste.getTaxiliste().get(i).getVerfuegbar() == true) {
                        tNr = taxiliste.getTaxiliste().get(i).getTaxiNr();
                        tKz = taxiliste.getTaxiliste().get(i).getKennzeichen();
                        taxiStringList.add(tNr + ": " + tKz);
                    }
                }
                String[] taxiStringArr = new String[taxiStringList.size()];
                taxiStringArr = taxiStringList.toArray(taxiStringArr);

                jCB_taxilist.removeAllItems();

                for (int i = 0; i < taxiStringArr.length; i++) {
                    jCB_taxilist.addItem(taxiStringArr[i]);
                }
            }
        });

        // OK-Button
        jB_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (kunde != null && taxi != null && taxifahrer != null
                        && !jTF_preis.getText().isEmpty()) {

                    int nr = auftragsliste.getAnr();

                    auftrag.setAuftragsNr(String.valueOf(nr));
                    auftrag.setKunde(kunde);
                    auftrag.setStartOrt(startStrasse);
                    auftrag.setZielOrt(zielStrasse);
                    datum = (Date) JS_spinner.getValue();
                    auftrag.setDatum(datum);
                    auftrag.setTaxi(taxi);
                    auftrag.setTaxifahrer(taxifahrer);
                    auftrag.setPreis(jTF_preis.getText());

                    auftragsliste.auftragHinzufuegen(auftrag);

                    Calendar cal = Calendar.getInstance();

                    Date verfuegbarkeit[][] = new Date[100][2];
                    verfuegbarkeit = taxi.getVerfuegbarkeit();

                    for (int i = 0; i < 100; i++) // setzen des Anfangsdatum und Endzeitpunkt in die Liste
                    {
                        if (verfuegbarkeit[i][0] == null) {
                            verfuegbarkeit[i][0] = datum;          // Anfangszeit
                            cal.setTime(datum);
                            cal.add(Calendar.HOUR, 2);
                            verfuegbarkeit[i][1] = cal.getTime();  // Endzeitpunkt                        
                            continue;
                        }
                    }

                    JOptionPane.showMessageDialog(null, "Auftrag gespeichert");
                } else {
                    JOptionPane.showMessageDialog(null, "Einige Felder sind leer.\n Auftrag koennte nicht gespeichert werden");
                }

                dialog.dispose();
                AuftragsFormular.showInputDialog(dialog, message, title, auftragsliste, kundenliste, taxiliste, taxifahrerliste, strassenKarte);

            }
        });

        // Cancel-Button
        jB_cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // Anordnung der GUI-Controls in 2 Spalten
        dialog.setSize(500, 440);
        dialog.setLayout(null);

        dialog.add(messageLabel);
        dialog.add(versionLabel);
        messageLabel.setBounds(10, 10, 150, 28);
        versionLabel.setBounds(160, 10, 310, 28);

        dialog.add(jL_auftragsNr);
        dialog.add(jTF_auftragsNr);
        jL_auftragsNr.setBounds(10, 45, 150, 28);
        jTF_auftragsNr.setBounds(160, 45, 310, 28);

        dialog.add(jL_kunde);
        dialog.add(jCB_kundenlist);
        jL_kunde.setBounds(10, 80, 150, 28);
        jCB_kundenlist.setBounds(160, 80, 310, 28);

        dialog.add(jL_startStrasse);
        dialog.add(jCB_startstrassenlist);
        jL_startStrasse.setBounds(10, 115, 150, 28);
        jCB_startstrassenlist.setBounds(160, 115, 310, 28);

        dialog.add(jL_zielStrasse);
        dialog.add(jCB_zielstrassenlist);
        jL_zielStrasse.setBounds(10, 150, 150, 28);
        jCB_zielstrassenlist.setBounds(160, 150, 310, 28);

//        dialog.add(jL_datum);
//        dialog.add(jTF_datum);
//        jL_datum.setBounds(10, 185, 150, 28);
//        jTF_datum.setBounds(160, 185, 218, 28);
        
        dialog.add(jL_datum);
        dialog.add(JS_spinner);
        jL_datum.setBounds(10, 185, 150, 27);
        JS_spinner.setBounds(160, 185, 218, 27);

        dialog.add(jL_taxi);
        jL_taxi.setBounds(10, 220, 150, 28);
        dialog.add(jCB_taxilist);
        jCB_taxilist.setBounds(160, 220, 310, 28);

        dialog.add(jL_taxiFahrer);
        dialog.add(jCB_taxifahrerlist);
        jL_taxiFahrer.setBounds(10, 255, 150, 28);
        jCB_taxifahrerlist.setBounds(160, 255, 310, 28);

        dialog.add(jL_Preis);
        dialog.add(jTF_preis);
        jL_Preis.setBounds(10, 290, 150, 28);
        jTF_preis.setBounds(160, 290, 200, 28);
        dialog.add(jB_preisBerechnen);
        jB_preisBerechnen.setBounds(380, 290, 90, 30);

        dialog.add(jB_ok);
        jB_ok.setBounds(280, 360, 90, 30);
        dialog.add(jB_cancel);
        jB_cancel.setBounds(380, 360, 90, 30);
        dialog.add(jB_aktualisieren);
        jB_aktualisieren.setBounds(380, 185, 90, 30);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return null;

    }

}
