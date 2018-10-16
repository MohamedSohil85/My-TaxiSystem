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

package se;

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

public class NeuerKundeDialog implements Serializable{

    final static boolean DEBUG = true;

    // Kundenobjekt f??r Eingabe
    static private Kunde kunde;
    static private Kundenliste kundenliste;

    // Anzeige eines Dialogs zur Eingabe der Kundendaten
    public static Kunde showInputDialog(Window owner, String message, String title, Kundenliste kliste) {

        kundenliste = kliste;  // Referenz speichern

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");

        JLabel jL_kundenNr = new JLabel("Kundennummer");
        JTextField jTF_kundenNr = new JTextField();        
     
        JLabel jL_kundenName = new JLabel("Name");
        JTextField jTF_kundenName = new JTextField();
        
        JLabel jL_kundenVorname = new JLabel("Vorname");
        JTextField jTF_kundenVorname = new JTextField();
        
        JLabel jL_kundenOrt = new JLabel("Ort");
        JTextField jTF_kundenOrt = new JTextField();
        
        JLabel jL_kundenStrasse = new JLabel("Strasse");
        JTextField jTF_kundenStrasse = new JTextField();
        
        JLabel jL_kundenHausnummer = new JLabel("Hausnummer");
        JTextField jTF_kundenHausnummer = new JTextField();
        
        
        int knr = kliste.getKnr();
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
                if(jTF_kundenName.getText().compareTo("") == 0 ||
                        jTF_kundenVorname.getText().compareTo("") == 0 ||
                        jTF_kundenOrt.getText().compareTo("") == 0 ||
                        jTF_kundenStrasse.getText().compareTo("") == 0 ||
                        jTF_kundenHausnummer.getText().compareTo("") == 0)
                {
                          JOptionPane.showMessageDialog(null, "Ein oder mehrere Felder sind nicht richtig ausgefüllt!!");
                    
                }
                else
                {
                int nr = kundenliste.getKnr();
                // Neues Kundenobjekt anlegen 
                kunde = new Kunde();
                
// Abfangen mittels If = Null ! messagebox oder Rot markieren
// Abfrage ob Person bereits exisitiert
                
                kunde.setKundenNr(String.valueOf(nr));
                kunde.setKundenName(jTF_kundenName.getText());
                kunde.setKundenVorname(jTF_kundenVorname.getText());
                kunde.setKundenOrt(jTF_kundenOrt.getText());
                kunde.setKundenStrasse(jTF_kundenStrasse.getText());
                kunde.setKundenHausnummer(jTF_kundenHausnummer.getText());
                                           
                kundenliste.kundeHinzufuegen(kunde);

                if (DEBUG) {
                    kundenliste.listeAusgeben();
                }
                dialog.dispose();
            }
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
