/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Window;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author 
 */
public class AuftragBearbeiten implements Serializable {

    final static boolean DEBUG = true;

    // Kundenobjekt für Eingabe
    static private Auftrag auftrag;
    static private Auftragsliste auftragsliste;
    static private Kunde kunde;
    static private Kundenliste kundenliste;
    static private Taxi taxi;
    static private Taxiliste taxiliste;
    static private Taxifahrer taxifahrer;
    static private Taxifahrerliste taxifahrerliste;
    static private Strassenkarte strassenKarte;
    //static private int indexAuftrag;
    static private Date datum;
    static private String status;
    static private JSpinner JS_spinner;

    static private Strasse startStrasse;
    static private Strasse zielStrasse;

    // Anzeige eines Dialogs zur Eingabe der Kundendaten
    public static Auftrag showInputDialog(Window owner, String message, String title,
            Auftragsliste aListe, Kundenliste kListe, Taxiliste tListe, Taxifahrerliste fListe, Strassenkarte sKarte, int index) {
        //SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm");

        auftragsliste = aListe;  // Referenz 
        kundenliste = kListe;
        taxiliste = tListe;
        taxifahrerliste = fListe;
        strassenKarte = sKarte;
        int indexAuftrag = index;
        auftrag = aListe.getAuftragsliste().get(indexAuftrag);
        datum = auftrag.getDatum();

        kunde = auftrag.getKunde();
        int indexKunde = kundenliste.getKundeliste().indexOf(kunde);

        startStrasse = auftrag.getStartOrt();
        int indexStartStrasse = sKarte.getStrassenliste().indexOf(startStrasse);
        zielStrasse = auftrag.getZielOrt();
        int indexZielStrasse = sKarte.getStrassenliste().indexOf(zielStrasse);

        taxi = auftrag.getTaxi();
        int indexTaxi = taxiliste.getTaxiliste().indexOf(taxi);

        taxifahrer = auftrag.getTaxifahrer();
        int indexFahrer = taxifahrerliste.getTaxifahrerListe().indexOf(taxifahrer);

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");

        JLabel jL_auftragsNr = new JLabel("Auftragsnummer");
        JLabel jTF_auftragsNr = new JLabel();
        String auftragsNum = auftrag.getAuftragsNr();
        jTF_auftragsNr.setText(String.valueOf(auftragsNum));

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
        JLabel jL_startOrt = new JLabel("Start");
        JComboBox jCB_startstrassenlist = new JComboBox(strassenStringArr);
        jCB_startstrassenlist.setSelectedIndex(indexStartStrasse + 1);
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
        JLabel jL_zielOrt = new JLabel("Ziel");
        JComboBox jCB_zielstrassenlist = new JComboBox(strassenStringArr);
        jCB_zielstrassenlist.setSelectedIndex(indexZielStrasse + 1);
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
        JLabel jL_kunde = new JLabel("Kunde");
        final JTextField jTF_kunde = new JTextField();
        jTF_kunde.setFocusable(true);

        List<String> kundeStringList = new ArrayList<String>();
        kundeStringList.add("Bitte auswaehlen");
        for (int i = 0; i < kundenliste.getKundeliste().size(); i++) {
            String kNr, kVn, kNm;
            kNr = kundenliste.getKundeliste().get(i).getKundenNr();
            kVn = kundenliste.getKundeliste().get(i).getKundenVorname();
            kNm = kundenliste.getKundeliste().get(i).getKundenName();
            // auffuellen
            kundeStringList.add(kNr + ": " + kVn + " " + kNm);
        }

        String[] kundeStringArr = new String[kundeStringList.size()];
        kundeStringArr = kundeStringList.toArray(kundeStringArr);
        // 
        JComboBox jCB_kundenlist = new JComboBox(kundeStringArr);
        jCB_kundenlist.setSelectedIndex(indexKunde + 1);
        jCB_kundenlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // da Index in ComboBox gleich Index in Kundenliste ist, speichere es zwischen
                int tmpKundenIndex = jCB_kundenlist.getSelectedIndex();
                // 
                if (tmpKundenIndex != 0) {
                    tmpKundenIndex -= 1;
                    kunde = kundenliste.getKundeliste().get(tmpKundenIndex);
                } else {
                    kunde = null;
                }
            }
        });

        // Datum und Zeit
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

        List<String> taxiStringList = new ArrayList<String>();
        taxiStringList.add("Bitte auswaehlen");
        for (int i = 0; i < taxiliste.getTaxiliste().size(); i++) {
            String tNr, tKz;
            tNr = taxiliste.getTaxiliste().get(i).getTaxiNr();
            tKz = taxiliste.getTaxiliste().get(i).getKennzeichen();
            // TaxiListString auffuellen
            taxiStringList.add(tNr + ": " + tKz);
        }
        // Als Default wird erster Taxi zu einem Auftrag zugewiesen

        String[] taxiStringArr = new String[taxiStringList.size()];
        taxiStringArr = taxiStringList.toArray(taxiStringArr);

        JComboBox jCB_taxilist = new JComboBox(taxiStringArr);
        jCB_taxilist.setSelectedIndex(indexTaxi + 1);
        jCB_taxilist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int tmpTaxiIndex = jCB_taxilist.getSelectedIndex();
                if (tmpTaxiIndex != 0) {
                    tmpTaxiIndex -= 1;
                    taxi = taxiliste.getTaxiliste().get(tmpTaxiIndex);
                } else {
                    taxi = null;
                }
            }
        });

        // Taxifahrer
        JLabel jL_taxiFahrer = new JLabel("Taxi Fahrer");
        final JTextField jTF_taxiFahrer = new JTextField();
        jTF_taxiFahrer.setFocusable(true);

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

        // Als Default wird erster Taxifahrer zu einem Auftrag zugewiesen
        String[] taxifahrerStringArr = new String[fahrerStringList.size()];
        taxifahrerStringArr = fahrerStringList.toArray(taxifahrerStringArr);
        // 
        JComboBox jCB_taxifahrerlist = new JComboBox(taxifahrerStringArr);
        jCB_taxifahrerlist.setSelectedIndex(indexFahrer + 1);
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

        JLabel jL_Preis = new JLabel("Preis");
        final JTextField jTF_preis = new JTextField();
        String preis = auftrag.getPreis();
        jTF_preis.setText(preis);
        jTF_preis.setEditable(true);
        jTF_preis.setFocusable(true);

        JButton jB_preisBerechnen = new JButton("Berechnen");

        // Preis Berechnen
        jB_preisBerechnen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                if (startStrasse != null && zielStrasse != null) {
                    System.out.println("Strassen eingetragen: Berechnen");
                    for (int i = 0; i <= sKarte.getStrassenliste().size() - 1; i++) {
                        double distance = strassenKarte.DistanzBerechnen(startStrasse, zielStrasse);
                        double preis = distance + auftrag.getGrundPreis();
                        DecimalFormat dFormatter = new DecimalFormat("######.00");
                        String sPreis = dFormatter.format(preis);
                        jTF_preis.setText(sPreis);
                        //jTF_preis.setText(String.valueOf(preis));
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bitte Start und Ziel eintragen.\n Preis konnte nicht berechnet werden");
                }

            }
        });
        // Status wird automatisch berechnet. JLabel und JcomboBox werden nicht mehr gebraucht
        //JLabel jL_Status = new JLabel("Status");
        String[] auftragsStatus = {"Erstellt", "In Bearbeitung", "Abgeschlossen"};
        JComboBox jCB_Status = new JComboBox(auftragsStatus);

        for (int i = 0; i < auftragsStatus.length; i++) {
            if (status == auftragsStatus[i]) {
                jCB_Status.setSelectedIndex(i);
            }
        }

        //status = (String) jCB_Status.getSelectedItem();
        //jCB_Status.setEnabled(false);
        jCB_Status.setEditable(false);
        jCB_Status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = jCB_Status.getSelectedIndex();
                if (i != -1) {
                    status = (String) jCB_Status.getSelectedItem();
                }

            }
        });

        JButton jB_ok = new JButton("Speichern");
        JButton jB_cancel = new JButton("Abbrechen");
        //JButton jB_kalender = new JButton("Kalender");

        // OK-Button
        jB_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (kunde != null && taxi != null && taxifahrer != null
                        && !jTF_preis.getText().isEmpty()) {

                    auftrag.setKunde(kunde);
                    auftrag.setStartOrt(startStrasse);
                    auftrag.setZielOrt(zielStrasse);
                    datum = (Date) JS_spinner.getValue();
                    auftrag.setDatum(datum);
                    auftrag.setTaxi(taxi);
                    auftrag.setTaxifahrer(taxifahrer);
                    auftrag.setPreis(jTF_preis.getText());
                    auftrag.setStatus(status);
                    JOptionPane.showMessageDialog(null, "Aenderungen vorgenommen");
                } else {
                    JOptionPane.showMessageDialog(null, "Einige Felder sind leer.\n Auftrag koennte nicht bearbeitet werden");
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
                AuftragsFormular.showInputDialog(dialog, message, title, auftragsliste, kundenliste, taxiliste, taxifahrerliste, strassenKarte);
            }
        });

