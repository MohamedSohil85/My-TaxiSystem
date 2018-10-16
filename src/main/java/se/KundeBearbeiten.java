/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Dialog;
import java.awt.Dialog.ModalityType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import java.awt.Window;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class KundeBearbeiten implements Serializable{
    
    /*
 * Projekt: TaxiSys
 * Datei  : NeuerKundeDialog.java (modaler Dialog)
 * Gruppe : <hier Namen der Mitglieder eintragen>
 * Letzte ??nderung: 26.08.2016
 *
 * ??nderungen
 * 26.08.16 : Initiale Erstellung der Klasse
 *
 */

    final static boolean DEBUG = true;

    // Kundenobjekt f??r Eingabe
    static private Kunde kunde;
    static private Kundenliste kundenliste;
    static private int nummer;

    // Anzeige eines Dialogs zur Eingabe der Kundendaten
    public static Kunde showInputDialog(Window owner, String message, String title, Kundenliste kListe, Integer index) {
        
   
        kundenliste = kListe;  // Referenz speichern
        nummer = index;
        kunde = kListe.getKundeliste().get(nummer);
        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");
        
        //Kundennummer, nicht veränderbar
        JLabel jL_kundenNr = new JLabel("Kundennummer");
        JTextField jTF_kundenNr = new JTextField();   
        String kundenNum = kunde.getKundenNr();
        jTF_kundenNr.setText(String.valueOf(kundenNum));
        jTF_kundenNr.setEditable(false);
        jTF_kundenNr.setFocusable(false);
     
        //Kundenname, veränderbar
        JLabel jL_kundenName = new JLabel("Name");
        JTextField jTF_kundenName = new JTextField();
        String kundenName = kunde.getKundenName();
        jTF_kundenName.setText(kundenName);
        jTF_kundenName.setEditable(true);
        jTF_kundenName.setFocusable(true);
        
        //Kundenvorname, veränderbar
        JLabel jL_kundenVorname = new JLabel("Vorname");
        JTextField jTF_kundenVorname = new JTextField();
        String kundenVorname = kunde.getKundenVorname();
        jTF_kundenVorname.setText(kundenVorname);
        jTF_kundenVorname.setEditable(true);
        jTF_kundenVorname.setFocusable(true);
        
        //Ort, veränderbar
        JLabel jL_kundenOrt = new JLabel("Ort");
        JTextField jTF_kundenOrt = new JTextField();
        String kundenOrt = kunde.getKundenOrt();
        jTF_kundenOrt.setText(kundenOrt);
        jTF_kundenOrt.setEditable(true);
        jTF_kundenOrt.setFocusable(true);
        
        //Kundenstraße, veränderbar
        JLabel jL_kundenStrasse = new JLabel("Strasse");
        JTextField jTF_kundenStrasse = new JTextField();
        String kundenStrasse = kunde.getKundenStrasse();
        jTF_kundenStrasse.setText(kundenStrasse);
        jTF_kundenStrasse.setEditable(true);
        jTF_kundenStrasse.setFocusable(true);
        
        //KundenHausnummer, veränderbar
        JLabel jL_kundenHausnummer = new JLabel("Hausnummer");
        JTextField jTF_kundenHausnummer = new JTextField();
        String kundenHausnummer = kunde.getKundenHausnummer();
        jTF_kundenHausnummer.setText(kundenHausnummer);
        jTF_kundenHausnummer.setEditable(true);
        jTF_kundenHausnummer.setFocusable(true);
        
        
        int knr = kListe.getKnr();
        jTF_kundenNr.setText(String.valueOf(knr));
           
        jTF_kundenNr.setEditable(false);
        jTF_kundenNr.setFocusable(false);

        //JLabel jL_kundenName = new JLabel("Kundenname");
        //final JTextField jTF_kundenName = new JTextField();
        jTF_kundenName.setFocusable(true);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // OK-Button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             //   int nr = kundenliste.getKnr();
                // Neues Kundenobjekt anlegen 
                
// Abfangen mittels If = Null ! messagebox oder Rot markieren
// Abfrage ob Person bereits exisitiert
                
                
                kunde.changeKundenName(jTF_kundenName.getText());
                kunde.changeKundenVorname(jTF_kundenVorname.getText());
                kunde.changekundenOrt(jTF_kundenOrt.getText());
                kunde.changekundenStrasse(jTF_kundenStrasse.getText());
                kunde.changeKundenHausnummer(jTF_kundenHausnummer.getText());
                                           

                if (DEBUG) {
                    kunde.print();
                }
                
                JOptionPane.showMessageDialog(null, "Änderung vorgenommen");
                dialog.dispose();
                KundenFormular.showInputDialog(dialog, message, title, kundenliste);
                
            }
            
        });

        // Cancel-Button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });

        // Anordnung der GUI-Controls in 2 Spalten
        dialog.setLayout(new GridLayout(8, 2)); 
        dialog.add(messageLabel);
        dialog.add(versionLabel);

        dialog.add(jL_kundenNr);
        dialog.add(jTF_kundenNr);

        dialog.add(jL_kundenName);
        dialog.add(jTF_kundenName);
        
        dialog.add(jL_kundenVorname);
        dialog.add(jTF_kundenVorname);
         
        dialog.add(jL_kundenOrt);
        dialog.add(jTF_kundenOrt);
                
        dialog.add(jL_kundenStrasse);
        dialog.add(jTF_kundenStrasse);
                
        dialog.add(jL_kundenHausnummer);
        dialog.add(jTF_kundenHausnummer);
        
        dialog.add(okButton);
        dialog.add(cancelButton);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return null;
    }

}


