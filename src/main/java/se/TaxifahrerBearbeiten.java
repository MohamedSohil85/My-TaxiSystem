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
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 *
 */
public class TaxifahrerBearbeiten implements Serializable{
    final static boolean DEBUG = true;

    // Kundenobjekt für Eingabe
    static private Taxifahrer taxifahrer;
    static private Taxifahrerliste taxifahrerliste;
    static private int aNum;

    // Anzeige eines Dialogs zur Eingabe der Kundendaten
    public static Taxifahrer showInputDialog(Window owner, String message, String title, Taxifahrerliste tfliste, int index) {

        taxifahrerliste = tfliste;  // Referenz speichern
        aNum = index;
        taxifahrer = tfliste.getTaxifahrerListe().get(aNum);

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");

        JLabel jL_taxifahrerNr = new JLabel("Taxifahrernummer");
        JTextField jTF_taxifahrerNr = new JTextField();
        String taxifahrernummer = taxifahrer.getFahrerNr();
        jTF_taxifahrerNr.setText(String.valueOf(taxifahrernummer));
        jTF_taxifahrerNr.setEditable(false);
        jTF_taxifahrerNr.setFocusable(false);

        JLabel jL_vorname = new JLabel("Vorname");
        final JTextField jTF_vorname = new JTextField();
        String vorname = taxifahrer.getFahrerVorname();
        jTF_vorname.setText(vorname);
        jTF_taxifahrerNr.setEditable(true);
        jTF_vorname.setFocusable(true);

        JLabel jL_nachname = new JLabel("Nachname");
        final JTextField jTF_nachname = new JTextField();
        String nachname = taxifahrer.getFahrerName();
        jTF_nachname.setText(nachname);
        jTF_taxifahrerNr.setEditable(true);
        jTF_nachname.setFocusable(true);

        JLabel jL_ort = new JLabel("Ort");
        final JTextField jTF_ort = new JTextField();
        String ort = taxifahrer.getFahrerOrt();
        jTF_ort.setText(ort);
        jTF_taxifahrerNr.setEditable(true);
        jTF_ort.setFocusable(true);

        JLabel jL_strasse = new JLabel("Strasse");
        final JTextField jTF_strasse = new JTextField();
        String strasse = taxifahrer.getFahrerStrasse();
        jTF_strasse.setText(strasse);
        jTF_taxifahrerNr.setEditable(true);
        jTF_strasse.setFocusable(true);

        JLabel jL_hausnummer = new JLabel("Hausnummer");
        final JTextField jTF_hausnummer = new JTextField();
        String hausnummer = taxifahrer.getFahrerHausnummer();
        jTF_hausnummer.setText(hausnummer);
        jTF_taxifahrerNr.setEditable(true);
        jTF_hausnummer.setFocusable(true);

        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // OK-Button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                taxifahrer.setFahrerVorname(jTF_vorname.getText());
                taxifahrer.setFahrerName(jTF_nachname.getText());
                taxifahrer.setFahrerOrt(jTF_ort.getText());
                taxifahrer.setFahrerStrasse(jTF_strasse.getText());
                taxifahrer.setFahrerHausnummer(jTF_hausnummer.getText());

                JOptionPane.showMessageDialog(null, "Änderung vorgenommen");
                dialog.dispose();
                TaxifahrerFormular.showInputDialog(null, message, title, tfliste);
            }
        });
        

        // Cancel-Button
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
                TaxifahrerFormular.showInputDialog(null, message, title, tfliste);
            }
        });

        // Anordnung der GUI-Controls in 2 Spalten
        dialog.setLayout(new GridLayout(8, 2));
        dialog.add(messageLabel);
        dialog.add(versionLabel);

        dialog.add(jL_taxifahrerNr);
        dialog.add(jTF_taxifahrerNr);

        dialog.add(jL_vorname);
        dialog.add(jTF_vorname);

        dialog.add(jL_nachname);
        dialog.add(jTF_nachname);

        dialog.add(jL_ort);
        dialog.add(jTF_ort);

        dialog.add(jL_strasse);
        dialog.add(jTF_strasse);

        dialog.add(jL_hausnummer);
        dialog.add(jTF_hausnummer);

        dialog.add(okButton);
        dialog.add(cancelButton);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return null;
    }
}