//        jB_kalender.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//
//            }
//        });
        // Anordnung der GUI-Controls in 2 Spalten
        dialog.setSize(500, 440);
        dialog.setLayout(null);

        dialog.add(messageLabel);
        dialog.add(versionLabel);
        messageLabel.setBounds(10, 10, 150, 25);
        versionLabel.setBounds(160, 10, 310, 25);

        dialog.add(jL_auftragsNr);
        dialog.add(jTF_auftragsNr);
        jL_auftragsNr.setBounds(10, 45, 150, 27);
        jTF_auftragsNr.setBounds(160, 45, 310, 27);

        dialog.add(jL_kunde);
        dialog.add(jCB_kundenlist);
        jL_kunde.setBounds(10, 80, 150, 27);
        jCB_kundenlist.setBounds(160, 80, 310, 27);

        dialog.add(jL_startOrt);
        dialog.add(jCB_startstrassenlist);
        jL_startOrt.setBounds(10, 115, 150, 27);
        jCB_startstrassenlist.setBounds(160, 115, 310, 27);

        dialog.add(jL_zielOrt);
        dialog.add(jCB_zielstrassenlist);
        jL_zielOrt.setBounds(10, 150, 150, 27);
        jCB_zielstrassenlist.setBounds(160, 150, 310, 27);

        /*
        dialog.add(jL_startOrt);
        dialog.add(jTF_startOrt);
        jL_startOrt.setBounds(10, 115, 150, 27);
        jTF_startOrt.setBounds(160, 115, 310, 27);

        dialog.add(jL_zielOrt);
        dialog.add(jTF_zielOrt);
        jL_zielOrt.setBounds(10, 150, 150, 27);
        jTF_zielOrt.setBounds(160, 150, 310, 27);
         */
        dialog.add(jL_datum);
        dialog.add(JS_spinner);
        jL_datum.setBounds(10, 185, 150, 27);
        JS_spinner.setBounds(160, 185, 218, 27);

        //dialog.add(jB_kalender);
        //jB_kalender.setBounds(389, 185, 80, 27);
        dialog.add(jL_taxi);
        dialog.add(jCB_taxilist);
        jL_taxi.setBounds(10, 220, 150, 27);
        jCB_taxilist.setBounds(160, 220, 310, 27);

        dialog.add(jL_taxiFahrer);
        dialog.add(jCB_taxifahrerlist);
        jL_taxiFahrer.setBounds(10, 255, 150, 27);
        jCB_taxifahrerlist.setBounds(160, 255, 310, 27);

        dialog.add(jL_Preis);
        dialog.add(jTF_preis);
        jL_Preis.setBounds(10, 290, 150, 27);
        jTF_preis.setBounds(160, 290, 200, 28);
        dialog.add(jB_preisBerechnen);
        jB_preisBerechnen.setBounds(380, 290, 90, 30);
        //dialog.add(jL_Status);
        //dialog.add(jCB_Status);
        //jL_Status.setBounds(10, 325, 150, 28);
        //jCB_Status.setBounds(160, 325, 310, 28);

        dialog.add(jB_ok);
        jB_ok.setBounds(280, 360, 90, 30);
        dialog.add(jB_cancel);
        jB_cancel.setBounds(380, 360, 90, 30);

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return null;
    }

}
