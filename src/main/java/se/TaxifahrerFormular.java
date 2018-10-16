/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se;

import java.awt.Dialog;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 *
 */
public class TaxifahrerFormular implements Serializable{

    // Fahrerobjekt für Eingabe
    static private Taxifahrer taxifahrer;
    static private Taxifahrerliste taxifahrerliste;
    static private int row;

    // Anzeige eines Dialogs zur Eingabe der Fahrerdaten
    public static Taxifahrer showInputDialog(Window owner, String message, String title, Taxifahrerliste tfListe) {

        taxifahrerliste = tfListe;  // Referenz speichern

        JDialog jD_taxifahrerDialog = new JDialog(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        JTable jT_taxifahrer = new javax.swing.JTable();
        JButton jB_edit = new javax.swing.JButton();
        JButton jB_delete = new javax.swing.JButton();
        JButton jB_cancel = new javax.swing.JButton();
        JTextField jTextField1 = new javax.swing.JTextField(5);

        int tfListeSize = tfListe.getTaxifahrerListe().size();
        Object[][] cellData = new Object[tfListeSize][6];
        //Setup Tabledata

        if (tfListeSize != 0) {
            for (int i = 0; i < tfListeSize; i++) {
                cellData[i][0] = tfListe.getTaxifahrerListe().get(i).getFahrerNr();
                cellData[i][1] = tfListe.getTaxifahrerListe().get(i).getFahrerVorname();
                cellData[i][2] = tfListe.getTaxifahrerListe().get(i).getFahrerName();
                cellData[i][3] = tfListe.getTaxifahrerListe().get(i).getFahrerOrt();
                cellData[i][4] = tfListe.getTaxifahrerListe().get(i).getFahrerStrasse();
                cellData[i][5] = tfListe.getTaxifahrerListe().get(i).getFahrerHausnummer();
            }
        } else {
            jB_edit.setEnabled(false);
            jB_delete.setEnabled(false);
        }
        String[] columnNames = {"Fahrernummer", "Vorname", "Nachname", "Ort", "Strasse", "Hausnummer"};

        jD_taxifahrerDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        jT_taxifahrer.setModel(new javax.swing.table.DefaultTableModel(cellData, columnNames) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPane1.setViewportView(jT_taxifahrer);
        if (jT_taxifahrer.getColumnModel().getColumnCount() > 0) {
            jT_taxifahrer.getColumnModel().getColumn(0).setPreferredWidth(6);
            jT_taxifahrer.getColumnModel().getColumn(5).setPreferredWidth(7);
        }

        jB_edit.setText("Bearbeiten");
        jB_edit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jButton1ActionPerformed(evt);
            }
        });

        jB_delete.setText("Löschen");

        jB_cancel.setText("Abbrechen");

        jT_taxifahrer.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                row = jT_taxifahrer.getSelectedRow();
                jTextField1.setText(String.valueOf(jT_taxifahrer.getValueAt(row, 0)));
            }
        });

        // Edit-Button
        jB_edit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = jT_taxifahrer.getSelectedRow();
                if (index != -1) {
                    jD_taxifahrerDialog.dispose();

                    Taxifahrer taxifahrer = TaxifahrerBearbeiten.showInputDialog(null, "Taxifahrer Bearbeiten", "Taxifahrer Bearbeiten", taxifahrerliste, index);

                    if (taxifahrer != null) {
                        System.out.println(taxifahrer.getFahrerVorname());
                        System.out.println(taxifahrer.getFahrerName());
                        System.out.println(taxifahrer.getFahrerOrt());
                        System.out.println(taxifahrer.getFahrerStrasse());
                        System.out.println(taxifahrer.getFahrerHausnummer());
                    }

                }
            }
        }
        );

        // Delete- Button
        jB_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (taxifahrerliste.getTaxifahrerListe().size() != 0) {
                    Object[] options = {"Ja", "Nein"};
                    int[] indexes = jT_taxifahrer.getSelectedRows();
                    if (indexes.length != 0) {
                        int antwort = JOptionPane.showOptionDialog(jD_taxifahrerDialog, "Wollen Sie den Taxifahrer loeschen? ", "Warnung",
                                JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
                                null, options, options[1]);

                        if (antwort == JOptionPane.YES_OPTION) {

                            int k = indexes.length - 1; // 
                            for (int i = k; i >= 0; i--) {
                                taxifahrerliste.getTaxifahrerListe().remove(indexes[i]);
                            }
                            if (k < 0) {
                                JOptionPane.showMessageDialog(null, "Taxifahrer geloescht");
                            }
                        }

                        jD_taxifahrerDialog.dispose();
                        TaxifahrerFormular.showInputDialog(null, message, title, tfListe);
                    }
                }
            }
        }
        );

        // Cancel-Button
        jB_cancel.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e
            ) {
                jD_taxifahrerDialog.dispose();
            }
        }
        );

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                //jTextField1
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(jD_taxifahrerDialog.getContentPane());
        jD_taxifahrerDialog.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1)
                .addGroup(layout.createSequentialGroup()
                        .addGap(187, 187, 187)
                        .addComponent(jB_edit)
                        .addGap(18, 18, 18)
                        .addComponent(jB_delete)
                        .addGap(18, 18, 18)
                        .addComponent(jB_cancel)
                        .addContainerGap(181, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jB_edit)
                                .addComponent(jB_delete)
                                .addComponent(jB_cancel))
                        .addContainerGap())
        );

        jD_taxifahrerDialog.setLocation(300, 200);
        jD_taxifahrerDialog.pack();
        jD_taxifahrerDialog.setVisible(true);

        return null;
    }
}
