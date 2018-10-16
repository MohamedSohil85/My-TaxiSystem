
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * 
 */
public class NeuerTaxifahrerDialog implements Serializable{
    
    final static boolean DEBUG = true;

    // Fahrerobjekt für Eingabe
    static private Taxifahrer taxifahrer;
    static private Taxifahrerliste taxifahrerliste;

    // Anzeige eines Dialogs zur Eingabe der Fahrerdaten
    public static Taxifahrer showInputDialog(Window owner, String message, String title, Taxifahrerliste fListe) {

        taxifahrerliste = fListe;  // Referenz speichern

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");

        JLabel jL_fahrerNr = new JLabel("Taxifahrernummer");
        JTextField jTF_fahrerNr = new JTextField();        
     
        JLabel jL_fahrerName = new JLabel("Name");
        JTextField jTF_fahrerName = new JTextField();
        
        JLabel jL_fahrerVorname = new JLabel("Vorname");
        JTextField jTF_fahrerVorname = new JTextField();
        
        JLabel jL_fahrerOrt = new JLabel("Ort");
        JTextField jTF_fahrerOrt = new JTextField();
        
        JLabel jL_fahrerStrasse = new JLabel("Strasse");
        JTextField jTF_fahrerStrasse = new JTextField();
        
        JLabel jL_fahrerHausnummer = new JLabel("Hausnummer");
        JTextField jTF_fahrerHausnummer = new JTextField();
        
        
        int fNr = fListe.getfNr();
        jTF_fahrerNr.setText(String.valueOf(fNr));
           
        jTF_fahrerNr.setEditable(false);
        jTF_fahrerNr.setFocusable(false);

        jTF_fahrerName.setFocusable(true);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // OK-Button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nr = fListe.getfNr();
                // Neues Fahrerobjekt anlegen 
                taxifahrer = new Taxifahrer();
                
// Abfangen mittels If = Null ! messagebox oder Rot markieren
// Abfrage ob Person bereits exisitiert
                
                taxifahrer.setFahrerNr(String.valueOf(nr));
                taxifahrer.setFahrerName(jTF_fahrerName.getText());
                taxifahrer.setFahrerVorname(jTF_fahrerVorname.getText());
                taxifahrer.setFahrerOrt(jTF_fahrerOrt.getText());
                taxifahrer.setFahrerStrasse(jTF_fahrerStrasse.getText());
                taxifahrer.setFahrerHausnummer(jTF_fahrerHausnummer.getText());
                                           
                fListe.fahrerHinzufuegen(taxifahrer);

                if (DEBUG) {
                    fListe.listeAusgeben();
                }
                dialog.dispose();
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

        dialog.add(jL_fahrerNr);
        dialog.add(jTF_fahrerNr);

        dialog.add(jL_fahrerName);
        dialog.add(jTF_fahrerName);
        
        dialog.add(jL_fahrerVorname);
        dialog.add(jTF_fahrerVorname);
         
        dialog.add(jL_fahrerOrt);
        dialog.add(jTF_fahrerOrt);
                
        dialog.add(jL_fahrerStrasse);
        dialog.add(jTF_fahrerStrasse);
                
        dialog.add(jL_fahrerHausnummer);
        dialog.add(jTF_fahrerHausnummer);
        
        dialog.add(okButton);
        dialog.add(cancelButton);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return null;
    }
}
