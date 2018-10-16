
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Dialog.ModalityType;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Dialog;
import java.io.Serializable;


/**
 *
 * 
 * 
 * NeuesTaxiDialog um neues Taxi anzulegen
 * Evtl neue Klasse TaxiFormular zum Bearbeiten, alpternativ in Taxiliste bearbeiten
 * Taxi für Objekte
 * Taxiliste für die Gelisteten Objekte
 * 
 *  
 */
public class NeuesTaxiDialog implements Serializable{    
    
        static private Taxi taxi;
        static private Taxiliste taxiliste;
        final static boolean DEBUG = true;

    
         public static Taxi showInputDialog(Window owner, String message, String title, Taxiliste tliste) {

        taxiliste = tliste;  // Referenz speichern

        // Modaler Dialog erzeugen
        final JDialog dialog = new JDialog(owner, title, ModalityType.APPLICATION_MODAL);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // GUI Controls
        JLabel messageLabel = new JLabel(message);
        JLabel versionLabel = new JLabel(" v1");
        
        JLabel jL_tNr = new JLabel("TaxiNr");
        JTextField jTF_tNr = new JTextField();  

        JLabel jL_Kennzeichen = new JLabel("Kennzeichen");
        JTextField jTF_Kennzeichen = new JTextField();
        
        JLabel jL_Fahrzeugtyp = new JLabel("Fahrzeugtyp");
        JTextField jTF_Fahrzeugtyp = new JTextField();
        
        int tnr = tliste.getNr();
        jTF_tNr.setText(String.valueOf(tnr));
           
        jTF_tNr.setEditable(false);
        jTF_tNr.setFocusable(false);

        
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        // OK-Button
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int nr = taxiliste.getNr();
                
                // Neues Taxiobjekt anlegen 
                taxi = new Taxi();
                
                taxi.setFahrzeugtyp(jTF_Fahrzeugtyp.getText());
                taxi.setKennzeichen(jTF_Kennzeichen.getText());
                taxi.setTaxiNr(String.valueOf(tnr));
               
                
                
                taxiliste.TaxiHinzufuegen(taxi);

                //liste Ausgeben muss noch implementiert werden
                if (DEBUG) {
                    taxiliste.listeAusgeben();
                }
                dialog.dispose();
                
            }

            //@Override
            /*
                public void actionPerformed(ActionEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }*/
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

        dialog.add(jL_tNr);
        dialog.add(jTF_tNr);

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