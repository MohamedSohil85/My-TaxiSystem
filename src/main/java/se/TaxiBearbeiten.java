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
public class TaxiBearbeiten implements Serializable{
    final static boolean DEBUG = true;
    
    // Kundenobjekt für Eingabe
    static private Taxi taxi;
    static private Taxiliste taxiliste;
    static private int aNum;
    
    // Anzeige eines Dialogs zur Eingabe der Kundendaten
    public static Taxi showInputDialog(Window owner, String message, String title, Taxiliste tliste, int index) {

        taxiliste = tliste;  // Referenz speichern
        aNum = index;
        taxi = tliste.getTaxiliste().get(aNum);
        

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");

        JLabel jL_taxiNr = new JLabel("Taxinummer");
        JTextField jTF_taxiNr = new JTextField();        
        String taxinummer = taxi.getTaxiNr();
        jTF_taxiNr.setText(String.valueOf(taxinummer));
        jTF_taxiNr.setEditable(false);
        jTF_taxiNr.setFocusable(false);

        JLabel jL_Kennzeichen = new JLabel("Kennzeichen");
        final JTextField jTF_Kennzeichen = new JTextField();
        String Kennzeichen = taxi.getKennzeichen();
        jTF_Kennzeichen.setText(Kennzeichen);
        jTF_Kennzeichen.setEditable(true);
        jTF_Kennzeichen.setFocusable(true);

        JLabel jL_Fahrzeugtyp = new JLabel("Fahrzeugtyp");
        final JTextField jTF_Fahrzeugtyp = new JTextField();
        String Fahrzeugtyp = taxi.getFahrzeugtyp();
        jTF_Fahrzeugtyp.setText(Fahrzeugtyp);
        jTF_Fahrzeugtyp.setEditable(true);
        jTF_Fahrzeugtyp.setFocusable(true);


        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // OK-Button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                taxi.setTaxiNr(jTF_taxiNr.getText());
                taxi.setFahrzeugtyp(jTF_Fahrzeugtyp.getText());
                taxi.setKennzeichen(jTF_Kennzeichen.getText());

        if (DEBUG) {
                    taxi.print();
                }
        
                JOptionPane.showMessageDialog(null, "Änderung vorgenommen");
                dialog.dispose();
                TaxiFormular.showInputDialog(dialog, message, title, taxiliste);
                
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

        dialog.add(jL_taxiNr);
        dialog.add(jTF_taxiNr);

        dialog.add(jL_Kennzeichen);
        dialog.add(jTF_Kennzeichen);

        dialog.add(jL_Fahrzeugtyp);
        dialog.add(jTF_Fahrzeugtyp);

        dialog.add(okButton);
        dialog.add(cancelButton);

        dialog.setSize(500, 400);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);

        return null;
    }  

}
